package com.example.larios;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EdgeEffect;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class RestContra extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_rest_contra);
    }

    public void newpass(View view){
        UsersDbHelper usersDbHelper = new UsersDbHelper(getBaseContext());
        EditText nombre = findViewById(R.id.nombreRemplace);
        EditText pass = findViewById(R.id.PassRemplace);
        EditText repeat = findViewById(R.id.RepeatPassRemplace);
        if (pass.getText().toString().equals(repeat.getText().toString())){
            ArrayList<Usuario> list= usersDbHelper.getAllObjects();
            Usuario user = userByName(list,nombre);
            if (user == null){
                nombre.setError("El usuario no existe");
            }else if (pass.getText().toString().equals("")){
                pass.setError("No puedes dejar campos vacios");
            }else{
                usersDbHelper.cambioDePass(nombre.getText().toString(),pass.getText().toString());
                returnVentanaLogin(view);
            }

        }else{
            repeat.setError("La contraseña no coincide");
        }
    }
    public Usuario userByName(ArrayList<Usuario> lista, EditText nombre){

        for (Usuario usuario : lista) {
            if (nombre.getText().toString().equals(usuario.getNombre())) {
                return usuario;
            }
        }
        return null;
    }

    public void returnVentanaLogin(View view){
        Intent intent = new Intent(this,EnterLayout.class);
        startActivity(intent);
    }

}