package com.mentor.app.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;

import com.mentor.app.R;
import com.mentor.app.activity.pendingList.ProductListActivity;
import com.mentor.app.activity.pendingList.ProductListNavigator;
import com.mentor.app.activity.pendingList.ProductListViewModel;
import com.mentor.app.activity.pendingList.adapter.AdapterProductList;
import com.mentor.app.base.BaseActivity;
import com.mentor.app.databinding.ForgotPasswordBinding;
import com.mentor.app.model.StateModel;

import java.util.ArrayList;
import java.util.List;

public class Forgot_pass extends BaseActivity<ForgotPasswordBinding, ProductListViewModel> implements ProductListNavigator, View.OnClickListener {
    private ProductListViewModel mSignInViewModel = new ProductListViewModel();
    
    private boolean isContinueActive = false, isBackCall = false;
    private String str_name = "", str_father_name = "", str_address = "", str_email = "", str_vehicle_type = "", str_vehicle_no = "", str_vehicale_registration_o = "",
            str_DL_no = "", str_adhar_no = "", str_pan_card = "", str_mobile = "";
    ArrayList<StateModel> list = new ArrayList<>();
    List<String> categories = new ArrayList<String>();
    ArrayAdapter<String> dataAdapter;
    private String call_for = "";
    private AdapterProductList adapterProductList;

    @Override
    public int getBindingVariable() {
        return com.mentor.app.BR.signInModel;
    }

    @Override
    public int getLayoutId() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        return R.layout.forgot_password;
    }

    @Override
    public ProductListViewModel getViewModel() {
        return mSignInViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSignInViewModel.setNavigator(this);

        
        categories = new ArrayList<>();
    }

    @Override
    public void onLinearVehi() {
    }

    @Override
    public void onLoginClick() {
    }

    @Override
    public void onBackPressed() {
        finish();
    }

    @Override
    public void onSignupClick() {
    }

    @Override
    public void onResendOTP() {
    }

    @Override
    public void forgetPasswordClick() {
    }

    @Override
    public void onBackpress() {
        onBackPressed();
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

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.imgback) {
            finish();
        } else if (view.getId() == R.id.tvSubmit) {
            startActivity(new Intent(Forgot_pass.this, ProductListActivity.class));
        }
    }
}
