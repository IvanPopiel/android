package com.example.cd_market;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class UserActivity extends AppCompatActivity {


    private RecyclerView recyclerViewJuego;
    private GameAdaptador adaptadorJuego;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_games);

        recyclerViewJuego = (RecyclerView)findViewById(R.id.recyclerJuego);
        recyclerViewJuego.setLayoutManager(new LinearLayoutManager(this));


        DatabaseHelper db = new DatabaseHelper(getApplicationContext());
        adaptadorJuego = new GameAdaptador(db.mostrarGames());
        recyclerViewJuego.setAdapter(adaptadorJuego);
    }
}