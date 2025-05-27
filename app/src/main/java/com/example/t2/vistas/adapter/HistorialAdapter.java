package com.example.t2.vistas.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.t2.R;
import com.example.t2.modelo.reportes.Historial;

import java.util.List;

public class HistorialAdapter extends ArrayAdapter<Historial> {
    private Context context;
    private List<Historial> historialList;

    public HistorialAdapter(Context context, List<Historial> historialList) {
        super(context, 0, historialList);
        this.context = context;
        this.historialList = historialList;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Historial h = historialList.get(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_historial, parent, false);
        }

        TextView txtCurso = convertView.findViewById(R.id.txtNombreCurso);
        TextView txtProm = convertView.findViewById(R.id.txtPromedio);
        TextView txtAnio = convertView.findViewById(R.id.txtAnio);

        txtCurso.setText("Curso: " + h.getNombreCurso());
        txtProm.setText("Promedio: " + String.format("%.2f", h.getPromedio()));
        txtAnio.setText("Año: " + h.getAnioLectivo());

        return convertView;
    }
}
