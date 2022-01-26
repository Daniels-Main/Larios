package com.example.larios.camarero;

import androidx.annotation.Nullable;
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
import android.widget.Toast;

import com.example.larios.GlobalVariables;
import com.example.larios.R;
import com.example.larios.admin.AddCamarero;
import com.example.larios.admin.MainTickets;
import com.example.larios.admin.Mensajes;
import com.example.larios.admin.MesasAdmin;
import com.example.larios.comidasybebidas.Plato;

import org.w3c.dom.Text;

import javax.xml.transform.OutputKeys;

public class InterfazPlatos extends AppCompatActivity {
    private Toolbar toolbar;
    private Menu mMenu;
    Plato editado;
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
                startActivityForResult(new Intent(this, Buscador.class),1568);
                break;
        }


        return super.onOptionsItemSelected(item);
    }

    public void volver(View view) {
        finish();
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1568) {
            if (resultCode == RESULT_OK) {
                editado = buscarPlato(data.getStringExtra("nombre_plato"));
                editado.setIngredientes(data.getStringArrayListExtra("ingredientes"));
            }else{
                Toast.makeText(this, "El plato no se ha añadido", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this,"No se desde donde has vuelto",Toast.LENGTH_SHORT);
        }
    }

    public Plato buscarPlato(String obejtoPulsado){
        for(Plato p : ((GlobalVariables) this.getApplication()).getPlatos()){
            if (obejtoPulsado.equals(p.getNombre())){
                return p;
            }
        }
        return null;
    }



}