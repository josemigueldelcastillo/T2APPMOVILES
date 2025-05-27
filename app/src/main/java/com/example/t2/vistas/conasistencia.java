package com.example.t2.vistas;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.t2.R;
import com.example.t2.controller.AsistenciaController;
import com.example.t2.controller.CursoController;
import com.example.t2.modelo.Asistencia;
import com.example.t2.modelo.Curso;

import java.util.ArrayList;

public class conasistencia extends AppCompatActivity {

    Button btnVolver;

    private ListView listaAsistencias;

    private AsistenciaController act = new AsistenciaController(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_conasistencia);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnVolver = findViewById(R.id.btn_regresar_asistencia);

        listaAsistencias = findViewById(R.id.lv_asistencia);

        cargarListaAsistencia();

        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(conasistencia.this, menudocentes.class);
                startActivity(intent);
            }
        });

    }

    private void cargarListaAsistencia() {
        ArrayList<Asistencia> lista = act.MostrarAsistencia();

        ArrayAdapter<Asistencia> adaptador = new ArrayAdapter<Asistencia>(this, R.layout.item_asistencia, lista) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                LayoutInflater inflater = LayoutInflater.from(getContext());
                View itemView = convertView != null ? convertView : inflater.inflate(R.layout.item_asistencia, parent, false);

                Asistencia asistencia = getItem(position);

                TextView AsisPrinc = itemView.findViewById(R.id.txt_AsisPrinc);
                TextView AsisSec = itemView.findViewById(R.id.txt_AsisSec);

                AsisPrinc.setText("| ID Curso: " + asistencia.getIdAsistencia() + "| ID Matricula: " + asistencia.getIdMatricula());
                AsisSec.setText("| Fecha: " + asistencia.getFecha() + "| Estado: " + asistencia.getEstado());

                return itemView;
            }
        };

        listaAsistencias.setAdapter(adaptador);
    }
}