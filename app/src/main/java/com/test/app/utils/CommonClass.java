package com.test.app.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class CommonClass {
    public static void setImageToGlide(Context mContext, ImageView view,String url) {
        Glide.with(mContext).load(url)
                .into(view);
    }
}
