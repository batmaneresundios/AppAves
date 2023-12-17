package com.example.appaves;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import modelo.RegistroAves;

public class ListaActivity extends AppCompatActivity {
    private RecyclerView rcvAvistamientos;
    private ArrayList<RegistroAves> avistamientosArrayList;
    private AvistamientosAdapter avistamientosAdapter;
    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista);

        rcvAvistamientos = findViewById(R.id.rcv_avistamientos);
        db = FirebaseFirestore.getInstance();
        avistamientosArrayList = new ArrayList<>();
        rcvAvistamientos.setHasFixedSize(true);
        rcvAvistamientos.setLayoutManager(new LinearLayoutManager(this));
        avistamientosAdapter = new AvistamientosAdapter(avistamientosArrayList,this);
        rcvAvistamientos.setAdapter(avistamientosAdapter);

        db.collection("RegistroAves").get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                        // Chequeamos si viene vacío
                        if (!queryDocumentSnapshots.isEmpty()) {
                            // Si no viene vacío conformamos lista
                            List<DocumentSnapshot> list = queryDocumentSnapshots.getDocuments();
                            for (DocumentSnapshot d : list) {
                                //Se obtienen los datos y se instancia por cada documento un objeto de la clase Entrada
                                RegistroAves registroAves = d.toObject(RegistroAves.class);
                                //Id para actualizaciòn
                                registroAves.setId(d.getId());
                                //El objeto se agrega a la lista de objetos de la clase Entrada
                                avistamientosArrayList.add(registroAves);
                            }
                            //Notificación de actualización de datos
                            avistamientosAdapter.notifyDataSetChanged();

                        } else {
                            // Si no se encuentran datos
                            Toast.makeText(ListaActivity.this, "No se encontraron datos", Toast.LENGTH_SHORT).show();
                        }
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // En caso de excepciones
                        Toast.makeText(ListaActivity.this, "Error al obtener datos.", Toast.LENGTH_SHORT).show();
                    }
                });
    }


}
