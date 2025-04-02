package com.ajfm.abcd_plantas.modelos;

public class subespecies {
    long Id;
    long Id_Familia;
    String Nombre_Familia;
    String Nombre_Genero;
    String Nombre_Especie;
    String Nombre_Subespecie;
    String Nombre_Grupo;
    String Figura;
    String Lamina;
    String Estado_Observ;
    String descripcion;
    long Id_Auto;
    int numTabla;

    public subespecies(long id, long id_Familia, String nombre_Familia, String nombre_Genero, String nombre_Especie,
                       String nombre_Subespecie, String nombre_Grupo, String figura, String lamina,
                       String estado_Observ, String descripcion, long id_Auto, int numTabla) {
        Id = id;
        Id_Familia = id_Familia;
        Nombre_Familia = nombre_Familia;
        Nombre_Genero = nombre_Genero;
        Nombre_Especie = nombre_Especie;
        Nombre_Subespecie = nombre_Subespecie;
        Nombre_Grupo = nombre_Grupo;
        Figura = figura;
        Lamina = lamina;
        Estado_Observ = estado_Observ;
        this.descripcion = descripcion;
        Id_Auto = id_Auto;
        this.numTabla = numTabla;
    }

    public long getId_Auto() {
        return Id_Auto;
    }

    public void setId_Auto(long id_Auto) {
        Id_Auto = id_Auto;
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
        return Id_Familia;
    }

    public void setId_Familia(long id_Familia) {
        Id_Familia = id_Familia;
    }

    public String getNombre_Familia() {
        return Nombre_Familia;
    }

    public void setNombre_Familia(String nombre_Familia) {
        Nombre_Familia = nombre_Familia;
    }

    public String getNombre_Genero() {
        return Nombre_Genero;
    }

    public void setNombre_Genero(String nombre_Genero) {
        Nombre_Genero = nombre_Genero;
    }

    public String getNombre_Especie() {
        return Nombre_Especie;
    }

    public void setNombre_Especie(String nombre_Especie) {
        Nombre_Especie = nombre_Especie;
    }

    public String getNombre_Subespecie() {
        return Nombre_Subespecie;
    }

    public void setNombre_Subespecie(String nombre_Subespecie) {
        Nombre_Subespecie = nombre_Subespecie;
    }

    public String getNombre_Grupo() {
        return Nombre_Grupo;
    }

    public void setNombre_Grupo(String nombre_Grupo) {
        Nombre_Grupo = nombre_Grupo;
    }

    public String getFigura() {
        return Figura;
    }

    public void setFigura(String figura) {
        Figura = figura;
    }

    public String getLamina() {
        return Lamina;
    }

    public void setLamina(String lamina) {
        Lamina = lamina;
    }

    public String getEstado_Observ() {
        return Estado_Observ;
    }

    public void setEstado_Observ(String estado_Observ) {
        Estado_Observ = estado_Observ;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public subespecies() {
    }

    @Override
    public String toString() {
        return "subespecies{" +
                "Id_Auto=" + Id_Auto +
                ", Id=" + Id +
                ", Id_Familia=" + Id_Familia +
                ", Nombre_Familia='" + Nombre_Familia + '\'' +
                ", Nombre_Genero='" + Nombre_Genero + '\'' +
                ", Nombre_Especie='" + Nombre_Especie + '\'' +
                ", Nombre_Subespecie='" + Nombre_Subespecie + '\'' +
                ", Nombre_Grupo='" + Nombre_Grupo + '\'' +
                ", Figura='" + Figura + '\'' +
                ", Lamina='" + Lamina + '\'' +
                ", Estado_Observ='" + Estado_Observ + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", numTabla=" + numTabla +
                '}';
    }
}
