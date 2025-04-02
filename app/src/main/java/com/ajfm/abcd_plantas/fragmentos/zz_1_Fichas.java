package com.ajfm.abcd_plantas.fragmentos;

import static com.ajfm.abcd_plantas.SplashActivity.acero;
import static com.ajfm.abcd_plantas.SplashActivity.allPlantasUsar;
import static com.ajfm.abcd_plantas.SplashActivity.consultaGlobal;
import static com.ajfm.abcd_plantas.SplashActivity.todasPlantas;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
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
import com.ajfm.abcd_plantas.adaptadores.FichaAdaptador;
import com.ajfm.abcd_plantas.hilos.HiloAbrirBDD2;
import com.ajfm.abcd_plantas.modelos.planta;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class zz_1_Fichas extends Fragment implements FichaAdaptador.OnItemClickListener {

    //=======================================================
    //private IRemitente emisor1;
    //private IReceptor receptor1;
    private TextView tV1;
    private LinearLayoutCompat ll01;
    public static int fichay = 0;

    public static RecyclerView mRecyclerView;
    public static RecyclerView.LayoutManager mlayoutManager;
    public static FichaAdaptador mAdapter;

    private TextView tV2;
    private EditText eT1;
    //private String str;
    private Button btnIr;

    public zz_1_Fichas() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("onde", "Entro en onCreate zz_1");
        Log.d("onde", "Salgo en onCreate zz_1");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("onde", "Entro en onCreateView zz_1");

        View view = inflater.inflate(R.layout.fragment_zz_1_fichas, container, false);

        //String info = "Hay " + Integer.toString(allPlantasUsar.size()) + " fichas";

        ll01 = view.findViewById(R.id.ll01);
        ll01.setVisibility(View.INVISIBLE);
        tV1 = view.findViewById(R.id.tV1);
        tV2 = (TextView) view.findViewById(R.id.tV2);
        tV2.setGravity(Gravity.CENTER_VERTICAL);
        eT1 = (EditText) view.findViewById(R.id.eT0);
        btnIr = (Button) view.findViewById(R.id.btnIr);

        btnIr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String posi = eT1.getText().toString();
                int l = posi.length();
                String pono = posi.substring(5, l);
                int posit = Integer.parseInt(pono);
                Objects.requireNonNull(mRecyclerView.getLayoutManager()).scrollToPosition(posit);
            }
        });

        if (!consultaGlobal.equals(acero)){
            allPlantasUsar.clear();
            mAdapter.notifyItemRangeRemoved(0, allPlantasUsar.size());
            Log.d("concol","ConsultaGlobal: " + consultaGlobal);
            fichay = realizarBusqueda(consultaGlobal);
            mRecyclerView.scrollToPosition(allPlantasUsar.size()-1);
            Objects.requireNonNull(mRecyclerView.getLayoutManager()).scrollToPosition(allPlantasUsar.size()-1);
        } else {
            Log.d("error", "He recibido la cadena de consulta vacía");
        }

        // Inflate the layout for this fragment
        Log.d("onde", "Salgo en onCreateView zz_1");

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d("onde", "Entro en onViewCreated zz_1");

        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclerV);
        mlayoutManager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mlayoutManager);
        mAdapter = new FichaAdaptador(allPlantasUsar);
        mRecyclerView.setAdapter(mAdapter);
        mAdapter.setOnItemClickListener(this);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());

        Log.d("onde", "Salgo en onViewCreated zz_1");
    }


    @Override
    public void onResume() {
        super.onResume();
        Log.d("onde", "Entro en onResume zz_1 -----------------------------------");
        String info = "Hay " + Integer.toString(allPlantasUsar.size()) + " fichas";
        Log.d("recy",info);
        tV2.setText(info);
        Log.d("onde", "Salgo en onResume zz_1 --------------------------------------");

    }
    public int realizarBusqueda(String consulta0) {
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
        if (tPlCur.getCount() > 0) {
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

                    resIDAA = getResources().getIdentifier(nombreAA, "drawable", getActivity().getPackageName());
                    resIDBB = getResources().getIdentifier(nombreBB, "drawable", getActivity().getPackageName());

                    if (resIDAA == 0) {
                        nombreAA = "pexels_gabby_k_6373491";
                        nombreBB = "pexels_angela_roma_7319326";

                        resIDAA = getResources().getIdentifier(nombreAA, "drawable", null);
                        resIDBB = getResources().getIdentifier(nombreBB, "drawable", null);
                    }

                    Log.d("buble", "0 - Id_auto --> " + tPlCur.getInt(0) );
                    Log.d("buble", "1 - Id      --> " + tPlCur.getInt(1) );
                    Log.d("buble", "2 - N.cient --> " + tPlCur.getString(2) );
                    Log.d("buble", "3 - N.vulg  --> " + tPlCur.getString(3) );
                    Log.d("buble", "4 - Familia --> " + tPlCur.getString(4) );
                    Log.d("buble", "5 - Grupo   --> " + tPlCur.getString(5) );
                    Log.d("buble", "6 - l.cien  --> " + tPlCur.getInt(6) );
                    Log.d("buble", "7 - e.cien  --> " + tPlCur.getInt(7) );
                    Log.d("buble", "8 - l.vul   --> " + tPlCur.getInt(8) );
                    Log.d("buble", "9 - e.vul   --> " + tPlCur.getInt(9) );
                    Log.d("buble", "10- l.famil --> " + tPlCur.getInt(10) );
                    Log.d("buble", "11- Foto_AA --> " + tPlCur.getString(11) );
                    Log.d("buble", "12- Foto_BB --> " + tPlCur.getString(12) );
                    Log.d("buble", "  - ResIDAA --> " + resIDAA);
                    Log.d("buble", "  - resIDBB --> " + resIDBB);
                    Log.d("buble", "13- FUClave --> " + tPlCur.getInt(13) );
                    Log.d("buble", "14- Estado  --> " + tPlCur.getString(14) );
                    Log.d("buble", "15- FUPlanta--> " + tPlCur.getInt(15) );

                    planta newPlanta = new planta(tPlCur.getInt(0), tPlCur.getInt(1), tPlCur.getString(2),
                            tPlCur.getString(3), tPlCur.getString(4), tPlCur.getString(5),
                            tPlCur.getInt(6), tPlCur.getInt(7), tPlCur.getInt(8),
                            tPlCur.getInt(9), tPlCur.getInt(10), tPlCur.getString(11),
                            tPlCur.getString(12), resIDAA, resIDBB, tPlCur.getInt(13),
                            tPlCur.getString(14), tPlCur.getInt(15));
                    allPlants.add(newPlanta);

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

        int incluir = 0;
        allPlantasUsar.clear();
        mAdapter.notifyDataSetChanged();
        if (allPlants.size() > 0) {
            for (planta p : allPlants) {
                allPlantasUsar.add(p);
                mAdapter.notifyItemInserted(incluir);
                incluir++;
            }

            //mRecyclerView.scrollToPosition(0);
        } else {
            Toast.makeText(getContext(),"No hay fichas de esa familia",Toast.LENGTH_LONG).show();
        }
        return incluir;
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("onde", "Entro en onPause zz_1");
        Log.d("onde", "Salgo en onPause zz_1");

    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("onde", "Entro en onStop zz_1");
        Log.d("onde", "Salgo en onStop zz_1");

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("onde", "Entro en onDestroy zz_1");
        Log.d("onde", "Salgo en onDestroy zz_1");

    }

    @Override
    public void OnItemClick(View v, int position) {
        Toast.makeText(getContext(), "Pulsado sobre " + allPlantasUsar.get(position).getmNombreCientifico(), Toast.LENGTH_SHORT).show();
        Log.d("click", "Pulsado sobre " + allPlantasUsar.get(position).getmNombreCientifico());
    }
}