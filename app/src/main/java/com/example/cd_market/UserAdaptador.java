package com.example.cd_market;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class UserAdaptador extends RecyclerView.Adapter<UserAdaptador.ViewHolder> {

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView idUsuario, nombreUsuario, email;

        public ViewHolder(View itemView) {
            super(itemView);
            // Inicializamos los TextViews
            idUsuario = itemView.findViewById(R.id.idUsuario);  // ID del Usuario
            nombreUsuario = itemView.findViewById(R.id.nombreUsuario);  // Nombre de Usuario
            email = itemView.findViewById(R.id.email);  // Email del Usuario
        }
    }

    public List<UserModelo> usuariosLista;

    public UserAdaptador(List<UserModelo> usuariosLista) {
        this.usuariosLista = usuariosLista;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Infla el layout del ítem
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // Establece los valores de los elementos del ítem
        UserModelo user = usuariosLista.get(position);
        holder.idUsuario.setText(String.valueOf(user.getId()));  // Muestra el ID
        holder.nombreUsuario.setText(user.getNombreUsuario());  // Muestra el Nombre
        holder.email.setText(user.getEmail());  // Muestra el Email
    }

    @Override
    public int getItemCount() {
        return usuariosLista.size();
    }
}
