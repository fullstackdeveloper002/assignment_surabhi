package com.mentor.pipes.activity.home.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.mentor.pipes.R;
import com.mentor.pipes.activity.completeList.CompleteListActivity;
import com.mentor.pipes.activity.pendingList.ProductListActivity;
import com.mentor.pipes.databinding.AdapterHomeBinding;

import java.util.ArrayList;
import java.util.List;

public class AdapterHome extends RecyclerView.Adapter<AdapterHome.ViewHolder> {

    //private List<OrderModel> OrderModelList;
    private Context mContext;
    private Boolean comingFromHome;
    private String type;
    private OnItemListClickListener onClickListener;
    public String call_for = "", totalproductsCount = "", totalpointsCount = "", totalordersCount = "";

    /*public AdapterHome(ArrayList<OrderModel> OrderModelList,
                       String call_for, OnItemListClickListener onClickListener, Context mContext) {
        this.OrderModelList = OrderModelList;
        this.mContext = mContext;
        this.onClickListener = onClickListener;
        this.call_for = call_for;
        this.OrderModelList = OrderModelList;
    }*/

    /**
     * Instantiates a new Time line adapter.
     */
    public AdapterHome(String totalproductsCount, String totalordersCount, String totalpointsCount, OnItemListClickListener onClickListener, Context mContext, String type) {
        this.totalpointsCount = totalpointsCount;
        this.totalordersCount = totalordersCount;
        this.totalproductsCount = totalproductsCount;
        this.onClickListener = onClickListener;
        this.mContext = mContext;
        this.type = type;
    }

    /**
     * Sets on item list click listener.
     *
     * @param onClickListener the on click listener
     */
    public void setOnItemListClickListener(OnItemListClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        AdapterHomeBinding mRowItemListBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.adapter_home, parent, false);
        ViewHolder viewHolder = new ViewHolder(mRowItemListBinding);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        if (position == 0) {
            Glide.with(mContext)
                    .load(mContext.getResources().getDrawable(R.drawable.total_assignment))
                    .into(holder.mRowItemListBinding.img);
            holder.mRowItemListBinding.mainLayout.setBackgroundTintList(mContext.getResources().getColorStateList(R.color.gray));

            holder.mRowItemListBinding.tvTitle.setText("Total Assigned FI.");

            holder.mRowItemListBinding.tvCount.setText("" + totalproductsCount);

        } else if (position == 1) {
            Glide.with(mContext)
                    .load(mContext.getResources().getDrawable(R.drawable.ic__delivered))
                    .into(holder.mRowItemListBinding.img);
            holder.mRowItemListBinding.mainLayout.setBackgroundTintList(mContext.getResources().getColorStateList(R.color.gray));
            holder.mRowItemListBinding.tvTitle.setText("Completed FI");

            holder.mRowItemListBinding.tvCount.setText("" + totalordersCount);
        } else if (position == 2) {
            Glide.with(mContext)
                    .load(mContext.getResources().getDrawable(R.drawable.pending))
                    .into(holder.mRowItemListBinding.img);

            holder.mRowItemListBinding.tvTitle.setText("Pending FI");

            holder.mRowItemListBinding.mainLayout.setBackgroundTintList(mContext.getResources().getColorStateList(R.color.gray));
            holder.mRowItemListBinding.tvCount.setText("" + totalpointsCount);
        }

        holder.mRowItemListBinding.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println(position);
                if (position == 2) {
                    mContext.startActivity(new Intent(mContext, ProductListActivity.class));
                } else if (position == 1) {
                    mContext.startActivity(new Intent(mContext, CompleteListActivity.class));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return 3;
    }

    /**
     * The interface On item list click listener.
     */
    public interface OnItemListClickListener {

        /**
         * On clicked.
         *
         * @param view     the view
         * @param position the position
         */
        void onClicked(View view, int position, String call);

        void onClickedbtn(View view, int position, String call);
    }

    /**
     * The type View holder.
     */
    public class ViewHolder extends RecyclerView.ViewHolder {

        /**
         * The M row item list binding.
         */
        public AdapterHomeBinding mRowItemListBinding;

        /**
         * Instantiates a new View holder.
         *
         * @param mRowItemListBinding the m row item list binding
         */
        public ViewHolder(AdapterHomeBinding mRowItemListBinding) {
            super(mRowItemListBinding.getRoot());
            this.mRowItemListBinding = mRowItemListBinding;
        }
    }
}