package com.example.t2.controller;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.annotation.Nullable;

import com.example.t2.dao.dJuniorsUPN;
import com.example.t2.modelo.Conducta;

import java.util.ArrayList;

public class ConductaController extends dJuniorsUPN{
    Context context;
    public ConductaController(@Nullable Context context){
        super(context);
        this.context=context;
    }

    public void InsertarConducta(Conducta dato){
        dJuniorsUPN x = new ConductaController(context);
        SQLiteDatabase database = x.getWritableDatabase();

        if (database != null){
            database.execSQL("INSERT INTO " + tConducta + " VALUES( "+
                    dato.getIdMatricula() + ", " +
                    dato.getBimestre() + ", " +
                    dato.getNotaConducta()  + ")"
            );
            database.close();
        }
    }

    public void ModificarConducta(Conducta dato){
        dJuniorsUPN x = new ConductaController(context);
        SQLiteDatabase database = x.getWritableDatabase();

        if(database != null){
            database.execSQL("UPDATE " + tConducta +
                    " SET id_conducta = " + dato.getIdConducta() +
                    " id_matricula = " + dato.getIdMatricula() +
                    " bimestre = " + dato.getBimestre() +
                    " nota_conducta = " + dato.getNotaConducta() + ")"
            );
            database.close();
        }
    }

    public void EliminarConducta(Conducta dato){
        dJuniorsUPN x = new ConductaController(context);
        SQLiteDatabase database = x.getWritableDatabase();

        if(database != null){
            database.execSQL("DELETE FROM " + tConducta +
                    "WHERE id_Conducta = " + dato.getIdConducta()
            );
            database.close();
        }
    }

    public ArrayList<Conducta> MostrarConducta(){
        dJuniorsUPN x = new ConductaController(context);
        SQLiteDatabase database = x.getReadableDatabase();

        ArrayList<Conducta> datos = new ArrayList<>();
        Cursor act = null;

        act = database.rawQuery("SELECT * FROM " + tConducta, null);

        if (act.moveToFirst()){
            do{
                datos.add(new Conducta(Integer.parseInt(act.getString(0)),
                        Integer.parseInt(act.getString(1)),
                        Integer.parseInt(act.getString(2)),
                        Double.parseDouble(act.getString(3))
                ));
            }while(act.moveToNext());
        }
        act.close();
        return datos;
    }

    public ArrayList<Conducta> BuscarConducta(Conducta dato){
        dJuniorsUPN x = new ConductaController(context);
        SQLiteDatabase database = x.getReadableDatabase();

        ArrayList<Conducta> datos = new ArrayList<>();
        Cursor act = null;

        act = database.rawQuery("SELECT * FROM " + tConducta + "WHERE id_conducta = " + dato.getIdConducta(), null);

        if (act.moveToFirst()){
            do{
                datos.add(new Conducta(Integer.parseInt(act.getString(0)),
                        Integer.parseInt(act.getString(1)),
                        Integer.parseInt(act.getString(2)),
                        Double.parseDouble(act.getString(3))
                ));
            }while(act.moveToNext());
        }
        act.close();
        return datos;
    }
}
