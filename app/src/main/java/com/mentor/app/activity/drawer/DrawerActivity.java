package com.mentor.app.activity.drawer;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AlertDialog;
import androidx.drawerlayout.widget.DrawerLayout;
import com.mentor.app.R;
import com.mentor.app.activity.home.HomeActivity;
import com.mentor.app.activity.login.SignInActivity2;
import com.mentor.app.activity.profile.ProfileActivity;
import com.mentor.app.activity.recordlist.RecordListActivity;
import com.mentor.app.base.BaseActivity;
import com.mentor.app.databinding.DrawerBinding;
import com.mentor.app.session.SessionManager;
import com.mentor.app.utils.CommonClass;

import java.util.ArrayList;

public class DrawerActivity extends BaseActivity<DrawerBinding, DrawerViewModel> implements DrawerNavigator, View.OnClickListener {

    private DrawerViewModel mDrawerViewModel = new DrawerViewModel();
    private ArrayList<ImageView> linearLayouts;
    public int possition_bottom = 1;
    Dialog dialog;
    private SessionManager sessionManager;
    TextView tvTimer;
    Handler handler;
    long MillisecondTime, StartTime, TimeBuff, UpdateTime = 0L;
    int Seconds, Minutes;
    private Dialog dialog_detail;
    TextView tvName, tvBussiness, tvContact, tvEmail;

    @Override
    public int getBindingVariable() {
        return com.mentor.app.BR.drawerVM;
    }

    @Override
    public int getLayoutId() {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        return R.layout.drawer;
    }

    @Override
    public DrawerViewModel getViewModel() {
        return mDrawerViewModel;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // mDrawerViewModel.setNavigator(this);
        sessionManager = new SessionManager(DrawerActivity.this);
        getViewDataBinding().drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);



        getViewDataBinding().bottomBar.bottomCustom.setVisibility(View.VISIBLE);

        getViewDataBinding().bottomBar.linear1.setOnClickListener(this);
        getViewDataBinding().bottomBar.linear2.setOnClickListener(this);
        getViewDataBinding().bottomBar.linear3.setOnClickListener(this);
        getViewDataBinding().bottomBar.linear4.setOnClickListener(this);
        getViewDataBinding().bottomBar.linear5.setOnClickListener(this);

