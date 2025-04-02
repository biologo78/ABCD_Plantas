package com.ajfm.abcd_plantas.modelos;

import android.content.ContentValues;

public class familia {
    private int mIdFamilia;
    private String mNombreFamilia;
    private String mEstado;
    private String mOrigen;
    private int mId_en_FUPlanta;

    public familia(int mIdFamilia, String mNombreFamilia, String mEstado, String mOrigen, int mId_en_FUPlanta) {
        this.mIdFamilia = mIdFamilia;
        this.mNombreFamilia = mNombreFamilia;
        this.mEstado = mEstado;
        this.mOrigen = mOrigen;
        this.mId_en_FUPlanta = mId_en_FUPlanta;
    }

    public familia() {
    }

    public familia(ContentValues values){

    }
    public int getmIdFamilia() {
        return mIdFamilia;
    }

    public void setmIdFamilia(int mIdFamilia) {
        this.mIdFamilia = mIdFamilia;
    }

    public String getmNombreFamilia() {
        return mNombreFamilia;
    }

    public void setmNombreFamilia(String mNombreFamilia) {
        this.mNombreFamilia = mNombreFamilia;
    }

    public String getmEstado() {
        return mEstado;
    }

    public void setmEstado(String mEstado) {
        this.mEstado = mEstado;
    }

    public String getmOrigen() {
        return mOrigen;
    }

    public void setmOrigen(String mOrigen) {
        this.mOrigen = mOrigen;
    }

    public int getmId_en_FUPlanta() {
        return mId_en_FUPlanta;
    }

    public void setmId_en_FUPlanta(int mId_en_FUPlanta) {
        this.mId_en_FUPlanta = mId_en_FUPlanta;
    }
}
