package com.mentor.app;

import static android.Manifest.permission.ACCESS_COARSE_LOCATION;
import static android.Manifest.permission.ACCESS_FINE_LOCATION;
import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.READ_EXTERNAL_STORAGE;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.app.ActivityCompat;

import com.github.florent37.viewanimator.ViewAnimator;
import com.google.android.material.snackbar.Snackbar;
import com.mentor.app.activity.login.SignInActivity2;

public class SplashActivity extends AppCompatActivity {
    private View mLayout;
    private static final int PERMISSION_REQUEST_CODE = 200;
    private static int SPLASH_TIME_OUT = 3000;
    ImageView logo;
    CoordinatorLayout coordinatorLayout;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setBackgroundDrawable(null);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash);

        context = this;
        logo = (ImageView) findViewById(R.id.logo);

        ViewAnimator.animate(logo).bounceIn().duration(3000).start();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                splashHandler1.sendEmptyMessageDelayed(0, 2000);
            }
        }, SPLASH_TIME_OUT);
    }

    Handler splashHandler1 = new Handler() {
        public void handleMessage(android.os.Message msg) {
            switch (msg.what) {
                case 0:
                    Intent intent = null;
                    intent = new Intent(SplashActivity.this, SignInActivity2.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                    startActivity(intent);
                    break;
                default:
                    break;
            }
        }
    };

    private void requestPermission() {
        ActivityCompat.requestPermissions(this, new String[]{ACCESS_FINE_LOCATION, READ_EXTERNAL_STORAGE, WRITE_EXTERNAL_STORAGE, ACCESS_COARSE_LOCATION, CAMERA}, PERMISSION_REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length > 0) {

                    boolean location_fine = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    boolean location_crose = grantResults[3] == PackageManager.PERMISSION_GRANTED;
                    boolean camera = grantResults[4] == PackageManager.PERMISSION_GRANTED;


                    if (location_fine && location_crose && camera) {

                        splashHandler1.sendEmptyMessageDelayed(0, 2000);

                    } else {

                        errorMessageSnackBar("Please allow the permissions to access the app.");
                        //      requestPermission();

                    }
                }
                break;
        }
    }


    public void errorMessageSnackBar(String message) {
        hideKeyboard(SplashActivity.this);
        Snackbar snackbar = Snackbar
                .make(logo, message, Snackbar.LENGTH_LONG)
                .setDuration(50000)
                .setAction("OK", new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        requestPermission();
                    }
                });
        snackbar.setActionTextColor(Color.BLACK);
        View sbView = snackbar.getView();
        TextView textView = (TextView) sbView.findViewById(com.google.android.material.R.id.snackbar_text);
        textView.setTextColor(Color.BLACK);
        sbView.setBackgroundColor(Color.parseColor("#ebebeb"));
        snackbar.show();


    }

    public static void hideKeyboard(Activity activity) {
        View view = activity.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm;
            imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }


}
