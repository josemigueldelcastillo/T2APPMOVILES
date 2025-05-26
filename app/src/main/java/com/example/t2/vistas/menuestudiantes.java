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

public class menuestudiantes extends AppCompatActivity {

    Button btnVolver, btnRegAlum,btnHistoAca, btnConLibNotas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_menuestudiantes);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        btnRegAlum = findViewById(R.id.btn_registro_alumnos);
        btnVolver = findViewById(R.id.btn_volver_estudiante);
        btnConLibNotas = findViewById(R.id.btn_conlibrenota);
        btnHistoAca = findViewById(R.id.btn_historialaca);

        btnRegAlum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(menuestudiantes.this, regestudiantes.class);
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
    public void abrirHistorialAcademico(View view) {
        Intent intent = new Intent(this, hisacademico.class);
        startActivity(intent);
    }

    public void abrirLibroNotas(View view) {
        Intent intent = new Intent(this, libnotas.class);
        startActivity(intent);
    }

}