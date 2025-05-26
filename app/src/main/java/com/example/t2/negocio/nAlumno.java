package com.example.t2.negocio;

import com.example.t2.controller.AlumnoController;
import com.example.t2.modelo.Alumno;

import java.util.ArrayList;

public class nAlumno {
    private AlumnoController A;
    private ArrayList<Alumno> lista = new ArrayList<>();
    private ArrayList<Alumno> busqueda = new ArrayList<>();

    public boolean Insertar(Alumno dato){
        if(dato.getNombre().isEmpty() || dato.getApellido().isEmpty() || dato.getDni().isEmpty() || dato.getNivel().isEmpty()){
            return false;
        }
        A.InsertarAlumno(dato);
        return true;
    }
    public void Actualizar(Alumno dato){

        A.ModificarAlumno(dato);
    }
    public void Eliminar(Alumno dato){
        A.EliminarAlumno(dato);
    }

    public ArrayList<Alumno> Listar(){
        return lista = A.MostrarAlumno();
    }

    public ArrayList<Alumno> BuscarDNI(String cod){
        return  busqueda = A.BuscarAlumno(cod);
    }
}
