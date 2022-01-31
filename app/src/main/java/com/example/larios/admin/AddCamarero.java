package com.example.larios.admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.larios.EnterLayout;
import com.example.larios.GlobalVariables;
import com.example.larios.R;
import com.example.larios.usuarios.UsersDbHelper;

public class AddCamarero extends AppCompatActivity {
    private Toolbar toolbar;
    private String url;
    private EditText editText;
    private Menu mMenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getWindow().getDecorView().setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_FULLSCREEN);

        setContentView(R.layout.activity_add_camarero);
        editText = findViewById(R.id.et_link_avatar);
        toolbar = (Toolbar) findViewById(R.id.tool_bar_camarero);
        setSupportActionBar(toolbar);
    }

    //Tod0 el tema de menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_admin, menu);
        toolbar.setTitle("");
        mMenu = menu;
        return true;
    }
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        if (((GlobalVariables) this.getApplication()).getMensajes().size() > 0){
            mMenu.getItem(3).setIcon(R.drawable.ic_flecha_on);
        }

        return super.onPrepareOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        switch (id){
            case R.id.action_am:
                startActivity(new Intent(this, MesasAdmin.class));
                break;
            case R.id.action_add:
                startActivity(new Intent(this,AddCamarero.class));
                break;
            case R.id.action_ticket:
                startActivity(new Intent(this,MainTickets.class));
                break;
            case R.id.mensajes:
                startActivity(new Intent(this, Mensajes.class));
        }


        return super.onOptionsItemSelected(item);
    }

    public void returnVentanaLogin(View view){
        Intent intent = new Intent(this, EnterLayout.class);
        startActivity(intent);
    }


    //Descarga la imagen de la URL que le hemos pasado y la enseña
    public void subirImagen(View view){
        ImageView imageView = findViewById(R.id.imageView2);
        url = editText.getText().toString();
        new LoadImage(imageView).execute(url);
        imageView.setMaxWidth(140);
        imageView.setMaxHeight(140);
        editText.getText().clear();
    }

    //Genera un usuario y lo guarda en la base de datos
    public void generar(View view){
        EditText user = findViewById(R.id.etNombre);
        EditText contra = findViewById(R.id.id_contra);
        EditText repe = findViewById(R.id.id_repecontra);
        try {
            if (url == null){
                editText.setError("Añade una imagen");
            }else if (user.getText().toString().equals("")){
                user.setError("El nombre no puede estar vacio");
            }else if (contra.getText().toString().equals("")){
                contra.setError("La contraseña no puede estar vacia");
            }else if (contra.getText().toString().equals(repe.getText().toString())){
                UsersDbHelper usersDbHelper = new UsersDbHelper(getBaseContext());
                usersDbHelper.generarUser(user.getText().toString(),contra.getText().toString(),url);
                user.getText().clear();
                contra.getText().clear();
                repe.getText().clear();
                Toast.makeText(this, "Se ha creado el camarero/a", Toast.LENGTH_SHORT).show();
            }else{
                repe.setError("Las contraseñas no coinciden");
            }
        } catch (Exception e){
            e.getMessage();
            user.setError("Ha ocurrido un error");
        }

    }

}