package com.ajfm.abcd_plantas.hilos;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.ajfm.abcd_plantas.adaptadores.Plantas_DB_HELPER;


public class HiloAbrirBDD2 extends Thread {
    private SQLiteDatabase db_3;
    private final Context cntx;
    private Plantas_DB_HELPER RAE_sg_Helper;
    public Cursor d;
    public String consultaCreadora;

    public Cursor getD() {
        Log.d("life", "Entrando en HiloAbr.. getD");
        Log.d("life", "Saliendo de HiloAbr.. getD");

        return d;
    }

    public HiloAbrirBDD2(SQLiteDatabase db_3, String consultaCreadora, Context cntx) {
        Log.d("life", "Entrando en HiloAbr.. constructor");
         this.db_3 = db_3;
        this.consultaCreadora = consultaCreadora;
        this.cntx = cntx;
        Log.d("life", "Saliendo de HiloAbr.. constructor");
    }

    public SQLiteDatabase getDb_3() {
        Log.d("life", "Entrando en HiloAbr.. getDb_3");
        Log.d("life", "Saliendo de HiloAbr.. getDb_3");

        return db_3;
    }

    @Override
    public void  run() {
        super.run();
        Log.d("life", "Entrando en HiloAbr.. run");

        try {
            RAE_sg_Helper = new Plantas_DB_HELPER(cntx);
            db_3 = RAE_sg_Helper.getWritableDatabase();
        } catch (Exception e) {
            Log.d("error", "Hasta los huevos " + e.toString());
        }

        try {
            d = db_3.rawQuery(consultaCreadora, null);
            Log.d("En HiloAbrirBDD2 consulta: ", consultaCreadora + " ha recuperado " + d.getCount() + " registros de " + db_3.getPath());
        } catch (Exception e) {
            Log.d("error", "Error al hacer la consulta linea 42 del Hilo2 --> " + e.toString());
        }
        Log.d("life", "Saliendo de HiloAbr.. run");

    }
}
