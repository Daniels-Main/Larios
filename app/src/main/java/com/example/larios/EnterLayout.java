package com.example.larios;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import com.example.larios.admin.MesasAdmin;
import com.example.larios.camarero.Mesas;
import com.example.larios.usuarios.UsersDbHelper;
import com.example.larios.usuarios.Usuario;

import java.util.ArrayList;

public class EnterLayout extends AppCompatActivity {
    private UsersDbHelper usersDbHelper;
    public static final String USER_MESSAGE = "com.example.app.MESSAGE";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_enter_layout);
        usersDbHelper= new UsersDbHelper(getBaseContext());


    }
    public void login(View view){


        Usuario actual = usuarioEncontrado(usersDbHelper.getAllObjects());

        if (actual == null){
            TextView tv = findViewById(R.id.passerror);
            TextView tv2 = findViewById(R.id.resetContra);
            tv.setText("El usuario o la contraseña son incorrectos");
            tv.setTextColor(Color.RED);
            SpannableString mitextoU = new SpannableString("¿Reiniciar contraseña?");
            mitextoU.setSpan(new UnderlineSpan(), 0, mitextoU.length(), 0);
            tv2.setText(mitextoU);
            tv2.setTextSize(15);
            tv2.setTextColor(Color.RED);
        }else{
            if (actual.getAdmin().equals("1")){
                Intent intent = new Intent(this, MesasAdmin.class);
                //Guardar el usuario como string
                //intent.putExtra(EXTRA_MESSAGE,actual.toString());
                startActivity(intent);
            }else{
                Intent intent = new Intent(this, Mesas.class);
                intent.putExtra(USER_MESSAGE,actual.getNombre());
                //Guardar el usuario como string
                //intent.putExtra(EXTRA_MESSAGE,actual.toString());
                startActivity(intent);
            }
        }


    }

    public Usuario usuarioEncontrado(ArrayList<Usuario> lista){
        EditText textNombre = findViewById(R.id.editTextTextPersonName);
        EditText passw = (EditText) findViewById(R.id.editTextTextPassword);
        for (Usuario usuario : lista) {
            if (textNombre.getText().toString().trim().equals(usuario.getNombre()) && passw.getText().toString().equals(usuario.getPass())) {
                return usuario;
            }
        }
        return null;
    }

    public void reset(View view){
        Intent intent = new Intent(this, RestContra.class);
        startActivity(intent);
    }


}