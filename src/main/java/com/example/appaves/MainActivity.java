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
        //Invocamos método para vicncular elementos
        vincularElementos();
        //Invocamos método para activar listener
        activarListener();
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.lista_regiones, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this, R.array.lista_comunas, android.R.layout.simple_spinner_item);

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spiRegion.setAdapter(adapter);
        spiComuna.setAdapter(adapter1);
    }



    private void activarListener() {
        spiRegion.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // Aquí puedes ejecutar código cuando se selecciona un elemento en el Spinner
                String selectedRegion = parentView.getItemAtPosition(position).toString();
                // Hacer algo con la región seleccionada
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // Cuando no se selecciona ningún elemento
            }
        });
        // Activa el listener para otros elementos aquí si los tienes.
    }

    private void vincularElementos() {
        spiRegion = findViewById(R.id.spinner_region);
        spiComuna = findViewById(R.id.spinner_comuna);
    }

    public void clickImagen(View view) {
        Intent intent = new Intent(this, MainActivity2.class);
        startActivity(intent);
    }
    //Toda caja de texto en java por defecto es String

}