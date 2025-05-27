package com.example.t2.negocio;

import android.content.Context;

import com.example.t2.controller.MatriculaController;
import com.example.t2.modelo.reportes.Historial;
import com.example.t2.modelo.reportes.LibretaNotas;

import java.util.ArrayList;

public class nMatricula {
    private Context context;

    public nMatricula(Context context) {
        this.context = context;
    }

    public ArrayList<Historial> obtenerHistorialAcademico(String dniAlumno, int anioInicio, int anioFin) {
        MatriculaController matriculaCtrl = new MatriculaController(context);
        return matriculaCtrl.historialAcademico(dniAlumno, anioInicio, anioFin);
    }
}
