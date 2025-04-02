package com.ajfm.abcd_plantas;

import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import com.ajfm.abcd_plantas.adaptadores.ViewPagerAdapter2;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import androidx.lifecycle.Observer;

public class MainActivity extends  AppCompatActivity{

    public static int widthTotal;
    public static int heightTotal;
    public static int widthImg;
    public static int heightImg;
    public static boolean flagBuscado;

    private TextView tvData;
    private LinearLayoutCompat lLMA;

    public static TabLayout tabLayout;
    public static ViewPager2 myViewPager2;
    public static ViewPagerAdapter2 myAdapter;

    private boolean seguir = false;
    private final String[] labels = new String[]{"Inicio", "Clasificar", "Fig/Lám","Fichas", "Género",
            "Ver en Web", "Buscar", "Editar", "Agregar","Salir"};

    private ModeloVision viewModel;  // private es correcto aquí

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Log.d("life", "Entrando en Main onCreate");

        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        widthTotal = metrics.widthPixels; // ancho absoluto en pixels
        heightTotal = metrics.heightPixels; // alto absoluto en pixels
        widthImg = widthTotal - 15;
        heightImg = heightTotal - 15;

        viewModel = new ViewModelProvider(this).get(ModeloVision.class);

        //myViewPager2 es el adaptador para el desplazamiento horizontal de los fragments
        //están alojados en FragmentContainerView

        Log.d("life", "Saliendo de Main onCreate");
        configViewPager2(savedInstanceState);
    }

    private void configViewPager2 (Bundle savedInstanceState) {

        ModeloVision viewModel = new ViewModelProvider(this).get(ModeloVision.class);
        viewModel.setMyData("Hola desde el MainActivity");

            // En una Activity o Fragment:
        tabLayout = findViewById(R.id.tabs);
        myViewPager2 = findViewById(R.id.viewPager2);
        myViewPager2.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        myAdapter = new ViewPagerAdapter2(getSupportFragmentManager(), getLifecycle());
        myViewPager2.setAdapter(myAdapter);

        new TabLayoutMediator(tabLayout, myViewPager2, (tab, position) -> tab.setText(labels[position])).attach();





        /*viewModel.getMyData().observe(this, new Observer<String>() {

            public void onChanged(String newData) {
                // Actualizar la UI con el nuevo valor
                Log.d("modelView", "Recibido: " + newData);
            }
        });*/
    }
}

 /*       fragmentContainerView = findViewById(R.id.fragment_container_view_tag);
        tvData = findViewById(R.id.tV1);
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                Fragment fragment = null;
                switch (tab.getPosition()) {
                    case 0:
                        fragment = new zz_0_Inicio_clasificar();
                        seguir = true;
                        break;
                    case 1:
                        fragment = new zz_2_clave_clasificar();
                        seguir = true;
                        break;
                    case 2:
                        fragment = new zz_1_Fichas();
                        seguir = true;
                        break;
                    case 3:
                        fragment = new zz_6_generoFrag();
                        seguir = true;
                        break;
                    case 4:
                        fragment = new zz_4_webFrag();
                        seguir = true;
                        break;
                    case 5:
                        fragment = new zz_5_buscar();
                        seguir = true;
                        break;
                    case 6:
                        fragment = new zz_3_agregar();
                        //pedirPermisos();
                        break;
                    case 7:
                        fragment = new zz_7_cambiar();
                        seguir = true;
                        break;
                    case 8:
                        fragment = new zz_8_salir();
                        seguir = true;
                        break;

                    //default:
                    //     fragment = new zz_0_Inicio_clasificar();
                }
                if (seguir) {
                    getSupportFragmentManager().beginTransaction().
                            remove(getSupportFragmentManager().findFragmentById(R.id.fragment_container_view_tag)).commit();

                    FragmentManager fm = getSupportFragmentManager();
                    FragmentTransaction ft = fm.beginTransaction();
                    ft.replace(R.id.fragment_container_view_tag, fragment);
                    ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                    ft.commit();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
            }
        });

  */


       /* myViewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                Log.d("tabulando", Integer.toString(position));
                tabLayout.selectTab(tabLayout.getTabAt(position));
            }
        });

        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(
                tabLayout, myViewPager2, true, new TabLayoutMediator.TabConfigurationStrategy() {
            @Override
            public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                tab.setText(labels[position]);
            }
        });
        tabLayoutMediator.attach();
        //selecciona el fragment de inicio --> getTabAt (xxx)
        tabLayout.selectTab(tabLayout.getTabAt(0));*/


    //====================================================================
    //====================================================================
    //ESta parte de debajo es todo lo que se necesita para pedir los permisos de READ, WRITE external storage
    //pero la he suprimido porque la lectura de ficheros externos la he pasado al Ripeador y esta app no necesito
    //leer nada del exterior. Para el acceso a internet no se necesita permiso
    //La declaración de permisos la tengo que hacer tambien en el Manifest pero la he quitado
    //sigue en el Ripeador

    /*private void pedirPermisos() {
        String release = Build.VERSION.RELEASE;
        int sdkVersion = Build.VERSION.SDK_INT;

        if (sdkVersion >= 30) {
            checkPermission(READ_MEDIA_IMAGES, STORAGE_PERMISSION_CODE);
            f = new File(READ_MEDIA_IMAGES);
        } else {
            checkPermission(READ_EXTERNAL_STORAGE, STORAGE_PERMISSION_CODE);
            checkPermission(WRITE_EXTERNAL_STORAGE, STORAGE_PERMISSION_CODE_W);
            f = new File(READ_EXTERNAL_STORAGE);
        }
    }*/

    // Function to check and request permission.
    /*public void checkPermission(String permission, int requestCode) {
        if (ContextCompat.checkSelfPermission(MainActivity.this, permission) == PackageManager.PERMISSION_DENIED) {
            // Requesting the permission
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{permission}, requestCode);
        } else {
            Toast.makeText(MainActivity.this, "Permission already granted", Toast.LENGTH_SHORT).show();
            seguir = true;
        }
    }*/


    // This function is called when the user accepts or decline the permission.
    // Request Code is used to check which permission called this function.
    // This request code is provided when the user is prompt for permission.

   /* @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode,
                permissions,
                grantResults);

        if (requestCode == STORAGE_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(MainActivity.this, "Storage Permission Granted", Toast.LENGTH_SHORT).show();
                seguir = true;
            } else {
                Toast.makeText(MainActivity.this, "Storage Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

}

    */