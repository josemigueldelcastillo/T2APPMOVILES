package com.example.t2.negocio;

import android.content.Context;

import com.example.t2.controller.EvaluacionController;
import com.example.t2.modelo.reportes.LibretaNotas;

import java.util.ArrayList;

public class nEvaluacion {
    private Context context;

    public nEvaluacion(Context context) {
        this.context = context;
    }

    public ArrayList<LibretaNotas> obtenerLibretaNotas(String dniAlumno, int anioLectivo, int bimestre) {
        EvaluacionController evalCtrl = new EvaluacionController(context);
        return evalCtrl.libretaNotas(dniAlumno, anioLectivo, bimestre);
    }
}
