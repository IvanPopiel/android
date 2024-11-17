package com.example.cd_market;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class GameAdaptador extends RecyclerView.Adapter<GameAdaptador.ViewHolder> {

    // ViewHolder que contiene las referencias a los TextViews
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView id, nombre, descripcion;

        public ViewHolder(View itemView) {
            super(itemView);
            // Inicializamos las referencias a los TextViews
            id = itemView.findViewById(R.id.id);
            nombre = itemView.findViewById(R.id.nombre);
            descripcion = itemView.findViewById(R.id.descripcion);
        }
    }

    private List<GameModelo> juegoLista;

    // Constructor del adaptador
    public GameAdaptador(List<GameModelo> juegoLista) {
        this.juegoLista = juegoLista;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Inflamos el layout de cada item
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_game, parent, false);
        return new ViewHolder(view);  // Retornamos el ViewHolder
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // Establecemos los valores de los TextViews para cada item
        GameModelo juego = juegoLista.get(position);
        holder.id.setText(String.valueOf(juego.getId()));  // Mostrar ID
        holder.nombre.setText(juego.getNombre());         // Mostrar nombre
        holder.descripcion.setText(juego.getDescripcion()); // Mostrar descripción
    }

    @Override
    public int getItemCount() {
        return juegoLista.size(); // Número total de juegos en la lista
    }
}
