package com.example.larios.camarero;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.larios.R;
import com.example.larios.comidasybebidas.Bebida;
import com.example.larios.comidasybebidas.Plato;

import java.util.List;

public class AdapterTicket extends BaseAdapter {

    Context context;
    protected List<Object> listMenu;
    LayoutInflater inflater;

    public AdapterTicket(Context context, List<Object> listMenu){
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.listMenu = listMenu;
    }

    public void setListMenu(List<Object> listMenu) {
        this.listMenu = listMenu;
    }

    @Override
    public int getCount() {
        return listMenu.size();
    }

    @Override
    public Object getItem(int i) {
        return listMenu.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        AdapterTicket.ViewHolder holder;
        if (view == null) {

            holder = new AdapterTicket.ViewHolder();
            view = this.inflater.inflate(R.layout.row_obj,viewGroup, false);

            holder.txtName = (TextView) view.findViewById(R.id.tv_nombreComida);
            holder.txtTipo = (TextView) view.findViewById(R.id.tv_tipoComida);
            holder.txtPrecio = (TextView) view.findViewById(R.id.tv_precioComida);


            view.setTag(holder);
        } else {
            holder = (AdapterTicket.ViewHolder) view.getTag();
        }

        Object ol = listMenu.get(i);

        if (ol instanceof Plato){
            Plato plato = (Plato) ol;
            holder.txtName.setText(plato.getNombre());
            holder.txtTipo.setText(plato.getTipo());
            if (context.getClass().equals(PedidosMesa.class)){
                holder.txtPrecio.setText("Preparando...");
            }else{
                holder.txtPrecio.setText(plato.getPrecio());
            }


        } else if (ol instanceof Bebida){
            Bebida bebida = (Bebida) ol;
            holder.txtName.setText(bebida.getNombre());
            holder.txtTipo.setText(bebida.getTipo());
            if (context.getClass().equals(PedidosMesa.class)){
                holder.txtPrecio.setText("Preparando...");
            }else{
                holder.txtPrecio.setText(bebida.getPrecio());
            }
        }

        return view;
    }
    private class ViewHolder {
        TextView txtName;
        TextView txtTipo;
        TextView txtPrecio;
    }
}
