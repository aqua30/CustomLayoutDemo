package com.aqua.demoproject.ui.views;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.content.ContextCompat;
import android.text.InputType;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.LinearLayout;

import com.aqua.demoproject.R;
import com.aqua.demoproject.ui.views.font.AppEditText;
import com.aqua.demoproject.ui.views.font.IconTextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Saurabh(aqua) on 28-05-2017.
 */

public class LoginField extends LinearLayout implements View.OnFocusChangeListener {

    @BindView(R.id.icon_login_field)IconTextView login_icon;
    @BindView(R.id.edit_login_field)AppEditText login_text;
    @BindView(R.id.view_separator)View separator;
    @BindView(R.id.icon_show_password)IconTextView show_password;

    private Context context;

    /* indicates if this view has focus */
    private boolean has_focus = false, is_password = false;
    private String icon_code, hint_text;
    private int focus_color, unfocus_color;

    public LoginField(Context context) {
        super(context);
        this.context = context;
        createView();
    }

    public LoginField(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs,R.styleable.attrs_login_fields,0, 0);
        try {
            icon_code = a.getString(R.styleable.attrs_login_fields_icon_code);
            hint_text = a.getString(R.styleable.attrs_login_fields_hint_text);
            has_focus = a.getBoolean(R.styleable.attrs_login_fields_has_focus, false);
            is_password = a.getBoolean(R.styleable.attrs_login_fields_is_password, false);
        } finally {
            a.recycle();
        }
        createView();
    }

    private void createView() {
        inflate(context,R.layout.ly_login_field, this);
        ButterKnife.bind(this);
        setOrientation(LinearLayout.HORIZONTAL);

        /* setting up the default selection color */
        focus_color = ContextCompat.getColor(context, R.color.colorPrimary);
        unfocus_color = ContextCompat.getColor(context, R.color.silver);

        login_icon.setText(icon_code);
        login_text.setHint(hint_text);
        setSelection(has_focus);

        login_text.setOnFocusChangeListener(this);
        if (is_password) {
            login_text.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD );
            show_password.setText(getResources().getString(R.string.icon_eye_closed));
        }
        ViewHelper.setViewVisibility(show_password, is_password);
    }

    public void setSelection(boolean has_focus){
        login_text.setTextColor(has_focus ? focus_color : unfocus_color);
        login_icon.setTextColor(has_focus ? focus_color : unfocus_color);
        separator.setBackgroundColor(has_focus ? focus_color : unfocus_color);
        show_password.setTextColor(has_focus ? focus_color : unfocus_color);
    }

    @OnClick(R.id.icon_show_password)
    void onShowPasswordClick(){
        is_password = !is_password;
        show_password.setText(is_password ? getResources().getString(R.string.icon_eye_closed) :
                getResources().getString(R.string.icon_eye));
        login_text.setInputType(is_password ? InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD :
                InputType.TYPE_CLASS_TEXT );
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        setSelection(hasFocus);
        if (hasFocus) {
            InputMethodManager imm = (InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
            if(imm != null){
                imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, 0);
            }
        }
    }

    public void setError(){
        login_text.setError("required");
    }

    public String getFieldText(){
        return login_text.getText().toString();
    }

    public void enableField(boolean state){
        login_text.setEnabled(state);
    }
}