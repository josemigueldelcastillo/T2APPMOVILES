package com.example.t2.negocio;

import com.example.t2.controller.DocenteController;
import com.example.t2.modelo.Docente;

import java.util.ArrayList;

public class nDocente {
    private DocenteController A;
    private ArrayList<Docente> lista = new ArrayList<>();
    private ArrayList<Docente> busqueda = new ArrayList<>();

    public boolean Insertar(Docente dato){
        if(dato.getNombre().isEmpty() || dato.getApellido().isEmpty() || dato.getDni().isEmpty() ){
            return false;
        }
        A.InsertarDocente(dato);
        return true;
    }
    public void Actualizar(Docente dato){

        A.ModificarDocente(dato);
    }
    public void Eliminar(Docente dato){
        A.EliminarDocente(dato);
    }

    public ArrayList<Docente> Listar(){
        return lista = A.MostrarDocente();
    }

    public ArrayList<Docente> BuscarDNI(Docente dato){
        return  busqueda = A.BuscarDocente(dato);
    }
}
