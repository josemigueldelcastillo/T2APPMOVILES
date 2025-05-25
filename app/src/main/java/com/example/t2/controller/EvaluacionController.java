package com.example.t2.controller;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.t2.dao.dJuniorsUPN;
import com.example.t2.modelo.Evaluacion;

import java.util.ArrayList;

public class EvaluacionController extends dJuniorsUPN {
    Context context;
    public EvaluacionController(@Nullable Context context){
        super(context);
        this.context=context;
    }

    public void InsertarEvaluacion(Evaluacion dato){
        dJuniorsUPN x = new EvaluacionController(context);
        SQLiteDatabase database = x.getWritableDatabase();

        if (database != null){
            database.execSQL("INSERT INTO " + tEvaluacion + " VALUES( "+
                    dato.getIdMatricula() + ", " +
                    dato.getIdCurso() + ", " +
                    dato.getBimestre() + ", " +
                    dato.getNota() + ", " +
                    dato.getFechaEvaluacion() + ")"
            );
            database.close();
        }
    }

    public void ModificarEvaluacion(Evaluacion dato){
        dJuniorsUPN x = new EvaluacionController(context);
        SQLiteDatabase database = x.getWritableDatabase();

        if(database != null){
            database.execSQL("UPDATE " + tEvaluacion +
                    " SET id_evaluacion = " + dato.getIdEvaluacion() +
                    " id_matricula = " + dato.getIdMatricula() +
                    " id_curso = " + dato.getIdCurso() +
                    " bimestre = " + dato.getBimestre() +
                    " nota = " + dato.getNota() +
                    " fecha_evaluacion = " + dato.getFechaEvaluacion() +
                    ")"
            );
            database.close();
        }
    }

    public void EliminarEvaluacion(Evaluacion dato){
        dJuniorsUPN x = new DocenteController(context);
        SQLiteDatabase database = x.getWritableDatabase();

        if(database != null){
            database.execSQL("DELETE FROM " + tDocente +
                    "WHERE id_evaluacion = " + dato.getIdEvaluacion()
            );
            database.close();
        }
    }

    public ArrayList<Evaluacion> MostrarEvaluacion(){
        dJuniorsUPN x = new DocenteController(context);
        SQLiteDatabase database = x.getReadableDatabase();

        ArrayList<Evaluacion> datos = new ArrayList<>();
        Cursor act = null;

        act = database.rawQuery("SELECT * FROM " + tEvaluacion, null);

        if (act.moveToFirst()){
            do{
                datos.add(new Evaluacion (Integer.parseInt(act.getString(0)),
                        Integer.parseInt(act.getString(1)),
                        Integer.parseInt(act.getString(2)),
                        Integer.parseInt(act.getString(3)),
                        Integer.parseInt(act.getString(4)),
                        act.getString(5)
                ));
            }while(act.moveToNext());
        }
        act.close();
        return datos;
    }

    public ArrayList<Evaluacion> BuscarEvaluacion(Evaluacion dato){
        dJuniorsUPN x = new DocenteController(context);
        SQLiteDatabase database = x.getReadableDatabase();

        ArrayList<Evaluacion> datos = new ArrayList<>();
        Cursor act = null;

        act = database.rawQuery("SELECT * FROM " + tEvaluacion + "WHERE id_docente = " + dato.getIdEvaluacion(), null);

        if (act.moveToFirst()){
            do{
                datos.add(new Evaluacion (Integer.parseInt(act.getString(0)),
                        Integer.parseInt(act.getString(1)),
                        Integer.parseInt(act.getString(2)),
                        Integer.parseInt(act.getString(3)),
                        Integer.parseInt(act.getString(4)),
                        act.getString(5)
                ));
            }while(act.moveToNext());
        }
        act.close();
        return datos;
    }
}
