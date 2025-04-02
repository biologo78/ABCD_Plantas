package com.ajfm.abcd_plantas.modelos;

import androidx.annotation.NonNull;

public class criterioGeneral {
    int Id;
    int nivel;
    int paso;
    int jerarquia;
    String indice;
    String criterio;
    String destino;
    String grupo;
    String familia;
    int Id_en_FUClave;
    String estado_clave;
    int Id_en_FUPlanta;
    String observaciones;
    String descripcion;
    String figura;
    String lamina;
    int Id_Auto;
    int numTabla;

    public criterioGeneral(int id, int nivel, int paso, int jerarquia, String indice, String criterio,
                           String destino, String grupo, String familia, int id_en_FUClave, String estado_clave,
                           int id_en_FUPlanta, String observaciones, String descripcion, String figura,
                           String lamina, int id_Auto, int numTabla) {
        this.Id = id;
        this.nivel = nivel;
        this.paso = paso;
        this.jerarquia = jerarquia;
        this.indice = indice;
        this.criterio = criterio;
        this.destino = destino;
        this.grupo = grupo;
        this.familia = familia;
        Id_en_FUClave = id_en_FUClave;
        this.estado_clave = estado_clave;
        Id_en_FUPlanta = id_en_FUPlanta;
        this.observaciones = observaciones;
        this.descripcion = descripcion;
        this.figura = figura;
        this.lamina = lamina;
        Id_Auto = id_Auto;
        this.numTabla = numTabla;
    }

    public criterioGeneral(int nivel, int paso, int jerarquia, String indice, String criterio,
                           String destino, String grupo, String familia, int id_en_FUClave,
                           String estado_clave, int id_en_FUPlanta, String observaciones,
                           String descripcion, String figura, String lamina) {
        this.nivel = nivel;
        this.paso = paso;
        this.jerarquia = jerarquia;
        this.indice = indice;
        this.criterio = criterio;
        this.destino = destino;
        this.grupo = grupo;
        this.familia = familia;
        Id_en_FUClave = id_en_FUClave;
        this.estado_clave = estado_clave;
        Id_en_FUPlanta = id_en_FUPlanta;
        this.observaciones = observaciones;
        this.descripcion = descripcion;
        this.figura = figura;
        this.lamina = lamina;
    }

    public criterioGeneral(int id, int nivel, int paso, int jerarquia, String indice, String criterio,
                           String destino, String grupo, String familia, int id_en_FUClave, String estado_clave,
                           int id_en_FUPlanta, String observaciones, String descripcion, String figura, String lamina) {
        this.Id = id;
        this.nivel = nivel;
        this.paso = paso;
        this.jerarquia = jerarquia;
        this.indice = indice;
        this.criterio = criterio;
        this.destino = destino;
        this.grupo = grupo;
        this.familia = familia;
        Id_en_FUClave = id_en_FUClave;
        this.estado_clave = estado_clave;
        Id_en_FUPlanta = id_en_FUPlanta;
        this.observaciones = observaciones;
        this.descripcion = descripcion;
        this.figura = figura;
        this.lamina = lamina;
    }

    public int getId_Auto() {
        return Id_Auto;
    }

    public void setId_Auto(int id_Auto) {
        Id_Auto = id_Auto;
    }

    public int getNumTabla() {
        return numTabla;
    }

    public void setNumTabla(int numTabla) {
        this.numTabla = numTabla;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
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

    public int getJerarquia() {
        return jerarquia;
    }

    public void setJerarquia(int jerarquia) {
        this.jerarquia = jerarquia;
    }

    public String getIndice() {
        return indice;
    }

    public void setIndice(String indice) {
        this.indice = indice;
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
        return Id_en_FUClave;
    }

    public void setId_en_FUClave(int id_en_FUClave) {
        Id_en_FUClave = id_en_FUClave;
    }

    public String getEstado_clave() {
        return estado_clave;
    }

    public void setEstado_clave(String estado_clave) {
        this.estado_clave = estado_clave;
    }

    public int getId_en_FUPlanta() {
        return Id_en_FUPlanta;
    }

    public void setId_en_FUPlanta(int id_en_FUPlanta) {
        Id_en_FUPlanta = id_en_FUPlanta;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFigura() {
        return figura;
    }

    public void setFigura(String figura) {
        this.figura = figura;
    }

    public String getLamina() {
        return lamina;
    }

    public void setLamina(String lamina) {
        this.lamina = lamina;
    }

    @NonNull
    @Override
    public String toString() {
        return "criterioGeneral{" +
                "Id=" + Id +
                ", nivel=" + nivel +
                ", paso=" + paso +
                ", jerarquia=" + jerarquia +
                ", indice='" + indice + '\'' +
                ", criterio='" + criterio + '\'' +
                ", destino='" + destino + '\'' +
                ", grupo='" + grupo + '\'' +
                ", familia='" + familia + '\'' +
                ", Id_en_FUClave=" + Id_en_FUClave +
                ", estado_clave='" + estado_clave + '\'' +
                ", Id_en_FUPlanta=" + Id_en_FUPlanta +
                ", observaciones='" + observaciones + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", figura='" + figura + '\'' +
                ", lamina='" + lamina + '\'' +
                ", Id_Auto=" + Id_Auto +
                ", numTabla=" + numTabla +
                '}';
    }

}
