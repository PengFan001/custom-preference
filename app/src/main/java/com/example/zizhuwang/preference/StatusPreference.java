package com.example.zizhuwang.preference;

import android.content.Context;
import android.preference.Preference;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zizhuwang.R;

public class StatusPreference extends Preference implements View.OnClickListener {

    private TextView tvTitle;
    private CheckBox cb_status_btn;

    public StatusPreference(Context context) {
        this(context, null);
    }

    public StatusPreference(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public StatusPreference(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected View onCreateView(ViewGroup parent) {
        super.onCreateView(parent);
        View view = LayoutInflater.from(getContext()).inflate(R.layout.status_preference, null);
        return view;
    }

    @Override
    protected void onBindView(View view) {
        super.onBindView(view);
        tvTitle = view.findViewById(R.id.status_title);
        cb_status_btn = view.findViewById(R.id.cb_btn);
        tvTitle.setText(getTitle());

        cb_status_btn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    Toast.makeText(getContext(), "checked", Toast.LENGTH_SHORT).show();
                    //todo add function
                }
                else {
                    Toast.makeText(getContext(), "unchecked", Toast.LENGTH_SHORT).show();
                    //todo add function
                }
            }
        });

        view.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(getContext(), getTitle(), Toast.LENGTH_SHORT).show();
        Log.i("click", "onClick: status");
    }
}
