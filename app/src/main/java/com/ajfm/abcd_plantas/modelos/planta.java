package com.ajfm.abcd_plantas.modelos;

import android.content.ContentValues;

public class planta  {
    private int mId_auto;
    private int mId;
    private String mNombreCientifico;
    private String mNobreVulgar;
    private String mFamilia;
    private String mGrupo;
    private int mLargoCient;
    private int mEspeciosCient;
    private int mLargoVulgar;
    private int mEspaciosCient;
    private int mLargoFamilia;
    private String mFotoAA;
    private String mFotoBB;
    private int mImgAA;
    private int mImgBB;
    private int mId_en_FUClave;
    private String mEstado;
    private int mId_en_FUPlanta;

    public planta() {
    }
    public planta(ContentValues values){

    }

    public planta(int mId_auto, int mId, String mNombreCientifico, String mNobreVulgar, String mFamilia,
                  String mGrupo, int mLargoCient, int mEspeciosCient, int mLargoVulgar, int mEspaciosCient,
                  int mLargoFamilia, String mFotoAA, String mFotoBB, int mImgAA, int mImgBB, int mId_en_FUClave,
                  String mEstado, int mId_en_FUPlanta) {
        this.mId_auto = mId_auto;                       //int - 0
        this.mId = mId;                                 //int - 1
        this.mNombreCientifico = mNombreCientifico;     //str - 2
        this.mNobreVulgar = mNobreVulgar;               //str - 3
        this.mFamilia = mFamilia;                       //str - 4
        this.mGrupo = mGrupo;                           //str - 5
        this.mLargoCient = mLargoCient;                 //int - 6
        this.mEspeciosCient = mEspeciosCient;           //int - 7
        this.mLargoVulgar = mLargoVulgar;               //int - 8
        this.mEspaciosCient = mEspaciosCient;           //int - 9
        this.mLargoFamilia = mLargoFamilia;             //int - 10
        this.mFotoAA = mFotoAA;                         //str - 11
        this.mFotoBB = mFotoBB;                         //str - 12
        this.mImgAA = mImgAA;                           //int -
        this.mImgBB = mImgBB;                           //int -
        this.mId_en_FUClave = mId_en_FUClave;           //int - 13
        this.mEstado = mEstado;                         //str - 14
        this.mId_en_FUPlanta = mId_en_FUPlanta;         //int - 15
    }

    public int getmId_auto() {
        return mId_auto;
    }

    public void setmId_auto(int mId_auto) {
        this.mId_auto = mId_auto;
    }

    public int getmId_en_FUPlanta() {
        return mId_en_FUPlanta;
    }

    public void setmId_en_FUPlanta(int mId_en_FUPlanta) {
        this.mId_en_FUPlanta = mId_en_FUPlanta;
    }

    public int getmImgAA() {
        return mImgAA;
    }

    public void setmImgAA(int mImgAA) {
        this.mImgAA = mImgAA;
    }

    public int getmImgBB() {
        return mImgBB;
    }

    public void setmImgBB(int mImgBB) {
        this.mImgBB = mImgBB;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
    }

    public String getmNombreCientifico() {
        return mNombreCientifico;
    }

    public void setmNombreCientifico(String mNombreCientifico) {
        this.mNombreCientifico = mNombreCientifico;
    }

    public String getmNobreVulgar() {
        return mNobreVulgar;
    }

    public void setmNobreVulgar(String mNobreVulgar) {
        this.mNobreVulgar = mNobreVulgar;
    }

    public String getmFamilia() {
        return mFamilia;
    }

    public void setmFamilia(String mFamilia) {
        this.mFamilia = mFamilia;
    }

    public String getmGrupo() {
        return mGrupo;
    }

    public void setmGrupo(String mGrupo) {
        this.mGrupo = mGrupo;
    }

    public int getmLargoCient() {
        return mLargoCient;
    }

    public void setmLargoCient(int mLargoCient) {
        this.mLargoCient = mLargoCient;
    }

    public int getmEspeciosCient() {
        return mEspeciosCient;
    }

    public void setmEspeciosCient(int mEspeciosCient) {
        this.mEspeciosCient = mEspeciosCient;
    }

    public int getmLargoVulgar() {
        return mLargoVulgar;
    }

    public void setmLargoVulgar(int mLargoVulgar) {
        this.mLargoVulgar = mLargoVulgar;
    }

    public int getmEspaciosCient() {
        return mEspaciosCient;
    }

    public void setmEspaciosCient(int mEspaciosCient) {
        this.mEspaciosCient = mEspaciosCient;
    }

    public int getmLargoFamilia() {
        return mLargoFamilia;
    }

    public void setmLargoFamilia(int mLargoFamilia) {
        this.mLargoFamilia = mLargoFamilia;
    }

    public String getmFotoAA() {
        return mFotoAA;
    }

    public void setmFotoAA(String mFotoAA) {
        this.mFotoAA = mFotoAA;
    }

    public String getmFotoBB() {
        return mFotoBB;
    }

    public void setmFotoBB(String mFotoBB) {
        this.mFotoBB = mFotoBB;
    }

    public int getmId_en_FUClave() {
        return mId_en_FUClave;
    }

    public void setmId_en_FUClave(int mId_en_FUClave) {
        this.mId_en_FUClave = mId_en_FUClave;
    }

    public String getmEstado() {
        return mEstado;
    }

    public void setmEstado(String mEstado) {
        this.mEstado = mEstado;
    }

    @Override
    public String toString() {
        return "planta{" +
                "mId_auto=" + mId_auto +
                ", mId=" + mId +
                ", mNombreCientifico='" + mNombreCientifico + '\'' +
                ", mNobreVulgar='" + mNobreVulgar + '\'' +
                ", mFamilia='" + mFamilia + '\'' +
                ", mGrupo='" + mGrupo + '\'' +
                ", mLargoCient=" + mLargoCient +
                ", mEspeciosCient=" + mEspeciosCient +
                ", mLargoVulgar=" + mLargoVulgar +
                ", mEspaciosCient=" + mEspaciosCient +
                ", mLargoFamilia=" + mLargoFamilia +
                ", mFotoAA='" + mFotoAA + '\'' +
                ", mFotoBB='" + mFotoBB + '\'' +
                ", mImgAA=" + mImgAA +
                ", mImgBB=" + mImgBB +
                ", mId_en_FUClave=" + mId_en_FUClave +
                ", mEstado='" + mEstado + '\'' +
                ", mId_en_FUPlanta=" + mId_en_FUPlanta +
                '}';
    }
}
