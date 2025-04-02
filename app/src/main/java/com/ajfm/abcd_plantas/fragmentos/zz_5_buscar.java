package com.ajfm.abcd_plantas.fragmentos;

import static com.ajfm.abcd_plantas.MainActivity.flagBuscado;
import static com.ajfm.abcd_plantas.MainActivity.tabLayout;
import static com.ajfm.abcd_plantas.SplashActivity.allFamiliasClaves;
import static com.ajfm.abcd_plantas.SplashActivity.allGrupos;
import static com.ajfm.abcd_plantas.SplashActivity.allPlantasTotal;
import static com.ajfm.abcd_plantas.SplashActivity.allPlantasUsar;
import static com.ajfm.abcd_plantas.SplashActivity.consultaGlobal;
import static com.ajfm.abcd_plantas.SplashActivity.todasPlantas;

import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ajfm.abcd_plantas.R;
import com.ajfm.abcd_plantas.adaptadores.AdapBusqueda;
import com.ajfm.abcd_plantas.hilos.HiloAbrirBDD2;
import com.ajfm.abcd_plantas.modelos.familia;
import com.ajfm.abcd_plantas.modelos.grupo;
import com.ajfm.abcd_plantas.modelos.planta;

import java.util.ArrayList;
import java.util.List;

public class zz_5_buscar extends Fragment implements AdapBusqueda.OnItemClickListener {

    private TextView tV5;
    private TextView tV55;
    private TextView tVN1;
    private TextView tVV11;

    private Button btn5;
    private int positionEspecifica = 0;
    private int positionVulgar = 0;
    private int positionFamilia = 0;
    private int positionGrupo = 0;
    private int insert = 0;
    private String trozoInter;
    private String consultaCrear;

    private Spinner spiner1, spiner2, spiner3, spiner4;
    private EditText eTnC, eTnV, eTfM, eTgR;
    private final List<String> spinerV1 = new ArrayList<>();
    private final List<String> spinerV2 = new ArrayList<>();
    private final List<String> spinerV3 = new ArrayList<>();
    private final List<String> spinerV4 = new ArrayList<>();
    private List<planta> encontradas;
    private RecyclerView mRecyclerViewb;
    private AdapBusqueda mAdapterC;
    private View v2;
    private Button btnBuscar;
    private Button btnFichas;


    public zz_5_buscar() { /* Required empty public constructor */}

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("rastro", "On y out de onCreate zz_5");

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.d("rastro", "On  onCreateView zz_5");
        Log.d("life","Entrando en zz_5... onCreateView");

        View view = inflater.inflate(R.layout.fragment_zz_5_buscar, container, false);

        tV55 = (TextView) view.findViewById(R.id.tV55);
        tVV11 = (TextView) view.findViewById(R.id.tVV11);
        tV5 = (TextView) view.findViewById(R.id.tV5);
        btnBuscar = (Button) view.findViewById(R.id.btnBuscar);
        btnFichas = (Button) view.findViewById(R.id.btnFichas);

        Log.d("rastro", "Out  onCreateView zz_5");
        Log.d("life","Saliendo de zz_5... onCreateView");
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d("rastro", "On  onViewCreated zz_5");
        LinearLayoutCompat ll05 = view.findViewById(R.id.ll05);
        ll05.setVisibility(View.INVISIBLE);

        spiner1 = (Spinner) view.findViewById(R.id.spinner1);
        spiner2 = (Spinner) view.findViewById(R.id.spinner2);
        spiner3 = (Spinner) view.findViewById(R.id.spinner3);
        spiner4 = (Spinner) view.findViewById(R.id.spinner4);

        eTnC = (EditText) view.findViewById(R.id.tV13);
        eTnV = (EditText) view.findViewById(R.id.tV23);
        eTfM = (EditText) view.findViewById(R.id.tV33);
        eTgR = (EditText) view.findViewById(R.id.tV43);

        eTnC.setText("");//4eTnC1");
        eTnV.setText("");//3eTnV2");
        eTfM.setText("");//2eTfM3");
        eTgR.setText("");//1eTgR4");

        encontradas = new ArrayList<>();

        mRecyclerViewb = (RecyclerView) view.findViewById(R.id.rV2);
        RecyclerView.LayoutManager mlayoutManagerb = new LinearLayoutManager(getContext());
        mRecyclerViewb.setLayoutManager(mlayoutManagerb);
        mAdapterC = new AdapBusqueda(allPlantasUsar);
        mRecyclerViewb.setAdapter(mAdapterC);
        mAdapterC.setOnItemClickListener(this);
        mRecyclerViewb.setItemAnimator(new DefaultItemAnimator());

