package com.example.larios.admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;

import com.example.larios.EnterLayout;
import com.example.larios.GlobalVariables;
import com.example.larios.R;

import java.util.ArrayList;
import java.util.HashMap;

public class MainTickets extends AppCompatActivity {
    private Toolbar toolbar;
    private Menu mMenu;
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

        setContentView(R.layout.activity_main_tickets);
        toolbar = (Toolbar) findViewById(R.id.tool_bar_ticketadmin);
        setSupportActionBar(toolbar);



        HashMap<Integer, ArrayList<Object>> comida = ((GlobalVariables) this.getApplication()).getCocina();


        for (Integer key : comida.keySet()){
            ImageButton im = findViewById(key);
            im.setBackgroundColor(Color.GREEN);


            im.setOnClickListener(view -> {
                Intent intent = new Intent(MainTickets.this, VistaTicketAdmin.class);
                intent.putExtra("numMesa",key);
                startActivity(intent);
            });

        }



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_admin, menu);
        toolbar.setTitle("");
        mMenu = menu;
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