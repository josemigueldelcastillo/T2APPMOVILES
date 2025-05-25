package com.example.t2.controller;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.t2.dao.dJuniorsUPN;
import com.example.t2.modelo.Curso;

import java.util.ArrayList;

public class CursoController extends dJuniorsUPN {
    Context context;
    public CursoController(@Nullable Context context){
        super(context);
        this.context=context;
    }

    public void InsertarCurso(Curso dato){
        dJuniorsUPN x = new CursoController(context);
        SQLiteDatabase database = x.getWritableDatabase();

        if (database != null){
            database.execSQL("INSERT INTO " + tCurso + " VALUES( "+
                    dato.getNombreCurso() + ", " +
                    dato.getNivel() +  ")"
            );
            database.close();
        }
    }

    public void ModificarCurso(Curso dato){
        dJuniorsUPN x = new CursoController(context);
        SQLiteDatabase database = x.getWritableDatabase();

        if(database != null){
            database.execSQL("UPDATE " + tCurso +
                    " SET id_curso = " + dato.getIdCurso() +
                    " nombre_curso = " + dato.getNombreCurso() +
                    " nivel = " + dato.getNivel() + ")"
            );
            database.close();
        }
    }

    public void EliminarCurso(Curso dato){
        dJuniorsUPN x = new CursoController(context);
        SQLiteDatabase database = x.getWritableDatabase();

        if(database != null){
            database.execSQL("DELETE FROM " + tCurso +
                    "WHERE id_Curso = " + dato.getIdCurso()
            );
            database.close();
        }
    }

    public ArrayList<Curso> MostrarCurso(){
        dJuniorsUPN x = new CursoController(context);
        SQLiteDatabase database = x.getReadableDatabase();

        ArrayList<Curso> datos = new ArrayList<>();
        Cursor act = null;

        act = database.rawQuery("SELECT * FROM " + tCurso, null);

        if (act.moveToFirst()){
            do{
                datos.add(new Curso(Integer.parseInt(act.getString(0)),
                        act.getString(1),
                        act.getString(2)
                ));
            }while(act.moveToNext());
        }
        act.close();
        return datos;
    }

    public ArrayList<Curso> BuscarCurso(Curso dato){
        dJuniorsUPN x = new CursoController(context);
        SQLiteDatabase database = x.getReadableDatabase();

        ArrayList<Curso> datos = new ArrayList<>();
        Cursor act = null;

        act = database.rawQuery("SELECT * FROM " + tCurso + "WHERE id_Curso = " + dato.getIdCurso(), null);

        if (act.moveToFirst()){
            do{
                datos.add(new Curso(Integer.parseInt(act.getString(0)),
                        act.getString(1),
                        act.getString(2)
                ));
            }while(act.moveToNext());
        }
        act.close();
        return datos;
    }
}
