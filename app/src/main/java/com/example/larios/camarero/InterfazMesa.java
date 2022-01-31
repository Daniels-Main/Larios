package com.example.larios.camarero;

import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
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

public class InterfazMesa extends AppCompatActivity {
    private Toolbar toolbar;
    private Menu mMenu;
    private String numMesa;
    Plato editado;
    @SuppressLint("NonConstantResourceId")
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

        //Recojo el id del boton de la pantalla anterior
        Bundle b = new Bundle();
        b = getIntent().getExtras();
        int id = b.getInt("id");

        //Para meterlo en un textView
        TextView numeroMesa = findViewById(R.id.textView2);

        //Swich case
        switch (id){
            case R.id.imageButton:
                numMesa = "1";
                break;
            case R.id.imageButton2:
                numMesa = "2";
                break;
            case R.id.imageButton3:
                numMesa = "3";
                break;
            case R.id.imageButton4:
                numMesa = "4";
                break;
            case R.id.imageButton5:
                numMesa = "5";
                break;
            case R.id.imageButton6:
                numMesa = "6";
                break;
        }

        //Si el numMesa esta vacio pues significa que no se encuentra el numero
        if (numMesa.equals("")){
            numeroMesa.setText("Mesa Numero Irreconocible");
        }else{
            numeroMesa.setText("Mesa Numero: "+numMesa);
        }

    }

    //Aquí le pongo el menu del camarero a la toolbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_camarero, menu);
        toolbar.setTitle("");
        mMenu = menu;
        return true;
    }

    //Metodo necesario para el menu
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        return super.onPrepareOptionsMenu(menu);
    }


    //Dependiendo de donde hagamos click en la toolbar, iremos a el Buscador o hacia atras
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id){
            case android.R.id.home:
                this.finish();
                return true;

            case R.id.action_carta:
                //Empezamos la siguiente pantalla a la espera de un plato
                startActivityForResult(new Intent(this, Buscador.class),1568);
                break;
        }


        return super.onOptionsItemSelected(item);
    }
    //Metotdo para vover hacia atras
    public void volver(View view) {
        finish();
    }


    //Aqui es donde gestionaremos la vuelta del Buscador
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1568) {
            if (resultCode == RESULT_OK) {
                editado = buscarPlato(data.getStringExtra("nombre_plato"));
                editado.setIngredientes(data.getStringArrayListExtra("ingredientes"));
            }
        }else{
            Toast.makeText(this,"No se desde donde has vuelto",Toast.LENGTH_SHORT);
        }
    }

    //Metodo para saber que plato es de la base de datos (Se repite 2 de veces en 2 clases diferentes)
    public Plato buscarPlato(String obejtoPulsado){
        for(Plato p : ((GlobalVariables) this.getApplication()).getPlatos()){
            if (obejtoPulsado.equals(p.getNombre())){
                return p;
            }
        }
        return null;
    }



}