package com.example.larios;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class MensajeAdapter extends ArrayAdapter<Mensaje> {
    private int newResourceId;
    public MensajeAdapter(Context context, int resourceId, List<Mensaje> cityList){
        super(context, resourceId, cityList);
        newResourceId = resourceId;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent){

        Mensaje m = getItem (position);
        View view = LayoutInflater.from (getContext ()).inflate (newResourceId, parent, false);

        TextView username = view.findViewById (R.id.label);
        TextView mensaje = view.findViewById (R.id.subitem);

        username.setText (m.getNombre ());
        mensaje.setText (m.getMensaje ());
        return view;
    }
}
