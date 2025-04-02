package com.ajfm.abcd_plantas;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.ajfm.abcd_plantas.hilos.HiloAbrirBDD2;
import com.ajfm.abcd_plantas.modelos.IdsGenero;
import com.ajfm.abcd_plantas.modelos.especiesUnicas;
import com.ajfm.abcd_plantas.modelos.familia;
import com.ajfm.abcd_plantas.modelos.familia_unificada;
import com.ajfm.abcd_plantas.modelos.genero;
import com.ajfm.abcd_plantas.modelos.generos_sin_repetir;
import com.ajfm.abcd_plantas.modelos.grupo;
import com.ajfm.abcd_plantas.modelos.planta;
import com.ajfm.abcd_plantas.modelos.subespecies;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class SplashActivity extends AppCompatActivity {
    public static SQLiteDatabase todasPlantas;
    public static List<planta> allPlantasTotal;
    public static List<planta> allPlantasUsar;
    public static List<familia> allFamiliasClaves;
    public static List<grupo> allGrupos;
    public static List<especiesUnicas> allEspeciesUnicas;
    public static List<subespecies> allSubespecies;

    public static List<familia_unificada> allFamUnificadas;

    public static List<genero> allGeneros;
    public static List<IdsGenero> allGenerosUnic;
    public static List<generos_sin_repetir> allGenerosSR;
    public static List<familia> allFamiliasFichas;

    public final static String acero = "Vengo vacio";
    public static int idFamiliaGlobalPlanta = 0;
    public static int idFamiliaGlobalClave = 0;
    public static String nombFamiliaGlobalClave = acero;
    public static String nombFamiliaGlobalPlanta = acero;

    public static String nombGeneroGlobal = acero;
    public static String consultaGlobal = acero;

    public static int pasadas_ZZ_11 = 0;

    public static final String[] divisiones = new String[]{"Clave General", "Pteridophyta", "Gymnospermae", "Angiospermae"};

    //Esta consulta devuelve las familias que están en Clave pero no tienen géneros ni en Géneros ni en Planta
    /*public static String consObtenerFamiliassinnada ="SELECT DISTINCT Familias_unicas_Clave.Familia, " +
            "Generos.Genero, Familias_unicas_Clave.Id_en_FUPlanta " +
            "FROM Familias_unicas_Clave LEFT JOIN Generos ON Familias_unicas_Clave.Familia = Generos.Familia " +
            "GROUP BY Familias_unicas_Clave.Familia, Generos.Genero, Familias_unicas_Clave.Id_en_FUPlanta " +
            "HAVING (((Generos.Genero) Is Null) AND ((Familias_unicas_Clave.Id_en_FUPlanta)=0));";
*/
    public static String consParaFamUnificadas = "Select Familias_unificadas.Id, Familias_unificadas.Familia, " +
            "Familias_unificadas.Familia2, Familias_unificadas.Numero_de_generos, Familias_unificadas.Num_gener_Clave, " +
            "Familias_unificadas.Num_gener_Planta, Familias_unificadas.Id_en_FUPlanta, Familias_unificadas.Id_en_FUClave," +
            "Familias_unificadas.En_Clave, Familias_unificadas.En_Planta, Familias_unificadas.Clava_estado, " +
            "Familias_unificadas.Planta_estado, Familias_unificadas.Linea " +
            "from Familias_unificadas order by Familias_unificadas.id;";

    public static String consParaEspeciesUnicas = "select * from AACC_Especies_unidas_4 order by Id_en_su_tabla;";


    public static long primeraEspecie = 0;
    public static long ultimaEspecie = 0;
    public static long primeraSubEsp = 0;
    public static long ultimaSubEsp = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d("life", "Entrando en Splash onCreate");

        String consultaCrear = "Select Planta.Id_auto ,Planta.Id, " +
                "Planta.Nombre_cientifico, Planta.Nombre_vulgar, Planta.Familia, Planta.Grupo, " +
                "Planta.largo_cient, Planta.espacios_cient, Planta.largo_vulgar, Planta.espacios_vulgar, Planta.largo_familia," +
                "Planta.Foto_AA, Planta.Foto_BB, Planta.Id_en_FUClave, Planta.Estado_Planta, Planta.Id_en_FUPlanta " +
                "from Planta " +
                "order by Familia ASC, Nombre_cientifico ASC;";

        allPlantasTotal = obtenerTodo(consultaCrear);
        allPlantasUsar = new ArrayList<>(allPlantasTotal);

        allFamiliasClaves = new ArrayList<>();
        allFamiliasClaves = obtenerFamilias();

        allGrupos = new ArrayList<>();
        allGrupos = obtenerGrupos();

        allFamiliasFichas = new ArrayList<>();
        allFamiliasFichas = obtenerFamiliasFichas();

        allFamUnificadas = new ArrayList<>();
        allFamUnificadas = obtenerFamUnificadas(consParaFamUnificadas);

        allGeneros = new ArrayList<>();
        allGeneros = obtenerGeneros();

        allGenerosUnic = new ArrayList<>();
        allGenerosUnic = obtenerGenerosUnicos();

        allGenerosSR = new ArrayList<>();
        allGenerosSR = obtenerGenerosSinRep();

        allEspeciesUnicas = new ArrayList<>();
        allEspeciesUnicas = obtenerEspeciesUnicas();

        primeraEspecie = allEspeciesUnicas.get(0).getId();
        int numEU = allEspeciesUnicas.size();
        ultimaEspecie = primeraEspecie + numEU;

        allSubespecies = new ArrayList<>();
        allSubespecies = obtenerSubespecies();

        primeraSubEsp = allSubespecies.get(0).getId();
        int numSEU = allSubespecies.size();
        ultimaSubEsp = primeraSubEsp + numSEU;

        Log.d("topes", "Subespecies empiezan en " + primeraSubEsp + " llegan hasta " + (primeraSubEsp + numSEU));
        Log.d("topes", "Especies empiezan en " + primeraEspecie + " llegan hasta " + (primeraEspecie + numEU));

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.d("life", "en Splash iniciando intent hacia Main");
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
                startActivity(intent);
                finish();
                Log.d("life", "en Splash terminado intent hacia Main");
            }
        }, 1000);
        Log.d("life", "Saliendo de  Splash onCreate");

    }

    public List<planta> obtenerTodo(String consultaCrear) {// obtiene todo lo contenido en la tabla Planta
        Log.d("life", "Entrando en Splash obtenerTodo");
        List<planta> allPlants = new ArrayList<>();
        HiloAbrirBDD2 hilo2 = new HiloAbrirBDD2(todasPlantas, consultaCrear, SplashActivity.this);
        try {
            hilo2.start();
            //Esperando a que termine el hilo2 ==========================================================
            int imas = 0;
            while (hilo2.getState() != Thread.State.TERMINATED) {
                if (imas == 0) {
                    imas = 1;
                } else {
                    imas = 0;
                }
            }
        } catch (Exception e) {
            Log.d("error", "Error1 al abrir Planta " + e.toString() + " Cause. " + e.getCause().toString());
        }

        todasPlantas = hilo2.getDb_3();
        Cursor tPlCur = hilo2.getD();
        if (tPlCur.getCount() > 0) {
            tPlCur.moveToFirst();
            int cols = tPlCur.getColumnCount();
            int recuento = 0;
            Log.d("planta", " Hay " + tPlCur.getCount() + " registros, con  " + tPlCur.getColumnCount() + " columnas");

            for (int x = 0; x < tPlCur.getColumnCount(); x++) {
                Log.d("planta", "Columna " + x + " nombre: " + tPlCur.getColumnName(x));
            }

            while (!tPlCur.isAfterLast()) {
                int resIDAA = -1;
                int resIDBB = -1;
                String nombreAA = "";
                String nombreBB = "";

                try {
                    nombreAA = tPlCur.getString(11);
                    int largo = nombreAA.length() - 4;
                    nombreAA = nombreAA.substring(0, largo);

                    nombreBB = tPlCur.getString(12);
                    largo = nombreBB.length() - 4;
                    nombreBB = nombreBB.substring(0, largo);

                    try {
                        resIDAA = getResources().getIdentifier(nombreAA, "drawable", getPackageName());
                        resIDBB = getResources().getIdentifier(nombreBB, "drawable", getPackageName());
                    } catch (Exception e) {
                        Log.d("enSplash", "Error obteniendo el ID de  " + nombreAA + " o " + nombreBB + "  " + e);
                    }

                    if (resIDAA == 0) {
                        nombreAA = "flora/0";//"pexels_markus_winkler_19867368";
                        nombreBB = "flora/2";//"pexels_markus_winkler_19867368";

                        resIDAA = getResources().getIdentifier(nombreAA, "drawable", getPackageName());
                        resIDBB = getResources().getIdentifier(nombreBB, "drawable", getPackageName());
                        Log.d("enSplash", "Error: He cambiado a  " + nombreAA + " o " + nombreBB);
                        if (resIDAA == 0) {
                            Log.d("enSplash", "Error: No obtengo el ID de   " + nombreAA + " o " + nombreBB);
                        }
                    }

                    planta newPlanta = new planta(tPlCur.getInt(0), tPlCur.getInt(1), tPlCur.getString(2),
                            tPlCur.getString(3), tPlCur.getString(4), tPlCur.getString(5),
                            tPlCur.getInt(6), tPlCur.getInt(7), tPlCur.getInt(8),
                            tPlCur.getInt(9), tPlCur.getInt(10), tPlCur.getString(11),
                            tPlCur.getString(12), resIDAA, resIDBB, tPlCur.getInt(13),
                            tPlCur.getString(14), tPlCur.getInt(15));
                    allPlants.add(newPlanta);

                } catch (Exception e) {

                    Log.d("planta", "Recuento: " + recuento + " Error en getIdentifier de " + nombreAA +
                            " o " + nombreBB + " error e:" + e.toString());
                }
                recuento++;
                tPlCur.moveToNext();
            }
        } else {
            Toast.makeText(this, "No hay registros", Toast.LENGTH_SHORT).show();
            Log.d("error", "Error en hilo2 no hay registros");
        }
        Log.d("life", "Saliendo de Splash obtenerTodo");

        return allPlants;
    }

    private List<familia> obtenerFamilias() { // obtiene las familias que están en Clave
        Log.d("life", "Entrando en Splash obtenerFamilias");
        List<familia> allFams = new ArrayList<>();
        String consultaCrear = "Select  Familias_unicas_Clave.Id_auto, Familias_unicas_Clave.Familia, " +
                "Familias_unicas_Clave.Estado_Clave, Familias_unicas_Clave.Origen, Familias_unicas_Clave.Id_en_FUPlanta " +
                "from Familias_unicas_Clave " +
                "order by Familias_unicas_Clave.Familia ASC";
        HiloAbrirBDD2 hilo2 = new HiloAbrirBDD2(todasPlantas, consultaCrear, SplashActivity.this);
        try {
            hilo2.start();
            //Esperando a que termine el hilo2 ==========================================================
            int imas = 0;
            while (hilo2.getState() != Thread.State.TERMINATED) {
                if (imas == 0) {
                    imas = 1;
                } else {
                    imas = 0;
                }
            }
        } catch (Exception e) {
            Toast.makeText(SplashActivity.this, "Error al abrir Familias " + e.toString(), Toast.LENGTH_LONG).show();
            Log.d("error", "Error1 al abrir Familias " + e.toString() + " Cause. " + e.getCause().toString());
        }

        todasPlantas = hilo2.getDb_3();
        Cursor tPlCur = hilo2.getD();
        if (tPlCur.getCount() > 0) {
            tPlCur.moveToFirst();
            while (!tPlCur.isAfterLast()) {
                try {
                    familia newFam = new familia(tPlCur.getInt(0), tPlCur.getString(1),
                            tPlCur.getString(2), tPlCur.getString(3), tPlCur.getInt(4));
                    allFams.add(newFam);

                } catch (Exception e) {

                    Log.d("planta", " Error en recuperar zz_2_clave_clasificar error e:" + e.toString());
                }
                tPlCur.moveToNext();
            }
        } else {
            Toast.makeText(this, "No hay registros", Toast.LENGTH_SHORT).show();
            Log.d("error", "Error en hilo2 no hay registros");
        }
        Log.d("life", "Saliendo de Splash obtenerFamilias");
        return allFams;
    }

    private List<grupo> obtenerGrupos() { // Obtiene la clasificación de las fichas por Grupo
        Log.d("life", "Entrando en Splash obtenerGrupos");
        List<grupo> allGroup = new ArrayList<>();
        String consultaCrear = "Select Grupo_X_Fichas.Grupo from Grupo_X_Fichas " +
                "order by Grupo_X_Fichas.Grupo ASC";
        HiloAbrirBDD2 hilo2 = new HiloAbrirBDD2(todasPlantas, consultaCrear, SplashActivity.this);
        try {
            hilo2.start();
            //Esperando a que termine el hilo2 ==========================================================
            int imas = 0;
            while (hilo2.getState() != Thread.State.TERMINATED) {
                if (imas == 0) {
                    imas = 1;
                } else {
                    imas = 0;
                }
            }
        } catch (Exception e) {
            Toast.makeText(SplashActivity.this, "Error al abrir Grupos " + e.toString(), Toast.LENGTH_LONG).show();
            Log.d("error", "Error1 al abrir Grupos " + e.toString() + " Cause. " + e.getCause().toString());
        }

        todasPlantas = hilo2.getDb_3();
        Cursor tPlCur = hilo2.getD();
        if (tPlCur.getCount() > 0) {
            tPlCur.moveToFirst();
            while (!tPlCur.isAfterLast()) {
                try {
                    grupo newGrp = new grupo(0, tPlCur.getString(0));
                    allGroup.add(newGrp);

                } catch (Exception e) {

                    Log.d("planta", " Error en recuperar grupos error e:" + e.toString());
                }
                tPlCur.moveToNext();
            }
        } else {
            Toast.makeText(this, "No hay registros", Toast.LENGTH_SHORT).show();
            Log.d("error", "Error en hilo2 no hay registros");
        }

        Log.d("life", "Saliendo de Splash obtenerGrupos");
        return allGroup;
    }

    // Obtiene las familias que están en la tabla Familias_unicas_Planta
    private List<familia> obtenerFamiliasFichas() {
        Log.d("life", "Entrando en Splash obtenerFamiliasFichas");
        List<familia> listFamF = new ArrayList<>();
        String consultaCrear = "SELECT Familias_unicas_Planta.Id_auto, Familias_unicas_Planta.Familia, " +
                "Familias_unicas_Planta.Estado_Planta, Familias_unicas_Planta.Origen, Familias_unicas_Planta.Id_en_FUGeneros " +
                "from Familias_unicas_Planta " +
                "order by Familias_unicas_Planta.Familia";
        HiloAbrirBDD2 hilo2 = new HiloAbrirBDD2(todasPlantas, consultaCrear, SplashActivity.this);
        try {
            hilo2.start();
            //Esperando a que termine el hilo2 ==========================================================
            int imas = 0;
            while (hilo2.getState() != Thread.State.TERMINATED) {
                if (imas == 0) {
                    imas = 1;
                } else {
                    imas = 0;
                }
            }
        } catch (Exception e) {
            Toast.makeText(SplashActivity.this, "Error al abrir FamiliasFichas " + e.toString(), Toast.LENGTH_LONG).show();
            Log.d("error", "Error1 al abrir FamiliasFichas " + e.toString() + " Cause. " + e.getCause().toString());
        }
        todasPlantas = hilo2.getDb_3();
        Cursor tPlCur = hilo2.getD();
        if (tPlCur.getCount() > 0) {
            tPlCur.moveToFirst();
            while (!tPlCur.isAfterLast()) {
                try {
                    familia newFam = new familia(tPlCur.getInt(0), tPlCur.getString(1),
                            tPlCur.getString(2), tPlCur.getString(3), tPlCur.getInt(4));
                    allFamiliasFichas.add(newFam);

                } catch (Exception e) {

                    Log.d("planta", " Error en recuperar Familias_Fichas error e:" + e.toString());
                }
                tPlCur.moveToNext();
            }
        } else {
            Toast.makeText(this, "No hay registros", Toast.LENGTH_SHORT).show();
            Log.d("error", "Error en hilo2 no hay registros");
        }
        Log.d("life", "Saliendo de Splash obtenerFamiliasFichas");
        return allFamiliasFichas;
    }

    //Obtiene la lista de Familia_Unificadas
    private List<familia_unificada> obtenerFamUnificadas(String consu) {
        Log.d("life", "Entrando en Splash obtenerFamUnificadas");
        List<familia_unificada> listFamF = new ArrayList<>();
        HiloAbrirBDD2 hilo2 = new HiloAbrirBDD2(todasPlantas, consu, SplashActivity.this);
        try {
            hilo2.start();
            //Esperando a que termine el hilo2 ==========================================================
            int imas = 0;
            while (hilo2.getState() != Thread.State.TERMINATED) {
                if (imas == 0) {
                    imas = 1;
                } else {
                    imas = 0;
                }
            }
        } catch (Exception e) {
            Toast.makeText(SplashActivity.this, "Error al abrir FamiliasUnificadas " + e.toString(), Toast.LENGTH_LONG).show();
            Log.d("error", "Error1 al abrir FamiliasUnificadas " + e.toString() + " Cause. " + e.getCause().toString());
        }
        todasPlantas = hilo2.getDb_3();
        Cursor tPlCur = hilo2.getD();
        if (tPlCur.getCount() > 0) {
            tPlCur.moveToFirst();
            while (!tPlCur.isAfterLast()) {
                try {
                    familia_unificada newFam = new familia_unificada(tPlCur.getInt(0),
                            tPlCur.getString(1), tPlCur.getString(2), tPlCur.getInt(3),
                            tPlCur.getInt(4), tPlCur.getInt(5), tPlCur.getInt(6),
                            tPlCur.getInt(7), tPlCur.getInt(8), tPlCur.getInt(9),
                            tPlCur.getString(10), tPlCur.getString(11), tPlCur.getInt(12));

                    listFamF.add(newFam);

                } catch (Exception e) {

                    Log.d("familia", " Error en recuperar Familias_unificadas error e:" + e.toString());
                }
                tPlCur.moveToNext();
            }
        } else {
            Toast.makeText(this, "No hay registros en Familia_unificada", Toast.LENGTH_SHORT).show();
            Log.d("familia", "Error en hilo2 no hay registros en Familia_unificada");
        }
        Log.d("familia", "Hay " + listFamF.size() + " familias");
        Log.d("life", "Saliendo de Splash obtenerFamUnificadas");
        return listFamF;
    }

    //Obtiene los generos de la tabla Generos unicos que contiene los generos de Pteridophyta
    private List<IdsGenero> obtenerGenerosUnicos() {
        Log.d("life", "Entrando en Splash obtenerGenerosUnicos");
        List<IdsGenero> listGenUni = new ArrayList<>();
        String consultaCrear = "Select  *  from AABB_Generos_unidos_4  order by Nombre_Familia,  Nombre_Genero";
        HiloAbrirBDD2 hilo2 = new HiloAbrirBDD2(todasPlantas, consultaCrear, SplashActivity.this);
        try {
            hilo2.start();
            //Esperando a que termine el hilo2 ==========================================================
            int imas = 0;
            while (hilo2.getState() != Thread.State.TERMINATED) {
                if (imas == 0) {
                    imas = 1;
                } else {
                    imas = 0;
                }
            }
        } catch (Exception e) {
            Toast.makeText(SplashActivity.this, "Error al abrir Generos unicos " + e.toString(), Toast.LENGTH_LONG).show();
            Log.d("error", "Error1 al abrir Generos unicos " + e.toString() + " Cause. " + e.getCause().toString());
        }
        todasPlantas = hilo2.getDb_3();
        Cursor tPlCur = hilo2.getD();
        if (tPlCur.getCount() > 0) {
            tPlCur.moveToFirst();
            while (!tPlCur.isAfterLast()) {
                try {
                    IdsGenero newGen = new IdsGenero(tPlCur.getLong(0), tPlCur.getLong(1),
                            tPlCur.getString(2), tPlCur.getString(3),
                            tPlCur.getString(4), tPlCur.getString(5), tPlCur.getString(6),
                            tPlCur.getString(7), tPlCur.getString(8), tPlCur.getLong(9),
                            tPlCur.getInt(10));
                    listGenUni.add(newGen);

                } catch (Exception e) {

                    Log.d("planta", " Error en recuperar Generos_unicos error e:" + e.toString());
                }
                tPlCur.moveToNext();
            }
        } else {
            Toast.makeText(this, "No hay registros", Toast.LENGTH_SHORT).show();
            Log.d("error", "Error en hilo2 no hay registros");
        }
        Log.d("gen_uni", "Hay " + listGenUni.size() + " generos unicos");
        Log.d("life", "Saliendo de Splash obtenerGenerosUnicos");
        return listGenUni;
    }

    private List<generos_sin_repetir> obtenerGenerosSinRep() {
        Log.d("life", "Entrando en Splash obtenerGenerosSinRep");
        List<generos_sin_repetir> gSR = new ArrayList<>();
        HiloAbrirBDD2 hilo2 = getHiloAbrirBDD2();
        try {
            hilo2.start();
            //Esperando a que termine el hilo2 ==========================================================
            int imas = 0;
            while (hilo2.getState() != Thread.State.TERMINATED) {
                if (imas == 0) {
                    imas = 1;
                } else {
                    imas = 0;
                }
            }
        } catch (Exception e) {
            Toast.makeText(SplashActivity.this, "Error al abrir GenerosSR " + e.toString(), Toast.LENGTH_LONG).show();
            Log.d("error", "Error1 al abrir GenerosSR " + e.toString() + " Cause. " + e.getCause().toString());
        }
        todasPlantas = hilo2.getDb_3();
        Cursor tPlCur = hilo2.getD();
        if (tPlCur.getCount() > 0) {
            tPlCur.moveToFirst();
            while (!tPlCur.isAfterLast()) {
                try {
                    generos_sin_repetir newGen = new generos_sin_repetir(tPlCur.getLong(0), tPlCur.getString(1),
                            tPlCur.getInt(2), tPlCur.getString(3),
                            tPlCur.getString(4), tPlCur.getInt(5), tPlCur.getInt(6),
                            tPlCur.getInt(7), tPlCur.getInt(8));
                    gSR.add(newGen);

                } catch (Exception e) {

                    Log.d("planta", " Error en recuperar GenerosSR error e:" + e.toString());
                }
                tPlCur.moveToNext();
            }
        } else {
            Toast.makeText(this, "No hay registros", Toast.LENGTH_SHORT).show();
            Log.d("error", "Error en hilo2 no hay registros");
        }
        Log.d("life", "Saliendo de Splash obtenerGenerosSinRep");
        return gSR;
    }

    private List<especiesUnicas> obtenerEspeciesUnicas() {
        Log.d("life", "Entrando en Splash obtenerEspeciesUnicas");
        List<especiesUnicas> eU = new ArrayList<>();
        HiloAbrirBDD2 hilo2 = getHiloAbrirBDD2_eU();
        try {
            hilo2.start();
            //Esperando a que termine el hilo2 ==========================================================
            int imas = 0;
            while (hilo2.getState() != Thread.State.TERMINATED) {
                if (imas == 0) {
                    imas = 1;
                } else {
                    imas = 0;
                }
            }
        } catch (Exception e) {
            Toast.makeText(SplashActivity.this, "Error al abrir Especies unicas " + e.toString(), Toast.LENGTH_LONG).show();
            Log.d("error", "Error1 al abrir Especies unicas " + e.toString() + " Cause. " + e.getCause().toString());
        }
        todasPlantas = hilo2.getDb_3();
        Cursor tPlCur = hilo2.getD();
        int cont = 0;
        if (tPlCur.getCount() > 0) {
            tPlCur.moveToFirst();
            while (!tPlCur.isAfterLast()) {
                try {
                    especiesUnicas newGen = new especiesUnicas(tPlCur.getLong(0), tPlCur.getLong(1),
                            tPlCur.getString(2), tPlCur.getString(3), tPlCur.getString(4),
                            tPlCur.getString(5), tPlCur.getString(6), tPlCur.getString(7),
                            tPlCur.getString(8), tPlCur.getString(9),
                            tPlCur.getLong(10), tPlCur.getInt(11));
                    eU.add(newGen);
                    cont++;
                    Log.d("planta", "Añadidas " + cont + " especies.");
                } catch (Exception e) {
                    Log.d("planta", "Añadidas " + cont + " especies. Hay error en recuperar especies unicas error e:" + e.toString());
                }
                tPlCur.moveToNext();
            }
        } else {
            Toast.makeText(this, "No hay registros", Toast.LENGTH_SHORT).show();
            Log.d("error", "Error en hilo2 no hay registros");
        }
        Log.d("life", "Saliendo de Splash obtenerEspeciesUnicas");
        return eU;
    }

    public List<subespecies> obtenerSubespecies() {
        Log.d("life", "Entrando en Splash obtenerSubespecies");
        List<subespecies> eU = new ArrayList<>();
        HiloAbrirBDD2 hilo2 = getHiloAbrirBDD2_Sub();
        try {
            hilo2.start();
            //Esperando a que termine el hilo2 ==========================================================
            int imas = 0;
            while (hilo2.getState() != Thread.State.TERMINATED) {
                if (imas == 0) {
                    imas = 1;
                } else {
                    imas = 0;
                }
            }
        } catch (Exception e) {
            Toast.makeText(SplashActivity.this, "Error al abrir Subespecies unicas " + e.toString(), Toast.LENGTH_LONG).show();
            Log.d("error", "Error1 al abrir Subespecies unicas " + e.toString() + " Cause. " + e.getCause().toString());
        }
        todasPlantas = hilo2.getDb_3();
        Cursor tPlCur = hilo2.getD();

        if (tPlCur.getCount() > 0) {
            tPlCur.moveToFirst();
            while (!tPlCur.isAfterLast()) {
                try {
                    subespecies newGen = new subespecies(tPlCur.getLong(0), tPlCur.getLong(1),
                            tPlCur.getString(2), tPlCur.getString(3), tPlCur.getString(4),
                            tPlCur.getString(5), tPlCur.getString(6), tPlCur.getString(7),
                            tPlCur.getString(8), tPlCur.getString(9), tPlCur.getString(10),
                            tPlCur.getLong(11), tPlCur.getInt(12));
                    eU.add(newGen);

                } catch (Exception e) {

                    Log.d("planta", " Error en recuperar Subespecies unicas error e:" + e.toString());
                }
                tPlCur.moveToNext();
            }
        } else {
            Toast.makeText(this, "No hay registros", Toast.LENGTH_SHORT).show();
            Log.d("error", "Error en hilo2 no hay registros");
        }
        Log.d("life", "Saliendo de Splash obtenerSubespecies");
        return eU;
    }

    private @NonNull HiloAbrirBDD2 getHiloAbrirBDD2() {
        Log.d("life", "Entrando en Splash HiloA");
        String consu = "SELECT Generos_sin_repetir.Id, Generos_sin_repetir.Genero, Generos_sin_repetir.TotaldeId_auto, " +
                "Generos_sin_repetir.Familia, Generos_sin_repetir.Estado_Clave, Generos_sin_repetir.Id_en_FUClave, " +
                "Generos_sin_repetir.Angiospermae, Generos_sin_repetir.Gymnospermae, Generos_sin_repetir.Pteridophyta " +
                "from Generos_sin_repetir " +
                "order by Generos_sin_repetir.Id";
        HiloAbrirBDD2 hilo2 = new HiloAbrirBDD2(todasPlantas, consu, SplashActivity.this);
        Log.d("life", "Saliendo de Splash HiloA");
        return hilo2;
    }

    private @NonNull HiloAbrirBDD2 getHiloAbrirBDD2_eU() {
        Log.d("life", "Entrando en Splash getHilo..eU");
        Log.d("life", "Saliendo de Splash getHilo..eU");
        HiloAbrirBDD2 hilo2 = new HiloAbrirBDD2(todasPlantas, consParaEspeciesUnicas, SplashActivity.this);
        return hilo2;
    }

    private @NonNull HiloAbrirBDD2 getHiloAbrirBDD2_Sub() {
        Log.d("life", "Entrando en Splash getHilo..Sub");
        String consParaSubespecies = " select * from AADD_Subespecies_unidas_4 order by Id";
        HiloAbrirBDD2 hilo2 = new HiloAbrirBDD2(todasPlantas, consParaSubespecies, SplashActivity.this);
        Log.d("life", "Saliendo de Splash getHilo..Sub");
        return hilo2;
    }
    // Obtiene los generos de la tabla Generos donde estan todos los generos de las Claves

    private List<genero> obtenerGeneros() {
        Log.d("life", "Entrando en Splash obtenerGeneros");
        List<genero> listGen = new ArrayList<>();
        String consultaCrear = "SELECT Generos.Id_auto, Generos.Id, Generos.Genero, Generos.especie, " +
                "Generos.subespecie_variedad, Generos.Familia, Generos.Genero_y_especie, Generos.Id_en_FUPlanta, " +
                "Generos.Estado_Clave,  Generos.Id_en_FUClave " +
                "from Generos order by Generos.Genero, Generos.especie, Generos.subespecie_variedad";
        HiloAbrirBDD2 hilo2 = new HiloAbrirBDD2(todasPlantas, consultaCrear, SplashActivity.this);
        try {
            hilo2.start();
            //Esperando a que termine el hilo2 ==========================================================
            int imas = 0;
            while (hilo2.getState() != Thread.State.TERMINATED) {
                if (imas == 0) {
                    imas = 1;
                } else {
                    imas = 0;
                }
            }
        } catch (Exception e) {
            Toast.makeText(SplashActivity.this, "Error al abrir Generos " + e.toString(), Toast.LENGTH_LONG).show();
            Log.d("error", "Error1 al abrir Generos " + e.toString() + " Cause. " + e.getCause().toString());
        }
        todasPlantas = hilo2.getDb_3();
        Cursor tPlCur = hilo2.getD();
        if (tPlCur.getCount() > 0) {
            tPlCur.moveToFirst();
            while (!tPlCur.isAfterLast()) {
                try {
                    genero newGen = new genero(tPlCur.getInt(0), tPlCur.getInt(1),
                            tPlCur.getString(2), tPlCur.getString(3),
                            tPlCur.getString(4), tPlCur.getString(5), tPlCur.getString(6),
                            tPlCur.getInt(7), tPlCur.getString(8), tPlCur.getInt(9));
                    listGen.add(newGen);

                } catch (Exception e) {

                    Log.d("planta", " Error en recuperar Generos error e:" + e.toString());
                }
                tPlCur.moveToNext();
            }
        } else {
            Toast.makeText(this, "No hay registros", Toast.LENGTH_SHORT).show();
            Log.d("error", "Error en hilo2 no hay registros");
        }
        Log.d("life", "Saliendo de Splash obtenerGeneros");
        return listGen;
    }


}