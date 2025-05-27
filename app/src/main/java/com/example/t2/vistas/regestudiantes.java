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
import com.example.t2.negocio.nAlumno;

import java.util.ArrayList;

public class regestudiantes extends AppCompatActivity {

    private Button btnVolver, btnAgregar, btnEliminar, btnEditar, btnBuscar, btnLimpiar;
    private EditText nom, ape, numdoc, nacio, busc;
    private Spinner niv;
    private ListView listaEstudiantes;
    private nAlumno gestorAlumnos;

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
        busc = findViewById(R.id.text_buscar_estudiante);

        listaEstudiantes = findViewById(R.id.lv_estudiantes);
        btnVolver = findViewById(R.id.btn_regresar_estudiante);
        btnAgregar = findViewById(R.id.btn_agregar_estudiante);
        btnBuscar = findViewById(R.id.btn_buscar_estu);
        btnLimpiar = findViewById(R.id.btn_limpiar_estu);

        gestorAlumnos = new nAlumno(this);  // Inicializamos negocio

        String[] niveles = {"Primaria", "Secundaria"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, niveles);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        niv.setAdapter(adapter);
        numdoc.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                nacio.setText(s.length() > 8 ? "Extranjero" : "Peruano");
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
        btnVolver.setOnClickListener(v -> finish());

        btnAgregar.setOnClickListener(v -> agregarAlumno());

        btnBuscar.setOnClickListener(v -> buscarAlumnoPorDNI());

        cargarListaEstudiantes();
    }

    private void agregarAlumno() {
        try {
            String nombre = nom.getText().toString().trim();
            String apellido = ape.getText().toString().trim();
            String dnice = numdoc.getText().toString().trim();
            String nacionalidad = nacio.getText().toString().trim();
            String nivel = niv.getSelectedItem().toString();

            Alumno nuevoAlumno = new Alumno(dnice, nombre, apellido, nacionalidad, nivel);
            boolean insertado = gestorAlumnos.Insertar(nuevoAlumno);

            if (insertado) {
                Toast.makeText(this, "Alumno agregado correctamente", Toast.LENGTH_SHORT).show();
                limpiarCampos();
                cargarListaEstudiantes();
            } else {
                Toast.makeText(this, "Error: Verifica que los datos estén correctos o el alumno ya exista", Toast.LENGTH_LONG).show();
            }

        } catch (Exception e) {
            Toast.makeText(this, "Error al insertar: " + e.getMessage(), Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
    }
    private void limpiarCampos() {
        nom.setText("");
        ape.setText("");
        numdoc.setText("");
        nacio.setText("");
        niv.setSelection(0);
    }


    private void cargarListaEstudiantes() {
        ArrayList<Alumno> lista = gestorAlumnos.Listar();

        ArrayAdapter<Alumno> adaptador = new ArrayAdapter<Alumno>(this, R.layout.item_alumno, lista) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View itemView = LayoutInflater.from(getContext())
                        .inflate(R.layout.item_alumno, parent, false);

                Alumno alumno = getItem(position);

                TextView nombreApellido = itemView.findViewById(R.id.txtNombreApellido);
                TextView datosExtra = itemView.findViewById(R.id.txtDatosExtra);

                nombreApellido.setText(alumno.getNombre() + " " + alumno.getApellido());
                datosExtra.setText("| DNI: " + alumno.getDni() +
                        "\n| Nacionalidad: " + alumno.getNacionalidad() +
                        "\n| Nivel: " + alumno.getNivel());

                return itemView;
            }
        };

        listaEstudiantes.setAdapter(adaptador);
    }

    private void buscarAlumnoPorDNI() {
        String codi = busc.getText().toString().trim();

        // Validación 1: Campo vacío
        if (codi.isEmpty()) {
            Toast.makeText(regestudiantes.this, "Por favor para buscar.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Validación 2: Longitud del DNI y que sea numérico
        if (codi.length() != 8 || !codi.matches("\\d+")) {
            Toast.makeText(regestudiantes.this, "El DNI debe tener 8 dígitos numéricos.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Ejecutar búsqueda
        ArrayList<Alumno> resul = gestorAlumnos.BuscarDNI(codi);

        // Validación 3: No se encontró ningún alumno
        if (resul.isEmpty()) {
            Toast.makeText(regestudiantes.this, "No se encontró ningún alumno con ese DNI.", Toast.LENGTH_SHORT).show();
            listaEstudiantes.setAdapter(null); // Limpiar lista
            return;
        }

        // Mostrar resultados en el ListView
        ArrayAdapter<Alumno> adaptador = new ArrayAdapter<Alumno>(regestudiantes.this, R.layout.item_alumno, resul) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                LayoutInflater inflater = LayoutInflater.from(getContext());
                View itemView = convertView != null ? convertView : inflater.inflate(R.layout.item_alumno, parent, false);

                Alumno alumno = getItem(position);

                TextView nombreApellido = itemView.findViewById(R.id.txtNombreApellido);
                TextView datosExtra = itemView.findViewById(R.id.txtDatosExtra);

                nombreApellido.setText(alumno.getNombre() + " " + alumno.getApellido());
                datosExtra.setText(" | DNI: " + alumno.getDni() + "\n | Nacionalidad: " + alumno.getNacionalidad() + "\n | Nivel: " + alumno.getNivel());

                nom.setText(alumno.getNombre());
                ape.setText(alumno.getApellido());
                numdoc.setText(alumno.getDni());
                //Aqui quiero

                return itemView;
            }
        };

        listaEstudiantes.setAdapter(adaptador);
    }

    private void LimpiarResetear(){
        limpiarCampos();
        cargarListaEstudiantes();
    }
}