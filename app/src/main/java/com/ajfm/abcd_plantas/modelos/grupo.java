package com.ajfm.abcd_plantas.modelos;

public class grupo {
    private int mIdGrupo;
    private String mNombreGrupo;

    public grupo(int mIdGrupo, String mNombreGrupo) {
        this.mIdGrupo = mIdGrupo;
        this.mNombreGrupo = mNombreGrupo;
    }

    public int getmIdGrupo() {
        return mIdGrupo;
    }

    public void setmIdGrupo(int mIdGrupo) {
        this.mIdGrupo = mIdGrupo;
    }

    public String getmNombreGrupo() {
        return mNombreGrupo;
    }

    public void setmNombreGrupo(String mNombreGrupo) {
        this.mNombreGrupo = mNombreGrupo;
    }
}
