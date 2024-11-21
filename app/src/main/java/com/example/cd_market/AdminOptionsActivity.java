package com.example.cd_market;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class AdminOptionsActivity extends AppCompatActivity {

    Button buttonUsuarios, buttonJuegos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_options);

        buttonJuegos = findViewById(R.id.buttonJuegos);
        buttonUsuarios = findViewById(R.id.buttonUsuarios);

        // Acción para el botón CRUD JUEGOS
        buttonJuegos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Iniciar AdminActivity cuando se hace clic en "CRUD JUEGOS"
                Intent intentJuegos = new Intent(AdminOptionsActivity.this, AdminActivity.class);
                startActivity(intentJuegos);
            }
        });

        // Acción para el botón CRUD USUARIOS
        buttonUsuarios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Iniciar UsersActivity cuando se hace clic en "CRUD USUARIOS"
                Intent intentUsuarios = new Intent(AdminOptionsActivity.this, AdminUsersActivity.class);
                startActivity(intentUsuarios);
            }
        });
    }
}
