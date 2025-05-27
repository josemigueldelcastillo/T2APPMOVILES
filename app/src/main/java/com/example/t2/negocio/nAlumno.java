package com.example.t2.negocio;

import android.content.Context;

import com.example.t2.controller.AlumnoController;
import com.example.t2.modelo.Alumno;

import java.util.ArrayList;

public class nAlumno {
    private AlumnoController A;
    private ArrayList<Alumno> lista = new ArrayList<>();
    private ArrayList<Alumno> busqueda = new ArrayList<>();

    public nAlumno(Context context) {
        A = new AlumnoController(context);
    }
    public boolean Insertar(Alumno dato) {
        // Validar campos vacíos
        if (dato.getNombre().trim().isEmpty() ||
                dato.getApellido().trim().isEmpty() ||
                dato.getDni().trim().isEmpty() ||
                dato.getNacionalidad().trim().isEmpty() ||
                dato.getNivel().trim().isEmpty()) {
            return false; // Hay campos vacíos
        }

        // Validar formato del DNI (ejemplo: 8 dígitos)
        if (!dato.getDni().matches("\\d{8}")) {
            return false; // DNI inválido
        }

        // Validar que el nombre y apellido no tengan números
        if (!dato.getNombre().matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+") ||
                !dato.getApellido().matches("[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+")) {
            return false; // Nombre o apellido inválidos
        }

        // Validar nivel
        if (!dato.getNivel().equalsIgnoreCase("Primaria") &&
                !dato.getNivel().equalsIgnoreCase("Secundaria")) {
            return false; // Nivel no reconocido
        }

        // Validar si ya existe un alumno con el mismo DNI
        String filtro = "";
        filtro = dato.getDni().toString().trim();
        ArrayList<Alumno> existentes = A.BuscarAlumno(filtro);
        if (!existentes.isEmpty()) {
            return false; // Ya existe un alumno con ese DNI
        }

        A.InsertarAlumno(dato);
        return true;
    }

    public boolean Actualizar(Alumno dato) {
        // Validaciones similares a Insertar
        if (dato.getIdAlumno() <= 0) return false;
        return Insertar(dato); // Reutiliza validaciones de arriba
    }

    public boolean Eliminar(Alumno dato) {
        if (dato.getIdAlumno() <= 0) return false;
        A.EliminarAlumno(dato);
        return true;
    }

    public ArrayList<Alumno> Listar() {
        return lista = A.MostrarAlumno();
    }

    public ArrayList<Alumno> BuscarDNI(String dato) {
        if (dato == null || dato.trim().isEmpty()) {
            return new ArrayList<>();
        }
        return busqueda = A.BuscarAlumno(dato);
    }
}
