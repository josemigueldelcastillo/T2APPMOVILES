package com.example.t2.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class dJuniorsUPN  extends SQLiteOpenHelper {
    public static final String DB_NAME = "juniors_upn.db";
    public static final int DB_VERSION = 1;

    public static final String tDocente = "Docente";
    public static final String tAlumno = "Alumno";
    public static final String tCurso = "Curso";
    public static final String tSeccion = "Seccion";
    public static final String tMatricula = "Matricula";
    public static final String tAsignacionCurso = "AsignacionCurso";
    public static final String tEvaluacion = "Evaluacion";
    public static final String tAsistencia = "Asistencia";
    public static final String tConducta = "Conducta";

    public dJuniorsUPN(@Nullable Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + tAlumno + "(" +
                "id_alumno INTEGER PRIMARY KEY AUTOINCREMENT," +
                "dni TEXT UNIQUE NOT NULL," +
                "nombre TEXT," +
                "apellido TEXT," +
                "fecha_nacimiento TEXT," +
                "nacionalidad TEXT," +
                "nivel TEXT CHECK(nivel IN ('Primaria','Secundaria')))");

        db.execSQL("CREATE TABLE "+ tDocente + "(" +
                "id_docente INTEGER PRIMARY KEY AUTOINCREMENT," +
                "dni TEXT UNIQUE NOT NULL," +
                "nombre TEXT," +
                "apellido TEXT," +
                "nacionalidad TEXT)");

        db.execSQL("CREATE TABLE " + tCurso + "(" +
                "id_curso INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombre_curso TEXT NOT NULL," +
                "nivel TEXT CHECK(nivel IN ('Primaria','Secundaria')))");

        db.execSQL("CREATE TABLE " + tSeccion + "(" +
                "id_seccion INTEGER PRIMARY KEY AUTOINCREMENT," +
                "letra TEXT CHECK(letra IN ('A','B','C'))," +
                "turno TEXT CHECK(turno IN ('Mañana','Tarde')))");

        db.execSQL("CREATE TABLE " + tMatricula + "(" +
                "id_matricula INTEGER PRIMARY KEY AUTOINCREMENT," +
                "id_alumno INTEGER," +
                "id_seccion INTEGER," +
                "anio_lectivo INTEGER," +
                "fecha_matricula TEXT," +
                "FOREIGN KEY(id_alumno) REFERENCES Alumno(id_alumno)," +
                "FOREIGN KEY(id_seccion) REFERENCES Seccion(id_seccion))");

        db.execSQL("CREATE TABLE " + tAsignacionCurso + "(" +
                "id_asignacion INTEGER PRIMARY KEY AUTOINCREMENT," +
                "id_curso INTEGER," +
                "id_docente_principal INTEGER," +
                "id_docente_auxiliar INTEGER," +
                "id_seccion INTEGER," +
                "anio_lectivo INTEGER," +
                "FOREIGN KEY(id_curso) REFERENCES Curso(id_curso)," +
                "FOREIGN KEY(id_docente_principal) REFERENCES Docente(id_docente)," +
                "FOREIGN KEY(id_docente_auxiliar) REFERENCES Docente(id_docente)," +
                "FOREIGN KEY(id_seccion) REFERENCES Seccion(id_seccion))");

        db.execSQL("CREATE TABLE " + tEvaluacion + "(" +
                "id_evaluacion INTEGER PRIMARY KEY AUTOINCREMENT," +
                "id_matricula INTEGER," +
                "id_curso INTEGER," +
                "bimestre INTEGER CHECK(bimestre BETWEEN 1 AND 4)," +
                "nota REAL," +
                "fecha_evaluacion TEXT," +
                "FOREIGN KEY(id_matricula) REFERENCES Matricula(id_matricula)," +
                "FOREIGN KEY(id_curso) REFERENCES Curso(id_curso))");

        db.execSQL("CREATE TABLE "+ tAsistencia +"(" +
                "id_asistencia INTEGER PRIMARY KEY AUTOINCREMENT," +
                "id_matricula INTEGER," +
                "fecha TEXT," +
                "estado TEXT CHECK(estado IN ('Asistencia','Falta','Tardanza'))," +
                "FOREIGN KEY(id_matricula) REFERENCES Matricula(id_matricula))");

        db.execSQL("CREATE TABLE "+ tConducta + "(" +
                "id_conducta INTEGER PRIMARY KEY AUTOINCREMENT," +
                "id_matricula INTEGER," +
                "bimestre INTEGER," +
                "nota_conducta REAL," +
                "FOREIGN KEY(id_matricula) REFERENCES Matricula(id_matricula))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Conducta");
        db.execSQL("DROP TABLE IF EXISTS Asistencia");
        db.execSQL("DROP TABLE IF EXISTS Evaluacion");
        db.execSQL("DROP TABLE IF EXISTS AsignacionCurso");
        db.execSQL("DROP TABLE IF EXISTS Matricula");
        db.execSQL("DROP TABLE IF EXISTS Seccion");
        db.execSQL("DROP TABLE IF EXISTS Curso");
        db.execSQL("DROP TABLE IF EXISTS Docente");
        db.execSQL("DROP TABLE IF EXISTS Alumno");
        onCreate(db);
    }
}
