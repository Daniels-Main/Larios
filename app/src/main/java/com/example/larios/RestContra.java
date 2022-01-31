package com.example.larios;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;

import com.example.larios.usuarios.UsersDbHelper;
import com.example.larios.usuarios.Usuario;

import java.util.ArrayList;

public class RestContra extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);
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
        finish();
    }

}