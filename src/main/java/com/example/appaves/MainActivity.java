package com.example.appaves;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

//Extends hereda lo que está al lado derecho. Cuando se utiliza implements cuando se hereda una clase, se toma el contenido de la clase y obligadamente se implementa
public class MainActivity extends AppCompatActivity{
    //Variables globales (Objetos de JAVA)
    Spinner spiRegion,spiComuna;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        vincularElementos();
        activarListener();
        // Se crean adaptadores para los Spinners a partir de recursos de cadenas.
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.lista_regiones, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.lista_comunas, android.R.layout.simple_spinner_item);
        // Se establece el diseño para las opciones desplegables de los adaptadores. Adaptadores: función principal es actuar como un puente entre los datos y la interfaz de usuario,
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spiRegion.setAdapter(adapter);
        spiComuna.setAdapter(adapter1);
    }

    private void activarListener() {
        spiRegion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                String selectedRegion = parentView.getItemAtPosition(position).toString();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
            }
        });
    }

    private void vincularElementos() {
        spiRegion = findViewById(R.id.spinner_region);
        spiComuna = findViewById(R.id.spinner_comuna);
    }

    public void clickImagen(View view) { // Método llamado clickImagen cuando se hace clic en una imagen.
        Intent intent = new Intent(this, Menu.class);// Crea un nuevo Intent para abrir la actividad MainActivity2.
        startActivity(intent);
    }
}