package com.mentor.pipes.activity.profile;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;

import com.mentor.pipes.BluePrintActivity;
import com.mentor.pipes.R;
import com.mentor.pipes.activity.completeList.adapter.AdapterProductList;
import com.mentor.pipes.activity.drawer.DrawerActivity;


import com.mentor.pipes.databinding.OrderListBinding;
import com.mentor.pipes.databinding.ProfileBinding;
import com.mentor.pipes.session.SessionManager;

public class ProfileActivity extends DrawerActivity implements AdapterProductList.OnItemListClickListener {

    private SessionManager sessionManager;
    
    private boolean isContinueActive = false, isBackCall = false;
    private ProfileBinding binding;
    private AdapterProductList adapterProductList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.profile,
                (ViewGroup) findViewById(R.id.linear_layout), true);

        sessionManager = new SessionManager(ProfileActivity.this);
        possition_bottom = 2;
        changeLayoutColorBottom();
        
    }

    @Override
    public void onResume() {
        super.onResume();
    }


    @Override
    public void onBackPressed() {

       /* Intent intent = new Intent(OrderListActivity.this, DashboardActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);*/
        timerCall();
        if (isBackCall)
            super.onBackPressed();
        else
            Toast.makeText(ProfileActivity.this, "Please press back again to exit", Toast.LENGTH_SHORT).show();
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
    public void onClicked(View view, int position, String call) {
        startActivity(new Intent(ProfileActivity.this, BluePrintActivity.class));
    }
}
