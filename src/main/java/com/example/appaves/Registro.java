package com.example.appaves;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Registro extends AppCompatActivity {
    Button btnGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        vincularElementos();
        // Configura un listener para el botón btnGuardar
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Crear un intent para abrir MainActivity
                Intent intent = new Intent(Registro.this, Menu.class);

                // Iniciar MainActivity
                startActivity(intent);
            }
        });
    }

    private void vincularElementos() {
        btnGuardar = findViewById(R.id.btn_guardar);
    }
}
