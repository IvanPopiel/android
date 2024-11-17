package com.example.cd_market;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdminUsersActivity extends AppCompatActivity {

    EditText editID, editNombreUsuario, editEmail;
    Button buttonAdd, buttonUpdate, buttonDelete, buttonList, buttonSearch;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_users);

        editID=(EditText)findViewById(R.id.editID);
        editNombreUsuario=(EditText)findViewById(R.id.editNombreUsuario);
        editEmail=(EditText)findViewById(R.id.editEmail);
        buttonUpdate=(Button)findViewById(R.id.buttonUpdate);
        buttonDelete=(Button)findViewById(R.id.buttonDelete);
        buttonList=(Button)findViewById(R.id.buttonList); //boton para mostrar
        buttonSearch=(Button)findViewById(R.id.buttonSearch); //boton para buscar


        final DatabaseHelper db = new DatabaseHelper(getApplicationContext());



        buttonList.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent mostrarUsuarios = new Intent(getApplicationContext(),UsersActivity.class);
                startActivity(mostrarUsuarios);
            }
        });

        buttonSearch.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                UserModelo users=new UserModelo();
                db.buscarUser(users,editID.getText().toString());
                editNombreUsuario.setText(users.getNombreUsuario());
                editEmail.setText(users.getEmail());
            }
        });

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.editUser(editID.getText().toString(), editNombreUsuario.getText().toString(), editEmail.getText().toString());
                Toast.makeText(getApplicationContext(), "Los datos han sido actualizados", Toast.LENGTH_SHORT).show();
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.deleteUser(editID.getText().toString());
                Toast.makeText(getApplicationContext(), "usuario eliminado exitosamente", Toast.LENGTH_SHORT).show();
            }
        });

    }
}