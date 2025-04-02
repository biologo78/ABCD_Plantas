package com.ajfm.abcd_plantas.fragmentos;

import static com.ajfm.abcd_plantas.MainActivity.myViewPager2;
import static com.ajfm.abcd_plantas.SplashActivity.allEspeciesUnicas;
import static com.ajfm.abcd_plantas.SplashActivity.allFamUnificadas;
import static com.ajfm.abcd_plantas.SplashActivity.nombFamiliaGlobalClave;
import static com.ajfm.abcd_plantas.SplashActivity.todasPlantas;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ajfm.abcd_plantas.ModeloVision;
import com.ajfm.abcd_plantas.R;
import com.ajfm.abcd_plantas.adaptadores.MiCritGeneral;
import com.ajfm.abcd_plantas.hilos.HiloAbrirBDD2;
import com.ajfm.abcd_plantas.modelos.IdsGenero;
import com.ajfm.abcd_plantas.modelos.criterioGeneral;
import com.ajfm.abcd_plantas.modelos.especiesUnicas;
import com.ajfm.abcd_plantas.modelos.figura;
import com.ajfm.abcd_plantas.modelos.lamina;
import com.ajfm.abcd_plantas.modelos.subespecies;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class zz_2_clave_clasificar extends Fragment
        implements MiCritGeneral.OnItemClickListener {

    private TextView tV0;
    private TextView tV1;
    private TextView tV11;
    private TextView tV12;
    private TextView tV21;
    private TextView tV22;
    private TextView tV31;
    private TextView tV32;

    public List<criterioGeneral> allCriterios;
    public List<criterioGeneral> critSelectos2;
    //public List<String> fines;
    private List<Integer> fines;
    private List<especiesUnicas> especiesHalladas;
    private List<subespecies> subespeciesHalladas;

    public static String consSel = "";
    private String consuUltima = "";

    public static RecyclerView mRecyclerViewb;
    public static RecyclerView.LayoutManager mlayoutManagerb;
    public MiCritGeneral mAdapterC;
    public MiCritGeneral.OnItemClickListener mListener;
    private View v;
    private Button btnStart, btnBack;

    private String[] res;

    private List<String> unafg;

    private int nS = 0;
    private String tD = ">-<";
    private String tF = ">-<";
    private String tG = ">-<";
    private String tE = ">-<";
    private ScrollView scr2, scr1;

    private String pieLam = "";
    private String pieFig = "";
    private int resIdLam = 0;
    private int resIdFig = 0;

    private ModeloVision viewModel;
    private int origen;//almacena el num. de fragment de origen:
    // 0--> zz_0_Inicio_clasificar,
    // 2--> zz_2_clave_clasificar
    // 9--> zz_9_figuraminas
    private String consIni;
    private String consuStringIni = "";

    //====================================================
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(ModeloVision.class);
        Log.d("zz_2_life", "onCreate");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("zz_2_life", "zz_2... onCreateView, INICIO");
        Log.d("zz_2_life", "Valores actuales: tD:" + tD + "tF:" + tF + "tG:" + tG + "nS:" + nS + "origen:" + origen);

        View v = inflater.inflate(R.layout.fragment_zz_2_clave_clasificar, container, false);

        tV0 = (TextView) v.findViewById(R.id.tV0);
        btnStart = (Button) v.findViewById(R.id.btnStart);
        btnBack = (Button) v.findViewById(R.id.btnBack);

        tV1 = (TextView) v.findViewById(R.id.tV1);
        tV11 = (TextView) v.findViewById(R.id.tV11);
        tV12 = (TextView) v.findViewById(R.id.tV12);
        tV21 = (TextView) v.findViewById(R.id.tV21);
        tV22 = (TextView) v.findViewById(R.id.tV22);
        tV31 = (TextView) v.findViewById(R.id.tV13);
        tV32 = (TextView) v.findViewById(R.id.tV14);

        scr1 = (ScrollView) v.findViewById(R.id.scrollview);
        scr2 = (ScrollView) v.findViewById(R.id.scrollview1);

        mRecyclerViewb = (RecyclerView) v.findViewById(R.id.rV3);
        mRecyclerViewb.setHasFixedSize(true); //si sabemos que no va a zz_7_cambiar de tamaño
        mRecyclerViewb.setItemAnimator(new DefaultItemAnimator());

        Log.d("zz_2_life", "Valores actuales: tD:" + tD + "tF:" + tF + "tG:" + tG + "nS:" + nS + "origen:" + origen);
        Log.d("zz_2_life", "zz_2... onCreateView, FINAL\n");
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d("zz_2_life", "zz_2... onViewCreated, INICIO");
        Log.d("zz_2_life", "Valores actuales: tD:" + tD + "tF:" + tF + "tG:" + tG + "nS:" + nS + "origen:" + origen);

        tV31.setVisibility(View.INVISIBLE);
        tV32.setVisibility(View.INVISIBLE);
        tV31.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        tV32.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
        tV31.setGravity(Gravity.CENTER_VERTICAL);
        tV32.setGravity(Gravity.CENTER_VERTICAL);

        btnStart.setEnabled(false);
        btnBack.setEnabled(false);

        tV11.setOnClickListener(this::clickado1);
        tV12.setOnClickListener(this::clickado2);
        tV21.setOnClickListener(this::clickado3);
        tV22.setOnClickListener(this::clickado4);
        tV31.setOnClickListener(this::clickado5);

        scr1.setVisibility(View.VISIBLE);
        scr2.setVisibility(View.INVISIBLE);

        inicializarDatos();
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //cadenaIni();
                reinicioAtras(v);
            }
        });

        // Inicializar el ViewModel compartido
        viewModel = new ViewModelProvider(requireActivity()).get(ModeloVision.class);

        btnBack.setOnClickListener(v1 -> retroceso4(v1, 0));

        viewModel.getMuttextSelectD().observe(getViewLifecycleOwner(), tDel -> {
            tD = tDel;
        });
        viewModel.getMuttextSelectF().observe(getViewLifecycleOwner(), tFdel -> {
            tF = tFdel;
        });
        viewModel.getMuttextSelectG().observe(getViewLifecycleOwner(), tGdel -> {
            tG = tGdel;
        });
        viewModel.getMuttextSelectE().observe(getViewLifecycleOwner(), tEdel -> {
            tE = tEdel;
        });
        viewModel.getMutnumSelect().observe(getViewLifecycleOwner(), nSdel -> {
            nS = nSdel;
        });
        viewModel.getPieFig().observe(getViewLifecycleOwner(), pieL -> {
            pieLam = pieL;
        });
        viewModel.getPieLam().observe(getViewLifecycleOwner(), pieF -> {
            pieFig = pieF;
        });
        viewModel.getResIdFig().observe(getViewLifecycleOwner(), resIdF -> {
            resIdFig = resIdF;
        });
        viewModel.getResIdLam().observe(getViewLifecycleOwner(), resIdL -> {
            resIdLam = resIdL;
        });
        viewModel.getOrigen().observe(getViewLifecycleOwner(), torig -> {
            origen = torig != null ? torig : 2;
        });
        viewModel.getConsuIni().observe(getViewLifecycleOwner(), tconsIni -> {
            consIni = tconsIni;
        });
        viewModel.getTcritSelect2().observe(getViewLifecycleOwner(), tcritSel2 -> {
            critSelectos2 = tcritSel2;
        });
        viewModel.gettfines().observe(getViewLifecycleOwner(), tfines -> {
            fines = tfines;
        });
        Log.d("zz_2_life", "Valores actuales: tD:" + tD + "tF:" + tF + "tG:" + tG + "nS:" + nS + "origen:" + origen);
        Log.d("zz_2_life", "zz_2... onViewCreated, FINAL\n");
        }

    @Override
    public void onPause() {
        super.onPause();
        //ModeloVision viewModel = new ViewModelProvider(requireActivity()).get(ModeloVision.class);
        Log.d("zz_2_life", "zz_2... onPause, INICIO");
        Log.d("zz_2_life", "Valores actuales: tD:" + tD + "tF:" + tF + "tG:" + tG + "nS:" + nS + "origen:" + origen);
        viewModel.setOrigen(2);
        viewModel.setConsuIni(consuUltima);
        viewModel.setTcritSelect2(critSelectos2);
        viewModel.settfines(fines);

        for (int x = 0; x < fines.size(); x++) {
            Log.d("zz_2_life", "Salgo de zz_2 por onPause num: " + x + "	Texto de fines.get(x) --> " + fines.get(x));
        }
        Log.d("zz_2_life", "Valores actuales: tD:" + tD + "tF:" + tF + "tG:" + tG + "tE:" + tE + "nS:" + nS + "origen:" + origen);
        Log.d("zz_2_life", "zz_2... onPause, FINAL\n");
        }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("zz_2_life", "zz_2... onResume, INICIO");
        Log.d("zz_2_life", "Valores actuales: tD:" + tD + "tF:" + tF + "tG:" + tG + "tE:" + tE + "nS:" + nS + "origen:" + origen);
        if (origen == 0 || critSelectos2.isEmpty()) {
            consuStringIni = cadenaIni();
        } else {
            Log.d("txtfines", "onResume: consRetor size-->" + fines.size());
            for (int x = 0; x < fines.size(); x++) {
                Log.d("txtfines", "num: " + x + "\tTexto--> " + fines.get(x));
            }
            mAdapterC = new MiCritGeneral(critSelectos2, mListener, zz_2_clave_clasificar.this);
        }

        int origen = viewModel.getOrigen().getValue() != null ? viewModel.getOrigen().getValue() : 0;
        if (origen == 9) {
            consuStringIni = viewModel.getConsuIni().getValue();
        }
        allCriterios = cargarCriterios(consuStringIni);
        reiniciarFigLam();

        //String cons_para_758 = "Select * from AAAA_Clave_general_4 where Id < 759 order by Id;";
        //List<criterioGeneral> critFamilias = new ArrayList<>();
        //critFamilias = cargarCriterios(cons_para_758);

        //actualizarFam solo se usa una vez para rellenar el campo Id_en_FUC de la Clave general
        //con los ids de la familia hasta el 758
        //-->actualizarFam();
        //-->Toast.makeText(getActivity(),"Actualizado Id_en_FUC",Toast.LENGTH_LONG).show();

        //encontrarFamConUnGenerooCero solo se usa una vez para completar Estado_Clave con -1 si esa familia tiene un solo género o ninguno
        //y por tanto no tiene criterios abajo, o con 1 si tiene más de un género y hay criterios en la parte de abajo de la tabla de criterios
        //-->encontrarFamConUnGenerooCero();
        //-->Toast.makeText(getActivity(),"Actualizado Estado_Clave. Terminado",Toast.LENGTH_LONG).show();

        //actulizar  Estado_Clave, tomo los que tienen -2 (familias que no están en la 2ª parte)
        //y compruebo si tienen algún género en AABB_Generos_unidos_4 si es así le pongo -2
        //tambien comprubo si tiene alguna especie en AACC_Especies_unidas_4 si es así le pongo -3
        //si no tiene ningun género ni especie le pongo -1
        //También se debe ejecutar una sola vez al usar por primera vez la base Plantas12.sqlite
        //-->actualizarEstadoClave();
        //-->Toast.makeText(getActivity(),"Actualizado Estado_Clave segunda fase. Terminado",Toast.LENGTH_LONG).show();

        if (!allCriterios.isEmpty()) {//He encontrado criterios que se corresponden con las consulta
            //inical de clasificacion
            mlayoutManagerb = new LinearLayoutManager(getContext());
            mRecyclerViewb.setLayoutManager(mlayoutManagerb);
            mAdapterC = new MiCritGeneral(critSelectos2, mListener, zz_2_clave_clasificar.this);
            mRecyclerViewb.setAdapter(mAdapterC);
            mAdapterC.setOnItemClickListener((crit, position) -> {
                Log.d("retroceso", "=============================\nLinea 210 Click en paso " + position + " criterio: " + crit.getCriterio());
                for (int x = 0; x <= position; x++) {
                    retroceso4(v, 0);
                }
                Toast.makeText(getContext(), "Posicion: " + position + "\nCriterio:" + crit.getCriterio(), Toast.LENGTH_LONG).show();
            });

            scr2.setVisibility(View.INVISIBLE);
            scr1.setVisibility(View.VISIBLE);
            String s = "";
            tV1.setText(s);

            mostrandoCriterios();

        } else {//No hay ningun criterio que se corresponda con la consulta inicial de clasificacion
            //se supone que el criterio suminitrado en la selección previa me conduce  un elemento final
            //ya sea Genero, Especie, subEspecie no puede ser porque no se elige en los desplegables anteriores
            //Tendría que ver que tipo de final trae el criterio y buscar en su tabla el dato final
            //y despues mostrar los resultados.

            int idFam = obtenerClaveIdFU(tF);
            Log.d("busqueda", tE + " " + tF + " " + tG + " " + tD + " " + nS);

            String paraGen = "Select * from AABB_Generos_unidos_4 where idFuc = " + idFam + " and Nombre_Genero like '%" + tG + "%';";
            String paraEsp = "Select * from AACC_Especies_unidas_4 where id_Familia = " + idFam + " and Nombre_Genero like '%" + tG + "%' " +
                    " and  Nombre_Especie like '%" + tE + "%';";
            List<especiesUnicas> esp = new ArrayList<>();
            List<IdsGenero> gen = new ArrayList<>();
            esp = obtenerEspeciesUnicas(paraEsp);
            String tit1 = "";
            String tit2 = "";
            String figu = "";
            String lami = "";
            if (esp.isEmpty()) {
                gen = obtenerGenerosUnicos(paraGen);
                if (gen.isEmpty()) {
                    alertarG("La familia " + tF + " no se puede clasificar en esta versión de Flora Ibérica");
                    //Toast.makeText(getContext(), "No se encontraron resultados", Toast.LENGTH_LONG).show();
                    //return;
                } else {
                    figu = gen.get(0).getFiguraGen();
                    lami = gen.get(0).getLaminaGen();
                    tit1 = gen.get(0).getNombreFamilia() + "\n" + gen.get(0).getNombreGenero();
                    tit2 = gen.get(0).getDescripcion();
                }
            } else {
                figu = esp.get(0).getFigura();
                lami = esp.get(0).getLamina();
                tit1 = esp.get(0).getNombre_Familia() + "\n" + esp.get(0).getNombre_Genero() + "\n" + esp.get(0).getNombre_Especie();
                tit2 = esp.get(0).getDescripcion();
            }
            if (!esp.isEmpty() || !gen.isEmpty()) {
                comprobarFigLam(figu, lami);
                enviarAzz9();
                alertarF(tit1, tit2);
                scr1.setVisibility(View.INVISIBLE);
                scr2.setVisibility(View.VISIBLE);
                String s = tit1 + "\n" + tit2;
                tV1.setText(s);
                btnStart.setEnabled(true);
            }
            //Aqui se termina porque no hay mas criterios que mostrar
        }
        //A partir de aqui queda esperando un click en alguno de los tV o en los botones
        Log.d("zz_2_life", "Valores actuales: tD:" + tD + "tF:" + tF + "tG:" + tG + "nS:" + nS + "origen:" + origen);
        Log.d("zz_2_life", "zz_2... onResume FINAL\n");
        }

    @Override
    public void onDestroyView(){
        super.onDestroyView();
        Log.d("zz_2_life", "onDestroyView");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("zz_2_life", "onDestroy");
    }

    private void inicializarDatos() {
        //empiezo desde cero
        int nivel = 0;
        allCriterios = new ArrayList<>();
        fines = new ArrayList<>();
        critSelectos2 = new ArrayList<>();

        especiesHalladas = new ArrayList<>();
        subespeciesHalladas = new ArrayList<>();

        List<String> unafg = new ArrayList<>();

        res = new String[10];
        Arrays.fill(res, "");
        //res[0] --> División (Filas 1 a 4)
        //res[1] --> Familia (Filas 5 a 758)
        //res[2] --> Género (Filas 759 en adelante)
        //res[3] --> Descripcion del genero
        //res[4] --> Especie (Filas 759 en adelante)
        //res[5] --> Descripcion de la especie
        //res[6] --> Subespecie (Filas 759 en adelante)
        //res[7] --> Descripcion de la subespecie
        //res[8] --> Figuras
        //res[9] --> Láminas
    }

    private String cadenaIni() {
        String cIni = "";
        cIni = recuperarCadenaAjustada(nS, tD, tF, tG);
        Log.d("consuIni", cIni);
        Log.d("onde", "Salgo en onViewCreated zz_2");
        return cIni;
    }

    private List<criterioGeneral> cargarCriterios(String consStr) {
        List<criterioGeneral> allCrit = new ArrayList<>();

        //allCriterios va a contener los criterios que corresponden a la seleccion inicial de clasificacion
        //no incluye todos los criterios de la Clave general, solo lo relacionados con el inicio
        //está basada en el numSelect y los textSelect de Division, Familia y Género

        Log.d("criterios", "Consulta inicial:" + consStr);
        allCrit = recuperar_Clave_general(consStr, "");
        int hay = allCrit.size();
        //StringBuilder bbs = new StringBuilder();
        //bbs.append("NumSel: ").append(nSel).append(" TextSel:").append(forNfo);
        if (hay > 0) {
            int indFinal = allCrit.get(hay - 1).getId();
            int indIni = allCrit.get(0).getId();
            //bbs.append("\t\tHay ").append(hay).append(" indice inicial:").append(indIni).append(" final:").append(indFinal);
        }
        //else {
        //bbs.append("\t\tNo hay criterios");
        //}

        //Toast.makeText(getContext(), bbs.toString(), Toast.LENGTH_LONG).show();
        //Log.d("criterios", bbs.toString());
        return allCrit;
    }

    private void mostrandoCriterios() {
        //muestra el primer  criterio y su alternativa de la lista de allCriterios
        actualizartVs(0);
        String wph = allCriterios.get(0).getGrupo() + ". " + allCriterios.get(0).getFamilia();
        //tV0 es textBox de arriba del todo y muestra el grupo y familia
        tV0.setText(wph);
    }

    //Recupera todos los criterios de la tabla Clave_general segun la consulta basada en las opciones elegidas
    private List<criterioGeneral> recuperar_Clave_general(String consuStr, String textSl) {
        List<criterioGeneral> allCG = new ArrayList<>();
        if (!Objects.equals(consuStr, "")) {
            int counter = 0;

            try {
                Cursor cG = cursorClaveGeneral(consuStr);
                cG.moveToFirst();
                while (!cG.isAfterLast()) {
                    criterioGeneral critGen = new criterioGeneral(cG.getInt(0), cG.getInt(1), cG.getInt(2),
                            cG.getInt(3), cG.getString(4), cG.getString(5), cG.getString(6),
                            cG.getString(7), cG.getString(8), cG.getInt(9), cG.getString(10),
                            cG.getInt(11), cG.getString(12), cG.getString(13), cG.getString(14),
                            cG.getString(15), cG.getInt(16), cG.getInt(17));
                    allCG.add(critGen);
                    if (cG.getInt(1) == 52) {
                        Log.d("critgral", "Posicion:" + counter + " Criterio:" + critGen);
                    }
                    cG.moveToNext();
                    counter++;
                }
            } catch (Exception e) {
                Log.d("error", "Error al importar Clave_General");
            }
        } else {
            Toast.makeText(getContext(), "No hay criterios para " + textSl, Toast.LENGTH_LONG).show();
            allCG = null;
        }
        return allCG;
    }

    private int factorizarNSel(int nSel) {
        int div = 0;
        switch (nSel) {
            case 3:
            case 19:
            case 35:
            case 51:
                div = 1;
                break;
            case 5:
            case 21:
            case 37:
            case 53:
                div = 2;
                break;
            case 9:
            case 25:
            case 41:
            case 57:
                div = 3;
                break;
        }
        return div;
    }

    private String recuperarCadenaAjustada(int numSel, String tSelDiv, String tSelFam, String tSelGen) {

        //En funcion de las opciones elegidas tengo que obtener una cadena para la consulta que llene allCriterios
        //para empezar a clasificar, devuelvo la cadena con el select de los criterios que corresponden a ese inicio
        int divis = factorizarNSel(numSel);
        //String consOut = "";
        switch (numSel) {
            case 0:
                //consOut = "select * from  AAAA_Clave_general_4 order by Nivel,Jerarquia,Paso;";
                consSel = "select * from  AAAA_Clave_general_4 where jerarquia < 0 order by Nivel,Jerarquia,Observaciones, Paso;";
                break;
//si numSelect es = 1-> select * from Clave_general order by Nivel,Jerarquia,Paso;";                             IdInicial = 93295, hay 934 filas;
//si es = 2         --> select * from Clave_general order by Nivel,Jerarquia,Paso;";                             IdInicial = 93295, hay 934 filas;
//si es = 3         --> select * from Clave_general order by Nivel,Jerarquia,Paso;";                             IdInicial = 93295, hay 934 filas;
//si es = 4         --> select * from Clave_general order by Nivel,Jerarquia,Paso;";                             IdInicial = 93295, hay 934 filas;
            case 1://6 , 11, 16 corresponden con Buscar cualquier Pteri, Gymno, Angio. sin conocer Fam, ni Gen, ni Esp.
            case 3:
            case 5:
            case 9:
//si es = 6   --> select * from Clave_general where Grupo ='Pteridophyta' order by Nivel,Jerarquia,Paso;"; IdInicial = 93295, hay 314 filas;
//si es = 11  --> select * from Clave_general where Grupo ='Gymnospermae' order by Nivel,Jerarquia,Paso;"; IdInicial = 93387, hay 10 filass;
//si es = 16  --> select * from Clave_general where Grupo ='Angiospermae' order by Nivel,Jerarquia,Paso;"; IdInicial = 93393, hay 660 filas;
                consSel = " select * from  AAAA_Clave_general_4 where Nivel = " + divis + " order by Jerarquia, Paso;";
                break;
            case 16:
            case 19:
            case 21:
            case 25:
                //Corresponde con buscar P,G,A conociendo la Familia
//si es = 7  y Idfamilia = 100 --> select * from Clave_general where  Nivel = 100 order by Nivel,Jerarquia,Paso;"; IdInicial = 94053, hya 10 filas;
//si es = 12 y Idfamilia = 76 -->select * from Clave_general where  Nivel = 76 order by Nivel,Jerarquia,Paso;"; De momento no hay claves para clasificar familias de Gymn
//si es = 17 y Idfamilia = 102 ->select * from Clave_general where  Nivel = 102 order by Nivel,Jerarquia,Paso;";De momento no hay claves para clasificar familias de Angiospermas
                int FUC = obtenerIdFUClave(tSelFam);
                //consOut = "select * from AAAA_Clave_general_4 where  (Nivel = " + FUC + " and Jerarquia >0) order by Nivel,Jerarquia,Observaciones, Paso;";
                consSel = "select * from AAAA_Clave_general_4 where  (Nivel = " + FUC + ") order by Nivel,Jerarquia,Observaciones, Paso;";
                Cursor tres = cursorClaveGeneral(consSel);
                if (tres.getCount() == 0) {
                    //Familia con un solo género
                    boolean salida = false;
                    int counter = 0;
                    while (!salida && counter < allEspeciesUnicas.size()) {
                        long idEsp = allEspeciesUnicas.get(counter).getId_Familia();
                        if (FUC == idEsp) {
                            StringBuilder sb = new StringBuilder();
                            sb.append("La familia ").append(tSelFam).
                                    append(" tiene una sola especie: ").
                                    append(allEspeciesUnicas.get(counter).getNombre_Genero()).
                                    append("\n\nDescripción: ").
                                    append(allEspeciesUnicas.get(counter).getDescripcion());
                            Toast.makeText(getContext(), sb.toString(), Toast.LENGTH_LONG).show();
                            alertarE("", sb.toString());
                        }
                        counter++;
                    }
                }
                break;

            case 32:
            case 35:
            case 37:
            case 41:
                //Corresponde con busqueda conociendo el Género
                //si es = 13 y Género = 'Juniperus' --> select * from Clave_general where  Observaciones like '%Juniperus%'  order by Id; entonces busco ese género en
                //Destino -->
                //select * from Clave_general where  Destino like '%Juniperus%'  order by Id; Tampoco hay coincidencia porque no hay claves para ese Género
//si es = 18 y Género = 'Capsella' --> Tampoco da resultados ni en observaciones ni en Destino porque no clave para ese Género

                //si es = 8 y Género = 'Lycopodium' --> select * from Clave_general where  Observaciones like '%Lycopodium%'  order by Id;
                //si no hay coincidencia es que se trata de Género/Especie con una única especie/subespecie.ej
                //select * from Clave_general where  Observaciones like '%Huperzia%'  order by Id; da CERO resultados, entonces busco ese género en
                //Destino -->
                //select * from Clave_general where  Destino like '%Huperzia%'  order by Id; me da una coincidencia con Descripcion = 9572
                //busco en la tabla Especies únicas
                consSel = "select * from AAAA_Clave_general_4 where  Observaciones like '%" + tSelGen + "%'  order by Nivel,Jerarquia,Observaciones, Paso;";
                Cursor uno = cursorClaveGeneral(consSel);
                if (uno.getCount() == 0) {
                    //No encontrado el Género en Observaciones lo busco en Destino
                    String consDos = "select * from AAAA_Clave_general_4 where  Destino like '%" + tSelGen + "%'  order by Nivel,Jerarquia,Observaciones, Paso;";
                    Cursor dos = cursorClaveGeneral(consDos);
                    if (dos.getCount() == 0) {
                        //No encontrado en Destino no existen Claves para ese Género
                        Toast.makeText(getContext(), "No hay claves para ese Género", Toast.LENGTH_LONG).show();
                    } else {
                        //Hay al menos una coincidencia busco el dato que esté en Descripción (Id de Genero) en Especies_Unicas
                        dos.moveToFirst();
                        long idGen = dos.getLong(13);
                        boolean salida = false;
                        int counter = 0;
                        while (!salida && counter < allEspeciesUnicas.size()) {
                            long idEsp = allEspeciesUnicas.get(counter).getId();
                            if (idGen == idEsp) {
                                StringBuilder sb = new StringBuilder();
                                sb.append("Encontrado especie ").append("\nFamilia: ").append(allEspeciesUnicas.get(counter).getNombre_Familia()).
                                        append("\nGénero:").append(allEspeciesUnicas.get(counter).getNombre_Genero()).
                                        append("\nNombre especie y desvripción:");
                                alertarE(sb.toString(), allEspeciesUnicas.get(counter).getDescripcion());
                            }
                            counter++;
                        }
                    }
                }
                break;
            case 48:
            case 51:
            case 53:
            case 57:
                //Corresponde con busqueda conociendo la Especie, solo se muestran en el spinner las especies que tienen subespecie
                //si es  = 9 y Especie = 'I. velatum A. Braun....',
                //select * from Clave_general where  Observaciones like '%I. velatum A. Braun...%'  order by Id; me da dos coincidencias (dos subespecies)
                //la primera es por donde tengo que empezar

                consSel = "select * from AAAA_Clave_general_4 where  Observaciones like '%" + tSelGen + "%'  order by Nivel,Jerarquia,Observaciones, Paso;";
//si es 14 o 19 sigo el mismo procedimiento pero ahora mismo no hay Claves para esos casos.
                break;
        }
        return consSel;
    }

    private int obtenerIdFUClave(String nF) {
        boolean salir = false;
        String NF = nF.toUpperCase();
        int indix = 0;
        int salida = 0;
        while (!salir && indix < allFamUnificadas.size()) {
            String f1 = allFamUnificadas.get(indix).getFamili1();
            String f2 = allFamUnificadas.get(indix).getFamili2();
            if (f1.contains(NF) || f2.contains(NF)) {
                salida = allFamUnificadas.get(indix).getId_FUClav();
            }
            indix++;
        }
        return salida;
    }

    private int obtenerClaveIdFU(String nF) {
        boolean salir = false;
        String NF = nF.toUpperCase();
        int indix = 0;
        int salida = 0;
        while (!salir && indix < allFamUnificadas.size()) {
            String f1 = allFamUnificadas.get(indix).getFamili1();
            String f2 = allFamUnificadas.get(indix).getFamili2();
            if (NF.contains(f1) || NF.contains(f2)) {
                salida = allFamUnificadas.get(indix).getId_FUClav();
            }
            indix++;
        }
        return salida;
    }

    private Cursor cursorClaveGeneral(String consu) {
        HiloAbrirBDD2 hilo2 = getHiloAbrirBDD2_ClaveGen(consu);
        Cursor cCF = null;
        try {
            hilo2.start();
            //Esperando a que termine el hilo2 ==========================================================
            int imas = 0;
            while (hilo2.getState() != Thread.State.TERMINATED) {
                if (imas > 0) {
                    imas++;
                } else {
                    imas += 2;
                }
            }
        } catch (Exception e) {
            Log.d("Error1", "Error1 al abrir Clave_Familias " + e);
        }
        try {
            cCF = hilo2.getD();
            Log.d("grabando", "Obtenido cursor de Clave_Familias con " + cCF.getCount() + " regitros");
        } catch (Exception e) {
            Log.d("Error1", "Error al obtener cursor de Clave_Familias" + e);

        }
        return cCF;
    }

    private @NonNull HiloAbrirBDD2 getHiloAbrirBDD2_ClaveGen(String consuCG) {
        HiloAbrirBDD2 hilo3;
        hilo3 = new HiloAbrirBDD2(todasPlantas, consuCG, getContext());
        return hilo3;
    }

    /*
    private void reinicio(View v, String consIni, String fIfo, int nSel) {
        tV31.setVisibility(View.INVISIBLE);
        allCriterios = cargarCriterios(consIni);
        actualizartVs(0);
        critSelectos2.clear();
        fines.clear();
        mAdapterC.notifyItemRangeChanged(0, critSelectos2.size());
        critSelectos2.add(0, allCriterios.get(0));
        mAdapterC.notifyItemInserted(0);
        fines.add(0, consIni);
        Log.d("txtSelectos", "sP5 Reini Criterios: size-->" + critSelectos2.size() + " Id-->" +
                critSelectos2.get(0).getId() + " Dest-->" + critSelectos2.get(0).getDestino() +
                "\t\tConsulta: size-->" + fines.size() + " Texto--> " + fines.get(0));
        //mAdapterC.notify();
        myViewPager2.setCurrentItem(0);
    }
*/
    private void reinicioAtras(View v) {
        allCriterios.clear();
        scr1.setVisibility(View.VISIBLE);
        scr2.setVisibility(View.INVISIBLE);
        tV31.setVisibility(View.INVISIBLE);
        critSelectos2.clear();
        fines.clear();
        if (mAdapterC != null) {
            mAdapterC.notifyItemRangeChanged(0, critSelectos2.size());
        }
        myViewPager2.setCurrentItem(0);
    }

    private void retroceso5(View v, int inici) {
        String s = "Select * from AAAA_Clave_general_4 where (Id = " + inici + " or Id = " + (inici + 1) + ") order by Id ";
        allCriterios = cargarCriterios(s);
        tV1.setText("");
        actualizartVs(0);
        scr1.setVisibility(View.VISIBLE);
        scr2.setVisibility(View.INVISIBLE);
    }

    private void retroceso4(View v, int pos) {
        Log.d("retroceso", "Retroceder a posición: " + pos);
        int bor = fines.size();
        if (bor != 0) {
            allCriterios.clear();
            String indice = critSelectos2.get(pos).getIndice();
            String famil = critSelectos2.get(pos).getFamilia();
            int idSelec = critSelectos2.get(pos).getId();
            int idFami = critSelectos2.get(pos).getId_en_FUClave();
            int tipoFin = fines.get(pos);
            int inic = 0;
            String s = "";

            switch (tipoFin) {
                case -1://Sigo clasificando, pulsó en el cuadro de abajo
                    inic = idSelec - 1;
                    retroceso5(v, inic);
                    break;
                case 0://Sigo clasificando, pulsó en el cuadro de arriba
                    inic = idSelec;
                    retroceso5(v, inic);
                    break;
                case 1://Terminó de clasificar en especie
                    /*String nomEsp = critSelectos2.get(pos).getDestino();
                    s = "Select * from AACC_Especies_unidas_4 where Nombre_especie like '%" + nomEsp + "%' and Id_Familia = " + idFami +
                            " order by Id ";
                    List<especiesUnicas> eU = obtenerEspeciesUnicas(s);
                    especiesUnicas eH = new especiesUnicas();

                    if (eU.size() == 1) {//He encontrado una única especie
                        eH = eU.get(0);
                        res[4] = eH.getNombre_Especie();
                        res[5] = eH.getDescripcion();
                        res[8] = eH.getFigura();
                        res[9] = eH.getLamina();
                        res[0] = eH.getNombre_Grupo();
                        res[0] = res[0].toUpperCase();
                        res[1] = eH.getNombre_Familia();
                        int i = trocearDescripcion(eH.getDescripcion());
                        if (i > 0) {
                            res[2] = eH.getNombre_Genero().substring(0, i - 1);
                            res[3] = eH.getDescripcion().substring(i);
                        } else {
                            res[2] = eH.getNombre_Genero();
                            res[3] = eH.getDescripcion();
                        }
                    }
                    prepararMessg();*/
                    break;
                case 2://Terminó de clasificar en subespecie




                    /*String nomSubEsp = critSelectos2.get(pos).getDestino();
                    s = "Select * from AADD_Subespecies_unidas_4 where Nombre_Subespecie like '%" + nomSubEsp + "%' and Id_Familia = " + idFami +
                            " order by Id ";
                    List<subespecies> seU = obtenerSubespecies(s);
                    subespecies seH = new subespecies();

                    if (seU.size() == 1) {//He encontrado una única especie
                        seH = seU.get(0);
                        res[4] = seH.getNombre_Especie();
                        res[5] = seH.getDescripcion();
                        res[8] = seH.getFigura();
                        res[9] = seH.getLamina();
                        res[0] = seH.getNombre_Grupo();
                        res[0] = res[0].toUpperCase();
                        res[1] = seH.getNombre_Familia();
                        int i = trocearDescripcion(seH.getDescripcion());
                        if (i > 0) {
                            res[6] = seH.getDescripcion().substring(0, i - 1);
                            res[7] = seH.getDescripcion().substring(i);
                        } else {
                            res[6] = seH.getNombre_Subespecie();
                            res[7] = seH.getDescripcion();
                        }
                    }
                    prepararMessg();*/
                    break;
                case 3://Ejemplar no encontrado en las claves
                    String messg = "La familia: " + famil + " no está incluida en Flora Ibérica";
                    String messg2 = famil + " no incluida ";
                    alertarF(messg, messg2);
                    scr1.setVisibility(View.INVISIBLE);
                    scr2.setVisibility(View.VISIBLE);
                    s = messg + "\n" + messg2;
                    tV1.setText(s);
                    btnStart.setEnabled(true);
                    break;
                case 4://No hay claves para clasificar
                    messg = "El ejemplar con esas características no está incluido en Flora Ibérica";
                    messg2 = "Ejemplar no conocido en Flora Ibérica";
                    alertarF(messg, messg2);
                    scr1.setVisibility(View.INVISIBLE);
                    scr2.setVisibility(View.VISIBLE);
                    s = messg + "\n" + messg2;
                    tV1.setText(s);
                    btnStart.setEnabled(true);
                    break;
            }
            critSelectos2.remove(0);
            fines.remove(0);
            mAdapterC.notifyItemRemoved(0);
            mlayoutManagerb.findViewByPosition(0);
        }
    }

    private void retroceso3(View v, int pos) {
        Log.d("retroceso", "Retroceder a posición: " + pos);
        int bor = fines.size();
        if (bor != 0) {
            allCriterios.clear();
            String s = tD + ":" + tF + ":" + tG + ":" + tE;
            tV1.setText(s);
            //String recup = fines.get(pos);
            String indice = critSelectos2.get(pos).getIndice();
            int idSelec = critSelectos2.get(pos).getId();
            //allCriterios = recuperar_Clave_general(recup, "n");
            int posiClv = allCriterios.indexOf(allCriterios.stream().filter(p -> p.getId() == idSelec).
                    findFirst().orElse(null));
            if (indice.length() < 2) {
                posiClv -= 1;
            }
            actualizartVs(posiClv);

            for (int x = 0; x <= pos; x++) {
                Log.d("retroceso", x + " eliminando :" + critSelectos2.get(0).getDestino() + " y " +
                        fines.get(0));
                fines.remove(0);
                critSelectos2.remove(0);
                mAdapterC.notifyItemRemoved(0);
                mlayoutManagerb.findViewByPosition(0);
            }

            for (int i = 0; i < fines.size() - 1; i++) {
                Log.d("dosele", i + " --> " + critSelectos2.get(i).getDestino() + "--> " + fines.get(i));
            }
        }
    }


    private void clickado1(View v) {
        try {
            int pos1 = (int) tV11.getTag();
            seguirPoniendo5(pos1, 0);
        } catch (Exception e) {
            Log.d("seguir", e.toString());
        }
    }

    private void clickado2(View v) {
        try {
            int pos1 = (int) tV12.getTag();
            seguirPoniendo5(pos1, 0);
        } catch (Exception e) {
            Log.d("seguir", e.toString());
        }
    }

    private void clickado3(View v) {
        try {
            int pos1 = (int) tV21.getTag();
            seguirPoniendo5(pos1, -1);
        } catch (Exception e) {
            Log.d("seguir", e.toString());
        }
    }

    private void clickado4(View v) {
        try {
            int pos1 = (int) tV22.getTag();
            seguirPoniendo5(pos1, -1);
        } catch (Exception e) {
            Log.d("tryerror", e.toString());
        }
    }

    private void clickado5(View v) {
        enviarAzz9();
        myViewPager2.setCurrentItem(2);
    }

    private void reiniciarFigLam() {
        String abre = "Abreviaturas";
        String flora = "Flora Ibérica";
        assert getActivity() != null;
        int resIdFlora = getActivity().getResources().getIdentifier("lam_a", "drawable", getActivity().getPackageName());
        int resIdAbrv = getActivity().getResources().getIdentifier("lam_b", "drawable", getActivity().getPackageName());

        //ModeloVision viewModel = new ViewModelProvider(requireActivity()).get(ModeloVision.class);
        viewModel.setPieFig(abre);
        viewModel.setresIdFig(resIdAbrv);
        viewModel.setPieLam(flora);
        viewModel.setresIdLam(resIdFlora);
    }

    private void enviarAzz9() {
        Log.d("unico", "zz_9_figuraminas - enviarAzz9 INICIO");
        String titLam = "N";
        String ficLam = "N";
        int resLam = 0;
        String titFig = unafg.get(0);
        String ficFig = unafg.get(1);
        int resFig = Integer.parseInt(unafg.get(2));
        int llevo = 3;
        if (unafg.size() > 3) {
            titLam = unafg.get(3);
            ficLam = unafg.get(4);
            resLam = Integer.parseInt(unafg.get(5));
            llevo = 6;
        } else {
            titLam = "N";
            ficLam = "N";
        }

        //ModeloVision viewModel = new ViewModelProvider(requireActivity()).get(ModeloVision.class);
        viewModel.setMutcriterios(critSelectos2);
        viewModel.setMutfines(fines);

        viewModel.setPieFig(titFig);
        viewModel.setresIdFig(resFig);
        viewModel.setPieLam(titLam);
        viewModel.setresIdLam(resLam);
    }


    private void comprobarFigLamCrit(criterioGeneral critActual) {
        //Log.d("comprobarfig", critActual.getFigura() + " " + critActual.getLamina());
        String fig = critActual.getFigura();
        String lam = critActual.getLamina();
        comprobarFigLam(fig, lam);
    }

    private void comprobarFigLam(String fig, String lam) {
        //String fig = critActual.getFigura();
        //String lam = critActual.getLamina();
        if (fig != null && !fig.isEmpty()) {
            fig = fig.replace("counter fig", "");
            boolean hayFig = !fig.equals("Pendiente");
            if (hayFig) {
                tV31.setVisibility(View.VISIBLE);
                unafg = ripFig(fig);
                //figuras++;
            }
        }
        if (lam != null && !lam.isEmpty()) {
            lam = lam.replace("counter lam", "");
            boolean hayLam = !lam.equals("Pendiente");
            if (hayLam) {
                tV31.setVisibility(View.VISIBLE);
                unafg = ripLam(lam);
                //laminas++;
            }
        }
        if (lam == null && fig == null) {
            tV31.setVisibility(View.INVISIBLE);
        }
    }

    private List<String> ripFig(String figra) {
        List<String> figs = new ArrayList<>();
        String nomFig = "";
        String txtFig = "";
        int numeroFig = 0;

        //Log.d("comprobarfig", "Figura: " + figura);
        int parenthesisIndex = figra.indexOf('(');
        int bracketIndex = figra.indexOf('[');
        int closeParenthIndx = figra.indexOf(')');
        int closeBracketIndx = figra.indexOf(']');
        int mediumIndex = figra.indexOf(':');

        int firstIndex = Math.max(parenthesisIndex, bracketIndex); // Si ambos son -1, firstIndex será -1
        int lastIndex = Math.max(closeParenthIndx, closeBracketIndx); // Si ambos son -1, lastIndex será -1

        if (firstIndex != -1) {
            txtFig = "Figura " + figra.substring(firstIndex + 6, lastIndex);
        }
        if (mediumIndex != -1) {
            nomFig = "fig_" + figra.substring(firstIndex + 6, mediumIndex);
        } else {
            nomFig = "fig_" + figra.substring(firstIndex + 6, lastIndex);
        }

        numeroFig = Integer.parseInt(nomFig.substring(4));
        List<figura> allFiguras = new ArrayList<>();
        allFiguras = obtenerFig("select * from Figuras where NumFig = " + numeroFig + ";");
        if (!allFiguras.isEmpty()) {
            figura fig = allFiguras.get(0);
            nomFig = "fig_" + fig.getNumFig();
            txtFig = fig.getTitulo();
        } else {
            nomFig = "N";
            txtFig = "N";
        }

        Resources resource = requireContext().getResources();
        assert getActivity() != null;
        int resID = resource.getIdentifier(nomFig, "drawable", getActivity().getPackageName());
        figs.add(txtFig);//titulo
        figs.add(nomFig);//fichero
        figs.add(Integer.toString(resID));//id
        // + "=== nomFig" + nomFig + "===" + resID + "<-----" + figura);.(txtFig);
        //figs.add("numeroFig ===" + numeroFig + "=== nomFig" +  nomFig + "===" + resID + "<-----" + figura);
        //for (int i = 0; i < figs.size(); i++) {
        //    Log.d("comprobarfig", "Figura===" + figs.get(i));
        //}
        return figs;
    }

    private List<String> ripLam(String lamina) {
        List<String> laminas = new ArrayList<>();
        String nomLam = "N";
        String txtLam = "N";
        String nomFig = "N";
        String txtFig = "N";
        int numeroLam = 0;
        int numeroFig = 0;

        int i2 = 0;

        int i0 = lamina.indexOf('[');
        int i5 = lamina.indexOf(']');
        int i1 = lamina.indexOf(';');
        int i4 = lamina.indexOf(':');
        int i3 = lamina.indexOf("fig. ");

        if (i0 != -1) {//Hay [
            i0 = i0 + 6;
            int i = i0;
            char character = lamina.charAt(i);
            while (!Character.isLetter(character) &&
                    (i < i5 || i < i1)) {
                i++;
                character = lamina.charAt(i);
            }
            i2 = i;
            if (i1 != -1) {//Hay punto y coma
                int i6 = Math.min(i2, i1);
                nomLam = "lam_" + lamina.substring(i0, i6);
                txtLam = "Lámina " + lamina.substring(i0, i1);
                numeroLam = Integer.parseInt(lamina.substring(i0, i6));

                //Ahora tengo que buscar fig. y :
                if (i3 != -1) {//Hay fig.
                    if (i4 != -1) {//Hay :
                        nomFig = "fig_" + lamina.substring(i3 + 5, i4);
                        txtFig = "Figura " + lamina.substring(i3 + 5, i4);
                        numeroFig = Integer.parseInt(lamina.substring(i3 + 5, i4));
                    } else {//No hay :
                        nomFig = "fig_" + lamina.substring(i3 + 5, i5);
                        txtFig = "Figura " + lamina.substring(i3 + 5, i5);
                        numeroFig = Integer.parseInt(lamina.substring(i3 + 5, i5));
                    }
                } else {//No hay figura
                    i3 = lamina.indexOf("fig.");
                    if (i4 != -1) {//Hay :
                        nomFig = "fig_" + lamina.substring(i3 + 5, i4);
                        txtFig = "Figura " + lamina.substring(i3 + 5, i4);
                        numeroFig = Integer.parseInt(lamina.substring(i3 + 5, i4));
                    } else {//No hay :
                        nomFig = "fig_" + lamina.substring(i3 + 5, i2);
                        txtFig = "Figura " + lamina.substring(i3 + 5, i2);
                        numeroFig = Integer.parseInt(lamina.substring(i3 + 5, i2));
                    }
                }
            } else {//No hay punto y coma
                int i6 = Math.min(i2, i5);
                nomLam = "lam_" + lamina.substring(i0, i6);
                txtLam = "Lámina " + lamina.substring(i0, i6);
            }

            int haycoma = nomLam.indexOf(',');
            if (haycoma != -1) {//Hay coma ,
                nomLam = nomLam.substring(0, haycoma);
                numeroLam = Integer.parseInt(nomLam.substring(4, haycoma));

            } else {//No hay coma ,
                numeroLam = Integer.parseInt(nomLam.substring(4));
            }
        }

        List<figura> allFiguras = new ArrayList<>();
        allFiguras = obtenerFig("select * from Figuras where NumFig = " + numeroFig + ";");
        if (!allFiguras.isEmpty()) {
            figura fig = allFiguras.get(0);
            nomFig = "fig_" + fig.getNumFig();
            txtFig = fig.getTitulo();
        }
        Resources resource = requireContext().getResources();
        assert getActivity() != null;
        int resID = resource.getIdentifier(nomFig, "drawable", getActivity().getPackageName());
        laminas.add(txtFig);//titulo
        laminas.add(nomFig);//fichero
        laminas.add(Integer.toString(resID));//id
        //figs.add("numeroFig ===" + numeroFig + "=== nomFig" + nomFig + "===" + resID + "===" + txtFig + "<-----" + lamina);

        if (!nomLam.isEmpty()) {
            List<lamina> allLaminas = new ArrayList<>();
            allLaminas = obtenerLam("select * from Laminas2 where NumLam = " + numeroLam + ";");
            if (!allLaminas.isEmpty()) {
                lamina lam = allLaminas.get(0);
                nomLam = "lam_" + lam.getNumFichero();
                txtLam = lam.getTitulo();
            }
            resource = requireContext().getResources();
            assert getActivity() != null;
            resID = resource.getIdentifier(nomLam, "drawable", getActivity().getPackageName());
            laminas.add(txtLam);//titulo
            laminas.add(nomLam);//fichero
            laminas.add(Integer.toString(resID));//id

            //laminas.add("numeroLam ===" + numeroLam + "=== nomLam" + nomLam + "===" + resID + "===" + txtLam + "<-----" + lamina);
            //laminas.add(nomLam +"===" + txtLam);
        }


        /*for (int i = 0; i < figs.size(); i++) {
            Log.d("listafig", "Figura===" + figs.get(i) + "===");
        }
        for (int i = 0; i < laminas.size(); i++) {
            Log.d("listalam", "Lámina===" + laminas.get(i) +"===");
        }*/
        return laminas;
    }


    private List<figura> obtenerFig(String cons) {
        List<figura> fg = new ArrayList<>();
        HiloAbrirBDD2 hilo2 = new HiloAbrirBDD2(todasPlantas, cons, getActivity());
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
            Toast.makeText(getActivity(), "Error al abrir Figuras " + e, Toast.LENGTH_LONG).show();
            Log.d("tryError", "Error1 al abrir Figuras " + e + " Cause. " + e.getCause());
        }
        todasPlantas = hilo2.getDb_3();
        Cursor tPlCur = hilo2.getD();
        int cont = 0;
        if (tPlCur.getCount() > 0) {
            tPlCur.moveToFirst();
            while (!tPlCur.isAfterLast()) {
                try {
                    figura newFig = new figura(tPlCur.getInt(0), tPlCur.getInt(1),
                            tPlCur.getString(2), tPlCur.getString(3));
                    fg.add(newFig);
                    cont++;
                } catch (Exception e) {
                    Log.d("tryError", e.toString());
                }
                tPlCur.moveToNext();
            }
        } else {
            Toast.makeText(getActivity(), "No hay figuras", Toast.LENGTH_SHORT).show();
            Log.d("tryError", "Error en hilo2 no hay figuras");
        }
        Log.d("life", "Saliendo de Splash obtenerfiguras");
        return fg;

    }

    private List<lamina> obtenerLam(String cons) {
        List<lamina> lm = new ArrayList<>();
        HiloAbrirBDD2 hilo2 = new HiloAbrirBDD2(todasPlantas, cons, getActivity());
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
            Toast.makeText(getActivity(), "Error al abrir Laminas " + e, Toast.LENGTH_LONG).show();
            Log.d("tryError", "Error1 al abrir Laminas " + e + " Cause. " + e.getCause());
        }
        todasPlantas = hilo2.getDb_3();
        Cursor tPlCur = hilo2.getD();
        int cont = 0;
        if (tPlCur.getCount() > 0) {
            tPlCur.moveToFirst();
            while (!tPlCur.isAfterLast()) {
                try {
                    lamina newLam = new lamina(tPlCur.getInt(0), tPlCur.getInt(1),
                            tPlCur.getString(2), tPlCur.getString(3),
                            tPlCur.getInt(5));
                    lm.add(newLam);
                    cont++;
                } catch (Exception e) {
                    Log.d("tryError", e.toString());
                }
                tPlCur.moveToNext();
            }
        } else {
            Toast.makeText(getActivity(), "No hay laminas", Toast.LENGTH_SHORT).show();
            Log.d("tryError", "Error en hilo2 no hay laminas");
        }
        Log.d("life", "Saliendo de Splash obtenerlaminas");
        return lm;

    }

    private void seguirPoniendo5(int pos, int finis) {
        btnStart.setEnabled(true);
        btnBack.setEnabled(true);

        criterioGeneral critActual = allCriterios.get(pos);

        comprobarFigLamCrit(critActual);

        int jrrq = critActual.getJerarquia();
        int level = critActual.getNivel();
        int idFam = critActual.getId_en_FUClave();
        int posClv = 0;
        boolean esNum = comprobarNumoText(critActual.getDestino());
        especiesUnicas eU = null;
        subespecies sU = null;
        int terminado = finis;

        //==================================terminado =
        // -1 --> pulsado abajo, no terminado //0 --> pulsado arriba, no terminado // 1 --> especie
        // 2 --> subespecie // 3 --> no encontrado // 4 --> no hay claves

        //===============================================================================================
        if (esNum) {//El destino es número===============================================================
            //int destino = Integer.parseInt(critActual.getDestino());//Guardo el num destino en 'destino
            final int paso0 = Integer.parseInt(critActual.getDestino());//paso0 contiene el destino del criterio actual, como 'destino
            //Entre todos los criterios buscaré el que tenga la jerarquia, el paso y el nivel del criterio actual
            //normalmente debe haber 2 que cumplan los requisitos, guardo la posición del primero de los dos en 'posClv
            //y con esa posición actualizo los tVs.

            posClv = allCriterios.indexOf(allCriterios.stream().filter(p -> p.getJerarquia() == jrrq &&
                    p.getNivel() == level && p.getPaso() == paso0).findFirst().orElse(null));
            Log.d("seguir", "Jeraquia: " + jrrq + " Nivel: " + level + " Paso: " + critActual.getPaso() +
                    " Destino: " + paso0);
            if (posClv != -1){//allCriterios está 'completo' y el destino está incluido
                //terminado = finis;
                actualizartVs(posClv);
            } else {//allCriterio ha sido cambiado al pulsar sobre los criterios de abajo y el destino
                //no se encuentra en él, tengo que reconstruir allCriterios
                String u ="";
                if (tE.isEmpty()) {
                    u = tG;
                } else {
                    u = tE;
                }
                String t = "Select * from AAAA_Clave_general_4 where " +
                        "(Nivel = " + level + " and Jerarquia = " + jrrq + " and Paso = " +paso0 +
                        " and Observaciones like '%" + u + "%')  order by Paso, Nivel, Jerarquia, Observaciones;";
                allCriterios = new ArrayList<>();
                allCriterios = recuperar_Clave_general(t, "n");
                actualizartVs(0);
            }
            //=================================================================================================
        } else {//El destino es texto======================================================================
            String destiny = "";
            if (critActual.getId() < 759) {//Si es un criterio de los 758 primeros al ser texto debe ser el nombre de una
                //familia que tengo que arreglar para poder buscarla después
                destiny = arreglarNomFam(critActual.getDestino());
            } else {
                destiny = critActual.getDestino();
            }

            Log.d("seguirponiendo5", critActual.getDestino() + " destiny:" + destiny);
            consSel = "Select * from AAAA_Clave_general_4 where Observaciones like '%" + destiny +
                    "%' order by Nivel, Jerarquia, Observaciones, Paso;";
            //paraRetro = consSel;
            consuUltima =consSel;
            List<criterioGeneral> critParcial = new ArrayList<>();
            critParcial = recuperar_Clave_general(consSel, "");
            int esta = critParcial.size();

            //==========================================================================================
            if (esta != 0) {//El destino ES TEXTO Y SÍ está en observaciones, luego tiene criterios más abajo y busco la
                // linea donde empiezan ================================================================
                allCriterios = new ArrayList<>(critParcial);
                //terminado = finis;
                actualizartVs(0);
                //
            } else {//El destino ES TEXTO  Y NO ESTÁ en observaciones, por tanto no tiene más criterios
                //puede ser que tenga un final único o que no tenga nada
                //o que sea familia con un solo género con varias especies
                //el final puede ser en género, especie o subespecie. Lo busco en las tres tablas

                List<IdsGenero> generosHallados = new ArrayList<>();
                switch (jrrq) {
                    //================Jerarquia -2 no hay que programarla porque no llega hasta aquí ya que sí se encontrará en Observaciones
                    //================JERARQUIA -1 Clasificar hasta familia
                    case -1://Clasificar familia, hay tres posiblidades de destino :

                        String consm11 = "Select * from AABB_Generos_unidos_4 where IdFUC = " + idFam + ";";
                        consuUltima =consm11;
                        generosHallados = obtenerGenerosUnicos(consm11);
                        //================hay un género
                        if (generosHallados.size() == 1) {//He encontrado un genero que pertenece a la familia
                            //paraRetro = consm11;
                            res[0] = generosHallados.get(0).getNombreGrupo();
                            res[0] = res[0].toUpperCase();
                            res[1] = generosHallados.get(0).getNombreFamilia();
                            int i = trocearDescripcion(generosHallados.get(0).getDescripcion());
                            if (i > 0) {
                                res[2] = generosHallados.get(0).getNombreGenero().substring(0, i - 1);
                                res[3] = generosHallados.get(0).getDescripcion().substring(i);
                            } else {
                                res[2] = generosHallados.get(0).getNombreGenero();
                                res[3] = generosHallados.get(0).getDescripcion();
                            }

                            String consuEu = "Select * from AACC_Especies_unidas_4 where Id_Familia = " + idFam + " and Nombre_Genero like '%" + res[2] + "%' ;";
                            consuUltima =consuEu;
                            Log.d("zz_2_life", "1347 tG:" + tG);
                            int ret = buscarEspecie(consuEu);
                            //============= Hay una especie
                            if (ret == 1) {
                                String consuSu = "";
                                String nombre = arreglarNomEsp(res[4]);
                                res[4] = nombre;
                                tE = nombre;
                                Log.d("zz_2_life", "1354 tE:" + tE);
                                consuSu = "Select * from AADD_Subespecies_unidas_4 where Id_Familia = " + idFam + " and Nombre_Especie like '%" + nombre + "%' ;";
                                consuUltima =consuSu;
                                int ret2 = buscarSubespecie(consuSu);
                                //=============== Hay 1 o 0 subespecies
                                if (ret2 == 1) {//He encontrado una subespecie
                                    //terminado = 1;
                                    prepararMessg();
                                } else if (ret2 == 0) {//No he encontrado ninguna subespecie
                                    //terminado = 3;
                                    prepararMessg();
                                    //=============== Hay más de 1 subespecies
                                } else if (ret2 == 2) {//He encontrado varias subespecies
                                    allCriterios = new ArrayList<>(critParcial);
                                    //terminado = 0;
                                    actualizartVs(0);
                                    //return;
                                }
                                //=============== No hay ninguna especie
                            } else if (ret == 0) { //He encontrado cero especies
                                //terminado = 3;
                                prepararMessg();
                                //return;
                                //=============== Hay más de una especie
                            } else if (ret == 2) {//He encontrado varias especies

                                consSel = "Select * from AAAA_Clave_general_4 where Observaciones like '%" + res[2] +
                                        "%' order by Nivel, Jerarquia, Observaciones, Paso;";
                                critParcial = new ArrayList<>();
                                consuUltima =consSel;
                                critParcial = recuperar_Clave_general(consSel, "");

                                allCriterios = new ArrayList<>(critParcial);
                                //terminado = finis;
                                actualizartVs(0);
                            }
                            //=============== No hay ningún genero
                        } else if (generosHallados.isEmpty()) {//No he encontrado ninguna género en esa familia
                            String messg = "La familia: " + destiny + " no está incluida en Flora Ibérica";
                            String messg2 = destiny + " no incluida ";
                            //terminado = 4;
                            alertarF(messg, messg2);
                            scr1.setVisibility(View.INVISIBLE);
                            scr2.setVisibility(View.VISIBLE);
                            String s = messg + "\n" + messg2;
                            tV1.setText(s);
                            btnStart.setEnabled(true);
                        }
                        break;
                    case 0:
                        //=============== Jerarquia es 0 --> Clasifica hasta género
                        String consSel0 = "Select * from AABB_Generos_unidos_4 where IdFUC = " + idFam + " and Nombre_Genero like '%" + destiny + "%';";
                        consuUltima =consSel0;
                        generosHallados = new ArrayList<>();
                        generosHallados = obtenerGenerosUnicos(consSel0);
                        //===============  Hay un género que pertenece a la familia
                        if (generosHallados.size() == 1) {//He encontrado un genero que pertenece a la familia
                            //paraRetro = consSel0;
                            res[0] = generosHallados.get(0).getNombreGrupo();
                            res[0] = res[0].toUpperCase();
                            res[1] = generosHallados.get(0).getNombreFamilia();
                            int i = trocearDescripcion(generosHallados.get(0).getDescripcion());
                            if (i > 0) {
                                res[2] = generosHallados.get(0).getDescripcion().substring(0, i - 1);
                                res[3] = generosHallados.get(0).getDescripcion().substring(i);
                            } else {
                                res[2] = generosHallados.get(0).getNombreGenero();
                                res[3] = generosHallados.get(0).getDescripcion();
                            }
                            Log.d("zz_2_life", "1421 tG:" + tG);
                            String consuEu = "Select * from AACC_Especies_unidas_4 where Id_Familia = " + idFam + " and Nombre_Genero like '%" + res[2] + "%' ;";
                            consuUltima =consuEu;
                            int ret = buscarEspecie(consuEu);
                            //===============  Hay un especie que pertenece a la familia
                            if (ret == 1) {
                                //paraRetro = consuEu;
                                String consuSu = "";
                                String nombre = arreglarNomEsp(res[4]);
                                res[4] = nombre;
                                tE = nombre;
                                consuSu = "Select * from AADD_Subespecies_unidas_4 where Id_Familia = " + idFam + " and Nombre_Especie like '%" + nombre + "%' ;";
                                consuUltima =consuSu;
                                int ret2 = buscarSubespecie(consuSu);
                                //===============  Hay una subespecie o ninguna que pertenece al género
                                if (ret2 == 1) {//He encontrado una subespecie
                                    //terminado = 2;
                                    prepararMessg();
                                } else if (ret2 == 0) {//No he encontrado ninguna subespecie
                                    //terminado = 3;
                                    prepararMessg();
                                    //===============  Hay varias subespecies que pertenecen al género
                                } else if (ret2 == 2) {//He encontrado varias subespecies
                                    allCriterios = new ArrayList<>(critParcial);
                                    //terminado = 0;
                                    actualizartVs(0);
                                    //return;
                                }
                                //===============  No hay ningúna especie que pertenezca a la familia
                            } else if (ret == 0) {//No hay ninguna especie que pertenezca a la familia
                                //agregar = false;
                                //terminado = 3;
                                prepararMessg();
                                //===============  Hay más de una especie que pertenezca a la familia
                            } else if (ret == 2) {
                                allCriterios = new ArrayList<>(critParcial);
                                //terminado = finis;
                                actualizartVs(0);
                            }
                        }
                        break;
                    case 1://Clasificar especie
                        //=============== Jerarquia es 1 --> Clasifica hasta especie
                        String cons1 = "Select * from AACC_Especies_unidas_4 where Id_Familia = " + idFam + " and Nombre_Especie like '%" + destiny + "%';";
                        consuUltima =cons1;
                        especiesHalladas = new ArrayList<>();
                        especiesHalladas = obtenerEspeciesUnicas(cons1);
                        //=============== He encontrado una especie que pertenece a ese genero
                        if (especiesHalladas.size() == 1) {
                            //paraRetro = cons1;
                            res[0] = especiesHalladas.get(0).getNombre_Grupo();
                            res[0] = res[0].toUpperCase();
                            res[1] = especiesHalladas.get(0).getNombre_Familia();
                            res[2] = especiesHalladas.get(0).getNombre_Genero();
                            int i = trocearDescripcion(especiesHalladas.get(0).getDescripcion());
                            if (i > 0) {
                                res[4] = especiesHalladas.get(0).getDescripcion().substring(0, i - 1);
                                res[5] = especiesHalladas.get(0).getDescripcion().substring(i);
                            } else {
                                res[4] = especiesHalladas.get(0).getNombre_Especie();
                                res[5] = especiesHalladas.get(0).getDescripcion();
                            }
                            String nombre = arreglarNomEsp(res[4]);
                            res[4] = nombre;
                            tE = nombre;
                            Log.d("zz_2_life", "1482 tE:" + tE);
                            String consuSu = "";
                            consuSu = "Select * from AADD_Subespecies_unidas_4 where Id_Familia = " + idFam + " and Nombre_Especie like '%" + nombre + "%' ;";
                            consuUltima =consuSu;
                            int ret2 = buscarSubespecie(consuSu);
                            //=============== He encontrado una subespecie que pertenece a ese genero
                            if (ret2 == 1) {//He encontrado una subespecie
                                //terminado = 2;
                                prepararMessg();
                            } else if (ret2 == 0) {//No he encontrado ninguna subespecie
                                //terminado = 3;
                                prepararMessg();
                                //=============== He encontrado más de una subespecie que pertenece a ese genero
                            } else if (ret2 == 2) {//He encontrado varias subespecies
                                allCriterios = new ArrayList<>(critParcial);
                                //terminado = finis;
                                actualizartVs(0);
                                //return;
                            }
                            //=============== No he encontrado ninguna especie que pertenece a ese genero
                        } else if (especiesHalladas.isEmpty()) {//No he encontrado ninguna especie que pertenece a ese genero
                            //agregar = false;
                            //terminado = 3;
                            prepararMessg();
                        } else {//Hay mas de una especie
                            especiesHalladas.size();
                            allCriterios = new ArrayList<>(critParcial);
                            //terminado = finis;
                            actualizartVs(0);
                        }
                        break;
                    case 2://
                        //=============== Jerarquia es 2 --> Clasifica hasta subespecie
                        String cons2 = "Select * from AADD_Subespecies_unidas_4 where Id_Familia = " + idFam + " and Nombre_Subespecie like '%" + destiny + "%';";
                        consuUltima =cons2;
                        subespeciesHalladas = new ArrayList<>();
                        subespeciesHalladas = obtenerSubespecies(cons2);
                        //=============== He encontrado una subespecie que pertenece a ese genero
                        if (subespeciesHalladas.size() == 1) {
                            //paraRetro = cons2;
                            res[0] = subespeciesHalladas.get(0).getNombre_Grupo();
                            res[0] = res[0].toUpperCase();
                            res[1] = subespeciesHalladas.get(0).getNombre_Familia();
                            res[2] = subespeciesHalladas.get(0).getNombre_Genero();
                            res[4] = subespeciesHalladas.get(0).getNombre_Especie();
                            int i = trocearDescripcion(subespeciesHalladas.get(0).getDescripcion());
                            if (i > 0) {
                                res[6] = subespeciesHalladas.get(0).getDescripcion().substring(0, i - 1);
                                res[7] = subespeciesHalladas.get(0).getDescripcion().substring(i);
                            } else {
                                res[6] = subespeciesHalladas.get(0).getNombre_Subespecie();
                                res[7] = subespeciesHalladas.get(0).getDescripcion();
                            }
                            //agregar = false;
                            //terminado = 2;
                            prepararMessg();
                            //=============== He encontrado más de una subespecie que pertenece a ese genero
                        } else if (subespeciesHalladas.size() == 2) {
                            allCriterios = new ArrayList<>(critParcial);
                            //terminado = finis;
                            actualizartVs(0);
                            //return;
                        }
                        break;
                }
            }
        }

        critSelectos2.add(0, critActual);
        fines.add(0, terminado);
        Log.d("txtSelectos", "sP5 agregar Criterios: size-->" + critSelectos2.size() + " Id-->" +
                critSelectos2.get(0).getId() + " Dest-->" + critSelectos2.get(0).getDestino() +
                "\t\tConsulta: size-->" + fines.size() + " Final--> " + fines.get(0));
        mAdapterC.notifyItemInserted(0);
        mlayoutManagerb.findViewByPosition(0);
        mlayoutManagerb.scrollToPosition(0);
    }

    private void prepararMessg() {
        StringBuilder mensaje = new StringBuilder();
        String tit = "";
        String[] tabs = {"", "\t", "\t", "\t\t", "\t\t", "\t\t\t", "\t\t\t", "", "", ""};
        int t = 0;
        for (String re : res) {
            if (!re.isEmpty()) {
                mensaje.append(tabs[t]).append(re).append("\n");
            }
            t++;
        }
        alertarF(mensaje.toString(), tit);
        scr1.setVisibility(View.INVISIBLE);
        scr2.setVisibility(View.VISIBLE);
        String s = mensaje.toString() + "\n" + tit;
        tV1.setText(s);
        btnStart.setEnabled(true);
    }

    private int buscarEspecie(String str) {
        int ret = -1;
        List<especiesUnicas> especieHallada = obtenerEspeciesUnicas(str);
        especiesUnicas eU = new especiesUnicas();
        if (especieHallada.size() == 1) {//He encontrado una única especie
            eU = especieHallada.get(0);
            res[4] = eU.getNombre_Especie();
            res[5] = eU.getDescripcion();
            res[8] = eU.getFigura();
            res[9] = eU.getLamina();
            //}
            ret = 1;
        } else if (especieHallada.size() > 1) {
            eU = especieHallada.get(0);
            String nombre = arreglarNomEsp(eU.getNombre_Genero());
            String ss = "Select * from AAAA_Clave_general_4 where Observaciones like '%" + nombre + "%' " +
                    "order by Nivel, Jerarquia, Observaciones, Paso;";
            generarActualizartVs(ss);
            ret = 2;
        } else {
            ret = 0;
        }
        return ret;
    }

    private int buscarSubespecie(String s) {
        int ret = -1;
        List<subespecies> subespeciesHalladas = obtenerSubespecies(s);
        subespecies sU = new subespecies();
        if (subespeciesHalladas.size() == 1) {//He encontrado una
            // única subespecie, esto no va a ocurrir

            sU = subespeciesHalladas.get(0);
            int j = trocearDescripcion(sU.getDescripcion());
            if (j > 0) {
                res[6] = sU.getNombre_Subespecie().substring(0, j);
                res[7] = sU.getDescripcion().substring(j);
            } else {
                res[6] = sU.getNombre_Subespecie();
                res[7] = sU.getDescripcion();
            }
            ret = 1;
        } else if (subespeciesHalladas.size() > 1) {
            sU = subespeciesHalladas.get(0);
            String nombre = arreglarNomEsp(sU.getNombre_Genero());
            String ss = "Select * from AAAA_Clave_general_4 where Observaciones like '%" + nombre + "%' " +
                    "order by Nivel, Jerarquia, Observaciones, Paso;";
            generarActualizartVs(ss);
            ret = 2;
        } else {//No he encontrado ninguna subespecie
            ret = 0;
        }
        return ret;
    }

    private String arreglarNomFam(String str) {
        //A partir del String recibido se buscan los dos primeros ¡espacios¡ y se extrae la
        // cadena desde cero hasta el segundo espacio
        String d = "";
        String inter = "";
        int i = str.indexOf(' ');
        if (i > 0) {
            inter = str.substring(i + 1);
            int j = inter.indexOf(' ');
            if (j > 0) {
                d = str.substring(0, i + j + 1);
            } else {
                d = str;
            }
        } else {
            d = str;
        }
        return d;
    }

    private String arreglarNomEsp(String str) {
        //A partir del String recibido se buscan los tres primeros ¡espacios¡ y se extrae la
        // cadena desde cero hasta el tercer espacio, si solo hay dos se extrae desde cero hasta el segundo espacio
        // y si solo hay un espacio se extrae desde cero hasta el primer espacio
        String e = "";
        String d = "";
        String inter = "";
        int i = str.indexOf(' ');
        if (i > 0) {
            inter = str.substring(i + 1);
            int j = inter.indexOf(' ');
            if (j > 0) {
                d = inter.substring(j + 1);
                int k = d.indexOf(' ');
                if (k > 0) {
                    e = str.substring(0, i + j + k + 3);
                } else {
                    e = str.substring(0, i + j + 2);
                }
            } else {
                e = str.substring(0, i + 1);
            }
        } else {
            e = str;
        }
        return e;
    }

    private void generarActualizartVs(String str) {
        List<criterioGeneral> critParcial1 = recuperar_Clave_general(str, "");
        int esta1 = critParcial1.size();
        if (esta1 != 0) {//El destino está en observaciones, luego tiene criterios más abajo y busco la
            allCriterios = new ArrayList<>(critParcial1);
            actualizartVs(0);
        }
    }

    private boolean comprobarNumoText(String s) {
        boolean esNumero;
        try {
            Integer.parseInt(s);
            esNumero = true;
        } catch (Exception f) { //Es texto pero no corresponde con lo esperado
            esNumero = false;
        }
        return esNumero;
    }


    private void actualizartVs(int newDestino) {
        //muestra el criterio newDestino de la lista de allCriterios
        //el criterio en tV11 y el destino en tV12
        //guarda el destino en el Tag()
        criterioGeneral newCrit = allCriterios.get(newDestino);
        tV11.setText(newCrit.getCriterio());
        tV12.setText(newCrit.getDestino());
        tV11.setTag(newDestino);
        tV12.setTag(newDestino);

        //muestra el criterio (newDestino + 1) de la lista de allCriterios
        //el criterio en tV21 y el destino en tV22
        //guarda el destino en el Tag()
        criterioGeneral sgtCrit = allCriterios.get(newDestino + 1);
        tV21.setText(sgtCrit.getCriterio());
        tV22.setText(sgtCrit.getDestino());
        tV21.setTag(newDestino + 1);
        tV22.setTag(newDestino + 1);

    }

    private void alertarG(String una) {//Dialogo para mostrar la especie hallada
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage(una);
        builder.setTitle("Versión resumida");
        builder.setCancelable(false);
        builder.setPositiveButton("Empezar nueva clasificación", (DialogInterface.OnClickListener) (dialog, which) -> reinicioAtras(v));
        // Set the Negative button with No name Lambda OnClickListener method is use of DialogInterface interface.
        // If user click no then dialog box is canceled.
        builder.setNegativeButton("Terminar completamente, salir de la app", (DialogInterface.OnClickListener) (dialog, which) -> {
            if (getActivity() != null) {
                getActivity().finishAffinity();
            }
            ;
        });
        // Create the Alert dialog
        AlertDialog alertDialog = builder.create();
        // Show the Alert Dialog box
        alertDialog.show();
    }

    private void alertarF(String una, String dos) {//Dialogo para mostrar la especie hallada
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMessage(una + "\n" + dos);
        builder.setTitle("Especie encontrada");
        builder.setCancelable(false);
        builder.setPositiveButton("Empezar nueva clasificación", (DialogInterface.OnClickListener) (dialog, which) -> reinicioAtras(v));
        // Set the Negative button with No name Lambda OnClickListener method is use of DialogInterface interface.
        // If user click no then dialog box is canceled.
        builder.setNegativeButton("Continuar", (DialogInterface.OnClickListener) (dialog, which) -> dialog.dismiss());
        // Create the Alert dialog
        AlertDialog alertDialog = builder.create();
        // Show the Alert Dialog box
        alertDialog.show();
    }

    private void alertarE(String spc, String descrp) {//Dialogo para mostrar alcanzado el final
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());

        // Set the message show for the Alert time

        builder.setMessage(spc + "\n" + descrp);
        builder.setTitle("Alcanzado el final");
        // Set Cancelable false for when the user clicks on the outside the Dialog Box then it will remain show
        builder.setCancelable(false);
        // Set the positive button with yes name Lambda OnClickListener method is use of DialogInterface interface.
        builder.setPositiveButton("Empezar de nuevo", (DialogInterface.OnClickListener) (dialog, which) -> reinicioAtras(v));
        // Set the Negative button with No name Lambda OnClickListener method is use of DialogInterface interface.
        builder.setNegativeButton("No", (DialogInterface.OnClickListener) (dialog, which) -> {
            // If user click no then dialog box is canceled.
            dialog.cancel();
        });
        // Create the Alert dialog
        AlertDialog alertDialog = builder.create();
        // Show the Alert Dialog box
        alertDialog.show();
    }


    private List<IdsGenero> obtenerGenerosUnicos(String buscar) {
        List<IdsGenero> gU = new ArrayList<>();
        HiloAbrirBDD2 hilo2 = new HiloAbrirBDD2(todasPlantas, buscar, getActivity());
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
            Toast.makeText(getActivity(), "Error al abrir Especies unicas " + e, Toast.LENGTH_LONG).show();
            Log.d("tryError", "Error1 al abrir Especies unicas " + e + " Cause. " + e.getCause());
        }
        todasPlantas = hilo2.getDb_3();
        Cursor tPlCur = hilo2.getD();
        int cont = 0;
        if (tPlCur.getCount() > 0) {
            tPlCur.moveToFirst();
            while (!tPlCur.isAfterLast()) {
                try {
                    IdsGenero newGen = new IdsGenero(tPlCur.getInt(0), tPlCur.getInt(1),
                            tPlCur.getString(2), tPlCur.getString(3), tPlCur.getString(4),
                            tPlCur.getString(5), tPlCur.getString(6), tPlCur.getString(7),
                            tPlCur.getString(8), tPlCur.getInt(9),
                            tPlCur.getInt(10));
                    gU.add(newGen);
                    cont++;
                } catch (Exception e) {
                    Log.d("tryError", e.toString());
                }
                tPlCur.moveToNext();
            }
        } else {
            Toast.makeText(getActivity(), "No hay registros", Toast.LENGTH_SHORT).show();
            Log.d("tryError", "Error en hilo2 no hay registros");
        }
        Log.d("life", "Saliendo de Splash obtenerEspeciesUnicas");
        return gU;
    }


    private List<especiesUnicas> obtenerEspeciesUnicas(String buscar) {
        List<especiesUnicas> eU = new ArrayList<>();
        HiloAbrirBDD2 hilo2 = new HiloAbrirBDD2(todasPlantas, buscar, getActivity());
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
            Toast.makeText(getActivity(), "Error al abrir Especies unicas " + e, Toast.LENGTH_LONG).show();
            Log.d("tryError", "Error1 al abrir Especies unicas " + e + " Cause. " + e.getCause());
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
                    Log.d("planta", "Añadidas " + cont + " especies. Hay error en recuperar especies unicas error e:" + e);
                }
                tPlCur.moveToNext();
            }
        } else {
            Toast.makeText(getActivity(), "No hay registros", Toast.LENGTH_SHORT).show();
            Log.d("tryError", "Error en hilo2 no hay registros");
        }
        Log.d("life", "Saliendo de Splash obtenerEspeciesUnicas");
        return eU;
    }

    private int trocearDescripcion(String descr) {
        int counter = 0;
        int i = 0;
        int tot = 0;
        while (counter < 5) {
            i = descr.indexOf(' ');
            descr = descr.substring(i + 1);
            counter++;
            tot = tot + i;
        }
        return tot;
    }

    public List<subespecies> obtenerSubespecies(String buscar) {
        Log.d("life", "Entrando en Splash obtenerSubespecies");
        List<subespecies> eU = new ArrayList<>();
        HiloAbrirBDD2 hilo2 = new HiloAbrirBDD2(todasPlantas, buscar, getActivity());
        Log.d("life", "Saliendo de Splash getHilo..Sub");
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
            Toast.makeText(getActivity(), "Error al abrir Subespecies unicas " + e, Toast.LENGTH_LONG).show();
            Log.d("tryError", "Error1 al abrir Subespecies unicas " + e + " Cause. " + e.getCause());
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

                    Log.d("planta", " Error en recuperar Subespecies unicas error e:" + e);
                }
                tPlCur.moveToNext();
            }
        } else {
            Toast.makeText(getActivity(), "No hay registros", Toast.LENGTH_SHORT).show();
            Log.d("tryError", "Error en hilo2 no hay registros");
        }
        Log.d("life", "Saliendo de Splash obtenerSubespecies");
        return eU;
    }

    @Override
    public void OnItemClick(criterioGeneral crit, int position) {
        Log.d("clickando", "Linea 1862 Click en paso " + position + " criterio: " + crit.getCriterio());
        Toast.makeText(getActivity(), "Click en posición " + position, Toast.LENGTH_LONG).show();
    }
}