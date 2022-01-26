package com.example.larios.camarero;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.larios.GlobalVariables;
import com.example.larios.R;
import com.example.larios.comidasybebidas.Bebida;
import com.example.larios.comidasybebidas.ListMenuAdapter;
import com.example.larios.comidasybebidas.ObjetoLista;
import com.example.larios.comidasybebidas.Plato;

import java.util.ArrayList;

public class Buscador extends AppCompatActivity {

    SearchView searchView;
    ListView listView;
    ArrayList<ObjetoLista> list;



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
        setContentView(R.layout.activity_buscador);
        searchView = findViewById(R.id.searchView);
        listView = findViewById(R.id.listview);


        list = new ArrayList<>();

        ArrayList<Plato> platos = ((GlobalVariables) this.getApplication()).getPlatos();
        ArrayList<Bebida> bebidas = ((GlobalVariables) this.getApplication()).getBebidas();

        for(Plato plato : platos){
            list.add(new ObjetoLista(plato.getNombre(), plato.getCategoria()));
        }
        for (Bebida b : bebidas){
            list.add(new ObjetoLista(b.getNombre(),b.getCategoria()));
        }
        ListMenuAdapter adapter = new ListMenuAdapter(this, list);
        listView.setAdapter(adapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                adapter.setListMenu(list);
                adapter.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });

        listView.setOnItemClickListener((adapterView, view, i, l) -> {

            ObjetoLista selected = adapter.getListMenu().get(i);
            String donde = "";
            if (buscarBebida(selected.getNombre())==null){
                donde = "plato";
            }else {
                donde = "bebida";
            }


            LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);

            if (donde.equals("plato")){
                Intent intent = new Intent(this, EdicionPlatos.class);
                intent.putExtra("plato",selected.getNombre());
                startActivityForResult(intent, 19);
            }else{
                Toast.makeText(Buscador.this, "Bebida añadida", Toast.LENGTH_SHORT).show();
            }

        });


    }


    public Bebida buscarBebida(String obejtoPulsado){
        for(Bebida b : ((GlobalVariables) this.getApplication()).getBebidas()){
            if (obejtoPulsado.equals(b.getNombre())){
                return b;
            }
        }
        return null;
    }



    public void volver(View view) {
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 19) {
            if (resultCode == RESULT_OK) {
                setResult(RESULT_OK, data);
                finish();
            }else{
                Toast.makeText(this, "El plato no se ha añadido", Toast.LENGTH_SHORT).show();
            }
        }
    }


}
