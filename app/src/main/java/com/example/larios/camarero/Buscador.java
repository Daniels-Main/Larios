package com.example.larios.camarero;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
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
    }

    public void volver(View view) {
        finish();
    }
}
