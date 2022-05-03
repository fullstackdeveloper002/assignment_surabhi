package com.mentor.app.activity.home.adapter;

import static com.mentor.app.activity.home.HomeActivity.arrayList_productList;
import static com.mentor.app.activity.home.HomeActivity.produstList_hash;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;

import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.mentor.app.R;
import com.mentor.app.databinding.AdapterListBinding;
import com.mentor.app.model.BrandModel;
import com.mentor.app.model.ProductModel;

import java.util.ArrayList;
import java.util.List;

public class AdapterListShow extends RecyclerView.Adapter<AdapterListShow.ViewHolder> {

    private List<BrandModel> brandlist;
    private List<ProductModel> productlist;
    private Context mContext;
    private Boolean comingFromHome;
    private OnItemListClickListener onClickListener;
    public String call_for = "";


    public AdapterListShow(String call_for, ArrayList<ProductModel> productlist,
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
    public AdapterListShow(String call_for, OnItemListClickListener onClickListener, Context mContext) {
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
        AdapterListBinding mRowItemListBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()), R.layout.adapter_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(mRowItemListBinding);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        holder.mRowItemListBinding.checkBox.setText("" + productlist.get(position).getTitle());
        produstList_hash.put(productlist.get(position).getId(), productlist.get(position).getTitle());

        if (arrayList_productList.size() > 0 && arrayList_productList.contains(productlist.get(position).getId())) {
            holder.mRowItemListBinding.checkBox.setChecked(true);
        } else {
            holder.mRowItemListBinding.checkBox.setChecked(false);
        }

        holder.mRowItemListBinding.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (((CompoundButton) view).isChecked()) {
                    arrayList_productList.add(productlist.get(position).getId());
                } else {
                    if (arrayList_productList.contains(productlist.get(position).getId())) {
                        arrayList_productList.remove(productlist.get(position).getId());
                    }
                }
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onClickListener.onItemSpinnerClick(view, position, call_for);
            }
        });
    }

    @Override
    public int getItemCount() {
        if (call_for.equalsIgnoreCase("brand")) {
            return brandlist.size();
        } else
            return productlist.size();
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
        public AdapterListBinding mRowItemListBinding;

        /**
         * Instantiates a new View holder.
         *
         * @param mRowItemListBinding the m row item list binding
         */
        public ViewHolder(AdapterListBinding mRowItemListBinding) {
            super(mRowItemListBinding.getRoot());
            this.mRowItemListBinding = mRowItemListBinding;
        }
    }
}