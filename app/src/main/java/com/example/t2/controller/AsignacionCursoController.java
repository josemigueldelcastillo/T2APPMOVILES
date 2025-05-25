package com.example.t2.controller;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.t2.dao.dJuniorsUPN;
import com.example.t2.modelo.AsignacionCurso;

import java.util.ArrayList;

public class AsignacionCursoController extends dJuniorsUPN {
    Context context;
    public AsignacionCursoController(@Nullable Context context){
        super(context);
        this.context=context;
    }

    public void InsertarAsignacionCurso(AsignacionCurso dato){
        dJuniorsUPN x = new AsignacionCursoController(context);
        SQLiteDatabase database = x.getWritableDatabase();

        if (database != null){
            database.execSQL("INSERT INTO " + tAsignacionCurso + " VALUES( "+
                    dato.getIdCurso() + ", " +
                    dato.getIdDocentePrincipal() + ", " +
                    dato.getIdDocenteAuxiliar() + ", " +
                    dato.getIdSeccion() + ", " +
                    dato.getAnioLectivo() + ")"
            );
            database.close();
        }
    }

    public void ModificarAsignacionCurso(AsignacionCurso dato){
        dJuniorsUPN x = new AsignacionCursoController(context);
        SQLiteDatabase database = x.getWritableDatabase();

        if(database != null){
            database.execSQL("UPDATE " + tAsignacionCurso +
                    " SET id_asignacion = " + dato.getIdAsignacion() +
                    " id_curso = " + dato.getIdCurso() +
                    " id_docente_principal = " + dato.getIdDocentePrincipal() +
                    " id_docente_auxiliar = " + dato.getIdDocenteAuxiliar() +
                    " id_seccion = " + dato.getIdSeccion() +
                    " anio_lectivo = " + dato.getAnioLectivo() +
                    ")"
            );
            database.close();
        }
    }

    public void EliminarAsignacionCurso(AsignacionCurso dato){
        dJuniorsUPN x = new DocenteController(context);
        SQLiteDatabase database = x.getWritableDatabase();

        if(database != null){
            database.execSQL("DELETE FROM " + tDocente +
                    "WHERE id_asignacion = " + dato.getIdAsignacion()
            );
            database.close();
        }
    }

    public ArrayList<AsignacionCurso> MostrarAsignacionCurso(){
        dJuniorsUPN x = new DocenteController(context);
        SQLiteDatabase database = x.getReadableDatabase();

        ArrayList<AsignacionCurso> datos = new ArrayList<>();
        Cursor act = null;

        act = database.rawQuery("SELECT * FROM " + tAsignacionCurso, null);

        if (act.moveToFirst()){
            do{
                datos.add(new AsignacionCurso (Integer.parseInt(act.getString(0)),
                        Integer.parseInt(act.getString(1)),
                        Integer.parseInt(act.getString(2)),
                        Integer.parseInt(act.getString(3)),
                        Integer.parseInt(act.getString(4)),
                        Integer.parseInt(act.getString(5))
                ));
            }while(act.moveToNext());
        }
        act.close();
        return datos;
    }

    public ArrayList<AsignacionCurso> BuscarAsignacionCurso(AsignacionCurso dato){
        dJuniorsUPN x = new DocenteController(context);
        SQLiteDatabase database = x.getReadableDatabase();

        ArrayList<AsignacionCurso> datos = new ArrayList<>();
        Cursor act = null;

        act = database.rawQuery("SELECT * FROM " + tAsignacionCurso + "WHERE id_docente = " + dato.getIdAsignacion(), null);

        if (act.moveToFirst()){
            do{
                datos.add(new AsignacionCurso (Integer.parseInt(act.getString(0)),
                        Integer.parseInt(act.getString(1)),
                        Integer.parseInt(act.getString(2)),
                        Integer.parseInt(act.getString(3)),
                        Integer.parseInt(act.getString(4)),
                        Integer.parseInt(act.getString(5))
                ));
            }while(act.moveToNext());
        }
        act.close();
        return datos;
    }
}
