package com.example.t2.modelo;

public class Curso {
    private int idCurso;
    private String nombreCurso;
    private int idDocentePrincipal;
    private int idDocenteAuxiliar;

    public Curso(){

    }

    public Curso ( int idCurso, String nombreCurso, int idDocentePrincipal, int idDocenteAuxiliar){
        this.idCurso = idCurso;
        this.nombreCurso = nombreCurso;
        this.idDocentePrincipal = idDocentePrincipal;
        this.idDocenteAuxiliar = idDocenteAuxiliar;
    }



    public int getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(int idCurso) {
        this.idCurso = idCurso;
    }

    public String getNombreCurso() {
        return nombreCurso;
    }

    public void setNombreCurso(String nombreCurso) {
        this.nombreCurso = nombreCurso;
    }

    public int getIdDocenteAuxiliar() {
        return idDocenteAuxiliar;
    }

    public void setIdDocenteAuxiliar(int idDocenteAuxiliar) {
        this.idDocenteAuxiliar = idDocenteAuxiliar;
    }

    public int getIdDocentePrincipal() {
        return idDocentePrincipal;
    }

    public void setIdDocentePrincipal(int idDocentePrincipal) {
        this.idDocentePrincipal = idDocentePrincipal;
    }


}
