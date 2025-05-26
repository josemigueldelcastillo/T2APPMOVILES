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
import com.example.t2.controller.DocenteController;
import com.example.t2.modelo.Docente;

import java.util.ArrayList;

public class regdocentes extends AppCompatActivity {


    private Button btnVolver, btnAgregar, btnEliminar, btnEditar, btnBuscar, btnLimpiar;
    private EditText nomdoc, apedoc, numdoc, nacio;
    private Spinner niv;
    private ListView listaDocentes;

    private Docente dato = new Docente();
    private DocenteController act = new DocenteController(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_regdocentes);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        nomdoc = findViewById(R.id.txt_nombres_docente);
        apedoc = findViewById(R.id.txt_apellido_docente);
        numdoc = findViewById(R.id.txt_dni_ce_docente);
        nacio = findViewById(R.id.txt_nacionalidad_docente);

        listaDocentes = findViewById(R.id.lv_docentes);

        btnVolver = findViewById(R.id.btn_regresar_docentes);

        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

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

        btnAgregar = findViewById(R.id.btn_agregar_docente);

        btnAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String nombredoc = nomdoc.getText().toString();
                    String apellidodoc = apedoc.getText().toString();
                    String dnicedoc = numdoc.getText().toString().trim();
                    String nacionalidaddoc = nacio.getText().toString().trim();


                    // Validaciones simples
                    if (nombredoc.isEmpty() || apellidodoc.isEmpty() || dnicedoc.isEmpty() || nacionalidaddoc.isEmpty()) {
                        Toast.makeText(getApplicationContext(), "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    if (dnicedoc.length() < 8) {
                        Toast.makeText(getApplicationContext(), "El número de documento debe tener al menos 8 caracteres", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    // Crear objeto Docente e insertar
                    Docente dato = new Docente(dnicedoc, nombredoc, apellidodoc, nacionalidaddoc);
                    act.InsertarDocente(dato);

                    Toast.makeText(getApplicationContext(), "Docente agregado correctamente", Toast.LENGTH_SHORT).show();

                    // Opcional: limpiar campos
                    nomdoc.setText("");
                    apedoc.setText("");
                    numdoc.setText("");
                    nacio.setText("");

                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Error al insertar Docente: " + e.getMessage(), Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                } finally {
                    cargarListaDocente();
                }

            }
        });

    }
    private void cargarListaDocente() {
        ArrayList<Docente> lista = act.MostrarDocente();

        ArrayAdapter<Docente> adaptador = new ArrayAdapter<Docente>(this, R.layout.item_docente, lista) {
            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                LayoutInflater inflater = LayoutInflater.from(getContext());
                View itemView = convertView != null ? convertView : inflater.inflate(R.layout.item_docente, parent, false);

                Docente docente = getItem(position);

                TextView nombreApellido = itemView.findViewById(R.id.txtNombresApellidosDocente);
                TextView datosExtra = itemView.findViewById(R.id.txtDatosExtraDocente);

                nombreApellido.setText(docente.getNombre() + " " + docente.getApellido());
                datosExtra.setText("| DNI: " + docente.getDni() + "\n | Nacionalidad: " + docente.getNacionalidad());

                return itemView;
            }
        };

        listaDocentes.setAdapter(adaptador);
    }
}