package com.mentor.app.activity.pendingList;
//commit
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;

import com.mentor.app.R;
import com.mentor.app.activity.FI_formActivity;
import com.mentor.app.activity.pendingList.adapter.AdapterProductList;

import com.mentor.app.base.BaseActivity;
import com.mentor.app.databinding.ProductlistBinding;
import com.mentor.app.model.StateModel;

import java.util.ArrayList;
import java.util.List;

public class ProductListActivity extends BaseActivity<ProductlistBinding, ProductListViewModel> implements ProductListNavigator,
        AdapterProductList.OnItemListClickListener {
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
        return R.layout.productlist;
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

        adapterProductList = new AdapterProductList("", ProductListActivity.this, ProductListActivity.this);
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
        // callDialogDetail("");
        startActivity(new Intent(ProductListActivity.this, FI_formActivity.class));
    }
}
