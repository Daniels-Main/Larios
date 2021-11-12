package com.example.larios;

import androidx.annotation.ColorRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Mesas extends AppCompatActivity {
    private ArrayList<ObjetoMesa> om;
    private String userName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_mesas);
        Intent intent = getIntent();
        userName = intent.getStringExtra(EnterLayout.USER_MESSAGE);

        om = ((GlobalVariables) this.getApplication()).getSomeVariable();

        validarColores();
    }

    private void validarColores(){
        for (ObjetoMesa oj : om){
            ImageButton im = findViewById(oj.getNumeroMesa());
            if (userName.equals(oj.getEmpleado())){
                im.setBackgroundColor(Color.BLUE);
            }else{
                im.setBackgroundColor(Color.RED);
            }
        }
    }

    public void empezar(View view){
        ImageButton imageButton = findViewById(view.getId());
        try {
            ColorDrawable drawable = (ColorDrawable) imageButton.getBackground();
            int colorId = drawable.getColor();


            if (colorId == Color.RED) {
                //Añadir algo a la cuenta
            } else if (colorId == Color.BLUE) {
                Intent intent = new Intent(this, InterfazPlatos.class);
                startActivity(intent);
            }
        }catch (Exception e){
            TextView textView = findViewById(R.id.textView3);
            textView.setText("Esta mesa no tiene ningún camarero");
        }
    }

    public void ayuda(View view){
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.escribir_mensaje, null);

        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true; // lets taps outside the popup also dismiss it
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

        popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

        Button visto = popupView.findViewById(R.id.button_send);
        visto.setOnClickListener(view1 -> {
            EditText msg = popupView.findViewById(R.id.et_mensaje);
            Mensaje mensaje = new Mensaje(userName,msg.getText().toString());
            popupWindow.dismiss();
            ((GlobalVariables) this.getApplication()).addM(mensaje);
        });

    }


    public void returnVentanaLogin(View view){
        Intent intent = new Intent(this,EnterLayout.class);
        startActivity(intent);
    }


}