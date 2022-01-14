package com.example.larios.camarero;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;

import com.example.larios.GlobalVariables;
import com.example.larios.R;
import com.example.larios.admin.AddCamarero;
import com.example.larios.admin.MainTickets;
import com.example.larios.admin.Mensajes;
import com.example.larios.admin.MesasAdmin;

import org.w3c.dom.Text;

import javax.xml.transform.OutputKeys;

public class InterfazPlatos extends AppCompatActivity {
    private Toolbar toolbar;
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

        setContentView(R.layout.activity_interfaz_platos);
        toolbar = (Toolbar) findViewById(R.id.tool_bar_i_platos);
        setSupportActionBar(toolbar);

        Bundle b = new Bundle();
        b = getIntent().getExtras();
        int id = b.getInt("id");

        TextView numeroMesa = findViewById(R.id.textView2);

        if (id==R.id.imageButton){
            numeroMesa.setText("Mesa Numero 1");
        }else if (id==R.id.imageButton2){
            numeroMesa.setText("Mesa Numero 2");
        }else if (id==R.id.imageButton3){
            numeroMesa.setText("Mesa Numero 3");
        }else if (id==R.id.imageButton4){
            numeroMesa.setText("Mesa Numero 4");
        }else if (id==R.id.imageButton5){
            numeroMesa.setText("Mesa Numero 5");
        }else if (id==R.id.imageButton6){
            numeroMesa.setText("Mesa Numero 6");
        }else{
            numeroMesa.setText("Mesa Numero Irreconocible");
        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_camarero, menu);
        toolbar.setTitle("");
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
            case android.R.id.home:
                this.finish();
                return true;

            case R.id.action_carta:
                startActivity(new Intent(this, Buscador.class));
                break;
        }


        return super.onOptionsItemSelected(item);
    }

    public void volver(View view) {
        finish();
    }



}