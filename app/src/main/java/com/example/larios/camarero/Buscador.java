package com.example.larios.camarero;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.larios.GlobalVariables;
import com.example.larios.R;
import com.example.larios.comidasybebidas.Bebida;
import com.example.larios.comidasybebidas.ListMenuAdapter;
import com.example.larios.comidasybebidas.ObjetoLista;
import com.example.larios.comidasybebidas.Plato;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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
            if (buscarPlato(selected.getNombre())==null){
                donde = "bebida";
            }else {
                donde = "plato";
            }


            LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);

            if (donde.equals("plato")){
                View popupView = inflater.inflate(R.layout.pop_up_window_plato, null);

                int width = LinearLayout.LayoutParams.WRAP_CONTENT;
                int height = LinearLayout.LayoutParams.WRAP_CONTENT;
                boolean focusable = true;
                final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);

                popupWindow.showAtLocation(view, Gravity.CENTER, 0, 0);

                Plato plato = buscarPlato(selected.getNombre());
                TextView nombre = popupView.findViewById(R.id.popUp_nombre_comida);
                TextView categoria = popupView.findViewById(R.id.popUp_categoria);
                TextView tipo = popupView.findViewById(R.id.popUp_tipo);
                TextView precio = popupView.findViewById(R.id.popUp_precio);
                nombre.setText(plato.getNombre());
                categoria.setText(plato.getCategoria());
                tipo.setText(plato.getTipo());
                precio.setText(plato.getPrecio()+"€  ");

                List<String> ingredientes = plato.getIngredientes();

                CheckBox verdura = popupView.findViewById(R.id.cb_verdura);
                CheckBox aceituna = popupView.findViewById(R.id.cb_aceitunas);
                CheckBox harina = popupView.findViewById(R.id.cb_harina);
                CheckBox lacteos = popupView.findViewById(R.id.cb_lacteos);
                CheckBox carne = popupView.findViewById(R.id.cb_carne);
                CheckBox pescado = popupView.findViewById(R.id.cb_pescado);
                CheckBox marisco = popupView.findViewById(R.id.cb_marisco);
                CheckBox arroz = popupView.findViewById(R.id.cb_arroz);
                CheckBox pasta = popupView.findViewById(R.id.cb_pasta);
                CheckBox azucar = popupView.findViewById(R.id.cb_azucar);
                CheckBox chocoles = popupView.findViewById(R.id.cb_chocolate);
                CheckBox huevos = popupView.findViewById(R.id.cb_huevo);
                CheckBox frutos_secos = popupView.findViewById(R.id.cb_frutos);

                if (ingredientes.contains("verduras")){
                    verdura.setChecked(true);
                }
                if (ingredientes.contains("aceitunas")){
                    aceituna.setChecked(true);
                }
                if (ingredientes.contains("harina")){
                    harina.setChecked(true);
                }
                if (ingredientes.contains("lacteos")){
                    lacteos.setChecked(true);
                }
                if (ingredientes.contains("carne")){
                    carne.setChecked(true);
                }
                if (ingredientes.contains("pescado")){
                    pescado.setChecked(true);
                }
                if (ingredientes.contains("marisco")){
                    marisco.setChecked(true);
                }
                if (ingredientes.contains("arroz")){
                    arroz.setChecked(true);
                }
                if (ingredientes.contains("pasta")){
                    pasta.setChecked(true);
                }
                if (ingredientes.contains("azúcar")){
                    azucar.setChecked(true);
                }
                if (ingredientes.contains("chocolate")){
                    chocoles.setChecked(true);
                }
                if (ingredientes.contains("huevos")){
                    huevos.setChecked(true);
                }
                if (ingredientes.contains("frutos_secos")){
                    frutos_secos.setChecked(true);
                }

            }else{
                Toast.makeText(Buscador.this, "Bebida añadida", Toast.LENGTH_SHORT).show();
            }



        });


    }

    public Plato buscarPlato(String obejtoPulsado){
        for(Plato p : ((GlobalVariables) this.getApplication()).getPlatos()){
            if (obejtoPulsado.equals(p.getNombre())){
                return p;
            }
        }
        return null;
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


}
