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
import com.example.t2.controller.AsignacionCursoController;
import com.example.t2.controller.AsistenciaController;
import com.example.t2.modelo.AsignacionCurso;
import com.example.t2.modelo.Asistencia;

import java.util.ArrayList;

public class asignarCurso extends AppCompatActivity {

    Button btnVolver;

    private ListView listaAsigCurso;

    private AsignacionCursoController act = new AsignacionCursoController(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_asignar_curso);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnVolver = findViewById(R.id.btn_regresar_asigcurso);

        listaAsigCurso = findViewById(R.id.lv_asignarcurso);

        cargarListaAsigCurso();

        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(asignarCurso.this, gescursos.class);
                startActivity(intent);
            }
        });
    }

    private void cargarListaAsigCurso() {
        ArrayList<AsignacionCurso> lista = act.MostrarAsignacionCurso();

        ArrayAdapter<AsignacionCurso> adaptador = new ArrayAdapter<AsignacionCurso>(this, R.layout.item_asigcurso, lista) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                LayoutInflater inflater = LayoutInflater.from(getContext());
                View itemView = convertView != null ? convertView : inflater.inflate(R.layout.item_asigcurso, parent, false);

                AsignacionCurso asignacioncurso = getItem(position);

                TextView AsisPrinc = itemView.findViewById(R.id.txt_AsigPrinc);
                TextView AsisSec = itemView.findViewById(R.id.txt_AsigSec);

                AsisPrinc.setText("| ID Asignacion Curso: " + asignacioncurso.getIdAsignacion() + "| ID Curso: " + asignacioncurso.getIdCurso() + "| ID Docente principal: " + asignacioncurso.getIdDocentePrincipal());
                AsisSec.setText("| ID Docente Auxiliar: " + asignacioncurso.getIdDocenteAuxiliar() + "| Seccion: " + asignacioncurso.getIdSeccion() + "| Año Electivo: " + asignacioncurso.getAnioLectivo());

                return itemView;
            }
        };

        listaAsigCurso.setAdapter(adaptador);
    }
}