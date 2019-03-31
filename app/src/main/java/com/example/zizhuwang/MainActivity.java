package com.example.zizhuwang;

import android.preference.PreferenceActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.zizhuwang.preference.ItemPreference;
import com.example.zizhuwang.preference.StatusPreference;

public class MainActivity extends PreferenceActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        addPreferencesFromResource(R.xml.zzw_menu);
        this.getListView().setDivider(this.getResources().getDrawable(R.color.dividerLine));
        this.getListView().setDividerHeight(2);
        this.getListView().setPadding(30,0,0,0);
    }
}
