package com.example.larios;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.larios.camarero.Buscador;
import com.example.larios.comidasybebidas.Plato;

import java.util.List;

public class EdicionPlatos extends AppCompatActivity {

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
        setContentView(R.layout.activity_edicion_platos);

        Intent i = getIntent();

        String nombre1 = i.getStringExtra("plato");
        Plato plato = buscarPlato(nombre1);
        TextView nombre = findViewById(R.id.popUp_nombre_comida);
        TextView categoria = findViewById(R.id.popUp_categoria);
        TextView tipo = findViewById(R.id.popUp_tipo);
        TextView precio = findViewById(R.id.popUp_precio);
        nombre.setText(plato.getNombre());
        categoria.setText(plato.getCategoria());
        tipo.setText(plato.getTipo());
        precio.setText(plato.getPrecio()+"€  ");

        List<String> ingredientes = plato.getIngredientes();

        CheckBox verdura = findViewById(R.id.cb_verdura);
        CheckBox aceituna = findViewById(R.id.cb_aceitunas);
        CheckBox harina = findViewById(R.id.cb_harina);
        CheckBox lacteos = findViewById(R.id.cb_lacteos);
        CheckBox carne = findViewById(R.id.cb_carne);
        CheckBox pescado = findViewById(R.id.cb_pescado);
        CheckBox marisco = findViewById(R.id.cb_marisco);
        CheckBox arroz = findViewById(R.id.cb_arroz);
        CheckBox pasta = findViewById(R.id.cb_pasta);
        CheckBox azucar = findViewById(R.id.cb_azucar);
        CheckBox chocoles = findViewById(R.id.cb_chocolate);
        CheckBox huevos = findViewById(R.id.cb_huevo);
        CheckBox frutos_secos = findViewById(R.id.cb_frutos);

        if (ingredientes.contains("verduras")){
            verdura.setChecked(true);
        }
        if (ingredientes.contains("aceitunas")){
            aceituna.setChecked(true);
        }
        if (ingredientes.contains("harina")){
            harina.setChecked(true);
        }
        if (ingredientes.contains("lacteos")){
            lacteos.setChecked(true);
        }
        if (ingredientes.contains("carne")){
            carne.setChecked(true);
        }
        if (ingredientes.contains("pescado")){
            pescado.setChecked(true);
        }
        if (ingredientes.contains("marisco")){
            marisco.setChecked(true);
        }
        if (ingredientes.contains("arroz")){
            arroz.setChecked(true);
        }
        if (ingredientes.contains("pasta")){
            pasta.setChecked(true);
        }
        if (ingredientes.contains("azúcar")){
            azucar.setChecked(true);
        }
        if (ingredientes.contains("chocolate")){
            chocoles.setChecked(true);
        }
        if (ingredientes.contains("huevos")){
            huevos.setChecked(true);
        }
        if (ingredientes.contains("frutos_secos")){
            frutos_secos.setChecked(true);
        }

        Button cancelar = findViewById(R.id.button_cancel);
        cancelar.setOnClickListener(view1 -> {
            finish();
        });

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