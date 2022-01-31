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

        setContentView(R.layout.activity_enter_layout);

        //Inicializo la clase de la base de datos SQLite
        usersDbHelper= new UsersDbHelper(getBaseContext());

    }
    public void login(View view){

        //Busco al usuario en la base de datos
        Usuario actual = usuarioEncontrado(usersDbHelper.getAllObjects());

        //Si el usario es null te saldra el texto para resetear la contraseña
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
            //Dependiendo de si el usuario es admin entra en una pantalla o en otra
            Intent intent;
            if (actual.getAdmin().equals("1")){
                intent = new Intent(this, MesasAdmin.class);
            }else{
                intent = new Intent(this, Mesas.class);
                intent.putExtra("user",actual.getNombre());
            }
            startActivity(intent);
        }


    }
    //Metodo que recoje la arrayList de usuarios que nos aporta la base de datos y devuelve el usuario encontrado
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
        TextView a = findViewById(view.getId());
        //Comprueba que el reset pass tiene texto porque sino se puede pulsar sin que te salga el mensaje
        if (!a.getText().toString().equals("")) {
            Intent intent = new Intent(this, RestContra.class);
            startActivity(intent);
        }
    }


}