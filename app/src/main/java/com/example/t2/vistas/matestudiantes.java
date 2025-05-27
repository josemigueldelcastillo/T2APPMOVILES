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
import com.example.t2.controller.MatriculaController;
import com.example.t2.modelo.Evaluacion;
import com.example.t2.modelo.Matricula;

import java.util.ArrayList;

public class matestudiantes extends AppCompatActivity {

    Button btnVolver;

    private ListView listaMatricula;

    private MatriculaController act = new MatriculaController(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_matestudiantes);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        listaMatricula = findViewById(R.id.lv_matestudiante);
        btnVolver = findViewById(R.id.btn_regresar_matricula);

        cargarListaMAtricula();

        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void cargarListaMAtricula() {
        ArrayList<Matricula> lista = act.MostrarMatricula();

        ArrayAdapter<Matricula> adaptador = new ArrayAdapter<Matricula>(this, R.layout.item_matricula, lista) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                LayoutInflater inflater = LayoutInflater.from(getContext());
                View itemView = convertView != null ? convertView : inflater.inflate(R.layout.item_matricula, parent, false);

                Matricula matricula = getItem(position);


                TextView MatPrinc = itemView.findViewById(R.id.txt_MatPrinc);
                TextView MatSec = itemView.findViewById(R.id.txt_MatSec);

                MatPrinc.setText("| ID Matricula: " + matricula.getIdMatricula() + "| ID Alumno: " + matricula.getIdAlumno());
                MatSec.setText("| ID Seccion: " + matricula.getIdSeccion() + "\n | Fecha: " + matricula.getFechaMatricula() + "\n | Año Lectivo: " + matricula.getAnioLectivo());

                return itemView;
            }
        };

        listaMatricula.setAdapter(adaptador);
    }
}