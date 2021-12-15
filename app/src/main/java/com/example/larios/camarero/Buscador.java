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
import com.example.larios.comidasybebidas.Plato;

import java.util.ArrayList;

public class Buscador extends AppCompatActivity {

    SearchView searchView;
    ListView listView;
    ArrayList<String> list;
    ArrayAdapter<String > adapter;

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
            list.add(plato.getNombre());
        }
        for (Bebida b : bebidas){
            list.add(b.getNombre());
        }
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,list);
        listView.setAdapter(adapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                if (list.contains(query)) {
                    adapter.getFilter().filter(query);
                } else {
                    Toast.makeText(Buscador.this, "No se ha encontrado el plato", Toast.LENGTH_LONG).show();
                }
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
