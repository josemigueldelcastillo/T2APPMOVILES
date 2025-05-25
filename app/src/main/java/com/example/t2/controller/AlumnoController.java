package com.example.t2.controller;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.t2.dao.dJuniorsUPN;
import com.example.t2.modelo.Alumno;

import java.util.ArrayList;

public class AlumnoController extends dJuniorsUPN {
    Context context;
    public AlumnoController(@Nullable Context context){
        super(context);
        this.context=context;
    }

    public void InsertarAlumno(Alumno dato){
        dJuniorsUPN x = new AlumnoController(context);
        SQLiteDatabase database = x.getWritableDatabase();

        if (database != null){
            database.execSQL("INSERT INTO " + tAlumno + " VALUES( "+
                    dato.getNombre() + ", " +
                    dato.getApellido() + ", " +
                    dato.getDni() + ", " +
                    dato.getNacionalidad() + ", " +
                    dato.getNivel() + ")"
            );
            database.close();
        }
    }

    public void ModificarAlumno(Alumno dato){
        dJuniorsUPN x = new AlumnoController(context);
        SQLiteDatabase database = x.getWritableDatabase();

        if(database != null){
            database.execSQL("UPDATE " + tAlumno +
                    " SET id_alumno = " + dato.getIdAlumno() +
                    " nombre = " + dato.getNombre() +
                    " apellido = " + dato.getApellido() +
                    " dni = " + dato.getDni() +
                    " nacionalidad = " + dato.getNacionalidad() +
                    " nivel = " + dato.getNivel() + ")"
            );
            database.close();
        }
    }

    public void EliminarAlumno(Alumno dato){
        dJuniorsUPN x = new AlumnoController(context);
        SQLiteDatabase database = x.getWritableDatabase();

        if(database != null){
            database.execSQL("DELETE FROM " + tAlumno +
                    "WHERE id_alumno = " + dato.getIdAlumno()
            );
            database.close();
        }
    }

    public ArrayList<Alumno> MostrarAlumno(){
        dJuniorsUPN x = new AlumnoController(context);
        SQLiteDatabase database = x.getReadableDatabase();

        ArrayList<Alumno> datos = new ArrayList<>();
        Cursor act = null;

        act = database.rawQuery("SELECT * FROM " + tAlumno, null);

        if (act.moveToFirst()){
            do{
                datos.add(new Alumno (Integer.parseInt(act.getString(0)),
                        act.getString(1),
                        act.getString(2),
                        act.getString(3),
                        act.getString(4),
                        act.getString(5),
                        act.getString(6)
                ));
            }while(act.moveToNext());
        }
        act.close();
        return datos;
    }

    public ArrayList<Alumno> BuscarAlumno(Alumno dato){
        dJuniorsUPN x = new DocenteController(context);
        SQLiteDatabase database = x.getReadableDatabase();

        ArrayList<Alumno> datos = new ArrayList<>();
        Cursor act = null;

        act = database.rawQuery("SELECT * FROM " + tAlumno + "WHERE id_alumno = " + dato.getIdAlumno(), null);

        if (act.moveToFirst()){
            do{
                datos.add(new Alumno (Integer.parseInt(act.getString(0)),
                        act.getString(1),
                        act.getString(2),
                        act.getString(3),
                        act.getString(4),
                        act.getString(5),
                        act.getString(6)
                ));
            }while(act.moveToNext());
        }
        act.close();
        return datos;
    }

    public ArrayList<Alumno> BuscarNombreAlumno(Alumno dato){
        dJuniorsUPN x = new DocenteController(context);
        SQLiteDatabase database = x.getReadableDatabase();

        ArrayList<Alumno> datos = new ArrayList<>();
        Cursor act = null;

        act = database.rawQuery("SELECT * FROM " + tAlumno + " WHERE nombre LIKE %" + dato.getNombre() + "%", null);

        if (act.moveToFirst()){
            do{
                datos.add(new Alumno (Integer.parseInt(act.getString(0)),
                        act.getString(1),
                        act.getString(2),
                        act.getString(3),
                        act.getString(4),
                        act.getString(5),
                        act.getString(6)
                ));
            }while(act.moveToNext());
        }
        act.close();
        return datos;
    }
}
