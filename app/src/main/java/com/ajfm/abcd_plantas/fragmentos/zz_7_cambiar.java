package com.ajfm.abcd_plantas.fragmentos;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.fragment.app.Fragment;

import com.ajfm.abcd_plantas.R;
import com.ajfm.abcd_plantas.modelos.reglinea;

import java.util.List;


public class zz_7_cambiar extends Fragment {

    //private IRemitente emisor7;
    //private IReceptor receptor7;
    private TextView tV7, tV77;
    private Button btn7;
    private LinearLayoutCompat ll07;
    private ListView lista;
    private List<reglinea> lineasCabeza;
    private List<Integer> lineasConFam;
    private List<Integer> lineasConGen;
    private List<Integer> lineasConEsp;
    private List<Integer> lineasConFle;
    private List<Integer> lineasConGui;

    private String[] renglones;

    private String fam = "Familia";
    private String gen = "Genero";
    private String esp = "Especie";
    private String flecha = "--> ";

    public zz_7_cambiar() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.d("life","Entrando en zz_7... onCreateView");
        View view = inflater.inflate(R.layout.fragment_zz_7_cambiar, container, false);
        Log.d("life","Saliendo de zz_7... onCreateView");

        return view;
    }
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

    }
}