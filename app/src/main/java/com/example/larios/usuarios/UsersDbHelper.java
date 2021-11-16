package com.example.larios.usuarios;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class UsersDbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Usuarios.db";

    public UsersDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("CREATE TABLE " + UsersTable.UsersEntry.TABLE_NAME + " ("
                + UsersTable.UsersEntry.ID + " TEXT NOT NULL,"
                + UsersTable.UsersEntry.NAME + " TEXT NOT NULL,"
                + UsersTable.UsersEntry.PASS + " TEXT NOT NULL,"
                + UsersTable.UsersEntry.ADMIN + " TEXT NOT NULL,"
                + UsersTable.UsersEntry.AVATAR_URI + " TEXT NOT NULL,"
                + "UNIQUE (" + UsersTable.UsersEntry.ID + "))");

        // Contenedor de valores
        Usuario Sheng = new Usuario("1","Sheng","shengye","1","avatarsheng.jpg");
        Usuario Fani = new Usuario("2","Fani","fani","0","avatarfani.jpg");
        Usuario Dani = new Usuario("3","Dani","daniel","0","avatardani.jpg");
        Usuario Clara = new Usuario("4","Clara","claramala","0","avatarclara.jpg");

        saveUser(Sheng);
        saveUser(Fani);
        saveUser(Dani);
        saveUser(Clara);


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //Dejar en blanco
    }

    public long saveUser(Usuario user) {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        return sqLiteDatabase.insert(
                UsersTable.UsersEntry.TABLE_NAME, null, user.toContentValues());

    }

    public ArrayList<Usuario> getAllObjects()
    {
        // Get the isntance of the database
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();




        Cursor cursor = sqLiteDatabase.query(
                UsersTable.UsersEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null
        );



        ArrayList<Usuario> objectList = new ArrayList<Usuario>();

        try
        {
            if (cursor.moveToFirst()) {
                do {
                    Usuario object= new Usuario(cursor.getString(0),
                            cursor.getString(1),
                            cursor.getString(2),
                            cursor.getString(3),
                            cursor.getString(4)
                    );

                    objectList.add(object);
                } while (cursor.moveToNext());
            }
        }
        catch (SQLiteException e)
        {
            Log.d("SQL Error", e.getMessage());
            return null;
        }
        finally
        {
            cursor.close();
            sqLiteDatabase.close();
        }
        return objectList;
    }

    public List<String> nombreCamareros(ArrayList<Usuario> lista){
        List<String> nombres = new ArrayList<>();
        for (Usuario usuario : lista){
            if (usuario.getAdmin().equals("0")){
                nombres.add(usuario.getNombre());
            }

        }
        return nombres;
    }

    public void cambioDePass(String nom,String passw){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("pass",passw);
        sqLiteDatabase.update(UsersTable.UsersEntry.TABLE_NAME, cv, "nombre = ?", new String[]{nom});
    }

    public void generarUser(String nom, String contra, String url){
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();

        String selectQuery = "SELECT  id FROM " + UsersTable.UsersEntry.TABLE_NAME;
        Cursor cursor = sqLiteDatabase.rawQuery(selectQuery, null);
        cursor.moveToLast();

        int a = Integer.parseInt(cursor.getString(0))+1;

        Usuario neu = new Usuario(String.valueOf(a),nom,contra,"0",url);
        cursor.close();
        saveUser(neu);
    }
}
