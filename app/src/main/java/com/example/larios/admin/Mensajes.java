package com.example.larios.admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.larios.EnterLayout;
import com.example.larios.GlobalVariables;
import com.example.larios.R;

import java.util.ArrayList;

public class Mensajes extends AppCompatActivity {
    private Toolbar toolbar;
    private Menu mMenu;
    private ListView lv;
    TextView tx;

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

        setContentView(R.layout.activity_mensajes);
        toolbar = (Toolbar) findViewById(R.id.toolbar_mensajes);
        setSupportActionBar(toolbar);
        lv = findViewById(R.id.list_msg);
        tx = findViewById(R.id.nada);

        ls();

    }

    //Metodo para ver los mensajes y al clikar se abre un popUp con el contenido
    public void ls(){
        tx.setText("");
        if (!(((GlobalVariables) this.getApplication()).getMensajes().size() ==0)){
            ArrayList<Mensaje> m = ((GlobalVariables) this.getApplication()).getMensajes();


            MensajeAdapter adapter = new MensajeAdapter(this,R.layout.row_layout,m);

            lv.setAdapter(adapter);

            lv.setOnItemClickListener((adapterView, view, position, id) -> {
                Mensaje mensaje = m.get (position);
                LayoutInflater inflater = (LayoutInflater)
                        getSystemService(LAYOUT_INFLATER_SERVICE);
                View popupView = inflater.inflate(R.layout.pop_up_window, null);

                int width = LinearLayout.LayoutParams.WRAP_CONTENT;
                int height = LinearLayout.LayoutParams.WRAP_CONTENT;
                boolean focusable = true;
                final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);


                popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);
                TextView user = popupView.findViewById(R.id.user_pop);
                user.setText(mensaje.getNombre());
                TextView msg = popupView.findViewById(R.id.mensaje_pop);
                msg.setText(mensaje.getMensaje());

                Button visto = popupView.findViewById(R.id.button_thrash);
                visto.setOnClickListener(view1 -> {
                    popupWindow.dismiss();
                    borrarMensaje(mensaje);
                });

            });
        }else{
            tx = findViewById(R.id.nada);
            tx.setText("No hay mensajes");
        }


    }

    //Borrar un mensaje
    public void borrarMensaje(Mensaje m){
        ((GlobalVariables) this.getApplication()).remove(m);
        finish();
        startActivity(getIntent());
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_admin, menu);
        toolbar.setTitle("");
        this.mMenu = menu;
        return true;
    }
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if (((GlobalVariables) this.getApplication()).getMensajes().size() > 0){
            mMenu.getItem(3).setIcon(R.drawable.ic_flecha_on);
        }

        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id){
            case R.id.action_am:
                startActivity(new Intent(this, MesasAdmin.class));
                break;
            case R.id.action_add:
                startActivity(new Intent(this,AddCamarero.class));
                break;
            case R.id.action_ticket:
                startActivity(new Intent(this,MainTickets.class));
                break;
            case R.id.mensajes:
                startActivity(new Intent(this, Mensajes.class));
        }


        return super.onOptionsItemSelected(item);
    }

    public void returnVentanaLogin(View view){
        Intent intent = new Intent(this, EnterLayout.class);
        startActivity(intent);
    }
}