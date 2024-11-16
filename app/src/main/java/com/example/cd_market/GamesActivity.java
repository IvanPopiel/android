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

        adaptadorJuego = new GameAdaptador(obtenerJuegos());
        recyclerViewJuego.setAdapter(adaptadorJuego);
    }

    public List<GameModelo> obtenerJuegos(){
        List<GameModelo> juego = new ArrayList<>();
        juego.add(new GameModelo("assassins creed", "tercera entrega"));
        juego.add(new GameModelo("assassins creed 2", "segunda entrega"));
        juego.add(new GameModelo("assassins creed 4", "cuarta entrega"));
        juego.add(new GameModelo("assassins creed 5", "quinta entrega"));

        return juego;
    }
}