        linearLayouts = new ArrayList<>();
        linearLayouts.add(getViewDataBinding().bottomBar.img1);
        linearLayouts.add(getViewDataBinding().bottomBar.img2);
        linearLayouts.add(getViewDataBinding().bottomBar.img3);
        linearLayouts.add(getViewDataBinding().bottomBar.img4);
        linearLayouts.add(getViewDataBinding().bottomBar.img5);
    }

    @Override
    protected void onResume() {
        super.onResume();
        getViewDataBinding().name.setText(CommonClass.capitalizeString(sessionManager.getString(SessionManager.DISTRIBUTOR_NAME)));
        getViewDataBinding().tvtype.setText(CommonClass.capitalizeString(sessionManager.getString(SessionManager.LOGIN_TYPE)));
    }




    public void onDrawerIconClick() {
        // getViewDataBinding().drawerLayout.openDrawer(GravityCompat.END);
    }

    public void onCloseDrawer() {
        // getViewDataBinding().drawerLayout.closeDrawer(GravityCompat.END);
    }

    @Override
    public void home() {
        Intent intent = new Intent(DrawerActivity.this, HomeActivity.class);
        startActivity(intent);
    }

    @Override
    public void order() {
        Intent intent = new Intent(DrawerActivity.this, RecordListActivity.class);
        startActivity(intent);
    }

    @Override
    public void products() {
      /*  Intent intent = new Intent(DrawerActivity.this, DashboardActivity.class);
        startActivity(intent);*/
    }

    @Override
    public void earning() {
    }

    @Override
    public void transaction() {

    }

    @Override
    public void addDealer() {

    }

    @Override
    public void dealerList() {

    }

    @Override
    public void distributorDetail() {

    }

    @Override
    public void setting() {

    }

    @Override
    public void support() {

    }

    @Override
    public void aboutus() {

    }

    @Override
    public void complain() {

    }

    @Override
    public void logout() {
        showLogOutDialog();
    }

    @Override
    public void menu() {

    }

    @Override
    public void timer() {

    }

    @Override
    public void profile() {

    }

    private void showLogOutDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(DrawerActivity.this);
        builder.setTitle("Account Logout");
        builder.setCancelable(true);
        builder.setMessage("Are you sure you want to Logout.");
        builder.setPositiveButton("Yes", (dialog, which) -> {
            sessionManager.logoutUser();
            startActivity(new Intent(DrawerActivity.this, SignInActivity2.class)
                    .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));

        });
        builder.setNegativeButton("No", (dialog, which) -> dialog.dismiss());
        builder.create().show();
    }

    TextView tvCustomer;
    Dialog dialog_support;


    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        } else {
            super.onBackPressed();
        }
    }

    public void setTitle(String s) {
        getViewDataBinding().tvTitle.setText(s);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.linear1:
                startActivity(new Intent(DrawerActivity.this, HomeActivity.class));
                possition_bottom = 1;
                changeLayoutColorBottom();
                break;
            case R.id.linear2:
                startActivity(new Intent(DrawerActivity.this, ProfileActivity.class));
                possition_bottom = 2;
                changeLayoutColorBottom();
                break;
            case R.id.linear3:
                possition_bottom = 3;
                changeLayoutColorBottom();
                startActivity(new Intent(DrawerActivity.this, RecordListActivity.class));
                break;
            case R.id.linear4:
                possition_bottom = 4;
                changeLayoutColorBottom();
                break;
            case R.id.linear5:
                possition_bottom = 5;
                onDrawerIconClick();
                break;
        }
    }


    public void changeLayoutColorBottom() {
        ImageView layout = null;
        if (possition_bottom == 1) {
            if (dialog != null && dialog.isShowing())
                dialog.dismiss();
            getViewDataBinding().tvTitle.setText("Dashboard");
            layout = getViewDataBinding().bottomBar.img1;
        } else if (possition_bottom == 2) {
            if (dialog != null && dialog.isShowing())
                dialog.dismiss();
            getViewDataBinding().tvTitle.setText("Profile");
            layout = getViewDataBinding().bottomBar.img2;
        } else if (possition_bottom == 3) {
            if (dialog != null && dialog.isShowing())
                dialog.dismiss();
            getViewDataBinding().tvTitle.setText("Report");
            layout = getViewDataBinding().bottomBar.img3;
        } else if (possition_bottom == 4) {
            if (dialog != null && dialog.isShowing())
                dialog.dismiss();
            getViewDataBinding().tvTitle.setText("Points");
            layout = getViewDataBinding().bottomBar.img4;
        } else if (possition_bottom == 5) {
            if (dialog != null && dialog.isShowing())
                dialog.dismiss();
            layout = getViewDataBinding().bottomBar.img5;
        }
        for (int i = 0; i < linearLayouts.size(); i++) {
            if (layout.getId() == linearLayouts.get(i).getId()) {
                linearLayouts.get(i).setColorFilter(getResources().getColor(R.color.red));
            } else {
                linearLayouts.get(i).setColorFilter(getResources().getColor(R.color.gray_text_color));
            }
        }
    }


    public Runnable runnable = new Runnable() {
        public void run() {
            MillisecondTime = SystemClock.uptimeMillis() - StartTime;

            UpdateTime = TimeBuff + MillisecondTime;

            Seconds = (int) (UpdateTime / 1000);

            Minutes = Seconds / 60;

            Seconds = Seconds % 60;
            if (("" + Minutes).length() == 1) {
                tvTimer.setText("0" + Minutes + ":"
                        + String.format("%02d", Seconds));
            } else {
                tvTimer.setText("" + Minutes + ":"
                        + String.format("%02d", Seconds));
            }

            handler.postDelayed(this, 0);
        }
    };
}
