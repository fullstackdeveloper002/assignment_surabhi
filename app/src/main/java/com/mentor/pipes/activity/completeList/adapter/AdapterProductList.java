package com.mentor.pipes.activity.completeList.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.mentor.pipes.R;

import com.mentor.pipes.databinding.AdapterProductListBinding;

import java.util.ArrayList;
import java.util.List;

public class AdapterProductList extends RecyclerView.Adapter<AdapterProductList.ViewHolder> {


    private Context mContext;
    private Boolean comingFromHome;
    private OnItemListClickListener onClickListener;
    public String call_for = "";


    /**
     * Instantiates a new Time line adapter.
     */
    public AdapterProductList(String call_for, OnItemListClickListener onClickListener, Context mContext) {
        this.call_for = call_for;
        this.onClickListener = onClickListener;
        this.mContext = mContext;
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
        AdapterProductListBinding mRowItemListBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.adapter_product_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(mRowItemListBinding);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickListener.onClicked(view, position, "");
            }
        });

    }

    @Override
    public int getItemCount() {
        return 7;
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

    }

    /**
     * The type View holder.
     */
    public class ViewHolder extends RecyclerView.ViewHolder {

        /**
         * The M row item list binding.
         */
        public AdapterProductListBinding mRowItemListBinding;

        /**
         * Instantiates a new View holder.
         *
         * @param mRowItemListBinding the m row item list binding
         */
        public ViewHolder(AdapterProductListBinding mRowItemListBinding) {
            super(mRowItemListBinding.getRoot());
            this.mRowItemListBinding = mRowItemListBinding;
        }
    }
}