package com.example.cd_market;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AdminActivity extends AppCompatActivity {

    EditText editID, editNombre, editDescripcion;
    Button buttonAdd, buttonUpdate, buttonDelete, buttonList, buttonSearch;

    // Crea un ExecutorService para ejecutar tareas en segundo plano
    ExecutorService executorService = Executors.newSingleThreadExecutor();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        editID = findViewById(R.id.editID);
        editNombre = findViewById(R.id.editNombre);
        editDescripcion = findViewById(R.id.editDescripcion);
        buttonAdd = findViewById(R.id.buttonAdd);
        buttonUpdate = findViewById(R.id.buttonUpdate);
        buttonDelete = findViewById(R.id.buttonDelete);
        buttonList = findViewById(R.id.buttonList); // Botón para mostrar
        buttonSearch = findViewById(R.id.buttonSearch); // Botón para buscar

        final DatabaseHelper db = new DatabaseHelper(getApplicationContext());

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                executorService.execute(new Runnable() {
                    @Override
                    public void run() {
                        // Verificar si el juego ya existe
                        boolean gameExists = db.gameExists(editID.getText().toString());

                        // Si el juego existe, mostrar un mensaje de error
                        if (gameExists) {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getApplicationContext(), "El juego con este ID ya existe", Toast.LENGTH_SHORT).show();
                                }
                            });
                        } else {
                            // Si no existe, agregar el juego
                            db.addGame(editID.getText().toString(), editNombre.getText().toString(), editDescripcion.getText().toString());

                            // Mostrar mensaje de éxito
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(getApplicationContext(), "Se agregó con éxito el juego.", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    }
                });
            }
        });


        // Mostrar lista de juegos
        buttonList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                executorService.execute(new Runnable() {
                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Intent mostrarJuegos = new Intent(getApplicationContext(), GamesActivity.class);
                                startActivity(mostrarJuegos);
                            }
                        });
                    }
                });
            }
        });

        buttonSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                executorService.execute(new Runnable() {
                    @Override
                    public void run() {
                        // Realizar la búsqueda en la base de datos en segundo plano
                        GameModelo games = new GameModelo();
                        db.buscarGames(games, editID.getText().toString());

                        // Una vez que la búsqueda esté completa, actualizar la UI
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                // Actualizar los campos en la interfaz de usuario
                                editNombre.setText(games.getNombre());
                                editDescripcion.setText(games.getDescripcion());
                            }
                        });
                    }
                });
            }
        });

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                executorService.execute(new Runnable() {
                    @Override
                    public void run() {
                        // Realizar la operación de actualización en la base de datos
                        db.editGame(editID.getText().toString(), editNombre.getText().toString(), editDescripcion.getText().toString());

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                Toast.makeText(getApplicationContext(), "Los datos han sido actualizados", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                executorService.execute(new Runnable() {
                    @Override
                    public void run() {

                        db.deleteGame(editID.getText().toString());

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                                Toast.makeText(getApplicationContext(), "Juego eliminado exitosamente", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                });
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Cerrar el ExecutorService cuando se destruye la actividad
        if (!executorService.isShutdown()) {
            executorService.shutdown();
        }
    }
}
