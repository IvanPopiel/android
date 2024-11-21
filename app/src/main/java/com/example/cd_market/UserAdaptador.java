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
            idUsuario = itemView.findViewById(R.id.idUsuario);
            nombreUsuario = itemView.findViewById(R.id.nombreUsuario);
            email = itemView.findViewById(R.id.email);
        }
    }

    public List<UserModelo> usuariosLista;

    public UserAdaptador(List<UserModelo> usuariosLista) {
        this.usuariosLista = usuariosLista;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_user, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        UserModelo user = usuariosLista.get(position);
        holder.idUsuario.setText(String.valueOf(user.getId()));
        holder.nombreUsuario.setText(user.getNombreUsuario());
        holder.email.setText(user.getEmail());
    }

    @Override
    public int getItemCount() {
        return usuariosLista.size();
    }
}
