package com.example.evaluacion_3;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class UpdateDeleteMed extends AppCompatActivity {

    private MedicamentoModel medModel;
    private EditText nombreInput, fechaInput ,cantidadInput, miligramosInput, descripcionInput;
    private Spinner presentacionSpinner;
    private Button updateButton, deleteButton;
    private DbManager databaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_delete);

        Intent intent = getIntent();
        medModel = (MedicamentoModel) intent.getSerializableExtra("medModel");

        databaseHelper = new DbManager(this);

        nombreInput = findViewById(R.id.nombreInput);
        cantidadInput = findViewById(R.id.cantidadInput);
        fechaInput = findViewById(R.id.fechaInput);
        miligramosInput = findViewById(R.id.miligramosInput);
        presentacionSpinner = findViewById(R.id.presentacionSpinner);
        descripcionInput = findViewById(R.id.descripcionInput);

        updateButton = findViewById(R.id.listarButton);
        deleteButton = findViewById(R.id.enviarButton);

        nombreInput.setText(medModel.getNombre());
        cantidadInput.setText(medModel.getCantidad());
        fechaInput.setText(medModel.getFechaVencimiento());
        miligramosInput.setText(medModel.getMiligramos());
        presentacionSpinner.setSelection(getSpinnerIndex(presentacionSpinner, medModel.getPresentacion()));
        descripcionInput.setText(medModel.getDescripcion());

        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseHelper.updateMedicamento(
                        medModel.getId(), nombreInput.getText().toString(),
                        cantidadInput.getText().toString(),
                        fechaInput.getText().toString(),
                        miligramosInput.getText().toString(),
                        presentacionSpinner.getSelectedItem().toString(),
                        descripcionInput.getText().toString());
                Toast.makeText(UpdateDeleteMed.this, "Medicamento actualizado", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(UpdateDeleteMed.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseHelper.deleteMedicamento(medModel.getId());
                Toast.makeText(UpdateDeleteMed.this, "Medicamento eliminado", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(UpdateDeleteMed.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        }
    // Método para obtener el índice del Spinner
    private int getSpinnerIndex(Spinner spinner, String myString) {
        for (int i = 0; i < spinner.getAdapter().getCount(); i++) {
            if (spinner.getAdapter().getItem(i).toString().equals(myString)) {
                return i;
            }
        }
        return 0; // Retorna 0 si no se encuentra el elemento
    }

}


