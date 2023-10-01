package com.example.appaves;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;
import com.example.appaves.InfoItem;
import com.example.appaves.InfoListAdapter;

import java.util.ArrayList;

public class informacion extends AppCompatActivity {
    ListView listView;
    ArrayList<InfoItem> infoItemList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacion);
        listView = findViewById(R.id.listView); // Asegúrate de que el ID sea el correcto
        infoItemList = new ArrayList<>();
        infoItemList.add(new InfoItem(R.drawable.aguila,getString(R.string.aguila)));
        infoItemList.add(new InfoItem(R.drawable.ic_image2, "Descripción 2"));
        InfoListAdapter adapter = new InfoListAdapter(this, infoItemList);
        listView.setAdapter(adapter);
    }
}