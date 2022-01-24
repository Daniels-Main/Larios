package com.example.larios.admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.ListPopupWindow;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.larios.EnterLayout;
import com.example.larios.GlobalVariables;
import com.example.larios.ObjetoMesa;
import com.example.larios.R;
import com.example.larios.usuarios.UsersDbHelper;

import java.util.List;

public class MesasAdmin extends AppCompatActivity {
    private Toolbar toolbar;
    private ListPopupWindow camarerosPopupList;
    private Menu mMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
                        | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);

        getWindow().setStatusBarColor(getResources().getColor(R.color.white));

        setContentView(R.layout.activity_mesas_admin);

        toolbar = (Toolbar) findViewById(R.id.tool_bar_mesas_admin);
        setSupportActionBar(toolbar);

    }


    public void imageButtonClicker(View view){
        setPopupList(view.getId());
        camarerosPopupList.show();
    }

    private void setPopupList(int vid) {
        UsersDbHelper usersDbHelper = new UsersDbHelper(getBaseContext());
        final List<String> status = usersDbHelper.nombreCamareros(usersDbHelper.getAllObjects());
        ImageButton ib = findViewById(vid);
        camarerosPopupList = new ListPopupWindow(this);
        ArrayAdapter adapter = new ArrayAdapter<>(this, R.layout.item_simple_status, R.id.tv_element, status);
        camarerosPopupList.setAnchorView(ib);
        camarerosPopupList.setAdapter(adapter);
        camarerosPopupList.setHeight(200);
        camarerosPopupList.setOnItemClickListener((parent, view, position, id) -> {
            for (ObjetoMesa objetoMesa : ((GlobalVariables) this.getApplication()).getSomeVariable()){
                if (objetoMesa.getNumeroMesa()==vid){
                    ((GlobalVariables) this.getApplication()).remove(objetoMesa);
                }
            }
            ((GlobalVariables) this.getApplication()).add(new ObjetoMesa(vid, status.get(position)));
            Toast.makeText(MesasAdmin.this, status.get(position)+" es camarero de esta mesa", Toast.LENGTH_SHORT).show();
            camarerosPopupList.dismiss();
        });
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