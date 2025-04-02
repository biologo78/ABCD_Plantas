package com.ajfm.abcd_plantas.fragmentos;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.fragment.app.Fragment;

import com.ajfm.abcd_plantas.R;
//import com.ajfm.abcd_plantas.interfaces.IReceptor;
//import com.ajfm.abcd_plantas.interfaces.IRemitente;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link zz_8_salir#newInstance} factory method to
 * create an instance of this fragment.
 */
public class zz_8_salir extends Fragment {

    //private IRemitente emisor8;
    //private IReceptor receptor8;
    private TextView tV8, tV88;
    private Button btn8;
    private LinearLayoutCompat ll08;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public zz_8_salir() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment zz_8_salir.
     */
    // TODO: Rename and change types and number of parameters
    public static zz_8_salir newInstance(String param1, String param2) {
        zz_8_salir fragment = new zz_8_salir();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.d("life","Entrando en zz_8... onCreateView");
        Log.d("life","Saliendo de zz_8... onCreateView");
        return inflater.inflate(R.layout.fragment_zz_8_salir, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
/*        ll08 = view.findViewById(R.id.ll08);
        ll08.setVisibility(View.VISIBLE);

        //Este LinearLayoutCompat no es visible porque lo usé para las pruebas de las interfaces, contiene un textview y un boton
        //para enviar a la interface el dato que se elija, como en realidad no ha hecho falta porque el envío lo he incluido
        //en el AdaptadorGenero no quiero eliminarlo por si vuelve a fallar, para volverlo a mostrar hay que hacerlo visible y
        //modificar los Constraint en el XML , lo que hay debajo 'tapa' el LLC y hay que cambiar su Top_ofTopof ="parent"
        //por Top_ofBottomof = "@id/linearLayoutCompat

        tV8 = (TextView) view.findViewById(R.id.tV8);
        Button btnSender2 = view.findViewById(R.id.button8);
        btnSender2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emisor8.dataSended("Desde Fragm 8");
            }
        });*/
    }
/*
    @Override
    public void onResume() {
        super.onResume();
        String recibido;
        if ((receptor8.getData() != null) && !(receptor8.getData().equals(""))){
            recibido = receptor8.getData();
        } else {
            recibido = "Fragmento Ocho";
        }
        tV8.setText(recibido);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        emisor8 = (IRemitente) context;
        receptor8 = (IReceptor) context;
    }*/
}