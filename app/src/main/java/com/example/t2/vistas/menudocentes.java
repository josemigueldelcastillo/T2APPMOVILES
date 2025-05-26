package com.example.t2.vistas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.t2.R;

public class menudocentes extends AppCompatActivity {

    Button btnVolver, btnRegistrar,btnMatri, btnCursos, btnRegEva, btnAsistencia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_menudocentes);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnRegistrar = findViewById(R.id.btn_registro_docente);
        btnMatri = findViewById(R.id.btn_matri_alumnos);
        btnCursos = findViewById(R.id.btn_gestion_cursos);
        btnRegEva = findViewById(R.id.btn_regis_eva);
        btnAsistencia = findViewById(R.id.btn_control_asistencia);
        btnVolver = findViewById(R.id.btn_volver_docente);

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(menudocentes.this, regdocentes.class);
                startActivity(intent);
            }
        });

        btnMatri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(menudocentes.this, matestudiantes.class);
                startActivity(intent);
            }
        });

        btnCursos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(menudocentes.this, gescursos.class);
                startActivity(intent);
            }
        });

        btnRegEva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(menudocentes.this, regevaluaciones.class);
                startActivity(intent);
            }
        });

        btnAsistencia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(menudocentes.this, conasistencia.class);
                startActivity(intent);
            }
        });

        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });



    }
}