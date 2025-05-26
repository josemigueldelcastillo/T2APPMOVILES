package com.example.t2.vistas;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.t2.R;
import com.example.t2.controller.AlumnoController;
import com.example.t2.modelo.Alumno;

import java.util.ArrayList;

public class regestudiantes extends AppCompatActivity {

    private Button btnVolver, btnAgregar, btnEliminar, btnEditar, btnBuscar, btnLimpiar;
    private EditText nom, ape, numdoc, nacio;
    private Spinner niv;
    private ListView listaEstudiantes;

    private Alumno dato = new Alumno();
    private AlumnoController act = new AlumnoController(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_regestudiantes);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        nom = findViewById(R.id.txt_nombres_estudiante);
        ape = findViewById(R.id.txt_apellidos_estudiante);
        numdoc = findViewById(R.id.txt_dni_ce_estudiante);
        niv = findViewById(R.id.spn_nivel_estudiante);
        nacio = findViewById(R.id.txt_nacionalidad);

        listaEstudiantes = findViewById(R.id.lv_estudiantes);

        String[] niveles = {"Primaria", "Secundaria"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                niveles
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        niv.setAdapter(adapter);

        numdoc.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (s.length() > 8) {
                    nacio.setText("Extranjero");
                } else {
                    nacio.setText("Peruano");
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        btnVolver = findViewById(R.id.btn_regresar_estudiante);

        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnAgregar = findViewById(R.id.btn_agregar_estudiante);

        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String nombre = nom.getText().toString();
                    String apellido = ape.getText().toString();
                    String dnice = numdoc.getText().toString().trim();
                    String nacionalidad = nacio.getText().toString().trim();
                    String nivel = niv.getSelectedItem().toString();

                    // Validaciones simples
                    if (nombre.isEmpty() || apellido.isEmpty() || dnice.isEmpty() || nacionalidad.isEmpty()) {
                        Toast.makeText(getApplicationContext(), "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if (dnice.length() < 8) {
                        Toast.makeText(getApplicationContext(), "El número de documento debe tener al menos 8 caracteres", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    // Crear objeto Alumno e insertar
                    Alumno dato = new Alumno(dnice, nombre, apellido,  nacionalidad, nivel);
                    act.InsertarAlumno(dato);

                    Toast.makeText(getApplicationContext(), "Alumno agregado correctamente", Toast.LENGTH_SHORT).show();

                    // Opcional: limpiar campos
                    nom.setText("");
                    ape.setText("");
                    numdoc.setText("");
                    nacio.setText("");
                    niv.setSelection(0); // Reinicia el spinner

                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Error al insertar alumno: " + e.getMessage(), Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                } finally {
                    cargarListaEstudiantes();
                }

            }
        });
    }

    private void cargarListaEstudiantes() {
        ArrayList<Alumno> lista = act.MostrarAlumno();

        ArrayAdapter<Alumno> adaptador = new ArrayAdapter<Alumno>(this, R.layout.item_alumno, lista) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                LayoutInflater inflater = LayoutInflater.from(getContext());
                View itemView = convertView != null ? convertView : inflater.inflate(R.layout.item_alumno, parent, false);

                Alumno alumno = getItem(position);

                TextView nombreApellido = itemView.findViewById(R.id.txtNombreApellido);
                TextView datosExtra = itemView.findViewById(R.id.txtDatosExtra);

                nombreApellido.setText(alumno.getNombre() + " " + alumno.getApellido());
                datosExtra.setText("| DNI: " + alumno.getDni() + "\n | Nacionalidad: " + alumno.getNacionalidad() + "\n | Nivel: " + alumno.getNivel());

                return itemView;
            }
        };

        listaEstudiantes.setAdapter(adaptador);
    }
}