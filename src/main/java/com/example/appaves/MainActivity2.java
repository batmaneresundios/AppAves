package com.example.appaves;

import android.content.Intent;
import android.os.Bundle;


import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.view.ViewStub;
import android.widget.ImageView;


public class MainActivity2 extends AppCompatActivity{
    Intent intento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        obtenerDatos();

    }
    private void obtenerDatos() {
        //Asociamos el intento al proceso de recepcion desde MainActivity
        intento = getIntent();
    }

    public void clickRegistro(View view) {
        Intent intent = new Intent(this, Registro.class);
        startActivity(intent);
    }
}