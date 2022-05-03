package com.mentor.app.activity.imageupload;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.mentor.app.R;
import com.mentor.app.activity.home.HomeActivity;
import com.mentor.app.activity.imageupload.adapter.ImageAdapter;
import com.mentor.app.databinding.ImageUploadBinding;

public class ImageUploadActivity extends AppCompatActivity implements View.OnClickListener {
    ImageUploadBinding binding;
    ImageAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.image_upload);

        binding.tvSubmit.setOnClickListener(this);
        binding.imgBack.setOnClickListener(this);

        adapter=new ImageAdapter(this,"cancel");
        binding.rvImage.setAdapter(adapter);

    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.imgBack) {
            finish();
        } else if (view.getId() == R.id.tvSubmit) {
            startActivity(new Intent(ImageUploadActivity.this, HomeActivity.class)
                    .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK));
        }
    }
}
