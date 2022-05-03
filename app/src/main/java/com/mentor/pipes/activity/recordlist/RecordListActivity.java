package com.mentor.pipes.activity.recordlist;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.databinding.DataBindingUtil;

import com.mentor.pipes.BluePrintActivity;
import com.mentor.pipes.R;
import com.mentor.pipes.activity.completeList.adapter.AdapterProductList;
import com.mentor.pipes.activity.drawer.DrawerActivity;
import com.mentor.pipes.databinding.OrderListBinding;
import com.mentor.pipes.utils.CommonClass;
import java.util.Calendar;

public class RecordListActivity extends DrawerActivity implements AdapterProductList.OnItemListClickListener {
    
    private boolean isContinueActive = false, isBackCall = false;
    private OrderListBinding binding;

    private AdapterProductList adapterProductList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = DataBindingUtil.inflate(getLayoutInflater(), R.layout.order_list,
                (ViewGroup) findViewById(R.id.linear_layout), true);

        possition_bottom = 3;
        changeLayoutColorBottom();
        

        adapterProductList = new AdapterProductList("", RecordListActivity.this, RecordListActivity.this);
        binding.recycleviewProductList.setAdapter(adapterProductList);

        binding.relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showFilterDialog();
            }
        });

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
                if (isFromDate) {
                    if (!str_to_date.isEmpty()) {
                        if (!CommonClass.isPreviousDate(str_from_date, CommonClass.chnageDateFormateToShow(str_date))) {
                            tvFromDate.setText(CommonClass.chnageDateFormateToShow(str_date));
                            str_from_date = tvFromDate.getText().toString();
                        } else {
                            str_from_date = "";
                            tvFromDate.setText("");
                            Toast.makeText(RecordListActivity.this, "Please select date before selected to date.", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        tvFromDate.setText(CommonClass.chnageDateFormateToShow(str_date));
                        str_from_date = tvFromDate.getText().toString();
                        tvToDate.setText("");
                    }
                } else if (isToDate) {
                    if (!str_from_date.isEmpty()) {
                        if (CommonClass.isPreviousDate(str_from_date, CommonClass.chnageDateFormateToShow(str_date))) {
                            tvToDate.setText(CommonClass.chnageDateFormateToShow(str_date));
                            str_to_date = tvToDate.getText().toString();
                        } else {
                            str_to_date = "";
                            tvToDate.setText("");
                            Toast.makeText(RecordListActivity.this, "Please select date after selected from date", Toast.LENGTH_SHORT).show();
                        }
                    } else {

                    }
                }
            }
        };

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
            Toast.makeText(RecordListActivity.this, "Please press back again to exit", Toast.LENGTH_SHORT).show();
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
        startActivity(new Intent(RecordListActivity.this, BluePrintActivity.class)
        .putExtra("call","report"));
    }

    TextView tvToDate, tvFromDate, tvSubmit, tvCancel;
    private Dialog dialog;
    private String str_from_date = "", str_to_date = "", str_date = "";
    private boolean isFromDate = false, isToDate = false;
    DatePickerDialog.OnDateSetListener date;
    final Calendar myCalendar = Calendar.getInstance();
    final Calendar myCalendar1 = Calendar.getInstance();

    private void showFilterDialog() {

        try {
            dialog = new Dialog(RecordListActivity.this);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
            dialog.setContentView(R.layout.dialog_filter);
            dialog.getWindow().setLayout(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
            dialog.getWindow().setDimAmount(0.6f);
            dialog.setCancelable(false);

            tvToDate = dialog.findViewById(R.id.tvToDate);
            tvFromDate = dialog.findViewById(R.id.tvFromDate);
            tvSubmit = dialog.findViewById(R.id.tvSubmit);
            tvCancel = dialog.findViewById(R.id.tvCancel);

            dialog.show();

            if (!(str_from_date.isEmpty() && str_to_date.isEmpty())) {
                tvToDate.setText(str_to_date);
                tvFromDate.setText(str_from_date);
            }

            tvCancel.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    dialog.dismiss();
                    str_from_date = "";
                    str_to_date = "";
                }
            });

           /* tvToDate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    isFromDate = false;
                    isToDate = true;
                    callPicker();
                }
            });

            tvFromDate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    isFromDate = true;
                    isToDate = false;
                    callPicker();
                }
            });*/

          /*  tvSubmit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (tvFromDate.getText().toString().isEmpty()) {
                        Toast.makeText(HistoryListActivity.this, "Please select from date", Toast.LENGTH_SHORT).show();
                    } else if (tvToDate.getText().toString().isEmpty()) {
                        Toast.makeText(HistoryListActivity.this, "Please select to date", Toast.LENGTH_SHORT).show();
                    } else {
                        CommonClass.showProgress(HistoryListActivity.this);
                        //  callRequest();
                    }
                }
            });*/
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void callPicker() {
        DatePickerDialog datePickerDialog = new DatePickerDialog(RecordListActivity.this, date, myCalendar1
                .get(Calendar.YEAR), myCalendar1.get(Calendar.MONTH),
                myCalendar1.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
        datePickerDialog.show();
    }
}
