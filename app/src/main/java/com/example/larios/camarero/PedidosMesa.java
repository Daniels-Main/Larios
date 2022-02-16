package com.example.larios.camarero;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.TextView;

import com.example.larios.GlobalVariables;
import com.example.larios.R;

public class PedidosMesa extends AppCompatActivity {

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
        setContentView(R.layout.activity_pedidos_mesa);

        Intent i = getIntent();
        int idMesa = i.getIntExtra("numMesa",0);
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
        ad.setTextSize(26);

        ListView lv = findViewById(R.id.listV);
        AdapterTicket adapterTicket = new AdapterTicket(this,((GlobalVariables) this.getApplication()).getCocina().get(idMesa));
        lv.setAdapter(adapterTicket);

    }

    public void volta(View view){
        finish();
    }
}