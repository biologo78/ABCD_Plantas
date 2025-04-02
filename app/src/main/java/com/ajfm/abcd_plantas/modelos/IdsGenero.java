package com.ajfm.abcd_plantas.modelos;

import android.content.ContentValues;

public class IdsGenero {
    long Id;
    long id_Familia;
    String nombreGenero;
    String nombreFamilia;
    String nombreGrupo;
    String figuraGen;
    String laminaGen;
    String estadoObservGen;
    String descripcion;
    long Id_Auto;
    int numTabla;

    public IdsGenero(long id, long id_Familia, String nombreGenero, String nombreFamilia, String nombreGrupo,
                     String figuraGen, String laminaGen, String estadoObservGen, String descripcion,
                     long id_Auto, int numTabla) {
        Id = id;
        this.id_Familia = id_Familia;
        this.nombreGenero = nombreGenero;
        this.nombreFamilia = nombreFamilia;
        this.nombreGrupo = nombreGrupo;
        this.figuraGen = figuraGen;
        this.laminaGen = laminaGen;
        this.estadoObservGen = estadoObservGen;
        this.descripcion = descripcion;
        Id_Auto = id_Auto;
        this.numTabla = numTabla;
    }

    public IdsGenero(ContentValues values) {
    }

    public IdsGenero() {

    }

    public long getId_Auto() {
        return Id_Auto;
    }

    public void setId_Auto(long id_Auto) {
        Id_Auto = id_Auto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getNumTabla() {
        return numTabla;
    }

    public void setNumTabla(int numTabla) {
        this.numTabla = numTabla;
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public long getId_Familia() {
        return id_Familia;
    }

    public void setId_Familia(long id_Familia) {
        this.id_Familia = id_Familia;
    }

    public String getNombreGenero() {
        return nombreGenero;
    }

    public void setNombreGenero(String nombreGenero) {
        this.nombreGenero = nombreGenero;
    }

    public String getNombreFamilia() {
        return nombreFamilia;
    }

    public void setNombreFamilia(String nombreFamilia) {
        this.nombreFamilia = nombreFamilia;
    }

    public String getNombreGrupo() {
        return nombreGrupo;
    }

    public void setNombreGrupo(String nombreGrupo) {
        this.nombreGrupo = nombreGrupo;
    }

    public String getFiguraGen() {
        return figuraGen;
    }

    public void setFiguraGen(String figuraGen) {
        this.figuraGen = figuraGen;
    }

    public String getLaminaGen() {
        return laminaGen;
    }

    public void setLaminaGen(String laminaGen) {
        this.laminaGen = laminaGen;
    }

    public String getEstadoObservGen() {
        return estadoObservGen;
    }

    public void setEstadoObservGen(String estadoObservGen) {
        this.estadoObservGen = estadoObservGen;
    }

    @Override
    public String toString() {
        return "IdsGenero{" +
                "Id_Auto=" + Id_Auto +
                ", Id=" + Id +
                ", id_Familia=" + id_Familia +
                ", nombreGenero='" + nombreGenero + '\'' +
                ", nombreFamilia='" + nombreFamilia + '\'' +
                ", nombreGrupo='" + nombreGrupo + '\'' +
                ", figuraGen='" + figuraGen + '\'' +
                ", laminaGen='" + laminaGen + '\'' +
                ", estadoObservGen='" + estadoObservGen + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", numTabla=" + numTabla +
                '}';
    }
}
