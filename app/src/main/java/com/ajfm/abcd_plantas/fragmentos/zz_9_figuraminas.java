package com.ajfm.abcd_plantas.fragmentos;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ajfm.abcd_plantas.ModeloVision;
import com.ajfm.abcd_plantas.R;
import com.github.chrisbanes.photoview.PhotoView;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Objects;

public class zz_9_figuraminas extends Fragment {

    ImageView imV1, imV2;
    TextView tit1, tit2;
    String titFig = "";
    String ficFig = "";
    int resFig = 0;

    String titLam = "";
    String ficLam = "";
    int resLam = 0;
    int llevo = 0;
    List<String> dataLista;

    private String pieLam;
    private String pieFig;
    private int resIdLam;
    private int resIdFig;

    private int nS = 0;
    private String tD = "<->";
    private String tF = "<->";
    private String tG = "<->";

    private ModeloVision viewModel;
    private int origen;

    public zz_9_figuraminas() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(ModeloVision.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_zz_9_figuraminas, container, false);
        assert getActivity() != null;
        return view;
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d("zz_2_life", "Entro zz_9_figuraminas - onViewCreated INICIO");
        Log.d("zz_2_life", "Valores actuales: tD:" + tD + "tF:" + tF + "tG:" + tG + "nS:" + nS + "origen:" + origen);
        imV1 = (PhotoView) view.findViewById(R.id.imV1);
        imV2 = (PhotoView) view.findViewById(R.id.imV2);
        tit1 = (TextView) view.findViewById(R.id.titulo1);
        tit2 = (TextView) view.findViewById(R.id.titulo2);

        imV1.setOnClickListener(this::onClick);
        imV2.setOnClickListener(this::onClick);
        tit1.setOnClickListener(this::onClick);
        tit2.setOnClickListener(this::onClick);

        // Inicializar el ViewModel compartido
        viewModel = new ViewModelProvider(requireActivity()).get(ModeloVision.class);

        viewModel.getMuttextSelectD().observe(getViewLifecycleOwner(), tDel -> {
            tD = tDel;
        });
        viewModel.getMuttextSelectF().observe(getViewLifecycleOwner(), tFdel -> {
            tF = tFdel;
        });
        viewModel.getMuttextSelectG().observe(getViewLifecycleOwner(), tGdel -> {
            tG = tGdel;
        });
        viewModel.getMutnumSelect().observe(getViewLifecycleOwner(), nSdel -> {
            nS = nSdel;
        });
        viewModel.getOrigen().observe(getViewLifecycleOwner(), torig -> {
            origen = torig;
        });
        Log.d("zz_2_life", "Valores actuales: tD:" + tD + "tF:" + tF + "tG:" + tG + "nS:" + nS + "origen:" + origen);
        Log.d("zz_2_life", "zz_9_figuraminas - onViewCreated FINAL\n");
        }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("zz_2_life", "zz_9_figuraminas - onResume INICIO");
        Log.d("zz_2_life", "Valores actuales: tD:" + tD + "tF:" + tF + "tG:" + tG + "nS:" + nS + "origen:" + origen);

        String abre = "Abreviaturas";
        String flora = "Flora Ibérica";
        assert getActivity() != null;
        //imagenes standard para el caso de entrar sin que haya ni fig ni lam
        int resIdFlora = getActivity().getResources().getIdentifier("lam_a", "drawable", getActivity().getPackageName());
        int resIdAbrv = getActivity().getResources().getIdentifier("lam_b", "drawable", getActivity().getPackageName());

        this.imV2.setVisibility(View.INVISIBLE);

        viewModel.getPieLam().observe(getViewLifecycleOwner(), pieL -> {
            pieLam = pieL != null ? pieL : flora;
        });
        viewModel.getPieFig().observe(getViewLifecycleOwner(), pieF -> {
            pieFig = pieF != null ? pieF : abre;
        });
        viewModel.getResIdFig().observe(getViewLifecycleOwner(), resIdF -> {
            resIdFig = resIdF != null ? resIdF : resIdAbrv;
        });
        viewModel.getResIdLam().observe(getViewLifecycleOwner(), resIdL -> {
            resIdLam = resIdL != null ? resIdL : resIdFlora;
        });
        viewModel.getOrigen().observe(getViewLifecycleOwner(), torig -> {
            origen = torig != null ? torig : 9;
        });

        //si no hay figura cargada pongo la de abreviaturas
        if (resIdFig == 0) {
            resIdFig = resIdAbrv;
        }
        Picasso.get().load(resIdFig).into(imV2);
        //si no hay lámina cargada pongo la de flora ibérica
        if (resIdLam == 0) {
            resIdLam = resIdFlora;
        }
        Picasso.get().load(resIdLam).into(imV1);
        tit2.setText(pieFig);
        tit1.setText(pieLam);

        Log.d("zz_2_life", "Valores actuales: tD:" + tD + "tF:" + tF + "tG:" + tG + "nS:" + nS + "origen:" + origen);
        Log.d("zz_2_life", "zz_9_figuraminas - onResume FINAL\n");

    }

    @Override
    public void onPause() {//cuando me voy de este fragment
        super.onPause();
        Log.d("zz_2_life", "zz_9_figuraminas - onPause INICIO");
        Log.d("zz_2_life", "Valores actuales: tD:" + tD + "tF:" + tF + "tG:" + tG + "nS:" + nS + "origen:" + origen);

        //ModeloVision viewModel = new ViewModelProvider(requireActivity()).get(ModeloVision.class);
        viewModel.setOrigen(9);
        viewModel.setPieFig(pieFig);
        viewModel.setPieLam(pieLam);
        viewModel.setresIdFig(resIdFig);
        viewModel.setresIdFig(resIdLam);

        Log.d("zz_2_life", "Valores actuales: tD:" + tD + "tF:" + tF + "tG:" + tG + "nS:" + nS + "origen:" + origen);
        Log.d("zz_2_life", "zz_9_figuraminas - onPause FINAL\n");
        }

    private void onClick(View v) {
        Toast.makeText(getActivity(), "Del viewModel " + tD + " " + tF + " " + tG + " " + nS, Toast.LENGTH_LONG).show();
        if (v == imV1 || v == tit2) {//Hago click en el PicView 1 o en titulo 2
            if (imV2.getVisibility() == View.INVISIBLE) {
                imV2.setVisibility(View.VISIBLE);
                imV1.setVisibility(View.INVISIBLE);
                tit1.setEnabled(true);
                tit2.setEnabled(false);
            } else {
                imV1.setVisibility(View.VISIBLE);
                imV2.setVisibility(View.INVISIBLE);
                tit2.setEnabled(true);
                tit1.setEnabled(false);
            }
        } else if (v == imV2 || v == tit1) {//Hago click en el PicView 2 o en titulo 1
            if (imV2.getVisibility() == View.INVISIBLE) {
                imV2.setVisibility(imV2.getVisibility());
                imV1.setVisibility(View.INVISIBLE);
                tit1.setEnabled(true);
                tit2.setEnabled(false);
            } else {
                imV1.setVisibility(View.VISIBLE);
                imV2.setVisibility(View.INVISIBLE);
                tit2.setEnabled(true);
                tit1.setEnabled(false);
            }
        }
    }
}