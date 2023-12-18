package com.example.appaves;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import modelo.RegistroAves;

public class ActualizarActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edtAveVisualizada,edtFecha,edtNumeroVistas,edtLugarAvistamiento;
    private Button btnActualizar,btnEliminar;
    private Date fecha;

    private String idUpdate;

    private String aveVisualizada, lugarAvistamiento;
    private int numeroVistas;


    private FirebaseFirestore db;

    RegistroAves registroAves;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar);

        //Obtenemos valores actuales
        registroAves = (RegistroAves) getIntent().getSerializableExtra("registroAves");

        //Preparaciòn de actualizaciòn
        // getting our instance from Firebase Firestore.
        db = FirebaseFirestore.getInstance();

        vincularElementos();
        habilitarListener();


        //Obtenemos datos

        edtFecha.setText(registroAves.getFecha().toString());
        edtAveVisualizada.setText(registroAves.getAveVisualizada());
        edtNumeroVistas.setText(String.valueOf(registroAves.getNumeroVistas()));
        edtLugarAvistamiento.setText(registroAves.getLugarAvistamiento());

        idUpdate = registroAves.getId();
        Toast.makeText(ActualizarActivity.this, "ID " + registroAves.getId(), Toast.LENGTH_SHORT).show();
    }

    private void habilitarListener() {

        btnActualizar.setOnClickListener(this);
        btnEliminar.setOnClickListener(this);
    }

    private void vincularElementos() {
        // initializing our edittext and buttons
        edtFecha = findViewById(R.id.edt_fecha);
        edtAveVisualizada = findViewById(R.id.edt_ave_visualizada);
        edtNumeroVistas = findViewById(R.id.edt_numero_vistas);
        edtLugarAvistamiento = findViewById(R.id.edt_lugar_exacto);
        btnActualizar = findViewById(R.id.btn_actualizar);
        btnEliminar = findViewById(R.id.btn_eliminar);
    }

    @Override
    public void onClick(View view) {

        if(view.getId() == R.id.btn_actualizar){
            //Obtenemos datos
            String fechaIngreso = edtFecha.getText().toString();
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date date = format.parse(fechaIngreso);
                fecha = date;
                System.out.println(date);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            aveVisualizada = edtAveVisualizada.getText().toString();
            numeroVistas = Integer.parseInt(edtNumeroVistas.getText().toString());
            lugarAvistamiento = edtLugarAvistamiento.getText().toString();

            updateCourses(registroAves, aveVisualizada, numeroVistas, lugarAvistamiento, fecha);
        }
        if (view.getId() == R.id.btn_eliminar){
            eliminarRegistros(idUpdate);
        }

    }

    private void updateCourses(RegistroAves registroAves, String aveVisualizada, int numeroVistas, String lugarAvistamiento, Date fecha) {

        RegistroAves registroAvesActualizada = new RegistroAves(aveVisualizada, numeroVistas, lugarAvistamiento, fecha);

        HashMap<String, Object> updateRegistroAves = new HashMap<>();
        updateRegistroAves.put("fecha", registroAvesActualizada.getFecha());
        updateRegistroAves.put("aveVisualizada", registroAvesActualizada.getAveVisualizada());
        updateRegistroAves.put("lugarAvistamiento", registroAvesActualizada.getLugarAvistamiento());
        updateRegistroAves.put("numeroVistas", registroAvesActualizada.getNumeroVistas());

        db.collection("RegistroAves").
                document(idUpdate).
                update(updateRegistroAves).
                addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                        Toast.makeText(ActualizarActivity.this, "Registro Actulizado", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(ActualizarActivity.this, ListaActivity.class);
                        startActivity(intent);
                    }
                }).addOnFailureListener(new OnFailureListener() {

                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(ActualizarActivity.this, "Error al obtener datos", Toast.LENGTH_SHORT).show();
                    }
                });
    }
    private void eliminarRegistros(String idUpdate) {

        db.collection("RegistroAves").

                document(idUpdate).

                delete().

                addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {

                        if (task.isSuccessful()) {

                            Toast.makeText(ActualizarActivity.this, "Registro eliminado.", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(ActualizarActivity.this, Menu.class);
                            startActivity(i);
                        } else {

                            Toast.makeText(ActualizarActivity.this, "Error al eliminar. ", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}