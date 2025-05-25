package com.example.t2.modelo;

public class AsignacionCurso {
    private int idAsignacion;
    private int idCurso;
    private int idDocentePrincipal;
    private int idDocenteAuxiliar;
    private int idSeccion;
    private int anioLectivo;

    public AsignacionCurso(){

    }

    public AsignacionCurso ( int idAsignacion, int idCurso, int idDocentePrincipal, int idDocenteAuxiliar, int idSeccion, int anioLectivo){
        this.idAsignacion = idAsignacion;
        this.idCurso = idCurso;
        this.idDocentePrincipal = idDocentePrincipal;
        this.idDocenteAuxiliar = idDocenteAuxiliar;
        this.idSeccion = idSeccion;
        this.anioLectivo = anioLectivo;
    }

    // Getters y Setters

    public int getIdAsignacion() {
        return idAsignacion;
    }

    public void setIdAsignacion(int idAsignacion) {
        this.idAsignacion = idAsignacion;
    }

    public int getIdCurso() {
        return idCurso;
    }

    public void setIdCurso(int idCurso) {
        this.idCurso = idCurso;
    }

    public int getIdDocentePrincipal() {
        return idDocentePrincipal;
    }

    public void setIdDocentePrincipal(int idDocentePrincipal) {
        this.idDocentePrincipal = idDocentePrincipal;
    }

    public int getIdDocenteAuxiliar() {
        return idDocenteAuxiliar;
    }

    public void setIdDocenteAuxiliar(int idDocenteAuxiliar) {
        this.idDocenteAuxiliar = idDocenteAuxiliar;
    }

    public int getIdSeccion() {
        return idSeccion;
    }

    public void setIdSeccion(int idSeccion) {
        this.idSeccion = idSeccion;
    }

    public int getAnioLectivo() {
        return anioLectivo;
    }

    public void setAnioLectivo(int anioLectivo) {
        this.anioLectivo = anioLectivo;
    }
}
