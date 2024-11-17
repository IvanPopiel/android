package com.example.cd_market;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdminActivity extends AppCompatActivity {

    EditText editID, editNombre, editDescripcion;
    Button buttonAdd, buttonUpdate, buttonDelete, buttonList, buttonSearch;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        editID=(EditText)findViewById(R.id.editID);
        editNombre=(EditText)findViewById(R.id.editNombre);
        editDescripcion=(EditText)findViewById(R.id.editDescripcion);
        buttonAdd=(Button)findViewById(R.id.buttonAdd);
        buttonUpdate=(Button)findViewById(R.id.buttonUpdate);
        buttonDelete=(Button)findViewById(R.id.buttonDelete);
        buttonList=(Button)findViewById(R.id.buttonList); //boton para mostrar
        buttonSearch=(Button)findViewById(R.id.buttonSearch); //boton para buscar


        final DatabaseHelper db = new DatabaseHelper(getApplicationContext());

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.addGame(editID.getText().toString(), editNombre.getText().toString(), editDescripcion.getText().toString());
                Toast.makeText(getApplicationContext(), "Se agregó con éxito el juego.", Toast.LENGTH_SHORT).show();

            }
        });

        buttonList.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent mostrarJuegos = new Intent(getApplicationContext(),GamesActivity.class);
                startActivity(mostrarJuegos);
            }
        });

        buttonSearch.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
               GameModelo games=new GameModelo();
               db.buscarGames(games,editID.getText().toString());
               editNombre.setText(games.getNombre());
               editDescripcion.setText(games.getDescripcion());
            }
        });

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.editGame(editID.getText().toString(), editNombre.getText().toString(), editDescripcion.getText().toString());
                Toast.makeText(getApplicationContext(), "Los datos han sido actualizados", Toast.LENGTH_SHORT).show();
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.deleteGame(editID.getText().toString());
                Toast.makeText(getApplicationContext(), "Juego eliminado exitosamente", Toast.LENGTH_SHORT).show();
            }
        });

    }
}