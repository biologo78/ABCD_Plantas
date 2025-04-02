package com.ajfm.abcd_plantas.modelos;

public class familia_unificada {
    private int id_fu;
    private String famili1;
    private String famili2;
    private int num_gen;
    private int num_gen_clave;
    private int num_gen_planta;
    private int id_en_FUPlan;
    private int id_FUClav;
    private int esta_en_clave;
    private int esta_en_planta;
    private String clava_estado;
    private String planta_estado;
    private int linea;

    public familia_unificada(int id_fu, String famili1, String famili2, int num_gen, int num_gen_clave,
                             int num_gen_planta, int id_en_FUPlan, int id_FUClav, int esta_en_clave,
                             int esta_en_planta, String clava_estado, String planta_estado, int linea) {
        this.id_fu = id_fu;
        this.famili1 = famili1;
        this.famili2 = famili2;
        this.num_gen = num_gen;
        this.num_gen_clave = num_gen_clave;
        this.num_gen_planta = num_gen_planta;
        this.id_en_FUPlan = id_en_FUPlan;
        this.id_FUClav = id_FUClav;
        this.esta_en_clave = esta_en_clave;
        this.esta_en_planta = esta_en_planta;
        this.clava_estado = clava_estado;
        this.planta_estado = planta_estado;
        this.linea = linea;
    }

    public int getId_fu() {
        return id_fu;
    }

    public void setId_fu(int id_fu) {
        this.id_fu = id_fu;
    }

    public int getLinea() {
        return linea;
    }

    public void setLinea(int linea) {
        this.linea = linea;
    }

    public familia_unificada(int id_fu, String famili1, String famili2, int num_gen, int num_gen_clave,
                             int num_gen_planta, int id_en_FUPlan, int id_FUClav, int esta_en_clave,
                             int esta_en_planta, String clava_estado, String planta_estado) {
        this.id_fu = id_fu;
        this.famili1 = famili1;
        this.famili2 = famili2;
        this.num_gen = num_gen;
        this.num_gen_clave = num_gen_clave;
        this.num_gen_planta = num_gen_planta;
        this.id_en_FUPlan = id_en_FUPlan;
        this.id_FUClav = id_FUClav;
        this.esta_en_clave = esta_en_clave;
        this.esta_en_planta = esta_en_planta;
        this.clava_estado = clava_estado;
        this.planta_estado = planta_estado;
    }

    public String getFamili1() {
        return famili1;
    }

    public void setFamili1(String famili1) {
        this.famili1 = famili1;
    }

    public String getFamili2() {
        return famili2;
    }

    public void setFamili2(String famili2) {
        this.famili2 = famili2;
    }

    public int getNum_gen() {
        return num_gen;
    }

    public void setNum_gen(int num_gen) {
        this.num_gen = num_gen;
    }

    public int getNum_gen_clave() {
        return num_gen_clave;
    }

    public void setNum_gen_clave(int num_gen_clave) {
        this.num_gen_clave = num_gen_clave;
    }

    public int getNum_gen_planta() {
        return num_gen_planta;
    }

    public void setNum_gen_planta(int num_gen_planta) {
        this.num_gen_planta = num_gen_planta;
    }

    public int getId_en_FUPlan() {
        return id_en_FUPlan;
    }

    public void setId_en_FUPlan(int id_en_FUPlan) {
        this.id_en_FUPlan = id_en_FUPlan;
    }

    public int getId_FUClav() {
        return id_FUClav;
    }

    public void setId_FUClav(int id_FUClav) {
        this.id_FUClav = id_FUClav;
    }

    public int getEsta_en_clave() {
        return esta_en_clave;
    }

    public void setEsta_en_clave(int esta_en_clave) {
        this.esta_en_clave = esta_en_clave;
    }

    public int getEsta_en_planta() {
        return esta_en_planta;
    }

    public void setEsta_en_planta(int esta_en_planta) {
        this.esta_en_planta = esta_en_planta;
    }

    public String getClava_estado() {
        return clava_estado;
    }

    public void setClava_estado(String clava_estado) {
        this.clava_estado = clava_estado;
    }

    public String getPlanta_estado() {
        return planta_estado;
    }

    public void setPlanta_estado(String planta_estado) {
        this.planta_estado = planta_estado;
    }

    @Override
    public String toString() {
        return "familia_unificada{" +
                "id_fu=" + id_fu +
                ", famili1='" + famili1 + '\'' +
                ", famili2='" + famili2 + '\'' +
                ", num_gen=" + num_gen +
                ", num_gen_clave=" + num_gen_clave +
                ", num_gen_planta=" + num_gen_planta +
                ", id_en_FUPlan=" + id_en_FUPlan +
                ", id_FUClav=" + id_FUClav +
                ", esta_en_clave=" + esta_en_clave +
                ", esta_en_planta=" + esta_en_planta +
                ", clava_estado='" + clava_estado + '\'' +
                ", planta_estado='" + planta_estado + '\'' +
                ", linea=" + linea +
                '}';
    }
}
