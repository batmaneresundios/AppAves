package com.example.appaves;

import android.content.Intent;
import android.os.Bundle;


import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.view.ViewStub;

import androidx.core.view.WindowCompat;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.appaves.databinding.ActivityMain2Binding;

public class MainActivity2 extends AppCompatActivity implements View.OnClickListener{
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
    public void onImageClick(View view) {
        // Lógica para manejar el clic en la imagen aquí
    }


    @Override
    public void onClick(View view) {

    }
}