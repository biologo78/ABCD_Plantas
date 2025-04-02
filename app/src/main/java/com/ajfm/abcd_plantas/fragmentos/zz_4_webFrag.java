package com.ajfm.abcd_plantas.fragmentos;

import static com.ajfm.abcd_plantas.SplashActivity.nombGeneroGlobal;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.fragment.app.Fragment;

import com.ajfm.abcd_plantas.R;

public class zz_4_webFrag extends Fragment {

    //private IRemitente emisor4;
    //private IReceptor receptor4;
    private TextView tV4, tWeb;
    private LinearLayoutCompat ll04;

    private WebView webView;
    private String generoRecibido;

    public zz_4_webFrag() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("camino", "Entro y salgo onCreateView");
        // Inflate the layout for this fragment
        Log.d("life","Entrando en zz_4... onCreateView");
        Log.d("life","Saliendo de zz_4... onCreateView");
        return inflater.inflate(R.layout.fragment_zz_4_web, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Log.d("camino", "Entro onViewCreated");

        ll04 = view.findViewById(R.id.ll04);
        ll04.setVisibility(View.VISIBLE);

        //Este LinearLayoutCompat no es visible porque lo usé para las pruebas de las interfaces, contiene un textview y un boton
        //para enviar a la interface el dato que se elija, como en realidad no ha hecho falta porque el envío lo he incluido
        //en el AdaptadorGenero no quiero eliminarlo por si vuelve a fallar, para volverlo a mostrar hay que hacerlo visible y
        //modificar los Constraint en el XML , lo que hay debajo 'tapa' el LLC y hay que cambiar su Top_ofTopof ="parent"
        //por Top_ofBottomof = "@id/linearLayoutCompat

        tV4 = (TextView) view.findViewById(R.id.tV4);
       /* Button btnSender2 = view.findViewById(R.id.button4);
        btnSender2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                emisor4.dataSended("Envío desde Frag4 webFrag");
            }
        });*/

        tWeb = (TextView) view.findViewById(R.id.tWeb);
        webView = (WebView) view.findViewById(R.id.web);
        Log.d("camino", "Salgo onViewCreated");

    }

    @Override
    public void onResume() {
        super.onResume();
       /* requireActivity().getSupportFragmentManager().setFragmentResultListener("key4", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                String genYesp = result.getString("genero");
                abrirWeb(genYesp);
            }
        });*/
        abrirWeb(nombGeneroGlobal);
    }

    private void abrirWeb(String genero) {
        Log.d("camino", "Entro abrirWeb " + genero);
        try {
            Log.d("culo", genero);
            String direccion1 = "https://es.wikipedia.org/wiki/" + genero;
            openWebPage(direccion1);
            webView.setBackgroundColor(Color.CYAN);
            webView.getSettings().setJavaScriptEnabled(true);
            webView.loadUrl(direccion1);
            webView.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    view.loadUrl(url);
                    return false;
                }
            });
        } catch (Exception e) {
            Log.d("culo", e.toString());
        }
        tWeb.setText(genero);
        Log.d("camino", "Salgo abrirWeb " + genero);

    }

    private void openWebPage(String url) {
        Log.d("camino", "Entro openWebPage " + url);

        Uri webPage = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, webPage);
        if (intent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivity(intent);
        }
        Log.d("camino", "Salgo openWebPage " + url);

    }

    /*@Override
    public void onResume() {
        super.onResume();
        String recibido="";//= gener;
        Log.d("camino", "Entro onResume " + recibido);
        if ((receptor4.getData() != null) && !(receptor4.getData().equals(""))) {
            try {
                recibido = receptor4.getData();
                int rec = Integer.parseInt(recibido);
            } catch (Exception e) {
                Log.d("error", "He recibido " + recibido + "(numero) en lugar del nombre de un genero, ¿Vengo a zz_3_ con el id de familiahallada? ");
                recibido = "no encontrado";
            }
        } else {
            recibido = "no encontrado";
        }
        abrirWeb(recibido);
        tV4.setText(recibido);
        Log.d("camino", "Salgo onResume " + recibido);

        getParentFragmentManager().setFragmentResultListener("key4", this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                String generoObtenido = result.getString("posicion");
                if ((generoObtenido != null) && !(generoObtenido.equals(""))) {
                    abrirWeb(generoObtenido);
                } else {
                    abrirWeb("HTTP_404");
                }
                tV4.setText(generoObtenido);
            }
        });

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d("camino", "Entro onAttach");
        emisor4 = (IRemitente) context;
        receptor4 = (IReceptor) context;
        Log.d("camino", "Salgo onAttach");

    }*/
}