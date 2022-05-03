package com.mentor.pipes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.mentor.pipes.activity.FI_formActivity;
import com.mentor.pipes.activity.imageupload.ImageUploadActivity;
import com.mentor.pipes.activity.imageupload.adapter.ImageAdapter;
import com.mentor.pipes.databinding.BlueprintFormBinding;

public class BluePrintActivity extends AppCompatActivity implements View.OnClickListener {
    BlueprintFormBinding binding;
    ImageAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.blueprint_form);

        binding.btnOfficeForm.setOnClickListener(this);
        binding.btnResidenceForm.setOnClickListener(this);
        binding.imgBack.setOnClickListener(this);
        binding.imgEdit.setOnClickListener(this);
        binding.tvSubmit.setOnClickListener(this);

        binding.btnResidenceForm.performClick();

        adapter = new ImageAdapter(this, "");
        binding.linearResidencelayoutInclude.rvImage.setAdapter(adapter);

        if (getIntent().getExtras() != null) {
            if (getIntent().getStringExtra("call").equalsIgnoreCase("report")) {
                binding.imgEdit.setVisibility(View.GONE);
                binding.linearResidencelayoutInclude.rvImage.setVisibility(View.GONE);
            } else {
                binding.imgEdit.setVisibility(View.VISIBLE);
                binding.tvSubmit.setVisibility(View.GONE);

                binding.linearResidencelayoutInclude.rvImage.setVisibility(View.VISIBLE);
            }
        } else {
            binding.imgEdit.setVisibility(View.GONE);
            binding.tvSubmit.setVisibility(View.VISIBLE);

            binding.linearResidencelayoutInclude.rvImage.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.btnOfficeForm) {
            binding.linearResidencelayout.setVisibility(View.GONE);
            binding.linearOfficelayout.setVisibility(View.VISIBLE);

            binding.btnResidenceForm.setBackgroundColor(getResources().getColor(R.color.gray));
            binding.btnResidenceForm.setTextColor(getResources().getColor(R.color.black));
            binding.btnOfficeForm.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
            binding.btnOfficeForm.setTextColor(getResources().getColor(R.color.white));

        } else if (view.getId() == R.id.btnResidenceForm) {
            binding.linearResidencelayout.setVisibility(View.VISIBLE);
            binding.linearOfficelayout.setVisibility(View.GONE);


            binding.btnResidenceForm.setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));
            binding.btnResidenceForm.setTextColor(getResources().getColor(R.color.white));
            binding.btnOfficeForm.setBackgroundColor(getResources().getColor(R.color.gray));
            binding.btnOfficeForm.setTextColor(getResources().getColor(R.color.black));

        } else if (view.getId() == R.id.imgBack) {
            finish();
        } else if (view.getId() == R.id.imgEdit) {
            startActivity(new Intent(BluePrintActivity.this, FI_formActivity.class));
        } else if (view.getId() == R.id.tvSubmit) {
            startActivity(new Intent(BluePrintActivity.this, ImageUploadActivity.class));
        }
    }
}
