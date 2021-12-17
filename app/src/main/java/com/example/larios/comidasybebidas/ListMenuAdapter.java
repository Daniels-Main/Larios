package com.example.larios.comidasybebidas;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.TextView;

import com.example.larios.R;

import java.text.Normalizer;
import java.util.ArrayList;
import java.util.List;

public class ListMenuAdapter extends BaseAdapter {

    Context context;

    protected List<ObjetoLista> listMenu;
    LayoutInflater inflater;

    public ListMenuAdapter(Context context, List<ObjetoLista> listMenu){
        this.context = context;
        this.inflater = LayoutInflater.from(context);
        this.listMenu = listMenu;
    }

    public void setListMenu(List<ObjetoLista> listMenu){
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
        ViewHolder holder;
        if (view == null) {

            holder = new ViewHolder();
            view = this.inflater.inflate(R.layout.list_menu,viewGroup, false);

            holder.txtName = (TextView) view.findViewById(R.id.txt_name);
            holder.txtCategoria = (TextView) view.findViewById(R.id.txt_category);


            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }

        ObjetoLista ol = listMenu.get(i);
        holder.txtName.setText(ol.getNombre());
        holder.txtCategoria.setText(ol.getCategoria());

        return view;
    }
    private class ViewHolder {
        TextView txtName;
        TextView txtCategoria;
    }

    public static String cleanString(String texto) {
        texto = Normalizer.normalize(texto, Normalizer.Form.NFD);
        texto = texto.replaceAll("[\\p{InCombiningDiacriticalMarks}]", "");
        return texto;
    }

    public Filter getFilter() {
        Filter filter = new Filter() {

            @SuppressWarnings("unchecked")
            @Override
            protected void publishResults(CharSequence constraint, FilterResults results) {
                listMenu= (List<ObjetoLista>) results.values;
                notifyDataSetChanged();
            }

            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                FilterResults results = new FilterResults();
                ArrayList<ObjetoLista> FilteredList= new ArrayList<>();
                if (constraint == null || constraint.length() == 0 || constraint.toString().equalsIgnoreCase("todo")) {
                    results.values = listMenu;
                    results.count = listMenu.size();
                }
                else {
                    for (int i = 0; i < listMenu.size(); i++) {
                        ObjetoLista data = listMenu.get(i);
                        if (data.getNombre().toLowerCase().contains(constraint.toString().toLowerCase()) || cleanString(data.getNombre()).toLowerCase().contains(constraint.toString().toLowerCase())) {
                            FilteredList.add(data);
                        }else if (data.getCategoria().toLowerCase().contains(constraint.toString().toLowerCase())){
                            FilteredList.add(data);
                        }
                    }
                    results.values = FilteredList;
                    results.count = FilteredList.size();
                }
                return results;
            }
        };
        return filter;
    }
}
