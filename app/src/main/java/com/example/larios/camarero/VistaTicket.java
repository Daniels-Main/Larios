package com.example.larios.camarero;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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
import java.util.List;
import java.util.Objects;

public class VistaTicket extends AppCompatActivity {

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
        setContentView(R.layout.activity_vista_ticket);

        Intent i = getIntent();
        int idMesa = i.getIntExtra("nuemrodemesa",0);
        String numMesa;
        switch (idMesa){
            case R.id.imageButton:
                numMesa = "Mesa 1";
                break;
            case R.id.imageButton2:
                numMesa = "Mesa 2";
                break;
            case R.id.imageButton3:
                numMesa = "Mesa 3";
                break;
            case R.id.imageButton4:
                numMesa = "Mesa 4";
                break;
            case R.id.imageButton5:
                numMesa = "Mesa 5";
                break;
            case R.id.imageButton6:
                numMesa = "Mesa 6";
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + idMesa);
        }
        TextView ad = findViewById(R.id.numeroDeMesa);
        ad.setText(numMesa);

        try{
            ListView lv = findViewById(R.id.listV);

            if (((GlobalVariables) this.getApplication()).getCocina().containsKey(idMesa)){
                List<Object> apet = ((GlobalVariables) this.getApplication()).getCocina().get(idMesa);
                if (!apet.isEmpty()){
                    AdapterTicket adapterTicket = new AdapterTicket(this,apet);
                    lv.setAdapter(adapterTicket);

                    actualizarTotal((ArrayList<Object>) apet);
                }else{
                    Toast.makeText(this, "No han pasado platos por cocina", Toast.LENGTH_SHORT).show();
                }
            }

        }catch (Exception e){
            Toast.makeText(this, "No han pasado platos por cocina", Toast.LENGTH_SHORT).show();
        }

    }

    public void volverTick(View v){
        finish();
    }

    public void actualizarTotal(ArrayList<Object> arrayList){
        TextView tvTotal = findViewById(R.id.precioTotalTick);
        double precioTotal = 0;
        for (Object ol : arrayList){
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
}