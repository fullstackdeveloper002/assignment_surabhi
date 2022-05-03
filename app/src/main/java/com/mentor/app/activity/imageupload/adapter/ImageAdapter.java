package com.mentor.app.activity.imageupload.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.mentor.app.R;
import com.mentor.app.databinding.ItemImageBinding;
import com.mentor.app.model.BrandModel;
import com.mentor.app.model.ProductModel;

import java.util.ArrayList;
import java.util.List;

public class ImageAdapter extends RecyclerView.Adapter<ImageAdapter.ViewHolder> {

    private List<BrandModel> brandlist;
    private List<ProductModel> productlist;
    private Context mContext;
    private Boolean comingFromHome;
    private OnItemListClickListener onClickListener;
    public String call_for = "";


    public ImageAdapter(String call_for, ArrayList<ProductModel> productlist,
                        OnItemListClickListener onClickListener, Context mContext) {

        this.productlist = productlist;
        this.call_for = call_for;
        this.mContext = mContext;
        this.onClickListener = onClickListener;
        this.call_for = call_for;
    }

    /**
     * Instantiates a new Time line adapter.
     */
    public ImageAdapter(Context mContext, String call_for) {
        this.call_for = call_for;
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

        ItemImageBinding mRowItemListBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.item_image, parent, false);
        ViewHolder viewHolder = new ViewHolder(mRowItemListBinding);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {

        if (call_for.equals("")) {
            holder.mRowItemListBinding.imgCancle.setVisibility(View.GONE);
        } else {
            holder.mRowItemListBinding.imgCancle.setVisibility(View.VISIBLE);

        }
    }

    @Override
    public int getItemCount() {
        return 4;
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
        void onItemSpinnerClick(View view, int position, String call);

    }

    /**
     * The type View holder.
     */
    public class ViewHolder extends RecyclerView.ViewHolder {

        /**
         * The M row item list binding.
         */
        public ItemImageBinding mRowItemListBinding;

        /**
         * Instantiates a new View holder.
         *
         * @param mRowItemListBinding the m row item list binding
         */
        public ViewHolder(ItemImageBinding mRowItemListBinding) {
            super(mRowItemListBinding.getRoot());
            this.mRowItemListBinding = mRowItemListBinding;
        }
    }
}