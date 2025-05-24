package com.example.t2.modelo;

public class Seccion {
    private int idSeccion;
    private char letra; // 'A', 'B' o 'C'
    private String turno; // "Mañana" o "Tarde"

    // Getters y Setters

    public int getIdSeccion() {
        return idSeccion;
    }

    public void setIdSeccion(int idSeccion) {
        this.idSeccion = idSeccion;
    }

    public char getLetra() {
        return letra;
    }

    public void setLetra(char letra) {
        this.letra = letra;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }
}
