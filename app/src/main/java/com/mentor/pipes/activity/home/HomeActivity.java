package com.mentor.pipes.activity.home;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;

import com.bumptech.glide.Glide;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.location.LocationRequest;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.mentor.pipes.R;
import com.mentor.pipes.activity.Create_fi_activity;
import com.mentor.pipes.activity.drawer.DrawerActivity;
import com.mentor.pipes.activity.home.adapter.AdapterHome;
import com.mentor.pipes.activity.home.adapter.AdapterListShow;

import com.mentor.pipes.databinding.HomeLayoutBinding;
import com.mentor.pipes.model.OrderMOdel;
import com.mentor.pipes.model.ProductModel;
import com.mentor.pipes.session.SessionManager;
import com.mentor.pipes.utils.CommonClass;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import io.reactivex.observers.DisposableObserver;

public class HomeActivity extends DrawerActivity implements AdapterListShow.OnItemListClickListener, AdapterHome.OnItemListClickListener {
    private String str_email, str_password;
    
    private boolean isContinueActive = false, isBackCall = false;
    private HomeLayoutBinding binding;
    private LocationRequest locationRequest;
    private Location location = null;
    private GoogleApiClient mGoogleApiClient;
    private SessionManager sessionManager;
    private final int PERMISSION_REQUEST = 999, REQUEST_LOCATION = 49;
    private AdapterHome adapterHome;


    DatePickerDialog.OnDateSetListener date;
    private String str_date = "";
    ArrayList<OrderMOdel> list_order = new ArrayList<>();
    ArrayList<ProductModel> list_product = new ArrayList<>();
    public static ArrayList<String> arrayList_productList = new ArrayList<>();
    public static ArrayList<String> arrayList_qty = new ArrayList<>();
    public static HashMap<String, String> produstList_hash = new HashMap<>(); // id, name

    private List<BannerDTO> list_image = new ArrayList();
    int currentPage = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.home_layout,
                (ViewGroup) findViewById(R.id.homeframe), true);

        possition_bottom = 1;
        changeLayoutColorBottom();

        
        sessionManager = new SessionManager(this);

        locationRequest = LocationRequest.create();
        locationRequest.setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
        locationRequest.setFastestInterval(10);
        locationRequest.setInterval(5000);

        //  binding.tvName.setText("" + CommonClass.capitalizeString(sessionManager.getString(SessionManager.DISTRIBUTOR_NAME)));
        binding.tvType.setText("" + CommonClass.capitalizeString(sessionManager.getString(SessionManager.LOGIN_TYPE)));

        binding.imgAddOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        binding.scrollview.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {
                Log.d("scrollHere", "aa");

            }
        });

        binding.btnCreateNewFI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, Create_fi_activity.class));
            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        //commonApiCall.getProfile(get_profile_detail, callProfileDetail());

        adapterHome = new AdapterHome("10",
                "5",
                "5",
                HomeActivity.this, HomeActivity.this
                , sessionManager.getString(SessionManager.LOGIN_TYPE));
        binding.recyclerView.setAdapter(adapterHome);

    }

    @Override
    public void onBackPressed() {
        if (isBackCall)
            super.onBackPressed();
        else
            Toast.makeText(HomeActivity.this, "Please press back again to exit", Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onClicked(View view, int position, String call) {


    }

    @Override
    public void onClickedbtn(View view, int position, String call) {

    }


    @Override
    public void onItemSpinnerClick(View view, int position, String call) {
    }


    Timer timer;
    final long DELAY_MS = 500;//delay in milliseconds before task is to be executed
    long PERIOD_MS = 3000;

    private void callViewPagerImageScroll() {
        try {
            final Handler handler = new Handler();
            final Runnable Update = new Runnable() {
                public void run() {
                    if (currentPage == list_image.size() - 1) {
                        currentPage = 0;
                    }
                    binding.pictureRecyclerView.setCurrentItem(currentPage++, true);
                }
            };
            timer = new Timer(); // This will create a new Thread
            timer.schedule(new TimerTask() { // task to be scheduled
                @Override
                public void run() {
                    handler.post(Update);
                }
            }, DELAY_MS, PERIOD_MS);
        } catch (Exception e) {
        }
    }
}
