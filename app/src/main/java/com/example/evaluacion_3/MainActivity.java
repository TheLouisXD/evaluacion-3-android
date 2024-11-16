package com.example.evaluacion_3;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

// importamos lo necesario para poder elegir la fecha
import android.app.DatePickerDialog;

import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {

    // creamos las variables necesarias para poder elegir la fecha
    private EditText fechaInput;
    private Calendar calendar;
    private SimpleDateFormat dateFormat;

    private EditText nombreInput, cantidadInput, miligramosInput, descripcionInput;
    private Spinner presentacionSpinner;
    private Button listarButton, enviarButton;
    private DbManager dbManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        // inicializamos el database helper
        dbManager = new DbManager(this);

        // inicializamos las variables
        nombreInput = findViewById(R.id.nombreInput);
        cantidadInput = findViewById(R.id.cantidadInput);
        miligramosInput = findViewById(R.id.miligramosInput);
        presentacionSpinner = findViewById(R.id.presentacionSpinner);
        descripcionInput = findViewById(R.id.descripcionInput);
        listarButton = findViewById(R.id.listarButton);
        enviarButton = findViewById(R.id.enviarButton);

        // inicializamos el EditText para elegir la fecha
        fechaInput = findViewById(R.id.fechaInput);
        calendar = Calendar.getInstance();
        dateFormat = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault());

        // Creamos un listener que detecta cuando se toca el botón enviar
        enviarButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                dbManager.addMedicamento(
                        nombreInput.getText().toString(),

                        Integer.parseInt(cantidadInput.getText().toString()),
                        fechaInput.getText().toString(),
                        Integer.parseInt(miligramosInput.getText().toString()),
                        presentacionSpinner.getSelectedItem().toString(),
                        descripcionInput.getText().toString()
                );
                nombreInput.setText("");
                cantidadInput.setText("");
                fechaInput.setText("");
                miligramosInput.setText("");
                presentacionSpinner.setSelection(0);
                descripcionInput.setText("");
                Toast.makeText(MainActivity.this, "Medicamento ingresado!!", Toast.LENGTH_SHORT).show();
            }
        });

        listarButton.setOnClickListener (new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, activity_get_all.class);
                startActivity(intent);
            }
        });

        // Creamos un listener que detecta cuando se toca el EditText
        fechaInput.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                showDatePickerDialog();
            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private void showDatePickerDialog() {
        // creamos el dialogo para poder elegir la fecha
        new DatePickerDialog(this, (view, year, month, dayOfMonth) -> {
            // Actualizamos el EditText con la fecha seleccionada
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month);
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

            // Ingresamos la fecha seleccionada en el EditText
            fechaInput.setText(dateFormat.format(calendar.getTime()));
        },
        // inicializamos la fecha actual
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)).show();
    }
}