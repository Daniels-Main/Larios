package com.example.larios.camarero;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;

import com.example.larios.GlobalVariables;
import com.example.larios.R;
import com.example.larios.comidasybebidas.Plato;

import java.util.ArrayList;
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


        //Recojo el nombre del plato de la pantalla anterior
        Intent i = getIntent();

        //Y lo pongo tod0 en esta pantalla de edición
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


        //Cancelar vuelve al buscador
        Button cancelar = findViewById(R.id.button_cancel);
        cancelar.setOnClickListener(view1 -> {
            finish();
        });

        //Aceptar vuelve a la InterfazMesa
        Button accept = findViewById(R.id.button_acept);
        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ArrayList<String> ingredientesBasura = new ArrayList<>();

                //Comprovando ingredientes
                if (verdura.isChecked())
                    ingredientesBasura.add("verduras");
                if (aceituna.isChecked())
                    ingredientesBasura.add("aceitunas");
                if (harina.isChecked())
                    ingredientesBasura.add("harina");
                if (lacteos.isChecked())
                    ingredientesBasura.add("lacteos");
                if (carne.isChecked())
                    ingredientesBasura.add("carne");
                if (pescado.isChecked())
                    ingredientesBasura.add("pescado");
                if (marisco.isChecked())
                    ingredientesBasura.add("mariscos");
                if (arroz.isChecked())
                    ingredientesBasura.add("arroz");
                if (pasta.isChecked())
                    ingredientesBasura.add("pasta");
                if (azucar.isChecked())
                    ingredientesBasura.add("azucar");
                if (chocoles.isChecked())
                    ingredientesBasura.add("chocolate");
                if (huevos.isChecked())
                    ingredientesBasura.add("huevos");
                if (frutos_secos.isChecked())
                    ingredientesBasura.add("frutos_secos");



                Intent a = new Intent();
                a.putExtra("nombre_plato",plato.getNombre());
                a.putExtra("ingredientes",ingredientesBasura);
                setResult(RESULT_OK, a);
                finish();
            }
        });

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