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

public class MatriculaController extends dJuniorsUPN {
    Context context;
    public MatriculaController(@Nullable Context context){
        super(context);
        this.context=context;
    }

    public void InsertarMatricula(Matricula dato){
        dJuniorsUPN x = new MatriculaController(context);
        SQLiteDatabase database = x.getWritableDatabase();

        if (database != null){
            database.execSQL("INSERT INTO " + tMatricula + " VALUES( "+
                    dato.getIdAlumno() + ", " +
                    dato.getIdSeccion() + ", " +
                    dato.getAnioLectivo() + ", " +
                    dato.getFechaMatricula() + ")"
            );
            database.close();
        }
    }

    public void ModificarMatricula(Matricula dato){
        dJuniorsUPN x = new MatriculaController(context);
        SQLiteDatabase database = x.getWritableDatabase();

        if(database != null){
            database.execSQL("UPDATE " + tMatricula +
                    " SET id_matricula = " + dato.getIdMatricula() +
                    " id_alumno = " + dato.getIdAlumno() +
                    " id_seccion = " + dato.getIdSeccion() +
                    " anio_lectivo = " + dato.getAnioLectivo() +
                    " fecha_Matricula = " + dato.getFechaMatricula() +
                    ")"
            );
            database.close();
        }
    }

    public void EliminarMatricula(Matricula dato){
        dJuniorsUPN x = new MatriculaController(context);
        SQLiteDatabase database = x.getWritableDatabase();

        if(database != null){
            database.execSQL("DELETE FROM " + tMatricula +
                    "WHERE id_matricula = " + dato.getIdMatricula()
            );
            database.close();
        }
    }

    public ArrayList<Matricula> MostrarMatricula(){
        dJuniorsUPN x = new MatriculaController(context);
        SQLiteDatabase database = x.getReadableDatabase();

        ArrayList<Matricula> datos = new ArrayList<>();

        Cursor act = null;

        act = database.rawQuery("SELECT * FROM " + tMatricula, null);

        if (act.moveToFirst()){
            do{
                datos.add(new Matricula (Integer.parseInt(act.getString(0)),
                        Integer.parseInt(act.getString(1)),
                        Integer.parseInt(act.getString(2)),
                        Integer.parseInt(act.getString(3)),
                        act.getString(4)
                ));
            }while(act.moveToNext());
        }
        act.close();
        return datos;
    }

    public ArrayList<Matricula> BuscarMatricula(Matricula dato){
        dJuniorsUPN x = new MatriculaController(context);
        SQLiteDatabase database = x.getReadableDatabase();

        ArrayList<Matricula> datos = new ArrayList<>();
        Cursor act = null;

        act = database.rawQuery("SELECT * FROM " + tMatricula + "WHERE id_matricula = " + dato.getIdMatricula(), null);

        if (act.moveToFirst()){
            do{
                datos.add(new Matricula (Integer.parseInt(act.getString(0)),
                        Integer.parseInt(act.getString(1)),
                        Integer.parseInt(act.getString(2)),
                        Integer.parseInt(act.getString(3)),
                        act.getString(4)
                ));
            }while(act.moveToNext());
        }
        act.close();
        return datos;
    }

    public boolean EstaMatriculado(Alumno dato1, Seccion dato2) {
        dJuniorsUPN x = new MatriculaController(context);
        SQLiteDatabase database = x.getReadableDatabase();

        ArrayList<Matricula> datos = new ArrayList<>();
        Cursor act = null;
        boolean resultado = false;

        act = database.rawQuery("SELECT COUNT(*) FROM " + tMatricula + "WHERE id_alumno =" + dato1.getIdAlumno() + " AND id_seccion =" + dato2.getIdSeccion(), null);
        // SELECT COUNT(*) FROM Matricula WHERE id_alumno = ? AND id_seccion = ?

        if (act.moveToFirst()) {
            int cantidad = act.getInt(0);
            if (cantidad > 0) {
                resultado = true;
            }
        }

        act.close();
        database.close();
        return resultado;

    }
}
