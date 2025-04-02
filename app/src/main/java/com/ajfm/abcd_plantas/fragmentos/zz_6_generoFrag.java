package com.ajfm.abcd_plantas.fragmentos;

import static com.ajfm.abcd_plantas.MainActivity.tabLayout;
import static com.ajfm.abcd_plantas.SplashActivity.allGeneros;
import static com.ajfm.abcd_plantas.SplashActivity.idFamiliaGlobalClave;
import static com.ajfm.abcd_plantas.SplashActivity.nombGeneroGlobal;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentContainerView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ajfm.abcd_plantas.R;
import com.ajfm.abcd_plantas.adaptadores.AdaptadorGenero;
import com.ajfm.abcd_plantas.modelos.genero;

import java.util.ArrayList;
import java.util.List;

public class zz_6_generoFrag extends Fragment implements AdaptadorGenero.OnItemClickListener {

    private TextView tVgen;
    private RecyclerView gRecyclerViewG;
    private RecyclerView.LayoutManager glayoutManagerG;
    private AdaptadorGenero gAdapterG;
    public static List<genero> hallados;
    public static String genYesp;
    private LinearLayoutCompat ll03;

    //private IRemitente emisor3;
    //private IReceptor receptor3;
    private TextView tV3;
    private FragmentContainerView fragment_container_view_tag;

    public zz_6_generoFrag() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("onde", "Entro en onCreate zz_3 ===================================");
        hallados = new ArrayList<>();

/*        getParentFragmentManager().setFragmentResultListener("key5", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                String cCrear = result.getString("conCre");
            }
        });*/

        Log.d("onde", "Salgo de onCreate zz_3");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("onde", "Entro en onCreateView zz_3-------------------------");
        // Inflate the layout for this fragment
        Log.d("life","Entrando en zz_3... onCreateView");


        View view = inflater.inflate(R.layout.fragment_zz_3_generofrag, container, false);
        tVgen = (TextView) view.findViewById(R.id.tVgen);


        Log.d("onde", "Salgo de onCreateView zz_3");
        Log.d("life","Saliendo de zz_3... onCreateView");
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("onde", "Entro en onStart zz_3");
        hallados = obtenerGeneros(idFamiliaGlobalClave);
        /*requireActivity().getSupportFragmentManager().setFragmentResultListener("key3", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                int faml = result.getInt("idFamilia");
                Toast.makeText(getContext(), "Recibido de FragmentManager correcto, con key3 y list(idFamilia)= " + faml, Toast.LENGTH_SHORT).show();
                hallados = obtenerGeneros(faml);
            }
        });*/
        Log.d("onde", "Salgo en onStart zz_3");
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d("onde", "Entro en onViewCreated zz_3");

        ll03 = view.findViewById(R.id.ll03);
        ll03.setVisibility(View.INVISIBLE);

        //Este LinearLayoutCompat no es visible porque lo usé para las pruebas de las interfaces, contiene un textview y un boton
        //para enviar a la interface el dato que se elija, como en realidad no ha hecho falta porque el envío lo he incluido
        //en el AdaptadorGenero no quiero eliminarlo por si vuelve a fallar, para volverlo a mostrar hay que hacerlo visible y
        //modificar los Constraint en el XML , lo que hay debajo 'tapa' el LLC y hay que cambiar su Top_ofTopof ="parent"
        //por Top_ofBottomof = "@id/linearLayoutCompat

        tV3 = (TextView) view.findViewById(R.id.tV3);
        fragment_container_view_tag = view.findViewById(R.id.fragment_container_view_tag);
        /*Button btnSender2 = view.findViewById(R.id.button3);
        btnSender2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emisor3.dataSended(gener);
                int pos = tabLayout.getSelectedTabPosition();
                tabLayout.selectTab(tabLayout.getTabAt(pos + 1));
                tV3.setText(Integer.toString(pos));
            }
        });*/

        gRecyclerViewG = (RecyclerView) view.findViewById(R.id.rCy);
        gRecyclerViewG.setHasFixedSize(true); //si sabemos que no va a zz_7_cambiar de tamaño
        gRecyclerViewG.setItemAnimator(new DefaultItemAnimator());

        glayoutManagerG = new LinearLayoutManager(getContext());
        gRecyclerViewG.setLayoutManager(glayoutManagerG);
        gAdapterG = new AdaptadorGenero(hallados);
        gRecyclerViewG.setAdapter(gAdapterG);
        gAdapterG.setOnItemClickListener(this);

