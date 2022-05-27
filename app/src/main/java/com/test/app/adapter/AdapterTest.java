package com.test.app.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.test.app.R;
import com.test.app.databinding.TestAdapterBinding;
import com.test.app.model.TestModel;
import com.test.app.utils.CommonClass;

import java.util.ArrayList;

public class AdapterTest extends RecyclerView.Adapter<AdapterTest.ViewHolder> {


    private Context mContext;
    private ArrayList<TestModel> mList;

    /**
     * Instantiates a new Time line adapter.
     */
    public AdapterTest(Context mContext, ArrayList<TestModel> mList) {
        this.mList = mList;
        this.mContext = mContext;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        TestAdapterBinding mRowItemListBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.test_adapter, parent, false);
        ViewHolder viewHolder = new ViewHolder(mRowItemListBinding);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        TestModel mData = mList.get(position);
        if (mData.isVisible()) {
            holder.mRowItemListBinding.toolbar.setVisibility(View.GONE);
        } else {
            holder.mRowItemListBinding.toolbar.setVisibility(View.VISIBLE);
        }
        holder.mRowItemListBinding.toolbar.setTitle(mData.getSection_name());

        CommonClass.setImageToGlide(mContext, holder.mRowItemListBinding.img1, mData.getImage_ur1());
        CommonClass.setImageToGlide(mContext, holder.mRowItemListBinding.img2, mData.getImage_ur2());
        CommonClass.setImageToGlide(mContext, holder.mRowItemListBinding.img3, mData.getImage_ur3());
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    /**
     * The type View holder.
     */
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TestAdapterBinding mRowItemListBinding;

        public ViewHolder(TestAdapterBinding mRowItemListBinding) {
            super(mRowItemListBinding.getRoot());
            this.mRowItemListBinding = mRowItemListBinding;
        }
    }
}