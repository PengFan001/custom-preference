package com.example.zizhuwang.preference;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.preference.ListPreference;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zizhuwang.R;

public class MyListPreference extends ListPreference implements View.OnClickListener {

    private final static String WORK_MODEL_KEY = "work_model";
    private final static String KEY_WAY_KEY = "key_way";
    private final static String USER_SPEED_KEY = "user_speed";
    private final static String LAUNCH_POWER_KEY = "launch_power";
    private final static String FREQUENCY_KEY = "frequency";
    private final static String TABLE_NUMBER_KEY = "table_number";

    private final static String TAG = "MyListPreference";

    private TextView tvTitle;
    private TextView tvSummary;
    private ImageButton ibRightBtn;
    private ImageButton ibLeftBtn;

    int checkedItem = 0;


    public MyListPreference(Context context) {
        this(context, null);
    }

    public MyListPreference(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public MyListPreference(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
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
        String saveValue = getSharedPreferences().getString(getKey(), "");
        tvTitle = view.findViewById(R.id.list_title);
        tvSummary = view.findViewById(R.id.list_summary);
        ibRightBtn = view.findViewById(R.id.list_right_btn);
        ibLeftBtn = view.findViewById(R.id.list_left_btn);
        tvTitle.setText(getTitle());
        if (!TextUtils.isEmpty(saveValue)) {
            tvSummary.setText(saveValue);
        }else {
            tvSummary.setText(getSummary());
        }
        view.setOnClickListener(this);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onClick(View v) {
        //todo set the listener
        switch (getKey()){
            case WORK_MODEL_KEY:
                //todo add the listDialog;
                showListDialog();
                break;
            case KEY_WAY_KEY:
                //todo add the listDialog;
                break;
            case USER_SPEED_KEY:
                showListDialog();
                //todo add the listDialog;
                break;
            case LAUNCH_POWER_KEY:
                //todo add the listDialog;
                break;
            case FREQUENCY_KEY:
                //todo add the listDialog;
                break;
            case TABLE_NUMBER_KEY:
                //todo add the listDialog;
                break;
        }
    }

    private void showListDialog(){
        checkedItem = getSharedPreferences().getInt(getKey()+"checkedItem", 0);
        final CharSequence[] entries = getEntries();
        final CharSequence[] entriesValue = getEntryValues();
        tvSummary.setText(entries[checkedItem]);
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext(), R.style.my_list_dialog);
        builder.setTitle(getTitle());
        builder.setSingleChoiceItems(entries, checkedItem, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                getSharedPreferences().edit().putInt(getKey()+"checkedItem", i).commit();
                getSharedPreferences().edit().putString(getKey(), String.valueOf(entries[i])).commit();
                Log.i(TAG, "item onClick: "+i);
                Log.i(TAG, "item onClick: "+entries[i]);
                Log.i(TAG, "item onClick: "+entriesValue[i]);
                Toast.makeText(getContext(), entries[i], Toast.LENGTH_SHORT).show();
            }
        });
        final AlertDialog dialog = builder.create();
        dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        dialog.getWindow().setBackgroundDrawableResource(R.color.backgroundColor);
        dialog.setButton(DialogInterface.BUTTON_POSITIVE, "OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                int which = getSharedPreferences().getInt(getKey()+"checkedItem", 0);
                Log.i(TAG, "button onClick: "+which);
                Log.i(TAG, "button onClick: "+entries[which]);
                tvSummary.setText(entries[which]);
                dialog.dismiss();
            }
        });
        dialog.setButton(DialogInterface.BUTTON_NEGATIVE, "cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

}
