package com.example.appaves;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import modelo.RegistroAves;

public class AvistamientosAdapter extends RecyclerView.Adapter<AvistamientosAdapter.ViewHolder> {
    private ArrayList<RegistroAves> registroArrayList;
    private Context context;

    public AvistamientosAdapter(ArrayList<RegistroAves> registroAvesArrayList, Context context) {
        this.registroArrayList = registroAvesArrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public AvistamientosAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(context).inflate(R.layout.avistamientos, parent, false));
    }

    @Override

    public void onBindViewHolder(@NonNull AvistamientosAdapter.ViewHolder holder, int position) {
        RegistroAves registroAves = registroArrayList.get(position);
        holder.txv_aveVisualizada.setText(registroAves.getAveVisualizada());

        // Correctly checking for null before calling toString()
        if (registroAves.getFecha() != null) {
            holder.txv_fecha.setText(registroAves.getFecha().toString()); // Consider formatting the date
        } else {
            holder.txv_fecha.setText("Fecha no disponible");
        }

        // This line should be removed as it's redundant and can cause NullPointerException
        // holder.txv_fecha.setText(registroAves.getFecha().toString()); <-- Remove this line
        //Utilicé la clase ResourcesCompat para aplicar la fuente de mi app al listar los registros
        Typeface typeface = ResourcesCompat.getFont(context, R.font.bree_serif_regular);
        holder.txv_aveVisualizada.setTypeface(typeface);
        holder.txv_fecha.setTypeface(typeface);
        holder.txv_numeroVistas.setTypeface(typeface);
        holder.txv_lugarAvistamiento.setTypeface(typeface);    }

    @Override
    public int getItemCount() {
        return registroArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView txv_aveVisualizada, txv_fecha, txv_numeroVistas, txv_lugarAvistamiento;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txv_aveVisualizada = itemView.findViewById(R.id.txv_aveVisualizada);
            txv_fecha = itemView.findViewById(R.id.txv_fecha);
            txv_numeroVistas = itemView.findViewById(R.id.txv_numeroVistas);
            txv_lugarAvistamiento = itemView.findViewById(R.id.txv_lugarAvistamiento);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    RegistroAves registroAves = registroArrayList.get(getAdapterPosition());
                    Intent i = new Intent(context, ActualizarActivity.class);
                    i.putExtra("registroAves", registroAves);
                    context.startActivity(i);
                }
            });
        }
    }
}
