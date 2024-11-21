package com.example.cd_market;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "marketDB";
    private static final int DATABASE_VERSION = 1;

    // Tabla de usuarios
    private static final String TABLE_USERS = "users";
    private static final String COLUMN_ID = "id";
    private static final String COLUMN_USERNAME = "username";
    private static final String COLUMN_EMAIL = "email";
    private static final String COLUMN_PASSWORD = "password";
    private static final String COLUMN_ROLE = "role";  // "user" o "admin"

    private static final String TABLE_GAMES = "CREATE TABLE GAMES(ID TEXT PRIMARY KEY, Nombre TEXT, Descripcion TEXT)";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Crear tabla de usuarios
        String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_USERS + "("
                + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_USERNAME + " TEXT, "
                + COLUMN_EMAIL + " TEXT, "
                + COLUMN_PASSWORD + " TEXT, "
                + COLUMN_ROLE + " TEXT)";
        db.execSQL(CREATE_USERS_TABLE);

        db.execSQL(TABLE_GAMES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        onCreate(db);
    }

    // Método para agregar un nuevo usuario
    public boolean addUser(String username, String email, String password, String role) {
        SQLiteDatabase db = this.getWritableDatabase();

        // Preparar los valores para insertar
        ContentValues values = new ContentValues();
        values.put(COLUMN_USERNAME, username);
        values.put(COLUMN_EMAIL, email);
        values.put(COLUMN_PASSWORD, password);
        values.put(COLUMN_ROLE, role);

        // Insertar en la base de datos
        long result = db.insert(TABLE_USERS, null, values);
        db.close();

        return result != -1;
    }

    // Método para verificar si un usuario existe por su nombre de usuario
    public boolean isUserExists(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_USERS + " WHERE " + COLUMN_USERNAME + " = ?",
                new String[] { username });

        if (cursor != null && cursor.moveToFirst()) {
            cursor.close();
            return true;  // Usuario existe
        } else {
            cursor.close();
            return false;  // Usuario no existe
        }
    }

    // Método para comprobar si las credenciales del usuario son válidas
    public boolean checkUser(String username, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = null;
        try {
            cursor = db.rawQuery("SELECT * FROM " + TABLE_USERS + " WHERE " + COLUMN_USERNAME + " = ? AND " + COLUMN_PASSWORD + " = ?",
                    new String[]{username, password});
            return cursor != null && cursor.moveToFirst();
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }


    public String getUserRole(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT " + COLUMN_ROLE + " FROM " + TABLE_USERS + " WHERE " + COLUMN_USERNAME + " = ?",
                new String[]{username});

        if (cursor != null && cursor.moveToFirst()) {
            String role = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_ROLE));
            cursor.close();
            return role;
        }
        if (cursor != null) {
            cursor.close();
        }
        return ""; // Retorna un string vacío si el usuario no existe
    }




    //juegos


    public void addGame(String id, String nombre, String descripcion){
        SQLiteDatabase db = getWritableDatabase();
        if(db!=null){
            db.execSQL("INSERT INTO GAMES VALUES('"+id+"', '"+nombre+"', '"+descripcion+"')");
            db.close();
        }
    }

    public List<GameModelo> mostrarGames(){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM GAMES", null);
        List<GameModelo> games = new ArrayList<>();
        if(cursor.moveToFirst()){
            do{
                games.add(new GameModelo(cursor.getInt(0), cursor.getString(1), cursor.getString(2)));
            }while(cursor.moveToNext());
        }
        return games;
    }

    public void buscarGames(GameModelo games, String id){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM GAMES WHERE ID ='"+id+"'", null);
        if(cursor.moveToFirst()){
            do{
                games.setNombre(cursor.getString(1));
                games.setDescripcion(cursor.getString(2));
            }while(cursor.moveToNext());
        }

    }

    public void editGame(String id, String nombre, String descripcion){
        SQLiteDatabase db = getWritableDatabase();
        if(db!=null){
            db.execSQL("UPDATE GAMES SET NOMBRE='"+nombre+"',DESCRIPCION='"+descripcion+"' WHERE ID='"+id+"'");
            db.close();
        }
    }

    public void deleteGame(String id){
        SQLiteDatabase db = getWritableDatabase();
        if(db!=null){
            db.execSQL("DELETE FROM GAMES WHERE ID='"+id+"'");
            db.close();
        }
    }



    //usuarios - ABM



    public void buscarUser(UserModelo users, String id){
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM USERS WHERE ID ='"+id+"'", null);
        if(cursor.moveToFirst()){
            do{
                users.setNombreUsuario(cursor.getString(1));
                users.setEmail(cursor.getString(2));
            }while(cursor.moveToNext());
        }

    }

    public void editUser(String id, String username, String email) {
        SQLiteDatabase db = getWritableDatabase();
        if (db != null) {
            db.execSQL("UPDATE users SET username='" + username + "', email='" + email + "' WHERE id='" + id + "'");
            db.close();
        }
    }


    public void deleteUser(String id){
        SQLiteDatabase db = getWritableDatabase();
        if(db!=null){
            db.execSQL("DELETE FROM USERS WHERE ID='"+id+"'");
            db.close();
        }
    }
    public List<UserModelo> mostrarUsuarios() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM USERS", null);
        List<UserModelo> users = new ArrayList<>();
        if(cursor.moveToFirst()){
            do{
                users.add(new UserModelo(cursor.getInt(0), cursor.getString(1),  cursor.getString(2)));
            }while(cursor.moveToNext());
        }
        return users;
    }
}
