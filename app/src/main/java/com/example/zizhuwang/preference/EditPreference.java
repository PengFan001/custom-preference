package com.example.zizhuwang.preference;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.drawable.ColorDrawable;
import android.preference.EditTextPreference;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.zizhuwang.R;

public class EditPreference extends EditTextPreference implements View.OnClickListener {

    private final static String MAC_KEY = "mac";
    private final static String IP_KEY = "ip";
    private final static String HIGHER_SET_KEY = "higher_set";

    private TextView tvTitle;
    private TextView tvSummary;
    private ImageButton ibRightBtn;

    private String title;
    private String summary;
    private String key;

    private Dialog editDialog;
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
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.edit_preference);
        title = typedArray.getString(R.styleable.edit_preference_edit_title);
        key = typedArray.getString(R.styleable.edit_preference_edit_key);
        summary = typedArray.getString(R.styleable.edit_preference_edit_summary);
        super.setKey(key);
        typedArray.recycle();
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
        tvTitle = view.findViewById(R.id.edit_title);
        tvSummary = view.findViewById(R.id.edit_summary);
        ibRightBtn = view.findViewById(R.id.edit_btn);
        tvTitle.setText(title);
        tvSummary.setText(summary);
        view.setOnClickListener(this);

        //todo the function of input dialog
        final View editDialogView = LayoutInflater.from(getContext()).inflate(R.layout.edit_dialog, null);
        editDialog = new Dialog(getContext());
        editDialog.getWindow().setBackgroundDrawable(new ColorDrawable(0));
        editDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        editDialog.setContentView(editDialogView);

        tvEditedDialogTitle = editDialogView.findViewById(R.id.edit_dialog_title);
        tvEditedDialogOk = editDialogView.findViewById(R.id.edit_dialog_ok);
        tvEditedDialogCancel = editDialogView.findViewById(R.id.edit_dialog_cancel);
        tvEditedDialogTitle.setText(title);

        tvEditedDialogOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                etEditedDialogContent = editDialogView.findViewById(R.id.edit_dialog_content);
                summary = etEditedDialogContent.getText().toString();
                setSummary(summary);
                tvSummary.setText(summary);
                editDialog.dismiss();
            }
        });

        tvEditedDialogCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editDialog.dismiss();
            }
        });
    }

//    @Override
//    protected View onCreateDialogView() {
//        super.onCreateDialogView();
//        View view = LayoutInflater.from(getContext()).inflate(R.layout.edit_dialog, null);
//        return view;
//    }
//
//    @Override
//    protected void onBindDialogView(View view) {
//        super.onBindDialogView(view);
//
//    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (key){
            case MAC_KEY:
                editDialog.show();
                break;
            case IP_KEY:
                editDialog.show();
                break;
            case HIGHER_SET_KEY:
                Toast.makeText(getContext(), title, Toast.LENGTH_SHORT).show();
                //todo add the intent
//                intent.setClass();
//                getContext().startActivity(intent);
                break;
        }

    }
}
