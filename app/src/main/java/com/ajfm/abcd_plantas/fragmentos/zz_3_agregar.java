package com.ajfm.abcd_plantas.fragmentos;

import android.content.ContentValues;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.ajfm.abcd_plantas.R;

import java.util.ArrayList;
import java.util.List;

public class zz_3_agregar extends Fragment {

    private TextView tVTit;
    private Button btnoenP, btnYaen2;
    private Button btnSave, btnClean;
    private Button btnUtil, btnUtil2;
    private Spinner sp61, sp62, sp63, sp64, sp65, sp67, sp68;
    private EditText eTfM, eTVulgar, eTObs;
    private final List<String> sp61V1 = new ArrayList<>();
    private final List<String> sp62V2 = new ArrayList<>();

    private ListView lstOpciones;

    private ImageView imgAA;
    private ImageView imgBB;

    private ContentValues cVPlanta;

    public zz_3_agregar() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        Log.d("life","Entrando en zz_6... onCreateView");
        Log.d("life","Saliendo de zz_6... onCreateView");
        return inflater.inflate(R.layout.fragment_zz_6_agregar, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        tVTit = (TextView) view.findViewById(R.id.tVTit);
        eTfM = (EditText) view.findViewById(R.id.eTfM);
        eTVulgar = (EditText) view.findViewById(R.id.eTVulgar);
        eTObs = (EditText) view.findViewById(R.id.eTObs);

        btnoenP = (Button) view.findViewById(R.id.btnoenP);
        btnYaen2 = (Button) view.findViewById(R.id.btnYaen2);
        btnSave = (Button) view.findViewById(R.id.btnSave);
        btnClean = (Button) view.findViewById(R.id.btnClean);
        btnUtil = (Button) view.findViewById(R.id.btnUtil);
        btnUtil2 = (Button) view.findViewById(R.id.btnUtil2);

        sp61 = (Spinner) view.findViewById(R.id.sp61);
        sp62 = (Spinner) view.findViewById(R.id.sp62);
        sp63 = (Spinner) view.findViewById(R.id.sp63);
        sp64 = (Spinner) view.findViewById(R.id.sp64);
        sp65 = (Spinner) view.findViewById(R.id.sp65);
        sp67 = (Spinner) view.findViewById(R.id.sp67);
        sp68 = (Spinner) view.findViewById(R.id.sp68);

        imgAA = (ImageView) view.findViewById(R.id.imgAA);
        imgBB = (ImageView) view.findViewById(R.id.imgBB);

        cVPlanta = new ContentValues();

        //=========================== BOTÓN GUARDAR NUEVA FICHA ============================================
        /*btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cVPlanta.put("Nombre_vulgar", eTVulgar.getText().toString());
                cVPlanta.put("largo_vulgar", eTVulgar.getText().toString().length());
                int esp = contarCaracter(eTVulgar.getText().toString(),' ');
                cVPlanta.put("espacios_vulgar", esp);
                cVPlanta.put("Estado_Planta",eTObs.getText().toString());
                Log.d("CntVal",cVPlanta.toString());

                String FtAA = cVPlanta.get("Foto_AA").toString();
                int fin = FtAA.length();
                FtAA = FtAA.substring(4,fin);

                String FtBB = cVPlanta.get("Foto_BB").toString();
                int finB = FtBB.length();
                FtBB = FtBB.substring(4,finB);

                try {
                    whenConvertingToFile_thenCorrect(FtAA);
                    whenConvertingToFile_thenCorrect(FtBB);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                 }
        });
*/
        //=========================== BOTÓN LIMPIAR ============================================
       /* btnClean.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eTfM.setText(getString(R.string.nueva_familia_en_planta));
                eTVulgar.setText(getString(R.string.nombre_vulgar));
                eTObs.setText(getString(R.string.observaciones));
                sp61.setSelection(0);
                sp62.setSelection(0);
                sp63.setSelection(0);
                sp64.setSelection(0);
                sp65.setSelection(0);

                String nodisp = "imagen_no_disponible" ;
                //File dir = Environment.getExternalStorageDirectory();
                //String path = dir.getAbsolutePath() + "/Download/" + nodisp;
                //eTVulgar.setText(path);
                int resIDAA = getResources().getIdentifier(nodisp, "drawable", getActivity().getPackageName());

                Picasso.get().load(resIDAA).resize(widthImg/2,widthImg/2).centerCrop().into(imgAA);
                Picasso.get().load(resIDAA).resize(widthImg/2,widthImg/2).centerCrop().into(imgBB);
            }
        });
*/
        //======================================================================
       /* adaptandoSpinnerFamCla(view.getContext(),0);
        List<String> paraSpin63 = leerDirectorio2();
        if (paraSpin63 != null){
            adaptarSpinners2(view.getContext(), leerDirectorio2());
        } else {
            Toast.makeText(view.getContext(),"No hay imágenes disponibles", Toast.LENGTH_LONG).show();
        }
    }

    public void whenConvertingToFile_thenCorrect(String nombre) throws IOException {
        //================== COPIA LAS IMAGENES DESDE LA CARPETA DOWNLOAD A LA CARPETA DE PICTURES DE LA APP =========
        Path path = null;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            path = Paths.get(Environment.getExternalStorageDirectory().getPath() + "/Download/" + nombre);

            //File dir = Environment.getExternalStorageDirectory();
            //path = dir.getAbsolutePath() + "/Download/" + nombre;
            byte[] buffer = Files.readAllBytes(path);
            File file1 = new File(getExternalStoragePublicDirectory(DIRECTORY_PICTURES),"/ABCPlantas/");
                    //getExternalFilesDir(null), "//ABCPlantas//");
            if (!file1.exists()){
                file1.mkdirs();
            }
            File targetFile = new File (file1 + nombre);
            OutputStream outStream = Files.newOutputStream(targetFile.toPath());
            outStream.write(buffer);
            outStream.close();
        } else {
        }
    }
    private void adaptandoSpinnerFamCla(Context context, int idFUP) {
        //===== Creacion y adaptación del spinner 1 (Fam_Clave)

        sp61V1.clear();
        sp62V2.clear();
        //=========== Crea la lista de géneros desde la bla Generos y la llena ============================
        for (genero g : allGeneros) {
            Log.d("spin", "-->" + g.getgGeneroyFamilia());
            sp62V2.add(g.getgGeneroyFamilia());
        }
        //=========== Crea la lista de familias desde la tabla Familias_Claves y la llena ==================
        List<familia> listaMostrada = new ArrayList<>();
        for (familia f : allFamiliasClaves) {
            if (f.getmId_en_FUPlanta() >= idFUP) {
                Log.d("spin", "-->" + f.getmNombreFamilia());
                sp61V1.add(f.getmNombreFamilia());
                listaMostrada.add(f);
            }
        }

        //=========== Configura y asigna Adapter1 al spinner61 con la lista de familias
        ArrayAdapter<String> adapter1 = new ArrayAdapter<>(context,
                R.layout.unspinner,
                sp61V1);
        adapter1.setDropDownViewResource(R.layout.unspinner);
        sp61.setAdapter(adapter1);
        sp61.setSelection(0);

        //=========== Asigna accion al click sobre item de sp61
        //=================== lo agrega a cVPlanta para guardarlo despues
        sp61.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                int idFUClave = listaMostrada.get(position).getmIdFamilia();
                int idFUPlanta = listaMostrada.get(position).getmId_en_FUPlanta();
                StringBuilder sb0 = new StringBuilder();
                sb0.append(idFUClave).append("-->").append(listaMostrada.get(position).getmNombreFamilia()).append("-->").append(idFUPlanta);
                eTfM.setText(sb0.toString());
                Log.d("spin", sb0.toString());

                //================ Guarda los datos de la familia selecciona en cVPlanta para poder guardarlos ========================
                adaptarSpinnerGenCla(view.getContext(), idFUClave);
                adaptandoSpinnerFamPla(view.getContext(),idFUPlanta);
                adaptandoSpinnerGenPla(view.getContext(),idFUPlanta);

                cVPlanta.put("Familia", listaMostrada.get(position).getmNombreFamilia());
                cVPlanta.put("largo_familia",listaMostrada.get(position).getmNombreFamilia().length());
                cVPlanta.put("Id_en_FUPlanta",listaMostrada.get(position).getmId_en_FUPlanta());
                cVPlanta.put("Id_en_FUClave", listaMostrada.get(position).getmIdFamilia());

                Log.d("seleccionando", "Genero  " + listaMostrada.get(position).getmNombreFamilia() + " - Id_en_FUPlanta: " +
                        listaMostrada.get(position).getmId_en_FUPlanta() + " - Id_en_FUClave: " + listaMostrada.get(position).getmIdFamilia());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void adaptarSpinnerGenCla(Context context, int idFU) {
        //Creando a adaptando el spinner 2 (Generos Clave)
        sp62V2.clear();

        //========== Limpia la lista del spinner62 y con el id de familia recibido de sp61
        // vuelve a llenar la lista del sp62 solo con esos generos
        List<genero> listaGenerosMostrados = new ArrayList<>();
        for (genero g : allGeneros) {
            //Log.d("spin",Integer.toString(g.getgId_en_FUClave()) + "-->" + idFU);
            if (g.getgId_en_FUClave() == idFU) {
                StringBuilder sb1 = new StringBuilder();
                sb1.append(g.getgGeneroyFamilia());
                if (g.getgSubespecie() != null) {
                    sb1.append(" subsp ").append(g.getgSubespecie());
                }
                Log.d("spin", "Añadido " + sb1.toString());

                listaGenerosMostrados.add(g);
                sp62V2.add(sb1.toString());
            }
        }

        //=========== Configura y asigna Adapter2 al spinner62 con la lista de géneros de la familia racibida
        ArrayAdapter<String> adapter2 = new ArrayAdapter<>(context,
                R.layout.unspinner2,
                sp62V2);
        adapter2.setDropDownViewResource(R.layout.unspinner2);
        sp62.setAdapter(adapter2);
        sp62.setSelection(0);

        //=========== Asigna accion al click sobre item de sp62
        //=================== lo agrega a cVPlanta para guardarlo despues
        sp62.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d("seleccionando", "Genero  " + listaGenerosMostrados.get(position).getgGenero());
                cVPlanta.put("Nombre_cientifico", listaGenerosMostrados.get(position).getgGeneroyFamilia());
                cVPlanta.put("largo_cient", listaGenerosMostrados.get(position).getgGeneroyFamilia().length());
                int esp = contarCaracter(listaGenerosMostrados.get(position).getgGeneroyFamilia(), ' ');
                cVPlanta.put("espacios_cient", esp);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
    private void adaptandoSpinnerFamPla(Context context, int idFUPlanta){
        //Creando y adaptando el spinner 7 (Familias en planta)

        List<String> sp67V7 = new ArrayList<>();
        for (familia f :allFamiliasFichas){
            if (f.getmIdFamilia() == idFUPlanta){
                sp67V7.add(f.getmNombreFamilia());
            }
        }
        //=========== Configura y asigna Adapter7 al spinner67 con la lista de familias en Planta
        ArrayAdapter<String> adapter7 = new ArrayAdapter<>(context,
                R.layout.unspinner,
                sp67V7);
        adapter7.setDropDownViewResource(R.layout.unspinner);
        sp67.setAdapter(adapter7);
        sp67.setSelection(0);

        //=========== Asigna accion al click sobre item de sp62
        //=================== lo agrega a cVPlanta para guardarlo despues
        sp67.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Log.d("seleccionando", "Genero  " + listaGenerosMostrados.get(position).getgGenero());
                //cVPlanta.put("Nombre_cientifico", listaGenerosMostrados.get(position).getgGeneroyFamilia());
                //cVPlanta.put("largo_cient",listaGenerosMostrados.get(position).getgGeneroyFamilia().length());
                //int esp = contarCaracter(listaGenerosMostrados.get(position).getgGeneroyFamilia(),' ');
                //cVPlanta.put("espacios_cient",esp);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }





    private void adaptandoSpinnerGenPla(Context context, int idFUPlanta){
        //Creando y adaptando el spinner 8 (Generos en planta)

        List<String> sp68V8 = new ArrayList<>();
        for (planta p :allPlantasTotal){
            if (p.getmId_en_FUPlanta() == idFUPlanta){
                sp68V8.add(p.getmNombreCientifico());
            }
        }
        //=========== Configura y asigna Adapter7 al spinner67 con la lista de familias en Planta
        ArrayAdapter<String> adapter8 = new ArrayAdapter<>(context,
                R.layout.unspinner,
                sp68V8);
        adapter8.setDropDownViewResource(R.layout.unspinner);
        sp68.setAdapter(adapter8);
        sp68.setSelection(0);

        //=========== Asigna accion al click sobre item de sp62
        //=================== lo agrega a cVPlanta para guardarlo despues
        sp68.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //Log.d("seleccionando", "Genero  " + listaGenerosMostrados.get(position).getgGenero());
                //cVPlanta.put("Nombre_cientifico", listaGenerosMostrados.get(position).getgGeneroyFamilia());
                //cVPlanta.put("largo_cient",listaGenerosMostrados.get(position).getgGeneroyFamilia().length());
                //int esp = contarCaracter(listaGenerosMostrados.get(position).getgGeneroyFamilia(),' ');
                //cVPlanta.put("espacios_cient",esp);

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }




    public static int contarCaracter(String word, char letter){
        //=============== recuenta los caracteres letter que están en la cadena word ===================
        int counter = 0;
        for (int i = 0; i < word.length(); i++)
        {
            if(word.charAt(i) == letter)
            {
                counter++;
            }
        }
        return counter;
    }


    private List<String> leerDirectorio2() {

        //================ Lee el directorio ExternalStorageDirectory ..> Download
        //================ los ficheros (imágenes) encontrados los mete en la lista 'item'
        List<String> item = new ArrayList<>();

        //File file1 = new File(getExternalStoragePublicDirectory(DIRECTORY_DOWNLOADS),null);
        //String paz = file1.getAbsolutePath();
        //File carpeta = new File(paz);

        File dir = Environment.getExternalStorageDirectory();
        String path = dir.getAbsolutePath() + "/Download/";
        File carpeta = new File(path);


        String[] listado = carpeta.list();
        if (listado == null || listado.length == 0) {
            Toast.makeText(getContext(), "No hay elementos dentro de la carpeta actual", Toast.LENGTH_SHORT).show();
            Log.d("lista", "No hay elementos dentro de la carpeta actual--> " + path);
            return null;
        } else {
            for (int i = 0; i < listado.length; i++) {
                Log.d("lista", listado[i]);
            }
            for (String fich : listado) {
                item.add(fich.toString());
            }
            return item;
        }
    }


    private void adaptarSpinners2(Context context, List<String> lista) {
        final String[] nombre = {""};

        //=========== Configura y asigna Adapter3 al spinner63 con la lista de strig recibida como parámetro
        ArrayAdapter<String> adapter3 = new ArrayAdapter<>(context,
                R.layout.unspinner2,
                lista);
        adapter3.setDropDownViewResource(R.layout.unspinner2);
        sp63.setAdapter(adapter3);
        sp63.setSelection(0);

        //=================== Configura la accion al hacer click sobre item del spinner 63 que contiene las imagenes en contradas
        //=================== al clicar muestra la foto en el imageView correspondiente imgAA
        //=================== lo agrega a cVPlanta para guardarlo despues
        sp63.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                nombre[0] = lista.get(position);


                File dir = Environment.getExternalStorageDirectory();
                String path = dir.getAbsolutePath() + "/Download/" + nombre[0];

                Glide.with(requireContext()).load(path).into(imgAA);
                Log.d("seleccionando", "Imagen AA " + nombre[0]);
                String aa = "xxx_" + nombre[0];
                cVPlanta.put("Foto_AA", aa);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //=========== Configura y asigna Adapter4 al spinner64 con la lista de strig recibida como parámetro
        //=========== es la misma lista que para el spinner 63 que contiene los nombres de las imagenes encontradas
        ArrayAdapter<String> adapter4 = new ArrayAdapter<>(context,
                R.layout.unspinner,
                lista);
        adapter4.setDropDownViewResource(R.layout.unspinner);
        sp64.setAdapter(adapter4);
        sp64.setSelection(0);
        //=================== Configura la accion al hacer click sobre item del spinner 64 que contiene las imagenes en contradas
        //=================== al clicar muestra la foto en el imageView correspondiente imgBB
        //=================== lo agrega a cVPlanta para guardarlo despues
        sp64.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                nombre[0] = lista.get(position);

                File dir = Environment.getExternalStorageDirectory();
                String path = dir.getAbsolutePath() + "/Download/" + nombre[0];
                //eTVulgar.setText(path);

                Glide.with(requireContext()).load(path).into(imgBB);

                Log.d("seleccionando", "Imagen BB " + nombre[0]);
                String aa = "xxx_" + nombre[0];
                cVPlanta.put("Foto_BB", aa);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        //============== Llena la lista con los grupos obtenidos en la tabla Grupo y los asigna al spinner65
        List<String> grupo1 = new ArrayList<>();
        for (grupo g : allGrupos) {
            grupo1.add(g.getmNombreGrupo());
        }


        //=========== Configura y asigna Adapter5 al spinner65 con la lista de grupos
        ArrayAdapter<String> adapter5 = new ArrayAdapter<>(context,
                R.layout.unspinner2,
                grupo1);
        adapter5.setDropDownViewResource(R.layout.unspinner2);
        sp65.setAdapter(adapter5);
        sp65.setSelection(0);

        //=================== Configura la accion al hacer click sobre item del spinner 65 que contiene grupos
        //=================== lo agrega a cVPlanta para guardarlo despues
        sp65.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.d("seleccionando", allGrupos.get(position).getmNombreGrupo() + "  --  " + allGrupos.get(position).getmIdGrupo());
                cVPlanta.put("Grupo", allGrupos.get(position).getmNombreGrupo());
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });*/
    }
}







    /*public void openDirectory(Uri uriToLoad) {

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            if (Environment.isExternalStorageManager()) {
                //todo when permission is granted
                // Choose a directory using the system's file picker.
                Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT_TREE);

                // Optionally, specify a URI for the directory that should be opened in
                // the system file picker when it loads.
                intent.putExtra(DocumentsContract.EXTRA_INITIAL_URI, uriToLoad);

                startActivityForResult(intent, 123);
            } else {
                //request for the permission
                Intent intent = new Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION);
                Uri uri = Uri.fromParts("package", getActivity().getPackageName(), null);
                intent.setData(uri);
                startActivity(intent);
            }
        }
    }


    public static String getInternalStorageDirectoryPath(Context context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            StorageManager storageManager = (StorageManager) context.getSystemService(Context.STORAGE_SERVICE);
            return storageManager.getPrimaryStorageVolume().getDirectory().getAbsolutePath();
        } else {
            return Environment.getExternalStorageDirectory().getAbsolutePath();
        }
    }

    private List<String> obtenerListaFotos(View view) {

        List<String> item = null;
        item = new ArrayList<String>();

        //String str = Environment.getExternalStorageState();
        //File f = Environment.getExternalStorageDirectory();
        File f = new File(Environment.DIRECTORY_DOWNLOADS);
        //ruta.setText("Estado :" + str);
        File fil = new File(f,"*.*");
        File[] files = f.listFiles();

       *//* for (File file : files) {
            if (file.isDirectory())
                item.add(file.getName() + "/");
            else
                item.add(file.getName());
        }
*//*
        String s = Environment.getExternalStoragePublicDirectory(DIRECTORY_DOWNLOADS).toString();
        //Mostramos la ruta en el layout
        ruta.setText(s);
        pulsindo(view, item);
        return item;
        //ruta.setText(Environment.getExternalStorageDirectory() + "/MiBotiquin/");
    }*/
/*    private void pulsindo(View view, List<String> item){
        //Localizamos y llenamos la lista
        ListView lstOpciones = (ListView) view.findViewById(R.id.listaFiles);
        ArrayAdapter<String> fileList = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_list_item_1, item);
        lstOpciones.setAdapter(fileList);

        // Accion para realizar al pulsar sobre la lista
        lstOpciones.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Devuelvo los datos a la activity principal
                pulsin = parent.getSelectedItemPosition();
                ruta.setText(item.get(pulsin));

            }
        });
    }*/

 /*private void llenarLista() {
        //Localizamos y llenamos la lista

       //ListView lstOpciones = (ListView) findViewById(R.id.lstOpciones);
        List<String> listaImg = new ArrayList<>();
        ArrayAdapter<String> fileList = new ArrayAdapter<String>(requireContext(), android.R.layout.simple_list_item_1, item);
        sp63.setAdapter(fileList);

        // Accion para realizar al pulsar sobre la lista
        sp63.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                nombre = item.get(position);
                File dir = Environment.getExternalStorageDirectory();
                String path = dir.getAbsolutePath() + "/Download/" + nombre;
                ruta.setText(path);

                Glide.with(requireContext()).load(path).into(imgAA);

            }
        });
    }*/
