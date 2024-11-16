package com.example.evaluacion_3;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.view.View;

import android.widget.AdapterView;
import android.widget.ListView;
import java.util.ArrayList;
import android.content.Intent;

public class activity_get_all extends AppCompatActivity {

    private ListView listView;
    private DbManager databaseManager;
    private ArrayList<MedicamentoModel> medicamentosArrayList;
    private CustomAdapter customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_get_all);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        listView = findViewById(R.id.listView);

        databaseManager = new DbManager(this);

        medicamentosArrayList = databaseManager.getAllMedicamentos();

        customAdapter = new CustomAdapter(this, medicamentosArrayList);
        listView.setAdapter(customAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MedicamentoModel medicamento = medicamentosArrayList.get(position);
                Intent intent = new Intent(activity_get_all.this, UpdateDeleteMed.class);
                intent.putExtra("medModel", medicamento);
                startActivity(intent);
            }
        });

    }

}