        adaptandoSpinners(view.getContext());

        if (flagBuscado){
            btnBuscar.setText("Limpiar");
            btnFichas.setEnabled(true);
            String s ="Hay " + allPlantasUsar.size() + " fichas";
            tVV11.setText(s);
        }


        TextView tV1 = (TextView) view.findViewById(R.id.tV1);
        TextView tV2 = (TextView) view.findViewById(R.id.tV3);

        btnBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flagBuscado =true;
                String b = "Buscar";
                if (btnBuscar.getText().equals(b)) {
                    btnBuscar.setText("Limpiar");
                    trozoInter = buscando(v);
                    consultaCrear = "Select Planta.Id_auto, Planta.Id, " +
                            "Planta.Nombre_cientifico, Planta.Nombre_vulgar, Planta.Familia, Planta.Grupo, " +
                            "Planta.largo_cient, Planta.espacios_cient, Planta.largo_vulgar, Planta.espacios_vulgar, Planta.largo_familia," +
                            "Planta.Foto_AA, Planta.Foto_BB, Planta.Id_en_FUClave, Planta.Estado_Planta, Planta.Id_en_FUPlanta" +
                            " from Planta " +
                            trozoInter +
                            " order by Nombre_cientifico ASC, Familia ASC;";
                    consultaGlobal = "Select Planta.Id_auto, Planta.Id, " +
                    "Planta.Nombre_cientifico, Planta.Nombre_vulgar, Planta.Familia, Planta.Grupo, " +
                            "Planta.largo_cient, Planta.espacios_cient, Planta.largo_vulgar, Planta.espacios_vulgar, Planta.largo_familia," +
                            "Planta.Foto_AA, Planta.Foto_BB, Planta.Id_en_FUClave, Planta.Estado_Planta, Planta.Id_en_FUPlanta" +
                            " from Planta " +
                            trozoInter +
                            " order by Nombre_cientifico ASC, Familia ASC;";

                    encontradas = realizarBusqueda(consultaCrear);

                    allPlantasUsar.clear();
                    mAdapterC.notifyDataSetChanged();
                    for (planta p : encontradas) {
                        allPlantasUsar.add(p);
                        mAdapterC.notifyItemInserted(insert);
                        insert++;
                    }
                    mRecyclerViewb.scrollToPosition(0);

                    if (insert > 0) {
                        String s = "Hay " + String.valueOf(insert) + " fichas";
                        tVV11.setText(s);

                        btnFichas.setEnabled(true);
                        btnFichas.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                Bundle bundle = new Bundle();
                                bundle.putString("consulta", consultaCrear);
                                requireActivity().getSupportFragmentManager().setFragmentResult("key", bundle);
                                tabLayout.selectTab(tabLayout.getTabAt(0));
                            }
                        });
                    }
                } else {
                    flagBuscado=false;
                    btnBuscar.setText(R.string.buscar);
                    spiner1.setSelection(0);
                    spiner2.setSelection(0);
                    spiner3.setSelection(0);
                    spiner4.setSelection(0);
                    eTnC.setText("");
                    eTnV.setText("");
                    eTfM.setText("");
                    eTgR.setText("");
                    btnFichas.setEnabled(false);
                    tVV11.setText("");
                }
            }
        });

        Log.d("rastro", "Out  onViewCreated zz_5");
    }

    private void adaptandoSpinners(Context context) { //adapter para spinner
        int p = 1;
        int i = 0;
        spinerV1.clear();
        spinerV2.clear();
        spinerV3.clear();
        spinerV4.clear();

        spinerV1.add(" ");
        spinerV1.add("Cualquier Nombre científico");

        spinerV2.add(" ");
        spinerV2.add("Cualquier Nombre vulgar");

        spinerV3.add(" ");
        spinerV3.add("Cualquier Familia");

        spinerV4.add(" ");
        spinerV4.add("Cualquier Grupo");

        for (planta j : allPlantasTotal) {
            spinerV1.add(j.getmNombreCientifico());
            spinerV2.add(j.getmNobreVulgar());
        }

        for (familia j : allFamiliasClaves) {
            spinerV3.add(j.getmNombreFamilia());

        }
        for (grupo j : allGrupos) {
            spinerV4.add(j.getmNombreGrupo());
        }

        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(context,
                android.R.layout.simple_spinner_dropdown_item,
                spinerV1);
        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spiner1.setAdapter(adapter1);
        spiner1.setSelection(0);

        //Hacer clic sobre un especifico del desplegable del spinner
        spiner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selected = (String) spiner1.getItemAtPosition(position);
                positionEspecifica = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(context,
                android.R.layout.simple_spinner_dropdown_item,
                spinerV2);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spiner2.setAdapter(adapter2);
        spiner2.setSelection(0);

        //Hacer clic sobre un especifico del desplegable del spinner
        spiner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selected = (String) spiner2.getItemAtPosition(position);
                positionVulgar = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        ArrayAdapter<String> adapter3 = new ArrayAdapter<>(context,
                android.R.layout.simple_spinner_dropdown_item,
                spinerV3);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spiner3.setAdapter(adapter3);
        spiner3.setSelection(0);

        //Hacer clic sobre un especifico del desplegable del spinner
        spiner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selected = (String) spiner3.getItemAtPosition(position);
                positionFamilia = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        ArrayAdapter<String> adapter4 = new ArrayAdapter<>(context,
                android.R.layout.simple_spinner_dropdown_item,
                spinerV4);
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spiner4.setAdapter(adapter4);
        spiner4.setSelection(0);

        //Hacer clic sobre un especifico del desplegable del spinner
        spiner4.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selected = (String) spiner4.getItemAtPosition(position);
                positionGrupo = position;
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

    }

    public String buscando(View v) {
        Log.d("pasoapaso", "Entrando en  onOptionsItemSelected");

        String eC = String.valueOf(eTnC.getText());
        String eV = String.valueOf(eTnV.getText());
        String eF = String.valueOf(eTfM.getText());
        String eG = String.valueOf(eTgR.getText());

        String trozo1, trozo2, trozo3, trozo4, trozoFinal;
        trozoFinal = "";
        //============  TROZO CIENTIFICO 1 =====================================
        String nC;
        if (positionEspecifica == 0 || positionEspecifica == 1) {
            nC = "";
        } else {
            nC = "Nombre_cientifico = '" + allPlantasTotal.get(positionEspecifica - 2).getmNombreCientifico() + "'";
        }
        //============  TROZO CIENTIFICO 2 =====================================
        if (nC.equals("")) {
            if (eC.equals("")) {
                trozo1 = "";
            } else {
                trozo1 = " Nombre_cientifico like '%" + eC + "%'";
            }
        } else {
            if (eC.equals("")) {
                trozo1 = nC;
            } else {
                trozo1 = nC + " or Nombre_cientifico like '%" + eC + "%'";
            }
        }
        //============  TROZO VULGAR 1 =====================================
        String nV;
        if (positionVulgar == 0 || positionVulgar == 1) {
            nV = "";
        } else {
            nV = "Nombre_vulgar = '" + allPlantasTotal.get(positionVulgar - 2).getmNobreVulgar() + "'";
        }
        //============  TROZO VULGAR 2 =====================================
        if (nV.equals("")) {
            if (eV.equals("")) {
                trozo2 = "";
            } else {
                trozo2 = " Nombre_vulgar like '%" + eV + "%'";
            }
        } else {
            if (eV.equals("")) {
                trozo2 = nV;
            } else {
                trozo2 = nV + " Nombre_vulgar like '%" + eC + "%'";
            }
        }
        //============  TROZO FAMILIA 1 =====================================
        String fM;
        if (positionFamilia == 0 || positionFamilia == 1) {
            fM = "";
        } else {
            fM = " Id_en_FUClave = " + allFamiliasClaves.get(positionFamilia - 2).getmIdFamilia();
        }
        //============  TROZO FAMILIA 2 =====================================
        if (fM.equals("")) {
            if (eF.equals("")) {
                trozo3 = "";
            } else {
                trozo3 = " Familia like '%" + eF + "%'";
            }
        } else {
            if (eF.equals("")) {
                trozo3 = fM;
            } else {
                trozo3 = fM + " or Familia like '%" + eF + "%'";
            }
        }
        //============  TROZO GRUPO 1 =====================================
        String gR;
        if (positionGrupo == 0 || positionGrupo == 1) {
            gR = "";
        } else {
            gR = " Grupo = '" + allGrupos.get(positionGrupo - 2).getmNombreGrupo() + "'";//Integer.toString(positionGrupo);//
        }
        //============  TROZO GRUPO 2 =====================================
        if (gR.equals("")) {
            if (eG.equals("")) {
                trozo4 = "";
            } else {
                trozo4 = " Grupo like '%" + eG + "%'";
            }
        } else {
            if (eG.equals("")) {
                trozo4 = gR;
            } else {
                trozo4 = gR + " or Grupo like '%" + eG + "%'";
            }
        }

        boolean ultimo = false;
        StringBuilder sb0 = new StringBuilder();
        sb0.append(trozoFinal);
        if (trozo1.length() > 0) {
            ultimo = true;
            sb0.append(" where (").append(trozo1).append(") ");
        }

        if (trozo2.length() > 0) {
            if (ultimo) {
                sb0.append(" and (");
            } else {
                ultimo = true;
                sb0.append((" where ("));
            }
            sb0.append(trozo2).append(")");
        }

        if (trozo3.length() > 0) {
            if (ultimo) {
                sb0.append(" and (");
            } else {
                ultimo = true;
                sb0.append((" where ("));
            }
            sb0.append(trozo3).append(")");
        }

        if (trozo4.length() > 0) {
            if (ultimo) {
                sb0.append(" and (");
            } else {
                ultimo = true;
                sb0.append((" where ("));
            }
            sb0.append(trozo4).append(")");
        }

        //sb0.append(";");

        StringBuilder sb = new StringBuilder();
        Toast.makeText(getContext(), sb0.toString(), Toast.LENGTH_LONG).show();
        Log.d("trozo", "Cadena consultar: " + sb0.toString());

        return sb0.toString();
    }

    private List<planta> realizarBusqueda(String consulta0) {
        List<planta> allPlants = new ArrayList<>();

        HiloAbrirBDD2 hilo2 = new HiloAbrirBDD2(todasPlantas, consulta0, getContext());
        Log.d("trozo", "Realizar busqueda: " + consulta0);
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
            Toast.makeText(getContext(), "Error al abrir Planta " + e.toString(), Toast.LENGTH_LONG).show();
            Log.d("error", "Error1 al abrir Planta " + e.toString() + " Cause. " + e.getCause().toString());
        }

        todasPlantas = hilo2.getDb_3();
        Cursor tPlCur = hilo2.getD();
        if (encontradas.size() > 0) {
            encontradas.clear();
        }
        if (tPlCur.getCount() > 0) {
            //tV1.setText(Integer.toString(tPlCur.getCount()));
            tPlCur.moveToFirst();
            int cols = tPlCur.getColumnCount();
            int recuento = 0;
            Log.d("planta", " Hay " + tPlCur.getCount() + " registros, con  " + tPlCur.getColumnCount() + " columnas");

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

                    resIDAA = getResources().getIdentifier(nombreAA, "drawable", null);
                    resIDBB = getResources().getIdentifier(nombreBB, "drawable", null);

                    if (resIDAA == 0) {
                        nombreAA = "pexels_markus_winkler_19867368";
                        nombreBB = "pexels_markus_winkler_19867368";

                        resIDAA = getResources().getIdentifier(nombreAA, "drawable", null);
                        resIDBB = getResources().getIdentifier(nombreBB, "drawable", null);
                    }

                    planta newPlanta = new planta(tPlCur.getInt(0), tPlCur.getInt(1), tPlCur.getString(2),
                            tPlCur.getString(3), tPlCur.getString(4), tPlCur.getString(5),
                            tPlCur.getInt(6), tPlCur.getInt(7), tPlCur.getInt(8),
                            tPlCur.getInt(9), tPlCur.getInt(10), tPlCur.getString(11),
                            tPlCur.getString(12), resIDAA, resIDBB, tPlCur.getInt(13),
                            tPlCur.getString(14), tPlCur.getInt(15));
                    allPlants.add(newPlanta);
                    Log.d("planta", newPlanta.toString());
                } catch (Exception e) {

                    Log.d("planta", " Error en getIdentifier de " + nombreAA +
                            " o " + nombreBB + " error e:" + e.toString());
                }
                tPlCur.moveToNext();
            }
        } else {
            Toast.makeText(getContext(), "No hay registros", Toast.LENGTH_SHORT).show();
            Log.d("error", "Error en hilo2 no hay registros");
        }

        return allPlants;
    }

    @Override
    public void OnItemClick(View v, int position) {
        StringBuilder sb = new StringBuilder();
        sb.append(allPlantasUsar.get(position).getmNombreCientifico());

        Bundle b = new Bundle();
        b.putString("genero",sb.toString());
        requireActivity().getSupportFragmentManager().setFragmentResult("key4",b);
    }
}