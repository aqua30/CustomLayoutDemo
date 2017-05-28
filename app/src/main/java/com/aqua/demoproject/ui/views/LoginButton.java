package com.aqua.demoproject.ui.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.aqua.demoproject.R;
import com.aqua.demoproject.ui.views.font.AppTextViewRegular;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Saurabh(aqua) on 28-05-2017.
 */

public class LoginButton extends LinearLayout {

    @BindView(R.id.login_button_loader)ProgressBar login_loader;
    @BindView(R.id.login_button_text)AppTextViewRegular login_text;

    private Context context;

    public LoginButton(Context context) {
        super(context);
        this.context = context;
        createView();
    }

    public LoginButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        createView();
    }

    private void createView(){
        inflate(context, R.layout.ly_login_button, this);
        ButterKnife.bind(this);
        setOrientation(LinearLayout.HORIZONTAL);
        setBackgroundResource(R.drawable.bg_round_corners);
        setGravity(Gravity.CENTER | Gravity.CENTER_VERTICAL);
        setButtonText("Login");
        login_loader.getIndeterminateDrawable().setColorFilter(0xFFFFFFFF, android.graphics.PorterDuff.Mode.MULTIPLY);
        setButtonLoadingState(false);
    }

    public void setButtonText(String text){
        login_text.setText(text);
    }

    public void setButtonLoadingState(boolean state){
        ViewHelper.setViewVisibility(login_loader, state);
    }
}
