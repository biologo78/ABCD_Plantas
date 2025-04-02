package com.ajfm.abcd_plantas.modelos;

public class figura {
    private int id;
    private int NumFig;
    private String Titulo;
    private String Texto;


    public figura(int id, int NumFig, String Titulo, String Texto) {
        this.id = id;
        this.NumFig = NumFig;
        this.Titulo = Titulo;
        this.Texto = Texto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNumFig() {
        return NumFig;
    }

    public void setNumFig(int numFig) {
        NumFig = numFig;
    }

    public String getTitulo() {
        return Titulo;
    }

    public void setTitulo(String titulo) {
        Titulo = titulo;
    }

    public String getTexto() {
        return Texto;
    }

    public void setTexto(String texto) {
        Texto = texto;
    }
}
