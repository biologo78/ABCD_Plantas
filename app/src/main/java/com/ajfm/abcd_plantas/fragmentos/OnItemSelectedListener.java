package com.ajfm.abcd_plantas.fragmentos;

import android.view.View;
import android.widget.AdapterView;

public interface OnItemSelectedListener {

    void onItemSelected(AdapterView<?> parent, View view, int position, long id);

    void onNothingSelected(AdapterView<?> parent);
}
