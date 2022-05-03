package com.mentor.pipes.activity.completeList;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;

import com.mentor.pipes.BluePrintActivity;
import com.mentor.pipes.R;
import com.mentor.pipes.activity.completeList.adapter.AdapterProductList;
import com.mentor.pipes.base.BaseActivity;
import com.mentor.pipes.databinding.CompleteFiListBinding;
import com.mentor.pipes.model.StateModel;
import java.util.ArrayList;
import java.util.List;

public class CompleteListActivity extends BaseActivity<CompleteFiListBinding, CompleteListViewModel> implements CompleteListNavigator,
        AdapterProductList.OnItemListClickListener {
    private CompleteListViewModel mSignInViewModel = new CompleteListViewModel();
    
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
        return R.layout.complete_fi_list;
    }

    @Override
    public CompleteListViewModel getViewModel() {
        return mSignInViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mSignInViewModel.setNavigator(this);

        getViewDataBinding().tvTitle.setText("Today's Completed FI");

        categories = new ArrayList<>();

        adapterProductList = new AdapterProductList("", CompleteListActivity.this, CompleteListActivity.this);
        getViewDataBinding().recycleviewProductList.setAdapter(adapterProductList);

        getViewDataBinding().imgback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
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
        startActivity(new Intent(CompleteListActivity.this, BluePrintActivity.class)
                .putExtra("call", "complete"));
    }
}