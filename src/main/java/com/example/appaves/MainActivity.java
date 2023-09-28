package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
//Extends hereda lo que está al lado derecho. Cuando se utiliza implements cuando se hereda una clase, se toma el contenido de la clase y obligadamente se implementa
public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    //Variables globales (Objetos de JAVA)
    EditText edtEdad;
    EditText edtPesoActual;
    Button btnCalcular;
    TextView txvResultado, txvEstadoPeso;
    Spinner spiRegion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Invocamos método para vicncular elementos
        vincularElementos();
        //Invocamos método para activar listener
        activarListener();
    }

    private void activarListener() {
        btnCalcular.setOnClickListener(this);
    }

    private void vincularElementos() {
        edtEdad = (EditText) findViewById(R.id.edt_edad);
        edtPesoActual = (EditText) findViewById(R.id.edt_peso_actual);
        txvResultado = (TextView) findViewById(R.id.txt_resultado);
        btnCalcular = (Button) findViewById(R.id.btn_calcular);
        txvEstadoPeso = (TextView) findViewById(R.id.txt_estado_peso);
    }
    //Toda caja de texto en java por defecto es String
    @Override
    public void onClick(View view) {
        int edad = Integer.parseInt(edtEdad.getText().toString());
        if(edad>=1 && edad <= 10) {
            //Obtener peso actual
            int pesoActual = Integer.parseInt(edtPesoActual.getText().toString());
            // calcularPesoIdeal(edad);
            Intent intentoResultado = new Intent(MainActivity.this,ResultadoActivity.class);
            //Agregamos datos al envio mediante intento
            intentoResultado.putExtra("p_edad",edad);
            intentoResultado.putExtra("p_peso_actual", pesoActual);
            startActivity(intentoResultado);
        }else{
            mostrarErrorIngreso();
        }
    }
    private void mostrarErrorIngreso(){
        txvResultado.setText("Edad requerida entre 1 y 10");
    }

    private void calcularPesoIdeal(int edad) {
        int pesoIdeal = edad * 2 + 8;
        txvResultado.setText("Su peso ideal es: " + pesoIdeal);
        //Obtener el peso acual
        int pesoActual = Integer.parseInt(edtPesoActual.getText().toString());
        determinarEstadoPeso(pesoActual,pesoIdeal);
    }
    private void determinarEstadoPeso(int pesoActual,int pesoIdeal){
        String estadoPeso;
        if(pesoActual == pesoIdeal){
            estadoPeso = "Peso ideal";
        }else{
            if(pesoActual > pesoIdeal){
                estadoPeso = "Sobrepeso";
            }else{
                estadoPeso = "Bajo peso";
            }
        }
        txvEstadoPeso.setText("Estado de peso: "+estadoPeso);
    }
}