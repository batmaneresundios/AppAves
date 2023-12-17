package com.example.appaves;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import modelo.RegistroAves;

public class Registro extends AppCompatActivity implements  View.OnClickListener {
    EditText edtAveVisualizada;
    EditText edtNumeroVistas;
    EditText edtLugarExacto;
    EditText dateFechaIngreso;
    Button btnGuardar;
    private String aveVisualizada, lugarAvistamiento;
    private int numeroVistas;

    private Date fecha;

    private FirebaseFirestore firebaseFirestore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        vincularElementos();
        habilitarListener();
        iniciarFirestone();
    }


    private void iniciarFirestone() {
        firebaseFirestore = FirebaseFirestore.getInstance();

    }

    private void habilitarListener() {
        btnGuardar.setOnClickListener(this);
    }


    private void vincularElementos() {
        edtAveVisualizada = (EditText) findViewById(R.id.edt_ave_visualizada);
        edtNumeroVistas = (EditText) findViewById(R.id.edt_numero_vistas);
        edtLugarExacto = (EditText) findViewById(R.id.edt_lugar_exacto);
        dateFechaIngreso = (EditText) findViewById(R.id.edt_fecha);
        btnGuardar = (Button) findViewById(R.id.btn_guardar);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_guardar) {
            //Obtenemos datos
            String fechaIngresada = dateFechaIngreso.getText().toString();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date date = format.parse(fechaIngresada);
                fecha = date;
                System.out.println(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }

            aveVisualizada = edtAveVisualizada.getText().toString();
            numeroVistas = Integer.parseInt(edtNumeroVistas.getText().toString());
            lugarAvistamiento = edtLugarExacto.getText().toString();

            //Invocamos método que agrega a firestore
            agregarFirestore(aveVisualizada, numeroVistas, lugarAvistamiento,fecha);
        }
        if (view.getId() == R.id.btn_guardar){
            Intent intento = new Intent(Registro.this,ListaActivity.class);
            startActivity(intento);
        }

    }
    private void agregarFirestore(String aveVisualizada, int numeroVistas, String lugarAvistamiento,Date fecha) {
        //Colección en Firestore
        CollectionReference coleccionRegistroAves = firebaseFirestore.collection("RegistroAves");
        //Objeto Entrada
        RegistroAves registroAves = new RegistroAves(aveVisualizada,numeroVistas,lugarAvistamiento,fecha);
        //intentamos agregar
        coleccionRegistroAves.add(registroAves).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
            @Override
            public void onSuccess(DocumentReference documentReference) {
                //En caso de éxito mostramos mensaje mediante Toast
                Toast.makeText(Registro.this,"Entrada registrada correctamente",Toast.LENGTH_SHORT).show();

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                //En caso de error, mensaje similar al anterior.
                Toast.makeText(Registro.this,"Error al agregar",Toast.LENGTH_SHORT).show();
            }
        });
    }
}