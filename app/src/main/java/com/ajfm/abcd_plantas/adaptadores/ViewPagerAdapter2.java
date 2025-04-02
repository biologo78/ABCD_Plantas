package com.ajfm.abcd_plantas.adaptadores;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.ajfm.abcd_plantas.fragmentos.zz_0_Inicio_clasificar;
import com.ajfm.abcd_plantas.fragmentos.zz_1_Fichas;
import com.ajfm.abcd_plantas.fragmentos.zz_2_clave_clasificar;
import com.ajfm.abcd_plantas.fragmentos.zz_3_agregar;
import com.ajfm.abcd_plantas.fragmentos.zz_6_generoFrag;
import com.ajfm.abcd_plantas.fragmentos.zz_4_webFrag;
import com.ajfm.abcd_plantas.fragmentos.zz_5_buscar;
import com.ajfm.abcd_plantas.fragmentos.zz_7_cambiar;
import com.ajfm.abcd_plantas.fragmentos.zz_8_salir;
import com.ajfm.abcd_plantas.fragmentos.zz_9_figuraminas;

public class ViewPagerAdapter2 extends FragmentStateAdapter {
    //ArrayList<Fragment> arrayFragment = new ArrayList<>();

    public ViewPagerAdapter2(@NonNull FragmentManager fragmentManager, @NonNull Lifecycle lifecycle) {
        super(fragmentManager, lifecycle);
        Log.d("life", "Entrando en ViewPA2 constructor");

        Log.d("life", "Saliendo ViewPA2 constructor");

    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        Log.d("life", "Entrando en ViewPA2 createFragment");

        Fragment fragment = new Fragment();
        switch (position){
            case 0:
                fragment = new zz_0_Inicio_clasificar();
                break;
            case 1:
                fragment = new zz_2_clave_clasificar();
                break;
            case 2:
                fragment = new zz_9_figuraminas();
                break;
            case 3:
                fragment = new zz_1_Fichas();
                break;
            case 4:
                fragment = new zz_6_generoFrag();
                break;
            case 5:
                fragment = new zz_4_webFrag();
                break;
            case 6:
                fragment = new zz_5_buscar();
                break;
            case 7:
                fragment = new zz_7_cambiar();
                break;
            case 8:
                fragment = new zz_3_agregar();
                break;
            case 9:
                fragment = new zz_8_salir();
                break;
            default:
                fragment = null;
                break;
        }
        Log.d("life", "Saliendo de ViewPA2 createFragment");

        assert fragment != null;
        return fragment;
    }
    @Override
    public int getItemCount() {
        return 10;
    }

}
