package com.mentor.pipes.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import com.mentor.pipes.BluePrintActivity;
import com.mentor.pipes.R;
import com.mentor.pipes.activity.pendingList.ProductListNavigator;
import com.mentor.pipes.activity.pendingList.ProductListViewModel;
import com.mentor.pipes.activity.pendingList.adapter.AdapterProductList;
import com.mentor.pipes.base.BaseActivity;
import com.mentor.pipes.databinding.FiFormBinding;
import com.mentor.pipes.model.StateModel;

import java.util.ArrayList;
import java.util.List;

public class FI_formActivity extends BaseActivity<FiFormBinding, ProductListViewModel> implements ProductListNavigator,
        AdapterProductList.OnItemListClickListener, View.OnClickListener {
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
        return com.mentor.pipes.BR.signInModel;
    }

    @Override
    public int getLayoutId() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        return R.layout.fi_form;
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


        getViewDataBinding().tvRecidence.setOnClickListener(this);
        getViewDataBinding().tvOffice.setOnClickListener(this);
        getViewDataBinding().tvSubmit.setOnClickListener(this);
        getViewDataBinding().imgback.setOnClickListener(this);
        getViewDataBinding().recidenceLayout1.btnResSubmit.setOnClickListener(this);
        getViewDataBinding().officelayout1.btnOfcSubmit.setOnClickListener(this);

        getViewDataBinding().tvRecidence.performClick();
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

    @Override
    public void onClicked(View view, int position, String call) {
        // callDialogDetail("");
    }


    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.tvRecidence) {
            getViewDataBinding().tvRecidence.setBackgroundTintList(getResources().getColorStateList(R.color.red));
            getViewDataBinding().tvOffice.setBackgroundTintList(getResources().getColorStateList(R.color.gray));

            getViewDataBinding().tvResidence.setTextColor(getResources().getColor(R.color.white));
            getViewDataBinding().tvOfficeText.setTextColor(getResources().getColor(R.color.black));


            getViewDataBinding().recidenceLayout1.recidenceLayout.setVisibility(View.VISIBLE);
            getViewDataBinding().officelayout1.officelayout.setVisibility(View.GONE);


        } else if (view.getId() == R.id.tvOffice) {
            getViewDataBinding().tvRecidence.setBackgroundTintList(getResources().getColorStateList(R.color.gray));
            getViewDataBinding().tvOffice.setBackgroundTintList(getResources().getColorStateList(R.color.red));

            getViewDataBinding().recidenceLayout1.recidenceLayout.setVisibility(View.GONE);
            getViewDataBinding().officelayout1.officelayout.setVisibility(View.VISIBLE);

            getViewDataBinding().tvResidence.setTextColor(getResources().getColor(R.color.black));
            getViewDataBinding().tvOfficeText.setTextColor(getResources().getColor(R.color.white));

        } else if (view.getId() == R.id.tvSubmit) {
            if (getViewDataBinding().imgOfcTick.getVisibility() == View.VISIBLE && getViewDataBinding().imgResTick.getVisibility() == View.VISIBLE) {
                startActivity(new Intent(this, BluePrintActivity.class));
            } else {
                Toast.makeText(FI_formActivity.this, "Please fill both the forms completely.", Toast.LENGTH_SHORT).show();
            }
        } else if (view.getId() == R.id.btnResSubmit) {
            // getViewDataBinding().imgResTick.setVisibility(View.VISIBLE);
            startActivity(new Intent(this, BluePrintActivity.class));
        } else if (view.getId() == R.id.btnOfcSubmit) {
            // getViewDataBinding().imgOfcTick.setVisibility(View.VISIBLE);
            startActivity(new Intent(this, BluePrintActivity.class));
        } else if (view.getId() == R.id.imgback) {
            finish();
        }
    }
}
