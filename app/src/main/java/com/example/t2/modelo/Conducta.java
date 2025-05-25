package com.example.t2.modelo;

public class Conducta {
    private int idConducta;
    private int idMatricula;
    private int bimestre;
    private double notaConducta;

    public Conducta(){

    }

    public Conducta ( int idConducta, int idMatricula, int bimestre, double notaConducta){
        this.idConducta = idConducta;
        this.idMatricula = idMatricula;
        this.bimestre = bimestre;
        this.notaConducta = notaConducta;
    }

    // Getters y Setters

    public int getIdConducta() {
        return idConducta;
    }

    public void setIdConducta(int idConducta) {
        this.idConducta = idConducta;
    }

    public int getIdMatricula() {
        return idMatricula;
    }

    public void setIdMatricula(int idMatricula) {
        this.idMatricula = idMatricula;
    }

    public int getBimestre() {
        return bimestre;
    }

    public void setBimestre(int bimestre) {
        this.bimestre = bimestre;
    }

    public double getNotaConducta() {
        return notaConducta;
    }

    public void setNotaConducta(double notaConducta) {
        this.notaConducta = notaConducta;
    }
}
