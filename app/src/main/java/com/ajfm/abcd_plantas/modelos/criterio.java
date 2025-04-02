package com.ajfm.abcd_plantas.modelos;

import android.content.ContentValues;

public class criterio {
    private  int id;
    private  int nivel;
    private  int paso;
    private  String paso_txt;
    private  String criterio;
    private  String destino;
    private  String grupo;
    private  String familia;
    private int id_en_FUClave;
    private String Resumen;
    private int id_en_FUPlanta;

    public criterio() {
    }

    public criterio(ContentValues values){

    }

    public criterio(int id, int nivel, int paso, String paso_txt, String criterio, String destino,
                    String grupo, String familia, int id_en_FUClave, String resumen, int id_en_FUPlanta) {
        this.id = id;
        this.nivel = nivel;
        this.paso = paso;
        this.paso_txt = paso_txt;
        this.criterio = criterio;
        this.destino = destino;
        this.grupo = grupo;
        this.familia = familia;
        this.id_en_FUClave = id_en_FUClave;
        Resumen = resumen;
        this.id_en_FUPlanta = id_en_FUPlanta;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public int getPaso() {
        return paso;
    }

    public void setPaso(int paso) {
        this.paso = paso;
    }

    public String getPaso_txt() {
        return paso_txt;
    }

    public void setPaso_txt(String paso_txt) {
        this.paso_txt = paso_txt;
    }

    public String getCriterio() {
        return criterio;
    }

    public void setCriterio(String criterio) {
        this.criterio = criterio;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public String getFamilia() {
        return familia;
    }

    public void setFamilia(String familia) {
        this.familia = familia;
    }

    public int getId_en_FUClave() {
        return id_en_FUClave;
    }

    public void setId_en_FUClave(int id_en_FUClave) {
        this.id_en_FUClave = id_en_FUClave;
    }

    public String getResumen() {
        return Resumen;
    }

    public void setResumen(String resumen) {
        Resumen = resumen;
    }

    public int getId_en_FUPlanta() {
        return id_en_FUPlanta;
    }

    public void setId_en_FUPlanta(int id_en_FUPlanta) {
        this.id_en_FUPlanta = id_en_FUPlanta;
    }

    @Override
    public String toString() {
        return "criterio{" +
                "id=" + id +
                ", nivel=" + nivel +
                ", paso=" + paso +
                ", paso_txt='" + paso_txt + '\'' +
                ", criterio='" + criterio + '\'' +
                ", destino='" + destino + '\'' +
                ", grupo='" + grupo + '\'' +
                ", familia='" + familia + '\'' +
                ", id_en_FUClave=" + id_en_FUClave +
                ", Resumen='" + Resumen + '\'' +
                ", id_en_FUPlanta=" + id_en_FUPlanta +
                '}';
    }
}
