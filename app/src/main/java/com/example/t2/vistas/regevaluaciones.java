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

import com.example.t2.controller.EvaluacionController;
import com.example.t2.modelo.Evaluacion;

import java.security.spec.ECField;
import java.util.ArrayList;

public class regevaluaciones extends AppCompatActivity {

    Button btnVolver;

    private ListView listaEvaluacion;
    private EvaluacionController act = new EvaluacionController(this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_regevaluaciones);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        listaEvaluacion = findViewById(R.id.lv_eva);

        btnVolver = findViewById(R.id.btn_regresar_eva);

        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        cargarListaEvaluacion();
    }

    private void cargarListaEvaluacion() {
        ArrayList<Evaluacion> lista = act.MostrarEvaluacion();

        ArrayAdapter<Evaluacion> adaptador = new ArrayAdapter<Evaluacion>(this, R.layout.item_evaluacion, lista) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                LayoutInflater inflater = LayoutInflater.from(getContext());
                View itemView = convertView != null ? convertView : inflater.inflate(R.layout.item_evaluacion, parent, false);

                Evaluacion evaluacion = getItem(position);


                TextView EvaPrinc = itemView.findViewById(R.id.txt_EvaPrinc);
                TextView EvaSec = itemView.findViewById(R.id.txt_EvaSec);

                EvaPrinc.setText("| ID Evaluación: " + evaluacion.getIdEvaluacion() + "| ID Curso: " + evaluacion.getIdCurso() + "\n | Fecha: " + evaluacion.getFechaEvaluacion());
                EvaSec.setText("| ID Matricula: " + evaluacion.getIdMatricula() + "\n | Nota: " + evaluacion.getNota() + "\n | Bimestre: " + evaluacion.getBimestre());

                return itemView;
            }
        };

        listaEvaluacion.setAdapter(adaptador);
    }
}