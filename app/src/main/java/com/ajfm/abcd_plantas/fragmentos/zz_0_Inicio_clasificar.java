package com.ajfm.abcd_plantas.fragmentos;

import static android.view.View.INVISIBLE;
import static android.view.View.VISIBLE;
import static com.ajfm.abcd_plantas.MainActivity.myViewPager2;
import static com.ajfm.abcd_plantas.SplashActivity.allFamUnificadas;
import static com.ajfm.abcd_plantas.SplashActivity.allGenerosUnic;
import static com.ajfm.abcd_plantas.SplashActivity.divisiones;
import static com.ajfm.abcd_plantas.SplashActivity.pasadas_ZZ_11;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.ajfm.abcd_plantas.ModeloVision;
import com.ajfm.abcd_plantas.R;
import com.ajfm.abcd_plantas.modelos.IdsGenero;
import com.ajfm.abcd_plantas.modelos.familia_unificada;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class zz_0_Inicio_clasificar extends Fragment {
    private String textSelectD = "";
    private String textSelectF = "";
    private String textSelectG = "";
    private String textSelectE = "";
    private String textSelectFinal = "";
    //private int numSelect = 0;

    private TextView tV11_1;
    private EditText etFam, etGen;
    private RadioGroup rGroup;
    private RadioButton rB1, rB2, rB3;
    public  CheckBox chkD, chkF, chkG;
    private Button btn7, btn6, btn2;
    private Spinner spn6, spn7;
    private LinearLayout ll1, ll2, ll3;
    private StringBuilder sb = new StringBuilder();
    private int[] slec1 = new int[6];
    private String[] slec2 = new String[3];

    private static boolean puedoSalir = false;
    private List<String> grupoSelec = new ArrayList<>();
    private List<String> grupoSelec2 = new ArrayList<>();
    private ArrayAdapter<String> adapter5;
    private ArrayAdapter<String> adapter6;

    private ModeloVision viewModel;
    private int origen;

    public zz_0_Inicio_clasificar() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(requireActivity()).get(ModeloVision.class);

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Log.d("life", "Entrando en zz_11_Ini onCreateView, pasadas:" + pasadas_ZZ_11);

        pasadas_ZZ_11++;

        View view = inflater.inflate(R.layout.fragment_zz_0__inicio_clasificar, container, false);

        Log.d("onde", "Entro en onCreateView zz_11=====================================");

        tV11_1 = (TextView) view.findViewById(R.id.tV11_1);
        etFam = (EditText) view.findViewById(R.id.etFam);
        etGen = (EditText) view.findViewById(R.id.etGen);

        ll1 = (LinearLayout) view.findViewById(R.id.llH1);
        ll2 = (LinearLayout) view.findViewById(R.id.llH2);
        ll3 = (LinearLayout) view.findViewById(R.id.llH3);

        rGroup = (RadioGroup) view.findViewById(R.id.rGroup);
        rB1 = (RadioButton) view.findViewById(R.id.rB1);
        rB2 = (RadioButton) view.findViewById(R.id.rB2);
        rB3 = (RadioButton) view.findViewById(R.id.rB3);
        rGroup.setVisibility(INVISIBLE);

        chkD = (CheckBox) view.findViewById(R.id.chkD);
        chkF = (CheckBox) view.findViewById(R.id.chkF);
        chkG = (CheckBox) view.findViewById(R.id.chkG);

        btn6 = (Button) view.findViewById(R.id.btn6);
        btn7 = (Button) view.findViewById(R.id.btn7);
        btn2 = (Button) view.findViewById(R.id.btn2);

        spn6 = (Spinner) view.findViewById(R.id.spin0);
        adapter5 = new ArrayAdapter<>(requireContext(),
                R.layout.unspinner,
                grupoSelec);
        adapter5.setDropDownViewResource(R.layout.unspinner4);
        spn6.setAdapter(adapter5);
        spn6.setSelection(0);

        spn7 = (Spinner) view.findViewById(R.id.spin1);

        btn6.setVisibility(INVISIBLE);
        spn6.setVisibility(INVISIBLE);
        ll2.setVisibility(INVISIBLE);
        spn7.setVisibility(INVISIBLE);
        ll3.setVisibility(INVISIBLE);

        btn7.setOnClickListener(new View.OnClickListener() {//Empezar a clasificar en Fragment 2
            @Override
            public void onClick(View v) {
                rB1.setEnabled(true);
                rB2.setEnabled(true);
                rB3.setEnabled(true);
                //pulsadoBuscar();
                int nSel = obtenerNumSelect();
                textSelectD = obtenerTxtSelD(nSel);
                boolean salir = false;
                salir = permitirSalida();

                if (salir) {
                    StringBuilder sbl2 = new StringBuilder();
                    sbl2.append(textSelectD).append("\t").append(textSelectF).append("\t").append(textSelectG).append("\t").append(textSelectE);
                    textSelectFinal = sbl2.toString();
                    Log.d("antesde", "Antes de alertar y hacer bundle:" + textSelectFinal + " NumSelect:" + nSel);
                    Log.d("antesde", "puedoSalir:" + String.valueOf(puedoSalir));
                    alertarD(nSel, textSelectFinal);
                    Log.d("antesde", "Despues de alertar y antes de hacer bundle:" + textSelectFinal + " NumSelect:" + nSel);
                    Log.d("antesde", "puedoSalir:" + String.valueOf(puedoSalir));

                    /*if (puedoSalir) {
                        StringBuilder sbl = new StringBuilder();
                        sbl.append(textSelectD).append("\t").append(textSelectF).append("\t").append(textSelectG).append("\tAntes de salir");
                        textSelectFinal = sbl.toString();
                        Toast.makeText(getContext(), textSelectFinal, Toast.LENGTH_LONG).show();

                        Bundle bundle = new Bundle();
                        bundle.putInt("numSel", numSelect);
                        bundle.putString("txtSel", textSelectFinal);
                        getParentFragmentManager().setFragmentResult("clave", bundle);
                        myViewPager2.setCurrentItem(1);
                        Log.d("antesde", "Despues de alertar y hacer bundle:" + textSelectFinal + "  " + numSelect);
                        Log.d("antesde", "puedoSalir:" + String.valueOf(puedoSalir));
                    }*/
                    Log.d("antesde", "Despues de todo:" + textSelectFinal + " NumSelect:" + nSel);
                    Log.d("antesde", "puedoSalir:" + String.valueOf(puedoSalir));
                    Log.d("saliendo", "Numero " + nSel + " Opcion:" + textSelectFinal);
                }
            }
        });

        btn6.setOnClickListener(new View.OnClickListener() {//Buscar en claves segun los radioButons, spinners y los CheckBoxes
            @Override
            public void onClick(View v) {
                pulsadoBuscar();
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {//Limpiar y desmarcar radioButons, spinners y los CheckBoxes
            @Override
            public void onClick(View v) {
                limpiar();
            }
        });
        chkD.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    rGroup.setVisibility(VISIBLE);
                    btn7.setVisibility(VISIBLE);
                    slec1[0] = 1;
                } else {
                    rGroup.setVisibility(INVISIBLE);
                    rB1.setChecked(false);
                    rB2.setChecked(false);
                    rB3.setChecked(false);
                    slec1[0] = 0;
                    slec1[1] = 0;
                    slec1[2] = 0;
                    slec1[3] = 0;
                }
            }
        });
        chkF.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    btn6.setVisibility(VISIBLE);
                    btn7.setVisibility(VISIBLE);
                    spn6.setVisibility(VISIBLE);
                    //ll2.setVisibility(VISIBLE);
                    slec1[4] = 1;
                    Log.d("checado", "Familia checada");
                } else {
                    slec1[4] = 0;
                    btn6.setVisibility(INVISIBLE);
                    btn7.setVisibility(VISIBLE);
                    spn6.setVisibility(INVISIBLE);
                    //ll2.setVisibility(INVISIBLE);
                    try {
                        spn6.setAdapter(null);
                        grupoSelec.clear();
                        adapter5.notifyDataSetChanged();
                    } catch (Exception e) {
                    }
                    Log.d("checado", "Familia no checada");
                }
            }
        });
        chkG.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    btn6.setVisibility(VISIBLE);
                    btn7.setVisibility(VISIBLE);
                    spn6.setVisibility(VISIBLE);
                    spn7.setVisibility(VISIBLE);
                    //ll3.setVisibility(VISIBLE);
                    slec1[5] = 1;
                    Log.d("checado", "Género checado");
                } else {
                    slec1[5] = 0;
                    btn6.setVisibility(INVISIBLE);
                    btn7.setVisibility(VISIBLE);
                    spn6.setVisibility(INVISIBLE);
                    spn7.setVisibility(INVISIBLE);
                    //ll3.setVisibility(INVISIBLE);
                    try {
                        spn6.setAdapter(null);
                        spn7.setAdapter(null);
                        grupoSelec.clear();
                        grupoSelec2.clear();
                        adapter6.notifyDataSetChanged();
                    } catch (Exception e) {
                    }
                    Log.d("checado", "Género no checado");
                }
            }
        });

        //Pteridophyta
        rB1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    slec1[1] = 1;
                } else {
                    try {
                        spn6.setVisibility(INVISIBLE);
                        spn7.setVisibility(INVISIBLE);
                        grupoSelec.clear();
                        adapter5.notifyDataSetChanged();
                        grupoSelec2.clear();
                        adapter6.notifyDataSetChanged();
                    } catch (Exception e) {
                        slec1[1] = 0;
                        Log.d("error", e.toString());
                    }
                }
            }
        });

        //Gymnosperma
        rB2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    slec1[2] = 1;
                } else {
                    try {
                        spn6.setVisibility(INVISIBLE);
                        spn7.setVisibility(INVISIBLE);
                        grupoSelec.clear();
                        adapter5.notifyDataSetChanged();
                        grupoSelec2.clear();
                        adapter6.notifyDataSetChanged();
                    } catch (Exception e) {
                    }
                    slec1[2] = 0;
                }
            }
        });

        //Angiosperma
        rB3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    slec1[3] = 1;
                } else {
                    try {
                        spn6.setVisibility(INVISIBLE);
                        spn7.setVisibility(INVISIBLE);
                        grupoSelec.clear();
                        adapter5.notifyDataSetChanged();
                        grupoSelec2.clear();
                        adapter6.notifyDataSetChanged();
                    } catch (Exception e) {
                    }
                    slec1[3] = 0;
                }
            }
        });

        spn6.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                textSelectF = (String) spn6.getItemAtPosition(position);
                int nSel = obtenerNumSelect();
                if (nSel > 47) {
                    grupoSelec2 = new ArrayList<>();
                    grupoSelec2 = obtenerConsu2(nSel, textSelectF);
                    grupoSelec2 = sortAlphabeticallyIgnoringNumber(grupoSelec2);
                    int hay = grupoSelec2.size();
                    if (hay > 0) {
                        spn7.setVisibility(VISIBLE);
                        ll3.setVisibility(VISIBLE);
                        Log.d("saliendo", "Num select:" + nSel);

                        adapter6 = new ArrayAdapter<>(getContext(),
                                R.layout.unspinner,
                                grupoSelec2);
                        adapter6.setDropDownViewResource(R.layout.unspinner3);
                        spn7.setAdapter(adapter6);
                        spn7.setSelection(0);
                    } else {
                        Toast.makeText(getContext(), "No hay géneros para clasificar en las claves", Toast.LENGTH_LONG).show();
                    }
                }
                hideKeyboard();
                Log.d("spin", "Seleccionado " + textSelectF);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                textSelectF = "";
            }
        });

        spn7.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                textSelectG = (String) spn7.getItemAtPosition(position);
                textSelectE ="";
                hideKeyboard();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                textSelectG = "";
                textSelectE = "";
            }
        });

        etFam.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterList(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        etGen.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterListG(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        onViewCreated();
        Log.d("onde", "Salgo en onCreateView zz_11");
        Log.d("life", "Saliendo de zz_11_Ini onCreateView, pasadas:" + pasadas_ZZ_11);
        return view;
    }

    private void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        View currentFocus = requireActivity().getCurrentFocus();
        if (currentFocus != null) {
            imm.hideSoftInputFromWindow(currentFocus.getWindowToken(), 0);
        }
    }

    private void filterList(String query) {
        List<String> filteredList = new ArrayList<>();
        if (query.isEmpty()) {
            filteredList.addAll(grupoSelec);
        } else {
            for (String item : grupoSelec) {
                if (item.toLowerCase().startsWith(query.toLowerCase())) {
                    filteredList.add(item);
                }
            }
        }
        adapter5 = new ArrayAdapter<>(requireContext(),
                R.layout.unspinner,
                filteredList);
        adapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spn6.setAdapter(adapter5);
    }

    private void filterListG(String query) {
        List<String> filteredList = new ArrayList<>();
        if (query.isEmpty()) {
            filteredList.addAll(grupoSelec2);
        } else {
            for (String item : grupoSelec2) {
                if (item.toLowerCase().contains(query.toLowerCase())) {
                    filteredList.add(item);
                }
            }
        }
        adapter6 = new ArrayAdapter<>(requireContext(),
                R.layout.unspinner2,
                filteredList);
        adapter6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spn7.setAdapter(adapter6);
    }

    public void onResume() {
        super.onResume();
        viewModel.getOrigen().observe(getViewLifecycleOwner(), torig -> {
            origen = torig != null ? torig : 0;
        });
        Log.d("origen", "Origen en zz_0_Inicio_clasificar: " + origen);
    }

    private void onViewCreated() {
        // Inicializar el ViewModel compartido
        viewModel = new ViewModelProvider(requireActivity()).get(ModeloVision.class);

        viewModel.getOrigen().observe(getViewLifecycleOwner(), torig -> {
            origen = torig != null ? torig : 0;
            ;
        });
    }

    @Override
    public void onPause() {
        super.onPause();
        viewModel.setOrigen(0);

        int nSel = obtenerNumSelect();

        viewModel.setMuttextSelectD(textSelectD);
        viewModel.setMuttextSelectF(textSelectF);
        viewModel.setMuttextSelectG(textSelectG);
        viewModel.setMuttextSelectE(textSelectE);
        viewModel.setMutnumSelect(nSel);

    }

    private void limpiar() {
        rB1.setChecked(false);
        rB2.setChecked(false);
        rB3.setChecked(false);
        chkD.setChecked(false);
        chkF.setChecked(false);
        chkG.setChecked(false);
        textSelectD = "";
        textSelectF = "";
        textSelectG = "";
        textSelectE = "";

        etFam.setText("");
        etGen.setText("");

        try {
            spn6.setAdapter(null);
            spn7.setAdapter(null);
            grupoSelec.clear();
            grupoSelec2.clear();
            adapter6.notifyDataSetChanged();
            adapter5.notifyDataSetChanged();
        } catch (Exception e) {
            Log.d("tryerror", "Error en limpiar spinner");
        }
    }

    private int obtenerNumSelect() {
        int nSel1 = 0;

        StringBuilder sb2 = new StringBuilder();
        //sb.delete(0, sb.length());
        for (int y = 0; y < 6; y++) {
            int i = (int) (Math.pow(2, y)) * slec1[y];
            nSel1 += i;
            sb.append(y).append(" ").append(slec1[y]).append(" ").append(i).append(" suma:").append(nSel1).append("\t\t-->");
            sb2.append(slec1[y]);
        }

        Log.d("calculando", sb2.toString() + "\tNum select: " + nSel1);
        return nSel1;

    }

    //=============================================================================================
    //=============================================================================================
    private void pulsadoBuscar() {
        int nSel1 = obtenerNumSelect();
        switch (nSel1) {
            case 0:
            case 1:
            case 3:
            case 5:
            case 9:
                //No es necesario llenar ningun spinner, pasar directamente a btn7
                btn6.setVisibility(INVISIBLE);
                btn7.setVisibility(VISIBLE);
                spn6.setVisibility(INVISIBLE);
                spn7.setVisibility(INVISIBLE);
                ll2.setVisibility(INVISIBLE);
                ll3.setVisibility(INVISIBLE);
                break;
            default:
                btn6.setVisibility(VISIBLE);
                btn7.setVisibility(VISIBLE);
                spn6.setVisibility(VISIBLE);
                spn7.setVisibility(INVISIBLE);
                ll2.setVisibility(VISIBLE);
                ll3.setVisibility(INVISIBLE);
                break;
        }
        textSelectD = obtenerTxtSelD(nSel1);
        grupoSelec = obtenerConsu(nSel1, textSelectD);
        grupoSelec2 = sortAlphabeticallyIgnoringNumber(grupoSelec);
        int hay = grupoSelec.size();
        if (hay > 0) {
            Log.d("saliendo", "Num select:" + nSel1);

            ArrayAdapter<String> adapter5 = new ArrayAdapter<>(getContext(),
                    R.layout.unspinner,
                    grupoSelec);
            adapter5.setDropDownViewResource(R.layout.unspinner4);
            spn6.setAdapter(adapter5);
            spn6.setSelection(0);
            adapter5.notifyDataSetChanged();
        } else {
            Toast.makeText(getContext(), "No hay familias en las claves", Toast.LENGTH_LONG).show();
        }

    }

    private List<String> obtenerConsu2(int t, String s) {
        List<String> paraSpinner = new ArrayList<>();
        int FU = obtenerClaveIdFU(s);
        for (IdsGenero gSr : allGenerosUnic) {
            try {
                if (gSr.getId_Familia() == FU) {
                    paraSpinner.add(gSr.getNombreGenero());
                }
            } catch (Exception e) {
                Log.d("error", e.toString());
            }
        }
        return paraSpinner;
    }

    private String obtenerTxtSelD(int t) {
        String division = "";
        switch (t) {
            case 0:
                division = " cero";
                break;
            case 3:
            case 19:
            case 35:
            case 51:
                //Busca 6 --> cualquier Pteridophyta, 7 --> Familia de Pteridophytas, 8 --> Género de Pteri., 9 --> Especie de Ptrid.
                division = divisiones[1];
                break;
            case 5:
            case 21:
            case 37:
            case 53:
                //Busca 11--> cualquier Gymn. 12-->Familia de Gymmn., 13-->Género de Gymn., 14--> Especie de Gymn.
                division = divisiones[2];
                break;
            case 9:
            case 25:
            case 41:
            case 57:
                //Busca 16--> cualquier Dicot., 17--> Familia de Dicot.,18-->Género de Dicot.,19-->Especie de Dicot.
                division = divisiones[3];
                break;
        }
        return division;
    }

    private List<String> obtenerConsu(int t, String division) {
        List<String> paraSpinner = new ArrayList<>();
        //Rellenando el spinner6
        switch (t) {
            case 16:
            case 17:
            case 48:
            case 49:
                //Sé la familia pero no la división
                //Tengo que seleccionar cualquier familia
                for (familia_unificada fu : allFamUnificadas) {
                    try {
                        if (fu.getClava_estado() != null) {
                            paraSpinner.add(fu.getFamili1());
                        }
                    } catch (Exception e) {
                        Log.d("clava", "Clava_estado es nulo");
                    }
                }
                break;
            case 19:
            case 21:
            case 25:
            case 51:
            case 53:
            case 57:
                //Sé la familia y la división
                //Tengo que se leccionar las familias de la división correspondiente
                for (familia_unificada fu : allFamUnificadas) {
                    try {
                        if (fu.getClava_estado().equals(division)) {
                            paraSpinner.add(fu.getFamili1());
                        }
                    } catch (Exception e) {
                        Log.d("clava", "Clava_estado es nulo");
                    }
                }
                break;
            case 32:
            case 33:
                //Sé el género pero no la divisón ni la familia
                //Tengo que seleccionar cualquier género
                for (IdsGenero gSr : allGenerosUnic) {
                    try {
                        paraSpinner.add(gSr.getNombreGenero());
                    } catch (Exception e) {
                        Log.d("clava", "Clava_estado es nulo");
                    }
                }
                break;
            case 35:
            case 37:
            case 41:
                //Sé el género y la división
                //Tengo que añadir solo los generos que pertenezcan a la division
                for (IdsGenero gSr : allGenerosUnic) {
                    try {
                        if (gSr.getNombreGrupo().equals(division)) {
                            paraSpinner.add(gSr.getNombreGenero());
                        }
                    } catch (Exception e) {
                        Log.d("clava", "Clava_estado es nulo");
                    }
                }
                break;
        }
        return paraSpinner;
    }

    public int obtenerClaveIdFU(String nF) {
        boolean salir = false;
        int indix = 0;
        int salida = 0;
        while (!salir && indix < allFamUnificadas.size()) {
            String f1 = allFamUnificadas.get(indix).getFamili1();
            String f2 = allFamUnificadas.get(indix).getFamili2();
            if (nF.contains(f1) || nF.contains(f2)) {
                salida = allFamUnificadas.get(indix).getId_FUClav();
            }
            indix++;
        }
        return salida;

    }

    private boolean permitirSalida() {
        boolean s = false;
        int nSel1 = obtenerNumSelect();
        // if (nSel == 0){textSelectD = " cero";}
        switch (nSel1) {
            case 1: //Falta división
            case 17: //Falta división
            case 33: //Falta división
            case 49: //Falta división
                s = false;
                Toast.makeText(getContext(), nSel1 + "  Debe elegir una división", Toast.LENGTH_LONG).show();
                break;
            case 0: //Correcto
            case 3: //Correcto
            case 5: //Correcto
            case 9: //Correcto
                s = true;
                Toast.makeText(getContext(), nSel1 + "  " + textSelectD, Toast.LENGTH_LONG).show();
                break;
            //Debe haber nombre de Familia
            case 16: //Debe haber nombreF
            case 19: //Debe haber nombreF Pteri
            case 21: //Debe haber nombreF Gymno
            case 25: //Debe haber nombreF Angio
                if (textSelectF.isEmpty()) {
                    s = false;
                    Toast.makeText(getContext(), nSel1 + "  Debe elegir una familia", Toast.LENGTH_LONG).show();
                } else {
                    s = true;
                }
                break;
            //Debe haber nombre de Género
            case 32: //Debe haber nombreG
            case 35: //Debe haber nombreG Pteri
            case 37: //Debe haber nombreG Gymno
            case 41: //Debe haber nombreG Angio
            case 48: //Debe haber nombreF y nombreG
            case 51: //Debe haber nombreG nombreF Pteri
            case 53: //Debe haber nombreG nombreF Gymno
            case 57: //Debe haber nombreG nombreF Angio
                if (textSelectG.isEmpty()) {
                    s = false;
                    Toast.makeText(getContext(), nSel1 + "  Debe elegir un género", Toast.LENGTH_LONG).show();
                } else {
                    s = true;
                }
                break;
        }
        return s;
    }

    private void alertarD(int nSel1, String txSel) {

        if (txSel.isEmpty()) {
            txSel = " cero. ";
        }
        // Create the object of AlertDialog Builder class
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        // Set the message show for the Alert time
        StringBuilder sb = new StringBuilder();
        sb.append("Clasificaremos a partir de ").append(txSel).append("\nCon índice ").append(nSel1);
        builder.setMessage(sb);
        builder.setTitle("Comprobar inicio ¿Es correcto? ");
        // Set Cancelable false for when the user clicks on the outside the Dialog Box then it will remain show
        builder.setCancelable(false);
        // Set the positive button with yes name Lambda OnClickListener method is use of DialogInterface interface.
        builder.setPositiveButton("Sí", (DialogInterface.OnClickListener) (dialog, which) -> {
            correcto();
        });
        // Set the Negative button with No name Lambda OnClickListener method is use of DialogInterface interface.
        builder.setNegativeButton("No", (DialogInterface.OnClickListener) (dialog, which) -> {
            // If user click no then dialog box is canceled.
            myViewPager2.setCurrentItem(0);
            dialog.cancel();
        });
        // Create the Alert dialog
        AlertDialog alertDialog = builder.create();
        // Show the Alert Dialog box
        alertDialog.show();
    }

    private void correcto() {
        //Toast.makeText(getContext(),"Estoy en correcto",Toast.LENGTH_LONG).show();
        textSelectFinal = "Div.:" + textSelectD + "\tFamilia:" + textSelectF + "\tGenero:" + textSelectG +
                "\tEspecie:" + textSelectE + "\tAntes de salir";
        //Toast.makeText(getContext(), textSelectFinal, Toast.LENGTH_LONG).show();
        int nSel = obtenerNumSelect();

        Log.d("zz_2_life","zz_0 --> Antes de actualizar vM" + textSelectFinal + "  " + nSel);
        ModeloVision viewModel = new ViewModelProvider(requireActivity()).get(ModeloVision.class);
        viewModel.setMuttextSelectD(textSelectD);
        viewModel.setMuttextSelectF(textSelectF);
        viewModel.setMuttextSelectG(textSelectG);
        viewModel.setMuttextSelectE(textSelectE);
        viewModel.setMutnumSelect(nSel);

        myViewPager2.setCurrentItem(1);
        Log.d("zz_2_life", "zz_0 -->correcto : Despues de alertar y hacer bundle:" + textSelectFinal + "  " + nSel);
        Log.d("antesde", "puedoSalir:" + String.valueOf(puedoSalir));

    }


    public static List<String> sortAlphabeticallyIgnoringNumber(List<String> items) {
        List<String> sortedItems = new ArrayList<>(items);
        sortedItems.sort(new Comparator<String>() {
            @Override
            public int compare(String item1, String item2) {
                // Extract the text after the number and period
                String text1 = extractText(item1);
                String text2 = extractText(item2);

                // Compare the extracted text alphabetically (ignoring case)
                return text1.compareToIgnoreCase(text2);
            }
        });
        return sortedItems;
    }

    public static String extractText(String item) {
        // Find the index of the first period
        int periodIndex = item.indexOf('.');

        // If a period is found, extract the text after it; otherwise, return the whole string
        if (periodIndex != -1 && periodIndex < item.length() - 1) {
            return item.substring(periodIndex + 1).trim();
        } else {
            return item.trim();
        }
    }
}

