package com.example.cd_market;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UsersActivity extends AppCompatActivity {

    private RecyclerView recyclerViewUsuario;
    private UserAdaptador adaptadorUsuario;
    private ExecutorService executorService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);

        recyclerViewUsuario = findViewById(R.id.recyclerUsuario);
        recyclerViewUsuario.setLayoutManager(new LinearLayoutManager(this));


        executorService = Executors.newSingleThreadExecutor();

        executorService.execute(new Runnable() {
            @Override
            public void run() {

                DatabaseHelper db = new DatabaseHelper(getApplicationContext());
                final List<UserModelo> usuarios = db.mostrarUsuarios();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adaptadorUsuario = new UserAdaptador(usuarios);
                        recyclerViewUsuario.setAdapter(adaptadorUsuario);
                    }
                });
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (executorService != null) {
            executorService.shutdown();
        }
    }
}
