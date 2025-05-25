package com.example.t2.modelo;

public class Asistencia {
    private int idAsistencia;
    private int idMatricula;
    private String fecha;
    private String estado; // "Asistencia", "Falta", "Tardanza"

    public Asistencia(){

    }

    public Asistencia ( int idAsistencia, int idMatricula, String fecha, String estado){
        this.idAsistencia = idAsistencia;
        this.idMatricula = idMatricula;
        this.fecha = fecha;
        this.estado = estado;
    }

    // Getters y Setters

    public int getIdAsistencia() {
        return idAsistencia;
    }

    public void setIdAsistencia(int idAsistencia) {
        this.idAsistencia = idAsistencia;
    }

    public int getIdMatricula() {
        return idMatricula;
    }

    public void setIdMatricula(int idMatricula) {
        this.idMatricula = idMatricula;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

}
