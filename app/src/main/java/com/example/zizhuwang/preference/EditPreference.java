package com.example.zizhuwang.preference;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.preference.EditTextPreference;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zizhuwang.R;

import static android.content.ContentValues.TAG;

public class EditPreference extends EditTextPreference implements View.OnClickListener{

    private final static String MAC_KEY = "mac";
    private final static String IP_KEY = "ip";
    private final static String HIGHER_SET_KEY = "higher_set";

    private TextView tvTitle;
    private TextView tvSummary;
    private ImageButton ibRightBtn;

    private Dialog dialog;
    private TextView tvEditedDialogTitle;
    private EditText etEditedDialogContent;
    private TextView tvEditedDialogOk;
    private TextView tvEditedDialogCancel;

    public EditPreference(Context context) {
        this(context, null);
    }

    public EditPreference(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public EditPreference(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected View onCreateView(ViewGroup parent) {
        super.onCreateView(parent);
        View view = LayoutInflater.from(getContext()).inflate(R.layout.edit_preference, null);
        return view;
    }

    @Override
    protected void onBindView(View view) {
        super.onBindView(view);
        String saveValue = getSharedPreferences().getString(getKey(), "");
        tvTitle = view.findViewById(R.id.edit_title);
        tvSummary = view.findViewById(R.id.edit_summary);
        ibRightBtn = view.findViewById(R.id.edit_btn);
        tvTitle.setText(getTitle());
//        tvSummary.setText(getSummary());
        if (!TextUtils.isEmpty(saveValue)) {
            tvSummary.setText(saveValue);
        }else {
            tvSummary.setText(getSummary());
        }
        view.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent();
        switch (getKey()){
            case MAC_KEY:
                //todo add the inputDialog
                showMACDialog();
                break;
            case IP_KEY:
                //todo add the inputDialog
                showIPDialog();
                break;
            case HIGHER_SET_KEY:
                Toast.makeText(getContext(), getTitle(), Toast.LENGTH_SHORT).show();
                //todo add the intent
                break;
        }
    }

    private void showMACDialog(){
        String currentValue = getSharedPreferences().getString(getKey(), "");
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        final View editDialogView = LayoutInflater.from(getContext()).inflate(R.layout.edit_dialog, null);
        tvEditedDialogTitle = editDialogView.findViewById(R.id.edit_dialog_title);
        etEditedDialogContent = editDialogView.findViewById(R.id.edit_dialog_content);
        tvEditedDialogOk = editDialogView.findViewById(R.id.edit_dialog_ok);
        tvEditedDialogCancel = editDialogView.findViewById(R.id.edit_dialog_cancel);
        tvEditedDialogTitle.setText(getTitle());
        etEditedDialogContent.setText(currentValue);
        etEditedDialogContent.setSelection(currentValue.length());
        etEditedDialogContent.requestFocus();
        dialog = builder.create();
        dialog.show();
        dialog.getWindow().setContentView(editDialogView);
        dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        tvEditedDialogOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSharedPreferences().edit().putString(getKey(), etEditedDialogContent.getText().toString()).commit();
                tvSummary.setText(etEditedDialogContent.getText().toString());
                Log.d(TAG, "onClick: "+etEditedDialogContent.getText().toString());
                dialog.dismiss();
            }
        });

        tvEditedDialogCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "cancel", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
    }

    private void showIPDialog(){
        String currentValue = getSharedPreferences().getString(getKey(), "");
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        final View editDialogView = LayoutInflater.from(getContext()).inflate(R.layout.ip_edit_dialog, null);
        tvEditedDialogTitle = editDialogView.findViewById(R.id.ip_edit_dialog_title);
        etEditedDialogContent = editDialogView.findViewById(R.id.ip_edit_dialog_content);
        tvEditedDialogOk = editDialogView.findViewById(R.id.ip_edit_dialog_ok);
        tvEditedDialogCancel = editDialogView.findViewById(R.id.ip_edit_dialog_cancel);
        tvEditedDialogTitle.setText(getTitle());
        etEditedDialogContent.setText(currentValue);
        etEditedDialogContent.setSelection(currentValue.length());
        etEditedDialogContent.requestFocus();
        dialog = builder.create();
        dialog.show();
        dialog.getWindow().setContentView(editDialogView);
        dialog.getWindow().clearFlags(WindowManager.LayoutParams.FLAG_ALT_FOCUSABLE_IM);
        tvEditedDialogOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getSharedPreferences().edit().putString(getKey(), etEditedDialogContent.getText().toString()).commit();
                tvSummary.setText(etEditedDialogContent.getText().toString());
                Log.d(TAG, "onClick: "+etEditedDialogContent.getText().toString());
                dialog.dismiss();
            }
        });

        tvEditedDialogCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getContext(), "cancel", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
    }

}
