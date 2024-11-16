package com.example.cd_market;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class GameAdaptador extends RecyclerView.Adapter<GameAdaptador.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView nombre, descripcion;

        public ViewHolder(View itemView) {
            super(itemView);
            nombre = itemView.findViewById(R.id.nombre);  // Asegúrate de que estos IDs existan
            descripcion = itemView.findViewById(R.id.descripcion);  // Asegúrate de que estos IDs existan
        }
    }

    public List<GameModelo> juegoLista;

    public GameAdaptador(List<GameModelo> juegoLista){
        this.juegoLista = juegoLista;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Infla el layout del ítem
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_game, parent, false);

        // Crea el ViewHolder y retorna la referencia
        return new ViewHolder(view);  // Devuelve el ViewHolder correctamente
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // Establece los valores de los elementos del ítem
        holder.nombre.setText(juegoLista.get(position).getNombre());
        holder.descripcion.setText(juegoLista.get(position).getDescripcion());
    }

    @Override
    public int getItemCount() {
        return juegoLista.size();
    }
}
