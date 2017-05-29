package com.aqua.demoproject.ui.activities;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.LinearLayout;

import com.aqua.demoproject.R;
import com.aqua.demoproject.ui.activities.base.BaseActivity;
import com.aqua.demoproject.ui.views.LoginButton;
import com.aqua.demoproject.ui.views.LoginField;
import com.aqua.demoproject.ui.views.ViewHelper;
import com.aqua.demoproject.ui.views.font.AppTextViewRegular;
import com.aqua.demoproject.ui.views.font.IconTextView;

import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Saurabh(aqua) on 28-05-2017.
 */

public class LoginActivity extends BaseActivity {

    @BindView(R.id.login_email)LoginField login_email;
    @BindView(R.id.login_password)LoginField login_password;
    @BindView(R.id.login_confirm_password)LoginField confirm_password;
    @BindView(R.id.login_button)LoginButton login_button;
    @BindView(R.id.layout_login_options)LinearLayout login_option_layout;
    @BindView(R.id.login_field_layout)LinearLayout login_field_layout;

    @BindViews({R.id.text_sign_up, R.id.text_forgot_password})
    List<AppTextViewRegular> login_options;

    private boolean has_sign_up = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ViewHelper.setViewVisibility(confirm_password, false);
    }

    @OnClick(R.id.login_button)
    void onLogin(){
        if (!login_email.getFieldText().isEmpty()) {
            if (!login_password.getFieldText().isEmpty()) {
                if (has_sign_up) {
                    if (!confirm_password.getFieldText().isEmpty()) {
                        login_button.setButtonLoadingState(true);
                        login_email.enableField(false);
                        login_password.enableField(false);
                        confirm_password.enableField(false);
                        new Handler().postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                login_button.setButtonLoadingState(false);
                                login_button.setButtonText("Success");
                            }
                        }, 3000);
                    } else {
                        confirm_password.setError();
                    }
                } else {
                    login_button.setButtonLoadingState(true);
                    login_email.enableField(false);
                    login_password.enableField(false);
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            login_button.setButtonLoadingState(false);
                            login_button.setButtonText("Success");
                        }
                    }, 3000);
                }
            } else {
                login_password.setError();
            }
        } else {
            login_email.setError();
        }
    }

    @OnClick(R.id.text_sign_up)
    void onSignup(){
        has_sign_up = !has_sign_up;
        ViewHelper.setViewVisibility(confirm_password, has_sign_up);
        login_button.setButtonText(has_sign_up ? "Sign Up":"Login");
        ButterKnife.apply(login_options, optionsState);
    }

    final ButterKnife.Action<View> optionsState = new ButterKnife.Action<View>() {
        @Override public void apply(View view, int index) {
            ViewHelper.setViewVisibility(view, !has_sign_up);
        }
    };

    @Override
    public void onBackPressed() {
        if (has_sign_up) {
            has_sign_up = !has_sign_up;
            ViewHelper.setViewVisibility(confirm_password, has_sign_up);
            login_button.setButtonText(has_sign_up ? "Sign Up":"Login");
            ButterKnife.apply(login_options, optionsState);
        } else {
            super.onBackPressed();
            finish();
        }
    }

    @Override
    protected int getActivityLayout() {
        return R.layout.ac_login;
    }
}
