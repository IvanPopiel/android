package com.example.cd_market;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class GamesActivity extends AppCompatActivity {

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