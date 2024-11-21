package com.example.cd_market;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    private EditText edtUsername, edtEmail, edtPassword, edtConfirmPassword;
    private RadioGroup radioGroupRole;
    private Button btnRegister;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        edtUsername = findViewById(R.id.edtUsername);
        edtEmail = findViewById(R.id.edtEmail);
        edtPassword = findViewById(R.id.edtPassword);
        edtConfirmPassword = findViewById(R.id.edtConfirmPassword);
        radioGroupRole = findViewById(R.id.radioGroupRole);
        btnRegister = findViewById(R.id.btnRegister);

        databaseHelper = new DatabaseHelper(this);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });
    }

    private void registerUser() {
        String username = edtUsername.getText().toString().trim();
        String email = edtEmail.getText().toString().trim();
        String password = edtPassword.getText().toString().trim();
        String confirmPassword = edtConfirmPassword.getText().toString().trim();

        // Verificar que los campos no estén vacíos
        if (TextUtils.isEmpty(username)) {
            edtUsername.setError("El nombre de usuario es obligatorio");
            return;
        }
        if (TextUtils.isEmpty(email)) {
            edtEmail.setError("El correo electrónico es obligatorio");
            return;
        }
        if (TextUtils.isEmpty(password)) {
            edtPassword.setError("La contraseña es obligatoria");
            return;
        }
        if (TextUtils.isEmpty(confirmPassword)) {
            edtConfirmPassword.setError("Debe confirmar la contraseña");
            return;
        }
        if (!password.equals(confirmPassword)) {
            edtConfirmPassword.setError("Las contraseñas no coinciden");
            return;
        }

        // Obtener el rol seleccionado (Usuario o Administrador)
        int selectedId = radioGroupRole.getCheckedRadioButtonId();
        RadioButton selectedRadioButton = findViewById(selectedId);
        String selectedRole = selectedRadioButton != null ? selectedRadioButton.getText().toString() : "Usuario Común";

        // Verificar si el nombre de usuario ya existe
        if (databaseHelper.isUserExists(username)) {
            edtUsername.setError("Este nombre de usuario ya está registrado");
            return;
        }

        // Llamar al método para agregar el usuario a la base de datos
        boolean isRegistered = databaseHelper.addUser(username, email, password, selectedRole);

        // Verificar si el registro fue exitoso
        if (isRegistered) {
            Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show();
            finish();
        } else {
            Toast.makeText(this, "Error al registrar el usuario. Inténtalo de nuevo.", Toast.LENGTH_SHORT).show();
        }
    }
}
