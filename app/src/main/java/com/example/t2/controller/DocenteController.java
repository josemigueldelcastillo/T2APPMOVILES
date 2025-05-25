package com.example.t2.controller;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.t2.dao.dJuniorsUPN;
import com.example.t2.modelo.Docente;

import java.util.ArrayList;

public class DocenteController extends dJuniorsUPN{
    Context context;
    public DocenteController(@Nullable Context context){
        super(context);
        this.context=context;
    }

    public void InsertarDocente(Docente dato){
        dJuniorsUPN x = new DocenteController(context);
        SQLiteDatabase database = x.getWritableDatabase();

        if (database != null){
            database.execSQL("INSERT INTO " + tDocente + " VALUES( "+
                    dato.getNombre() + ", " +
                    dato.getApellido() + ", " +
                    dato.getDni() + ", " +
                    dato.getNacionalidad() +
                    dato.getTipo() + ")"
            );
            database.close();
        }
    }

    public void ModificarDocente(Docente dato){
        dJuniorsUPN x = new DocenteController(context);
        SQLiteDatabase database = x.getWritableDatabase();

        if(database != null){
            database.execSQL("UPDATE " + tDocente +
                    " SET id_docente = " + dato.getIdDocente() +
                    " nombre = " + dato.getNombre() +
                    " apellido = " + dato.getApellido() +
                    " dni = " + dato.getDni() +
                    " nacionalidad = " + dato.getNacionalidad() +
                    " tipo = " + dato.getTipo() + ")"
            );
            database.close();
        }
    }

    public void EliminarDocente(Docente dato){
        dJuniorsUPN x = new DocenteController(context);
        SQLiteDatabase database = x.getWritableDatabase();

        if(database != null){
            database.execSQL("DELETE FROM " + tDocente +
                    "WHERE id_docente = " + dato.getIdDocente()
            );
            database.close();
        }
    }

    public ArrayList<Docente> MostrarDocente(){
        dJuniorsUPN x = new DocenteController(context);
        SQLiteDatabase database = x.getReadableDatabase();

        ArrayList<Docente> datos = new ArrayList<>();
        Cursor act = null;

        act = database.rawQuery("SELECT * FROM " + tDocente, null);

        if (act.moveToFirst()){
            do{
                datos.add(new Docente (Integer.parseInt(act.getString(0)),
                        act.getString(1),
                        act.getString(2),
                        act.getString(3),
                        act.getString(4),
                        act.getString(5)
                        ));
            }while(act.moveToNext());
        }
        act.close();
        return datos;
    }

    public ArrayList<Docente> BuscarDocente(Docente dato){
        dJuniorsUPN x = new DocenteController(context);
        SQLiteDatabase database = x.getReadableDatabase();

        ArrayList<Docente> datos = new ArrayList<>();
        Cursor act = null;

        act = database.rawQuery("SELECT * FROM " + tDocente + "WHERE id_docente = " + dato.getIdDocente(), null);

        if (act.moveToFirst()){
            do{
                datos.add(new Docente (Integer.parseInt(act.getString(0)),
                        act.getString(1),
                        act.getString(2),
                        act.getString(3),
                        act.getString(4),
                        act.getString(5)
                ));
            }while(act.moveToNext());
        }
        act.close();
        return datos;
    }
}
