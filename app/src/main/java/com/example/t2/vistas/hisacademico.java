package com.example.t2.vistas;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.t2.R;
import com.example.t2.modelo.Alumno;
import com.example.t2.modelo.reportes.Historial;
import com.example.t2.negocio.nAlumno;
import com.example.t2.negocio.nMatricula;
import com.example.t2.vistas.adapter.HistorialAdapter;

import java.util.ArrayList;

public class hisacademico extends AppCompatActivity {
    Button btnRegresarHA, btnVerHistorial;
    EditText dni, anioini, aniofin;
    ListView listaHistorial;

    nMatricula negocio;
    nAlumno fun;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_hisacademico);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnRegresarHA = findViewById(R.id.btn_regresar_estudiante2);
        btnVerHistorial = findViewById(R.id.btn_buscar_estudiante2);
        dni = findViewById(R.id.text_buscar_estudiante2);
        anioini = findViewById(R.id.txt_año_inicio);
        aniofin = findViewById(R.id.txt_año_fin);
        listaHistorial = findViewById(R.id.lv_regestudiante);

        btnRegresarHA.setOnClickListener(v-> finish());

        negocio = new nMatricula(this);
        fun = new nAlumno(this);

        btnVerHistorial.setOnClickListener(v -> {
            String dniAlumno = dni.getText().toString();
            int anioInicio = Integer.parseInt(anioini.getText().toString());
            int anioFin = Integer.parseInt(aniofin.getText().toString());

            ArrayList<Historial> lista = negocio.obtenerHistorialAcademico(dniAlumno, anioInicio, anioFin);
            ArrayList<Alumno> alumno = fun.BuscarDNI(dniAlumno);

            if (lista.isEmpty()) {
                Toast.makeText(this, "No se encontraron registros", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "Se encontro al Alumno: " + alumno.get(0).getNombre() + " " + alumno.get(0).getApellido().toString(), Toast.LENGTH_LONG).show();
            }

            HistorialAdapter adapter = new HistorialAdapter(this, lista);
            listaHistorial.setAdapter(adapter);
        });
    }
}