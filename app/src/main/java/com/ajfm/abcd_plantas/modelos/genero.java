package com.ajfm.abcd_plantas.modelos;

import java.io.Serializable;

public class genero implements Serializable {
    private int gId_auto;
    private int gId;
    private String gGenero;
    private String gespecie;
    private String gSubespecie;
    private String gFamilia;
    private String gGeneroyFamilia;
    private int gId_en_FUPlanta;
    private String  gEstado;
    private int gId_en_FUClave;

    public genero(int gId_auto, int gId, String gGenero, String gespecie, String gSubespecie,
                  String gFamilia, String gGeneroyFamilia, int gId_en_FUPlanta, String gEstado, int gId_en_FUClave) {
        this.gId_auto = gId_auto;
        this.gId = gId;
        this.gGenero = gGenero;
        this.gespecie = gespecie;
        this.gSubespecie = gSubespecie;
        this.gFamilia = gFamilia;
        this.gGeneroyFamilia = gGeneroyFamilia;
        this.gId_en_FUPlanta = gId_en_FUPlanta;
        this.gEstado = gEstado;
        this.gId_en_FUClave = gId_en_FUClave;
    }

    public int getgId() {
        return gId;
    }

    public void setgId(int gId) {
        this.gId = gId;
    }

    public String getgGenero() {
        return gGenero;
    }

    public void setgGenero(String gGenero) {
        this.gGenero = gGenero;
    }

    public String getGespecie() {
        return gespecie;
    }

    public void setGespecie(String gespecie) {
        this.gespecie = gespecie;
    }

    public String getgSubespecie() {
        return gSubespecie;
    }

    public void setgSubespecie(String gSubespecie) {
        this.gSubespecie = gSubespecie;
    }

    public String getgFamilia() {
        return gFamilia;
    }

    public void setgFamilia(String gFamilia) {
        this.gFamilia = gFamilia;
    }

    public String getgGeneroyFamilia() {
        return gGeneroyFamilia;
    }

    public void setgGeneroyFamilia(String gGeneroyFamilia) {
        this.gGeneroyFamilia = gGeneroyFamilia;
    }

    public int getgId_auto() {
        return gId_auto;
    }

    public void setgId_auto(int gId_auto) {
        this.gId_auto = gId_auto;
    }

    public int getgId_en_FUPlanta() {
        return gId_en_FUPlanta;
    }

    public void setgId_en_FUPlanta(int gId_en_FUPlanta) {
        this.gId_en_FUPlanta = gId_en_FUPlanta;
    }

    public String getgEstado() {
        return gEstado;
    }

    public void setgEstado(String gEstado) {
        this.gEstado = gEstado;
    }

    public int getgId_en_FUClave() {
        return gId_en_FUClave;
    }

    public void setgId_en_FUClave(int gId_en_FUClave) {
        this.gId_en_FUClave = gId_en_FUClave;
    }

    @Override
    public String toString() {
        return "genero{" +
                "gId_auto=" + gId_auto +
                ", gId=" + gId +
                ", gGenero='" + gGenero + '\'' +
                ", gespecie='" + gespecie + '\'' +
                ", gSubespecie='" + gSubespecie + '\'' +
                ", gFamilia='" + gFamilia + '\'' +
                ", gGeneroyFamilia='" + gGeneroyFamilia + '\'' +
                ", gId_en_FUPlanta=" + gId_en_FUPlanta +
                ", gEstado='" + gEstado + '\'' +
                ", gId_en_FUClave=" + gId_en_FUClave +
                '}';
    }
}
