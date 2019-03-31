package com.example.zizhuwang.preference;

import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.preference.Preference;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zizhuwang.ParamsSetActivity;
import com.example.zizhuwang.R;


public class ItemPreference extends Preference implements View.OnClickListener {

    private final static String INTENT_TAG_PARAMS_SET = "params_set";
    private final static String INTENT_TAG_INTERNET_INFO = "internet_info";
    private final static String INTENT_TAG_POINT_STATUS_INFO = "point_status_info";

    private TextView tvTitle;

    private String title;
    private String key;

    public ItemPreference(Context context) {
        this(context, null);
    }

    public ItemPreference(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ItemPreference(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.item_preference);
        title = typedArray.getString(R.styleable.item_preference_item_title);
        key = typedArray.getString(R.styleable.item_preference_item_key);
        super.setKey(key);
        typedArray.recycle();
    }

    @Override
    protected View onCreateView(ViewGroup parent) {
        super.onCreateView(parent);
        View view = LayoutInflater.from(getContext()).inflate(R.layout.item_preference, null);
        return view;
    }

    @Override
    protected void onBindView(View view) {
        super.onBindView(view);
        tvTitle = view.findViewById(R.id.item_title);
        tvTitle.setText(title);
        view.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (key){
            case INTENT_TAG_PARAMS_SET:
                intent.setClass(getContext(), ParamsSetActivity.class);
                getContext().startActivity(intent);
                break;
            case INTENT_TAG_INTERNET_INFO:
                Toast.makeText(getContext(), title, Toast.LENGTH_SHORT).show();
                //todo add the intent
                break;
            case INTENT_TAG_POINT_STATUS_INFO:
                Toast.makeText(getContext(), title, Toast.LENGTH_SHORT).show();
                //todo add the intent
                break;
        }
    }


}
