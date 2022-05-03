package com.mentor.app.activity;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;

import com.mentor.app.R;
import com.mentor.app.activity.pendingList.ProductListActivity;
import com.mentor.app.activity.pendingList.ProductListNavigator;
import com.mentor.app.activity.pendingList.ProductListViewModel;
import com.mentor.app.activity.pendingList.adapter.AdapterProductList;
import com.mentor.app.base.BaseActivity;
import com.mentor.app.databinding.CreateNewFiBinding;
import com.mentor.app.model.StateModel;
import com.mentor.app.utils.CommonClass;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Create_fi_activity extends BaseActivity<CreateNewFiBinding, ProductListViewModel> implements ProductListNavigator, View.OnClickListener {
    private ProductListViewModel mSignInViewModel = new ProductListViewModel();
    
    private boolean isContinueActive = false, isBackCall = false;
    private String str_name = "", str_father_name = "", str_address = "", str_email = "", str_vehicle_type = "", str_vehicle_no = "", str_vehicale_registration_o = "",
            str_DL_no = "", str_adhar_no = "", str_pan_card = "", str_mobile = "";
    ArrayList<StateModel> list = new ArrayList<>();
    List<String> categories = new ArrayList<String>();
    ArrayAdapter<String> dataAdapter;
    private String call_for = "";
    private AdapterProductList adapterProductList;

    private Dialog dialog;
    private String str_from_date = "", str_to_date = "", str_date = "";
    private boolean isFromDate = false, isToDate = false;
    DatePickerDialog.OnDateSetListener date;
    final Calendar myCalendar = Calendar.getInstance();
    final Calendar myCalendar1 = Calendar.getInstance();

    @Override
    public int getBindingVariable() {
        return com.mentor.app.BR.signInModel;
    }

    @Override
    public int getLayoutId() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        return R.layout.create_new_fi;
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

        getViewDataBinding().imgback.setOnClickListener(this);
        getViewDataBinding().tvSubmit.setOnClickListener(this);
        getViewDataBinding().tvDOB.setOnClickListener(this);

        date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                String s = "" + (monthOfYear + 1);
                String s_day = "" + (dayOfMonth + 1);
                if (s.length() == 1) {
                    s = "0" + s;
                    str_date = "" + year + "-" + s + "-" + dayOfMonth;
                } else {
                    str_date = "" + year + "-" + s + "-" + dayOfMonth;
                }
                if (s_day.length() == 1) {
                    str_date = "" + year + "-" + s + "-0" + dayOfMonth;
                } else {
                    str_date = "" + year + "-" + s + "-" + dayOfMonth;
                }
                //yyyy-mm-dd
                getViewDataBinding().tvDOB.setText(CommonClass.chnageDateFormateToShow(str_date));
                str_from_date = getViewDataBinding().tvDOB.getText().toString();
            }
        };

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
    public void onClick(View view) {

        if (view.getId() == R.id.imgback) {
            finish();
        } else if (view.getId() == R.id.tvSubmit) {
            startActivity(new Intent(Create_fi_activity.this, ProductListActivity.class));
            finish();
        } else if (view.getId() == R.id.tvDOB) {
            isFromDate = false;
            isToDate = true;
            callPicker();
        }
    }

    private void callPicker() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(Create_fi_activity.this, date, myCalendar1
                .get(Calendar.YEAR), myCalendar1.get(Calendar.MONTH),
                myCalendar1.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
        datePickerDialog.show();
    }
}
