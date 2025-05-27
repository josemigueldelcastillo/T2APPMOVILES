package com.example.t2.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

public class datosIniciales extends dJuniorsUPN{
    public datosIniciales(@Nullable Context context) {
        super(context);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        super.onCreate(db);
        insertarDatosIniciales(db);
    }

    private void insertarDatosIniciales(SQLiteDatabase db) {
        // Docentes
        db.execSQL("INSERT INTO Docente (dni, nombre, apellido, nacionalidad) VALUES " +
                "('12345678', 'Juan', 'Pérez', 'Peruana')," +
                "('87654321', 'María', 'Gómez', 'Peruana')");

        // Alumnos
        db.execSQL("INSERT INTO Alumno (dni, nombre, apellido, nacionalidad, nivel) VALUES " +
                "('11223344', 'Carlos', 'Ramírez', 'Peruana', 'Secundaria')," +
                "('14586230', 'Tapia', 'Apaza', 'Extrangero', 'Primaria')," +
                "('**', 'Ana', 'López', 'Peruana', 'Secundaria')");

        // Curso
        db.execSQL("-- Cursos para Primaria\n" +
                "INSERT INTO Curso(nombre_curso) VALUES\n" +
                "('Matemática P1'), ('Comunicación P1'), ('Ciencia P1'), ('Arte P1'), ('Educación Física P1'),\n" +
                "-- Cursos para Secundaria\n" +
                "('Matemática S1'), ('Comunicación S1'), ('Física S1'), ('Historia S1'), ('Inglés S1');\n");

        // Secciones
        db.execSQL("INSERT INTO Seccion (letra, turno) VALUES " +
                "('A', 'Mañana'), ('B', 'Tarde')");

        // Matrícula
        db.execSQL("INSERT INTO Matricula(id_alumno, id_seccion, anio_lectivo, fecha_matricula) VALUES\n" +
                "(1, 1, 2023, '2023-03-01'),\n" +
                "(1, 2, 2024, '2024-03-01'),\n" +
                "(2, 1, 2023, '2023-03-01'),\n" +
                "(3, 2, 2024, '2024-03-01');");

        // Asignación de Cursos
        db.execSQL("INSERT INTO AsignacionCurso(id_curso, id_docente_principal, id_docente_auxiliar, id_seccion, anio_lectivo) VALUES\n" +
                "(1, 1, 3, 1, 2023),\n" +
                "(2, 1, 3, 1, 2023),\n" +
                "(3, 2, 3, 1, 2023),\n" +
                "(4, 2, 3, 1, 2023),\n" +
                "(5, 1, 3, 1, 2023),\n" +
                "(6, 1, 3, 2, 2024),\n" +
                "(7, 2, 3, 2, 2024),\n" +
                "(8, 2, 3, 2, 2024),\n" +
                "(9, 1, 3, 2, 2024),\n" +
                "(10, 1, 3, 2, 2024);");

        // Evaluaciones
        db.execSQL("INSERT INTO Evaluacion(id_matricula, id_curso, bimestre, nota, fecha_evaluacion) VALUES\n" +
                "(1, 1, 1, 14.5, '2023-04-10'),\n" +
                "(1, 1, 2, 15.0, '2023-06-10'),\n" +
                "(1, 1, 3, 16.2, '2023-08-10'),\n" +
                "(1, 1, 4, 17.0, '2023-10-10'),\n" +
                "(1, 2, 1, 13.0, '2023-04-12'),\n" +
                "(1, 2, 2, 14.0, '2023-06-12'),\n" +
                "(1, 2, 3, 14.5, '2023-08-12'),\n" +
                "(1, 2, 4, 15.0, '2023-10-12'),\n" +
                "(1, 3, 1, 12.5, '2023-04-14'),\n" +
                "(1, 3, 2, 13.5, '2023-06-14'),\n" +
                "(1, 3, 3, 14.5, '2023-08-14'),\n" +
                "(1, 3, 4, 15.0, '2023-10-14');");

        db.execSQL(("INSERT INTO Evaluacion(id_matricula, id_curso, bimestre, nota, fecha_evaluacion) VALUES\n" +
                "(2, 6, 1, 15.0, '2024-04-10'),\n" +
                "(2, 6, 2, 16.0, '2024-06-10'),\n" +
                "(2, 6, 3, 17.0, '2024-08-10'),\n" +
                "(2, 6, 4, 18.0, '2024-10-10');"));

        // Asistencias
        db.execSQL("INSERT INTO Asistencia(id_matricula, fecha, estado) VALUES\n" +
                "(1, '2023-03-10', 'Asistencia'),\n" +
                "(1, '2023-03-11', 'Falta'),\n" +
                "(1, '2023-03-12', 'Asistencia'),\n" +
                "(1, '2023-03-13', 'Tardanza'),\n" +
                "(1, '2023-03-14', 'Asistencia'),\n" +
                "(1, '2023-03-15', 'Asistencia'),\n" +
                "(1, '2023-03-16', 'Falta'),\n" +
                "(1, '2023-03-17', 'Tardanza'),\n" +
                "(1, '2023-03-18', 'Asistencia'),\n" +
                "(1, '2023-03-19', 'Asistencia');");
    }
}
