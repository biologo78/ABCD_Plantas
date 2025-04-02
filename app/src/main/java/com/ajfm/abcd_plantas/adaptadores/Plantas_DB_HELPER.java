package com.ajfm.abcd_plantas.adaptadores;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

import java.io.IOException;

public class Plantas_DB_HELPER extends SQLiteAssetHelper {
    private static final String DB_NOMBRE = "Plantas20.sqlite";
    private static final int DB_VERSION = 1;
    public Plantas_DB_HELPER(Context context) throws IOException {
        super(context, DB_NOMBRE, null, DB_VERSION);
        Log.d("life", "Entrando en Plantas_DB.. constructor");
        Log.d("life", "Saliendo de Plantas_DB.. constructor");

    }
    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
        Log.d("life", "Entrando en Plantas_DB.. onOpen");
        Log.d("life", "Saliendo de Plantas_DB.. onOpen");

    }

    @Override
    public synchronized void close() {
        super.close();
        Log.d("life", "Entrando en Plantas_DB.. close");
        Log.d("life", "Saliendo de Plantas_DB.. close");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        newVersion = oldVersion + 1;
        db.execSQL("DROP TABLE IF EXISTS RAE_SIGNOS");
        onCreate(db);
        Log.d("life", "Entrando en Plantas_DB.. onUpgrade");
        Log.d("life", "Saliendo de Plantas_DB.. onUpgrade");

    }
}