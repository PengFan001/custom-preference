package com.example.zizhuwang.preference;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zizhuwang.R;

public class ListPreference extends android.preference.ListPreference implements View.OnClickListener {

    private TextView tvTitle;
    private TextView tvSummary;
    private ImageButton ibRightBtn;
    private ImageButton ibLeftBtn;

    private String title;
    private String summary;
    private String key;

    public ListPreference(Context context) {
        this(context, null);
    }

    public ListPreference(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ListPreference(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.list_preference);
        title = typedArray.getString(R.styleable.list_preference_list_title);
        summary = typedArray.getString(R.styleable.list_preference_list_summary);
        key = typedArray.getString(R.styleable.list_preference_list_key);
        super.setKey(key);
        typedArray.recycle();
    }

    @Override
    protected View onCreateView(ViewGroup parent) {
        super.onCreateView(parent);
        View view = LayoutInflater.from(getContext()).inflate(R.layout.list_preference, null);
        return view;
    }

    @Override
    protected void onBindView(View view) {
        super.onBindView(view);
        tvTitle = view.findViewById(R.id.list_title);
        tvSummary = view.findViewById(R.id.list_summary);
        ibRightBtn = view.findViewById(R.id.list_right_btn);
        ibLeftBtn = view.findViewById(R.id.list_left_btn);
        tvTitle.setText(title);
        tvSummary.setText(summary);
        view.setOnClickListener(this);
    }



    @Override
    public void onClick(View v) {
        Toast.makeText(getContext(), title, Toast.LENGTH_SHORT).show();
        //todo set the listener
    }
}
