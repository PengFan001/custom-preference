package com.example.zizhuwang;

import android.preference.PreferenceActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ParamsSetActivity extends PreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.params_set);
        this.getListView().setDivider(this.getResources().getDrawable(R.color.dividerLine));
        this.getListView().setDividerHeight(2);
        this.getListView().setPadding(30,0,0,0);
    }
}
