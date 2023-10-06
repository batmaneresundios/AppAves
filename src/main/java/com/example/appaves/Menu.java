package com.example.appaves;

import android.content.Intent;
import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;

import android.view.View;


public class Menu extends AppCompatActivity{
    Intent intento; // se declara una instancia de la clase Intent llamada 'intento'.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        obtenerDatos();

    }
    private void obtenerDatos() {
        //Asociamos el intento al proceso de recepcion desde MainActivity
        intento = getIntent(); // Obtiene el Intent enviado desde la actividad anterior (MainActivity)
    }

    public void clickRegistro(View view) { // Método llamado clickRegistro cuando se hace clic en un elemento de la vista.
        Intent intent = new Intent(this, Registro.class); // Crea un nuevo Intent para abrir la actividad Registro.
        startActivity(intent); // se inicia la actividad Registro.
    }
}