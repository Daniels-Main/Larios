package com.example.larios.camarero;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.larios.GlobalVariables;
import com.example.larios.ObjetoMesa;
import com.example.larios.R;
import com.example.larios.comidasybebidas.Bebida;
import com.example.larios.comidasybebidas.Plato;

import java.util.ArrayList;
import java.util.HashMap;

public class InterfazMesa extends AppCompatActivity {
    private Toolbar toolbar;
    private Menu mMenu;
    private String numMesa;
    ObjetoMesa esta;
    ListView lv;
    String camareroName;
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
        lv = findViewById(R.id.im_listview);
        //Recojo el id del boton de la pantalla anterior
        Bundle b = new Bundle();
        b = getIntent().getExtras();
        int id = b.getInt("id");
        camareroName = b.getString("camareroName");



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
        ArrayList<ObjetoMesa> mesas = ((GlobalVariables) this.getApplication()).getObjMesa();
        for (ObjetoMesa objetoMesa : mesas){
            if (objetoMesa.getNumeroMesa()==id){
                esta = objetoMesa;
            }
        }

        if (!camareroName.equals(esta.getEmpleado())){
            Toast.makeText(this, "Esta mesa es de: "+esta.getEmpleado(), Toast.LENGTH_SHORT).show();
        }

        //Si el numMesa esta vacio pues significa que no se encuentra el numero
        if (numMesa.equals("")){
            numeroMesa.setText("Mesa Numero Irreconocible");
        }else{
            numeroMesa.setText("Mesa Numero: "+numMesa);
        }
        lv.setOnItemLongClickListener((adapterView, view, i, l) -> {
            esta.removeComida(i);
            listView();
            return false;
        });

        HashMap<String,String> alert =((GlobalVariables) this.getApplication()).getAlertas();
        if (alert.containsKey(esta.getEmpleado())){
            Toast.makeText(this, "El camarero: "+alert.get(esta.getEmpleado()) +" ha añadido comida", Toast.LENGTH_SHORT).show();
            ((GlobalVariables) this.getApplication()).removeAlerta(esta.getEmpleado());
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

            case R.id.action_reload:
                listView();
                break;
        }


        return super.onOptionsItemSelected(item);
    }

    public void actualizarTotal(){
        TextView tvTotal = findViewById(R.id.tv_total);
        double precioTotal = 0;
        for (Object ol : esta.getComida()){
            if (ol instanceof Plato){
                Plato plato = (Plato) ol;
                precioTotal += Double.parseDouble(plato.getPrecio());
            } else if (ol instanceof Bebida){
                Bebida bebida = (Bebida) ol;
                precioTotal += Double.parseDouble(bebida.getPrecio());
            }
        }
        tvTotal.setText("TOTAL: "+precioTotal+"€");
    }


    public void listView(){

        AdapterTicket adapterTicket = new AdapterTicket(this, esta.getComida());
        lv.setAdapter(adapterTicket);
        adapterTicket.notifyDataSetChanged();
        actualizarTotal();
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
                Plato editado = buscarPlato(data.getStringExtra("nombre_plato"));
                editado.setIngredientes(data.getStringArrayListExtra("ingredientes"));
                esta.addComida(editado);
            }else if (resultCode == RESULT_FIRST_USER){
                Bebida bebida = buscarBebida(data.getStringExtra("nombre_bebida"));
                esta.addComida(bebida);
            }
            listView();
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

    //Metodo para saber que bebida es de la base de datos
    public Bebida buscarBebida(String obejtoPulsado){
        for(Bebida b : ((GlobalVariables) this.getApplication()).getBebidas()){
            if (obejtoPulsado.equals(b.getNombre())){
                return b;
            }
        }
        return null;
    }

    public void enviarCocina(View view){
        ((GlobalVariables) this.getApplication()).addPedido(esta.getNumeroMesa(),esta.getComida());
        ArrayList<Object> pedidoVacio = new ArrayList<>();
        esta.setComida(pedidoVacio);
        listView();
        if (!camareroName.equals(esta.getEmpleado())){
            ((GlobalVariables) this.getApplication()).addAlerta(esta.getEmpleado(),camareroName);
        }
        Toast.makeText(this, "Se ha enviado el pedido a cocina", Toast.LENGTH_SHORT).show();
    }

    public void enviarACaja(View view){
       Intent i = new Intent(this,VistaTicket.class);
       i.putExtra("nuemrodemesa",esta.getNumeroMesa());
       startActivity(i);
    }

}