package com.ajfm.abcd_plantas.modelos;

public class generos_sin_repetir {
    long Id;
    String Genero;
    int TotsldeId_auto;
    String Familia;
    String Estado_Clave;
    int Id_en_FUClave;
    int Angiospermae;
    int Gimnospermae;
    int Pteridophyta;

    public generos_sin_repetir(long id, String genero, int totsldeId_auto, String familia, String estado_Clave,
                               int id_en_FUClave, int angiospermae, int gimnospermae, int pteridophyta) {
        Id = id;
        Genero = genero;
        TotsldeId_auto = totsldeId_auto;
        Familia = familia;
        Estado_Clave = estado_Clave;
        Id_en_FUClave = id_en_FUClave;
        Angiospermae = angiospermae;
        Gimnospermae = gimnospermae;
        Pteridophyta = pteridophyta;
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getGenero() {
        return Genero;
    }

    public void setGenero(String genero) {
        Genero = genero;
    }

    public int getTotsldeId_auto() {
        return TotsldeId_auto;
    }

    public void setTotsldeId_auto(int totsldeId_auto) {
        TotsldeId_auto = totsldeId_auto;
    }

    public String getFamilia() {
        return Familia;
    }

    public void setFamilia(String familia) {
        Familia = familia;
    }

    public String getEstado_Clave() {
        return Estado_Clave;
    }

    public void setEstado_Clave(String estado_Clave) {
        Estado_Clave = estado_Clave;
    }

    public int getId_en_FUClave() {
        return Id_en_FUClave;
    }

    public void setId_en_FUClave(int id_en_FUClave) {
        Id_en_FUClave = id_en_FUClave;
    }

    public int getAngiospermae() {
        return Angiospermae;
    }

    public void setAngiospermae(int angiospermae) {
        Angiospermae = angiospermae;
    }

    public int getGimnospermae() {
        return Gimnospermae;
    }

    public void setGimnospermae(int gimnospermae) {
        Gimnospermae = gimnospermae;
    }

    public int getPteridophyta() {
        return Pteridophyta;
    }

    public void setPteridophyta(int pteridophyta) {
        Pteridophyta = pteridophyta;
    }

    @Override
    public String toString() {
        return "generos_sin_repetir{" +
                "Id=" + Id +
                ", Genero='" + Genero + '\'' +
                ", TotsldeId_auto=" + TotsldeId_auto +
                ", Familia='" + Familia + '\'' +
                ", Estado_Clave='" + Estado_Clave + '\'' +
                ", Id_en_FUClave=" + Id_en_FUClave +
                ", Angiospermae=" + Angiospermae +
                ", Gimnospermae=" + Gimnospermae +
                ", Pteridophyta=" + Pteridophyta +
                '}';
    }
}
