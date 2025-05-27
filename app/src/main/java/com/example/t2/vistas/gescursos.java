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
import com.example.t2.controller.CursoController;
import com.example.t2.controller.MatriculaController;
import com.example.t2.modelo.Curso;
import com.example.t2.modelo.Docente;

import java.util.ArrayList;

public class gescursos extends AppCompatActivity {

    Button btnVolver, BtnAsigCursos;

    private ListView listaCursos;

    private CursoController act = new CursoController(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_gescursos);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnVolver = findViewById(R.id.btn_regresar_cursos);
        BtnAsigCursos = findViewById(R.id.btn_asignar_curso);

        listaCursos = findViewById(R.id.lv_cursos);

        cargarListaCursos();

        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(gescursos.this, menudocentes.class);
                startActivity(intent);
            }
        });



        BtnAsigCursos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(gescursos.this, asignarCurso.class);
                startActivity(intent);
            }
        });


    }

    private void cargarListaCursos() {
        ArrayList<Curso> lista = act.MostrarCurso();

        ArrayAdapter<Curso> adaptador = new ArrayAdapter<Curso>(this, R.layout.item_curso, lista) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                LayoutInflater inflater = LayoutInflater.from(getContext());
                View itemView = convertView != null ? convertView : inflater.inflate(R.layout.item_curso, parent, false);

                Curso curso = getItem(position);

                TextView CurPrin = itemView.findViewById(R.id.txt_CurPrin);
                TextView CurSec = itemView.findViewById(R.id.txt_CurSec);

                CurPrin.setText("| ID Curso: " + curso.getIdCurso() + "| Nombre: " + curso.getNombreCurso());
                CurSec.setText("| Nivel: " + curso.getNivel());

                return itemView;
            }
        };

        listaCursos.setAdapter(adaptador);
    }
}