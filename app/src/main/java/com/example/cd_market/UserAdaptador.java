package com.example.cd_market;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class UserAdaptador extends RecyclerView.Adapter<UserAdaptador.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private TextView nombreUsuario, email;

        public ViewHolder(View itemView) {
            super(itemView);
            nombreUsuario = itemView.findViewById(R.id.nombreUsuario);  // Asegúrate de que estos IDs existan
            email = itemView.findViewById(R.id.email);  // Asegúrate de que estos IDs existan
        }
    }

    public List<UserModelo> usuariosLista;

    public UserAdaptador(List<UserModelo> usuariosLista){
        this.usuariosLista = usuariosLista;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Infla el layout del ítem
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);

        // Crea el ViewHolder y retorna la referencia
        return new ViewHolder(view);  // Devuelve el ViewHolder correctamente
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // Establece los valores de los elementos del ítem
        holder.nombreUsuario.setText(usuariosLista.get(position).getNombreUsuario());
        holder.email.setText(usuariosLista.get(position).getEmail());
    }

    @Override
    public int getItemCount() {
        return usuariosLista.size();
    }
}
