package com.example.cd_market;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AdminActivity extends AppCompatActivity {

    EditText editID, editNombre, editDescripcion;
    Button buttonAdd, buttonUpdate, buttonDelete, buttonList;




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
        buttonList=(Button)findViewById(R.id.buttonList);

        final DatabaseHelper db = new DatabaseHelper(getApplicationContext());

        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.addGame(editID.getText().toString(), editNombre.getText().toString(), editDescripcion.getText().toString());
                Toast.makeText(getApplicationContext(), "Se agregó con éxito el juego.", Toast.LENGTH_SHORT).show();
                Log.d("AdminActivity", "Juego agregado: " + editNombre.getText().toString());

            }
        });
    }
}