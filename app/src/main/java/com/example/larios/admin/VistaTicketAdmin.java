package com.example.larios.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.example.larios.GlobalVariables;
import com.example.larios.R;
import com.example.larios.camarero.AdapterTicket;
import com.example.larios.comidasybebidas.Bebida;
import com.example.larios.comidasybebidas.Plato;

import java.util.ArrayList;
import java.util.List;

public class VistaTicketAdmin extends AppCompatActivity {
    ListView lv;
    int idMesa;
    List<Object> apet;
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
        setContentView(R.layout.activity_vista_ticket_admin);
        Intent i = getIntent();
        idMesa = i.getIntExtra("numMesa",0);
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
            lv = findViewById(R.id.listV);

            if (((GlobalVariables) this.getApplication()).getCocina().containsKey(idMesa)){
                apet = ((GlobalVariables) this.getApplication()).getCocina().get(idMesa);
                if (!apet.isEmpty()){
                    AdapterTicket adapterTicket = new AdapterTicket(this,apet);
                    lv.setAdapter(adapterTicket);

                    actualizarTotal();
                }else{
                    Toast.makeText(this, "No han pasado platos por cocina", Toast.LENGTH_SHORT).show();
                }
            }
            lv.setOnItemClickListener((adapterView, view, i1, l) -> {
                Object a = apet.get(i1);
                LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
                View popupView = inflater.inflate(R.layout.cambiar_precio, null);

                int width = LinearLayout.LayoutParams.WRAP_CONTENT;
                int height = LinearLayout.LayoutParams.WRAP_CONTENT;
                boolean focusable = true;
                final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

                popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

                Button visto = popupView.findViewById(R.id.button_send);
                visto.setOnClickListener(view1 -> {
                    EditText msg = popupView.findViewById(R.id.et_price);

                    if (a instanceof Plato){
                        Plato plato = (Plato) a;
                        plato.setPrecio(msg.getText().toString());
                        apet.set(i1,plato);
                    } else {
                        Bebida bebida = (Bebida) a;
                        bebida.setPrecio(msg.getText().toString());
                        apet.set(i1,bebida);
                    }
                    popupWindow.dismiss();
                    listView();
                });



            });

        }catch (Exception e){
            Toast.makeText(this, "No han pasado platos por cocina", Toast.LENGTH_SHORT).show();
        }

    }
    public void listView(){
        AdapterTicket adapterTicket = new AdapterTicket(this, apet);
        lv.setAdapter(adapterTicket);
        adapterTicket.notifyDataSetChanged();
        actualizarTotal();
    }

    public void actualizarTotal(){
        TextView tvTotal = findViewById(R.id.precioTotalTick);
        double precioTotal = 0;
        for (Object ol : apet){
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


    public void volverTick(View view) {
        finish();
    }

    public void envairaca(View view) {
        ((GlobalVariables) this.getApplication()).getCocina().remove(idMesa);
        Toast.makeText(this, "Enviado a caja", Toast.LENGTH_SHORT).show();
    }
}