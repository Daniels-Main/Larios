package com.example.larios;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.UnderlineSpan;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;

import com.example.larios.comidasybebidas.CargarDelXML;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        ((GlobalVariables) this.getApplication()).inicializar();
        ((GlobalVariables) this.getApplication()).inicializarMensajes();
        CargarDelXML a = new CargarDelXML();
        try {
            a.TodasBebidas();
            a.TodosPlatos();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void iniciar(View view){
        Intent intent = new Intent(this,EnterLayout.class);
        startActivity(intent);
    }


}