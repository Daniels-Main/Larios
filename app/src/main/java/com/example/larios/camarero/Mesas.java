package com.example.larios.camarero;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;

import com.example.larios.EnterLayout;
import com.example.larios.GlobalVariables;
import com.example.larios.ObjetoMesa;
import com.example.larios.R;
import com.example.larios.admin.AddCamarero;
import com.example.larios.admin.MainTickets;
import com.example.larios.admin.Mensaje;
import com.example.larios.admin.Mensajes;
import com.example.larios.admin.MesasAdmin;

import java.util.ArrayList;

public class Mesas extends AppCompatActivity {
    private ArrayList<ObjetoMesa> om;
    private String userName;
    private Toolbar tb;
    private Menu mMenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);
        setContentView(R.layout.activity_mesas);

        //Recojo el nombre de usuario de la pantalla anterior
        Intent intent = getIntent();
        userName = intent.getStringExtra("user");

        //Busco los objetos mesas que estan asignados a este usuario
        om = ((GlobalVariables) this.getApplication()).getObjMesa();
        //Pongo los colores correspondientes
        validarColores();

        tb = (Toolbar) findViewById(R.id.tool_bar_mesas);
        setSupportActionBar(tb);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_listado, menu);
        tb.setTitle("");
        mMenu = menu;
        return true;
    }
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id){
            case R.id.action_comandas:
                Intent intent = new Intent(Mesas.this, ListadoMesas.class);
                startActivity(intent);
                break;
        }


        return super.onOptionsItemSelected(item);
    }

    private void validarColores(){
        for (ObjetoMesa oj : om){
            ImageButton im = findViewById(oj.getNumeroMesa());
            if (userName.equals(oj.getEmpleado())){
                im.setBackgroundColor(Color.BLUE);
            }else{
                im.setBackgroundColor(Color.RED);
            }
        }
    }

    public void empezar(View view){
        //Dependiendo que boton hayamos clicado si el color corresponde con azul entarra a la interfaz de la mesa
        ImageButton imageButton = findViewById(view.getId());
        try {
            ColorDrawable drawable = (ColorDrawable) imageButton.getBackground();
            int colorId = drawable.getColor();

            if (colorId == Color.RED) {
                //Añadir algo a la cuenta
            } else if (colorId == Color.BLUE) {
                Intent intent = new Intent(this, InterfazMesa.class);
                intent.putExtra("id",view.getId());
                startActivity(intent);
            }
        }catch (Exception e){
            //En el caso de que no haya color, no hay camarero
            TextView textView = findViewById(R.id.textView3);
            textView.setText("Esta mesa no tiene ningún camarero");
        }
    }

    //Crea un popup por el cual puedes escribir un mensaje y enviarlo al admin
    public void ayuda(View view){
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.escribir_mensaje, null);

        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true;
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

        Button visto = popupView.findViewById(R.id.button_send);
        visto.setOnClickListener(view1 -> {
            EditText msg = popupView.findViewById(R.id.et_mensaje);
            Mensaje mensaje = new Mensaje(userName,msg.getText().toString());
            popupWindow.dismiss();
            ((GlobalVariables) this.getApplication()).addM(mensaje);
        });

    }

    //Vuelve a la ventana Login
    public void returnVentanaLogin(View view){
        Intent intent = new Intent(this,EnterLayout.class);
        startActivity(intent);
    }


}