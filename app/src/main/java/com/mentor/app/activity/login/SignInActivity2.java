package com.mentor.app.activity.login;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.Formatter;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.mentor.app.R;

import com.mentor.app.activity.Forgot_pass;
import com.mentor.app.activity.home.HomeActivity;

import com.mentor.app.base.BaseActivity;
import com.mentor.app.databinding.Login2Binding;
import com.mentor.app.session.SessionManager;

public class SignInActivity2 extends BaseActivity<Login2Binding, SignInViewModel> implements SignInNavigator {
    private SignInViewModel mSignInViewModel = new SignInViewModel();
    private String str_otp_token = "", str_password, str_type = "";
    
    private boolean isContinueActive = false, isBackCall = false, isOTPActive = false;

    @Override
    public int getBindingVariable() {
        return com.mentor.app.BR.signInModel;
    }

    @Override
    public int getLayoutId() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        return R.layout.login2;
    }

    @Override
    public SignInViewModel getViewModel() {
        return mSignInViewModel;
    }

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSignInViewModel.setNavigator(this);

        FirebaseApp.initializeApp(SignInActivity2.this);

        WifiManager wm = (WifiManager) getApplicationContext().getSystemService(WIFI_SERVICE);
        String ip = Formatter.formatIpAddress(wm.getConnectionInfo().getIpAddress());

        sessionManager.setStringvalue(SessionManager.IP_ADDRESS, ip);

        getViewDataBinding().etMobile.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() < 4) {
                    isOTPActive = false;
                    getViewDataBinding().mobileFeild.setError("Please enter correct Username");
                    isContinueActive = false;
                    getViewDataBinding().btnContinue.setBackgroundColor(getResources().getColor(R.color.soft_gray));
                    getViewDataBinding().btnContinue.setTextColor(getResources().getColor(R.color.gray_text_color));
                    getViewDataBinding().passwordField.setVisibility(View.VISIBLE);
                } else {
                    getViewDataBinding().mobileFeild.setErrorEnabled(false);
                    isOTPActive = false;
                    isContinueActive = true;
                    if (!getViewDataBinding().etPassword.getText().toString().isEmpty() && getViewDataBinding().etPassword.getText().length() > 4) {
                        getViewDataBinding().btnContinue.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                        getViewDataBinding().btnContinue.setTextColor(getResources().getColor(R.color.white));
                        getViewDataBinding().passwordField.setVisibility(View.VISIBLE);
                    }
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
            }
        });

        getViewDataBinding().etPassword.addTextChangedListener(new TextWatcher() {
            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() < 4) {
                    isOTPActive = false;
                    getViewDataBinding().passwordField.setError("Please enter correct Password");
                } else {
                    getViewDataBinding().passwordField.setErrorEnabled(false);
                    isContinueActive = true;
                    isOTPActive = true;
                    if (!getViewDataBinding().etMobile.getText().toString().isEmpty() && getViewDataBinding().etMobile.getText().length() > 4) {
                        getViewDataBinding().btnContinue.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
                        getViewDataBinding().btnContinue.setTextColor(getResources().getColor(R.color.white));
                    }
                }
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start,
                                          int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start,
                                      int before, int count) {
            }
        });
    }

    @Override
    public void onLoginClick() {

        //  CommonClass.showProgress(SignInActivity2.this);
        if (isContinueActive && !isOTPActive) {
            isContinueActive = false;
            startActivity(new Intent(SignInActivity2.this, HomeActivity.class));
        } else if (isContinueActive && isOTPActive) {
            startActivity(new Intent(SignInActivity2.this, HomeActivity.class));
        } else {

        }


    }

    @Override
    public void onBackPressed() {
        timerCall();
        if (isBackCall)
            super.onBackPressed();
        else
            Toast.makeText(SignInActivity2.this, "Please press back again to exit", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onSignupClick() {
    }

    @Override
    public void onForgetpassword() {
        startActivity(new Intent(SignInActivity2.this, Forgot_pass.class));

        // commonApiCall.login(login, callAPI());
    }


    @Override
    public void forgetPasswordClick() {
    }

    @Override
    public void onBackpress() {
        finish();
    }

    private void timerCall() {
        new CountDownTimer(3000, 1000) {
            public void onTick(long millisUntilFinished) {
                Log.d("seconds_remaining: ", "" + +millisUntilFinished / 1000);
                isBackCall = true;
            }

            public void onFinish() {
                isBackCall = false;
            }

        }.start();
    }
}
