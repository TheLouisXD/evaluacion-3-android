package com.example.evaluacion_3;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class CustomAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<MedicamentoModel> medicamentosArrayList;

    public CustomAdapter(Context context, ArrayList<MedicamentoModel> medicamentosArrayList) {
        this.context = context;
        this.medicamentosArrayList = medicamentosArrayList;
    }

    @Override
    public int getCount() {
        return medicamentosArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return medicamentosArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.lv_item, null, true);

            holder.nombreTextView = convertView.findViewById(R.id.nombreTextView);
            holder.cantidadTextView = convertView.findViewById(R.id.cantidadTextView);
            holder.fechaVencimientoTextView = convertView.findViewById(R.id.fechaVencimientoTextView);
            holder.miligramosTextView = convertView.findViewById(R.id.miligramosTextView);
            holder.presentacionTextView = convertView.findViewById(R.id.presentacionTextView);
            holder.descripcionTextView = convertView.findViewById(R.id.descripcionTextView);

            convertView.setTag(holder);
        }else{
            holder = (ViewHolder) convertView.getTag();
        }

        holder.nombreTextView.setText("Nombre: " + medicamentosArrayList.get(position).getNombre());
        holder.cantidadTextView.setText("Cantidad: " + medicamentosArrayList.get(position).getCantidad());
        holder.fechaVencimientoTextView.setText("Fecha de Vencimiento: " + medicamentosArrayList.get(position).getFechaVencimiento());
        holder.miligramosTextView.setText("Miligramos: " + medicamentosArrayList.get(position).getMiligramos());
        holder.presentacionTextView.setText("Presentación: " + medicamentosArrayList.get(position).getPresentacion());
        holder.descripcionTextView.setText("Descripción: " + medicamentosArrayList.get(position).getDescripcion());

        return convertView;
    }

    private class ViewHolder {
        protected TextView nombreTextView, cantidadTextView, fechaVencimientoTextView, miligramosTextView, presentacionTextView, descripcionTextView;
    }
}
