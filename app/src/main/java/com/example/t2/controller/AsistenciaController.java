package com.example.t2.controller;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.t2.dao.dJuniorsUPN;
import com.example.t2.modelo.Asistencia;

import java.util.ArrayList;

public class AsistenciaController extends dJuniorsUPN {
    Context context;
    public AsistenciaController(@Nullable Context context){
        super(context);
        this.context=context;
    }

    public void InsertarAsistencia(Asistencia dato){
        dJuniorsUPN x = new AsistenciaController(context);
        SQLiteDatabase database = x.getWritableDatabase();

        if (database != null){
            database.execSQL("INSERT INTO " + tAsistencia + " VALUES( "+
                    dato.getIdMatricula() + ", " +
                    dato.getFecha() + ", " +
                    dato.getEstado()  + ")"
            );
            database.close();
        }
    }

    public void ModificarAsistencia(Asistencia dato){
        dJuniorsUPN x = new AsistenciaController(context);
        SQLiteDatabase database = x.getWritableDatabase();

        if(database != null){
            database.execSQL("UPDATE " + tAsistencia +
                    " SET id_asistencia = " + dato.getIdAsistencia() +
                    " id_matricula = " + dato.getIdMatricula() +
                    " fecha = " + dato.getFecha() +
                    " estado = " + dato.getEstado() + ")"
            );
            database.close();
        }
    }

    public void EliminarAsistencia(Asistencia dato){
        dJuniorsUPN x = new AsistenciaController(context);
        SQLiteDatabase database = x.getWritableDatabase();

        if(database != null){
            database.execSQL("DELETE FROM " + tAsistencia +
                    "WHERE id_Asistencia = " + dato.getIdAsistencia()
            );
            database.close();
        }
    }

    public ArrayList<Asistencia> MostrarAsistencia(){
        dJuniorsUPN x = new AsistenciaController(context);
        SQLiteDatabase database = x.getReadableDatabase();

        ArrayList<Asistencia> datos = new ArrayList<>();
        Cursor act = null;

        act = database.rawQuery("SELECT * FROM " + tAsistencia, null);

        if (act.moveToFirst()){
            do{
                datos.add(new Asistencia(Integer.parseInt(act.getString(0)),
                        Integer.parseInt(act.getString(1)),
                        act.getString(2),
                        act.getString(3)
                ));
            }while(act.moveToNext());
        }
        act.close();
        return datos;
    }

    public ArrayList<Asistencia> BuscarAsistencia(Asistencia dato){
        dJuniorsUPN x = new AsistenciaController(context);
        SQLiteDatabase database = x.getReadableDatabase();

        ArrayList<Asistencia> datos = new ArrayList<>();
        Cursor act = null;

        act = database.rawQuery("SELECT * FROM " + tAsistencia + "WHERE id_asistencia = " + dato.getIdAsistencia(), null);

        if (act.moveToFirst()){
            do{
                datos.add(new Asistencia(Integer.parseInt(act.getString(0)),
                        Integer.parseInt(act.getString(1)),
                        act.getString(2),
                        act.getString(3)
                ));
            }while(act.moveToNext());
        }
        act.close();
        return datos;
    }
}
