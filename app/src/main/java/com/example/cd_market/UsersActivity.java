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
    private ExecutorService executorService; // ExecutorService para manejar los hilos en segundo plano

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_users);

        recyclerViewUsuario = findViewById(R.id.recyclerUsuario);
        recyclerViewUsuario.setLayoutManager(new LinearLayoutManager(this));

        // Crear un ExecutorService con un solo hilo para ejecutar las tareas en segundo plano
        executorService = Executors.newSingleThreadExecutor();

        // Ejecutar la tarea de cargar los usuarios en segundo plano
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                // Aquí se realiza la operación en segundo plano (consulta a la base de datos)
                DatabaseHelper db = new DatabaseHelper(getApplicationContext());
                final List<UserModelo> usuarios = db.mostrarUsuarios();

                // Después de obtener los datos, actualizamos la UI en el hilo principal
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
        // Cerrar el ExecutorService cuando la actividad se destruya
        if (executorService != null) {
            executorService.shutdown();
        }
    }
}
