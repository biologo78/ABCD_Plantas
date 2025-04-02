package com.ajfm.abcd_plantas.modelos;

public class reglinea {
    private int numRenglon;
    private int nivelon;
    private int nivelin;
    private int nivelun;
    private String grupo;
    private String nombrePropio;
    private int largo;

    public reglinea(int numRenglon, int nivelon, int nivelin, int nivelun, String grupo, String nombrePropio, int largo) {
        this.numRenglon = numRenglon;
        this.nivelon = nivelon;
        this.nivelin = nivelin;
        this.nivelun = nivelun;
        this.grupo = grupo;
        this.nombrePropio = nombrePropio;
        this.largo = largo;
    }

    public int getNumRenglon() {
        return numRenglon;
    }

    public void setNumRenglon(int numRenglon) {
        this.numRenglon = numRenglon;
    }

    public int getNivelon() {
        return nivelon;
    }

    public void setNivelon(int nivelon) {
        this.nivelon = nivelon;
    }

    public int getNivelin() {
        return nivelin;
    }

    public void setNivelin(int nivelin) {
        this.nivelin = nivelin;
    }

    public int getNivelun() {
        return nivelun;
    }

    public void setNivelun(int nivelun) {
        this.nivelun = nivelun;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public String getNombrePropio() {
        return nombrePropio;
    }

    public void setNombrePropio(String nombrePropio) {
        this.nombrePropio = nombrePropio;
    }

    public int getLargo() {
        return largo;
    }

    public void setLargo(int largo) {
        this.largo = largo;
    }
}
