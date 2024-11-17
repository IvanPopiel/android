package com.example.cd_market;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class UsersActivity extends AppCompatActivity {

    private RecyclerView recyclerViewUsuario;
    private UserAdaptador adaptadorUsuario;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);

        recyclerViewUsuario = (RecyclerView)findViewById(R.id.recyclerUsuario);
        recyclerViewUsuario.setLayoutManager(new LinearLayoutManager(this));


        DatabaseHelper db = new DatabaseHelper(getApplicationContext());
        adaptadorUsuario = new UserAdaptador(db.mostrarUsuarios());
        recyclerViewUsuario.setAdapter(adaptadorUsuario);
    }


}