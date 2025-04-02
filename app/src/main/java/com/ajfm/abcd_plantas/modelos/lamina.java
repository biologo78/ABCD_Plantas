package com.ajfm.abcd_plantas.modelos;

public class lamina {
    int Id;
    int NumFichero;
    String Titulo;
    String Texto;
    int NumLam;

    public lamina(int id, int numFichero, String titulo, String texto, int numLam) {
        Id = id;
        NumFichero = numFichero;
        Titulo = titulo;
        Texto = texto;
        NumLam = numLam;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getNumFichero() {
        return NumFichero;
    }

    public void setNumFichero(int numFichero) {
        NumFichero = numFichero;
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

    public int getNumLam() {
        return NumLam;
    }

    public void setNumLam(int numLam) {
        NumLam = numLam;
    }
}
