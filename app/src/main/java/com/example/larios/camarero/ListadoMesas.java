package com.example.larios.camarero;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.larios.GlobalVariables;
import com.example.larios.ObjetoMesa;
import com.example.larios.R;
import com.example.larios.comidasybebidas.ObjetoLista;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;

public class ListadoMesas extends AppCompatActivity {

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
        setContentView(R.layout.activity_listado_mesas);



        HashMap<Integer, ArrayList<Object>> comida = ((GlobalVariables) this.getApplication()).getCocina();


        for (Integer key : comida.keySet()){
            ImageButton im = findViewById(key);
            im.setBackgroundColor(Color.GREEN);


            im.setOnClickListener(view -> {
                Intent intent = new Intent(ListadoMesas.this,PedidosMesa.class);
                intent.putExtra("numMesa",key);
                startActivity(intent);
            });

        }


    }



    public void returna(View view){
        finish();
    }
}