        int hay = hallados.size();
        if (hay>0){
            String heys = Integer.toString(hay);
            String str = "Hay " + heys + " géneros en total";
            tVgen.setTextAlignment(View.TEXT_ALIGNMENT_GRAVITY);
            tVgen.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            tVgen.setText(str);
            Log.d("onde", "Salgo de onViewCreated zz_3");
        } else {
            String str = "No hay ninguna familia seleccionada";
            tVgen.setTextAlignment(View.TEXT_ALIGNMENT_GRAVITY);
            tVgen.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            tVgen.setText(str);
        }
        Log.d("onde", "Salgo en onViewCreated zz_3");

    }

    @Override
    public void OnItemClick(View v, int position) {
        Log.d("onde", "Entro en onItemClick zz_3");

        if (hallados.size() > 0) {
            genYesp = hallados.get(position).getgGeneroyFamilia();

            nombGeneroGlobal = hallados.get(position).getgGeneroyFamilia();

            int pos = tabLayout.getSelectedTabPosition();
            tabLayout.selectTab(tabLayout.getTabAt(pos + 1));
            Log.d("culo", "Genero y especie elegido: " + genYesp);
        } else {
            String no = "No hay ninguna familia seleccionada ";
        }
        Log.d("onde", "Salgo en onItemClick zz_3");
    }
    private List<genero> obtenerGeneros(int fimili) {
        Log.d("onde", "Entro en obtenerGeneros zz_3");
        List<genero> hallando = new ArrayList<>();
        int genes = 0;
        for (genero egen : allGeneros) {
            if (egen.getgId_en_FUClave() == (fimili)) {
                hallando.add(egen);
                genes++;
                Log.d("genero", "Hallado genero " + genes + "  " + egen.getgGenero() + " " + egen.getGespecie());
            }
        }
        int hay = hallando.size();
        if (hay > 0) {
            gAdapterG = new AdaptadorGenero(hallando);
            gRecyclerViewG.setAdapter(gAdapterG);
            gAdapterG.notifyDataSetChanged();
            String heys = Integer.toString(hay);
            String str = heys + " géneros en " + hallando.get(0).getgFamilia();
            tVgen.setTextAlignment(View.TEXT_ALIGNMENT_GRAVITY);
            tVgen.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            tVgen.setText(str);
        } else {
            Log.d("nohay", "No hay generos en la familia con ese id:" + fimili);
        }
        Log.d("onde", "Salgo de obtenerGeneros zz_3");
        return hallando;
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("onde", "Entro en onResume zz_3");

        /* requireActivity().getSupportFragmentManager().setFragmentResultListener("key3", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                int faml = result.getInt("idFamilia");
                Toast.makeText(getContext(), "Recibido de FragmentManager correcto, con key3 y list(idFamilia)= " + faml, Toast.LENGTH_SHORT).show();
                obtenerGeneros(faml);
            }
        });*/

        Log.d("onde", "Salgo en onResume zz_3");
    }

    @Override
    public void onPause() {
        super.onPause();
        Log.d("onde", "Entro en onPause zz_3");
        Log.d("onde", "Salgo en onPause zz_3");

    }

    @Override
    public void onStop() {
        super.onStop();
        Log.d("onde", "Entro en onStop zz_3");
        Log.d("onde", "Salgo en onStop zz_3");

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("onde", "Entro en onDestroy zz_3");
        Log.d("onde", "Salgo en onDestroy zz_3");

    }

    /* private List<planta> obtenerSpinados(String consCr){
        List<planta> allPlants = new ArrayList<>();

        HiloAbrirBDD2 hilo2 = new HiloAbrirBDD2(todasPlantas, consCr, getContext());
        Log.d("trozo", "Realizar busqueda: " + consCr);
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
                    nombreAA = tPlCur.getString(10);
                    int largo = nombreAA.length() - 4;
                    nombreAA = nombreAA.substring(0, largo);

                    nombreBB = tPlCur.getString(11);
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

                    planta newPlanta = new planta(tPlCur.getInt(0), tPlCur.getString(1),
                            tPlCur.getString(2), tPlCur.getString(3), tPlCur.getString(4),
                            tPlCur.getInt(5), tPlCur.getInt(6), tPlCur.getInt(7),
                            tPlCur.getInt(8), tPlCur.getInt(9), tPlCur.getString(10),
                            tPlCur.getString(11), resIDAA, resIDBB, tPlCur.getInt(12));
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
    }*/

   /* @Override
    public void onResume() {
        super.onResume();
        String recibido;
        if ((receptor3.getData() != null)&& !(receptor3.getData().equals(""))){
            recibido = receptor3.getData();
            try {
                int rec = Integer.parseInt(recibido);
                obtenerGeneros(rec);
            } catch (Exception e){
                Log.d("error", "He recibido " + recibido + "(texto) en lugar del id de la familia, ¿Vengo a zz_4_ con el nombre de ungénero? ");
            }

        } else {
            recibido = "Familia ";
        }
        tV3.setText(recibido);
    }*/

    /*@Override
    public void onAttach(Context context) {
        super.onAttach(context);
        emisor3 = (IRemitente) context;
        receptor3 = (IReceptor) context;
    }*/
}