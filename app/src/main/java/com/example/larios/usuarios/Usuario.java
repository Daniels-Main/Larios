package com.example.larios.usuarios;

import android.content.ContentValues;

public class Usuario {
    private String id;
    private String nombre;
    private String pass;
    private String admin;
    private String avatarUri;


    public Usuario(String id,String nom, String passw, String adminn, String avatar){
        this.id = id;
        this.nombre=nom;
        this.pass=passw;
        this.admin=adminn;
        this.avatarUri = avatar;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    public String getAvatarUri() {
        return avatarUri;
    }

    public void setAvatarUri(String avatarUri) {
        this.avatarUri = avatarUri;
    }

    @Override
    public String toString() {
        return nombre + pass + admin;
    }

    public ContentValues toContentValues() {
        ContentValues values = new ContentValues();
        values.put(UsersTable.UsersEntry.ID, id);
        values.put(UsersTable.UsersEntry.NAME, nombre);
        values.put(UsersTable.UsersEntry.PASS, pass);
        values.put(UsersTable.UsersEntry.ADMIN, admin);
        values.put(UsersTable.UsersEntry.AVATAR_URI, avatarUri);
        return values;
    }
}
