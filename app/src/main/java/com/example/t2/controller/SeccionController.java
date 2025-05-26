package com.example.t2.controller;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.t2.dao.dJuniorsUPN;
import com.example.t2.modelo.Alumno;
import com.example.t2.modelo.Matricula;
import com.example.t2.modelo.Seccion;

import java.util.ArrayList;

public class SeccionController extends dJuniorsUPN {
    Context context;

    public SeccionController(@Nullable Context context) {
        super(context);
        this.context = context;
    }

    public void InsertarSeccion(Seccion dato){
        dJuniorsUPN x = new MatriculaController(context);
        SQLiteDatabase database = x.getWritableDatabase();

        if (database != null){
            database.execSQL("INSERT INTO " + tSeccion + " VALUES( "+
                    dato.getIdSeccion() + ", " +
                    dato.getLetra() + ", " +
                    dato.getTurno() + ")"
            );
            database.close();
        }
    }

    public ArrayList<Seccion> MostrarSeccion(){
        dJuniorsUPN x = new DocenteController(context);
        SQLiteDatabase database = x.getReadableDatabase();

        ArrayList<Seccion> datos = new ArrayList<>();
        Cursor act = null;

        act = database.rawQuery("SELECT * FROM " + tSeccion, null);

        if (act.moveToFirst()){
            do{
                datos.add(new Seccion (Integer.parseInt(act.getString(0)),
                        act.getString(1).charAt(0),
                        act.getString(2)
                ));
            }while(act.moveToNext());
        }
        act.close();
        return datos;
    }

    public ArrayList<Alumno> ListarAlumnosPorSeccion(Seccion dato) {
        dJuniorsUPN x = new AlumnoController(context);
        SQLiteDatabase database = x.getReadableDatabase();

        ArrayList<Alumno> datos = new ArrayList<>();
        Cursor act = null;

        act = database.rawQuery("SELECT a.id_alumno, a.nombre, a.apellido, a.dni, a.nacionalidad, a.nivel " +
                "FROM " + tAlumno + " a " +
                "JOIN " + tMatricula + " m ON a.id_alumno = m.id_alumno " +
                "WHERE m.id_seccion = " + dato.getIdSeccion(), null);

        if (act.moveToFirst()) {
            do {
                datos.add(new Alumno(
                        Integer.parseInt(act.getString(0)),  // id_alumno
                        act.getString(1),                   // nombre
                        act.getString(2),                   // apellido
                        act.getString(3),                   // dni
                        act.getString(4),                   // nacionalidad
                        act.getString(5)                    // nivel
                ));
            } while (act.moveToNext());
        }

        act.close();
        database.close();

        return datos;
    }

}

