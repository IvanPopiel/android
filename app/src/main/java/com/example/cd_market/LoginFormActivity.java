package com.example.cd_market;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LoginFormActivity extends AppCompatActivity {

    private EditText edtUsername, edtPassword;
    private Button btnLogin;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_form);

        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);
        btnLogin = findViewById(R.id.btnLogin);

        databaseHelper = new DatabaseHelper(this);

        btnLogin.setOnClickListener(v -> loginUser());
    }

    private void loginUser() {
        // Obtener los datos ingresados por el usuario
        String username = edtUsername.getText().toString().trim();
        String password = edtPassword.getText().toString().trim();

        // Verificar que los campos no estén vacíos
        if (TextUtils.isEmpty(username)) {
            edtUsername.setError("El nombre de usuario es obligatorio");
            return;
        }
        if (TextUtils.isEmpty(password)) {
            edtPassword.setError("La contraseña es obligatoria");
            return;
        }

        // Verificar si el usuario existe en la base de datos
        boolean isValidUser = databaseHelper.checkUser(username, password);

        if (isValidUser) {
            // Obtener el rol del usuario
            String role = databaseHelper.getUserRole(username);

            if (role.equalsIgnoreCase("Administrador")) {
                // Redirigir a la actividad de administrador
                Toast.makeText(this, "Bienvenido Administrador", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LoginFormActivity.this, AdminOptionsActivity.class);

                // Usamos el flag para evitar regresar a LoginFormActivity
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            } else {
                // Redirigir a la actividad de usuario
                Toast.makeText(this, "Bienvenido Usuario", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(LoginFormActivity.this, UserActivity.class);
                startActivity(intent);
            }
            finish();
        } else {
            Toast.makeText(this, "Usuario o contraseña incorrectos", Toast.LENGTH_SHORT).show();
        }

    }



}
