package com.softnahu.parcialandroid23_09_24.ui.listar;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.softnahu.parcialandroid23_09_24.R;
import com.softnahu.parcialandroid23_09_24.model.Auto;

import java.util.ArrayList;

public class ListaAdapter extends RecyclerView.Adapter<ListaAdapter.ViewHolderAuto>{

    private ArrayList<Auto> listaAutos;
    private LayoutInflater inflater;

    public ListaAdapter(ArrayList<Auto> listaAutos, LayoutInflater inflater) {
        this.listaAutos = listaAutos;
        this.inflater = inflater;
    }

    @NonNull
    @Override
    public ListaAdapter.ViewHolderAuto onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.tarjeta,parent,false);
        return new ViewHolderAuto(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ListaAdapter.ViewHolderAuto holder, int position) {
        Auto auto = listaAutos.get(position);
        holder.tvPatente.setText("Patente: " + auto.getPatente());
        holder.tvMarca.setText("Marca: " + auto.getMarca());
        holder.tvModelo.setText("Modelo: " + auto.getModelo());
        holder.tvPrecio.setText("Precio: $" + auto.getPrecio());
    }

    @Override
    public int getItemCount() {
        return listaAutos.size();
    }
    public class ViewHolderAuto extends RecyclerView.ViewHolder {
        TextView tvPatente;
        TextView tvMarca;
        TextView tvModelo;
        TextView tvPrecio;

        public ViewHolderAuto(@NonNull View itemView) {
            super(itemView);
            tvPatente = itemView.findViewById(R.id.tvPatente);
            tvMarca = itemView.findViewById(R.id.tvMarca);
            tvModelo = itemView.findViewById(R.id.tvModelo);
            tvPrecio = itemView.findViewById(R.id.tvPrecio);
        }
    }
}
