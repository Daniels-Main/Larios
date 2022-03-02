package com.example.larios;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import com.example.larios.comidasybebidas.Bebida;
import com.example.larios.comidasybebidas.CargarDelXML;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Tod0 el codigo de getWindow sirve para quitar la interfaz del movil y que ocupe un buen aspecto
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

    }
    //Este metodo para iniciar se ejecuta al pulsar el boton y carga todas las variables necesatias en unas variables globales
    public void iniciar(View view){
        //Cargo las mesas
        ((GlobalVariables) this.getApplication()).inicializar();
        //Cargo el xml
        CargarDelXML a = new CargarDelXML();
        try {
            ((GlobalVariables) this.getApplication()).setBebidas(a.TodasBebidas(this));
            ((GlobalVariables) this.getApplication()).setPlatos(a.TodosPlatos(this));
        } catch (Exception e) {
            //Un error y saldria de la app
            Log.e("MainActivity","Error al cargar los platos y bebidas");
            finishAffinity();
        }
        //Con esta linea cambio de pantalla
        Intent intent = new Intent(this,EnterLayout.class);
        startActivity(intent);
    }


}