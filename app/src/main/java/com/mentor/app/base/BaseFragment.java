package com.mentor.app.base;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.LayoutRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.fragment.app.Fragment;


import com.mentor.app.session.SessionManager;

/**
 * The type Base fragment.
 *
 * @param <T> the type parameter
 * @param <V> the type parameter
 */

public abstract class BaseFragment<T extends ViewDataBinding, V extends BaseViewModel> extends Fragment {

    private BaseActivity mActivity;
    private View mRootView;
    private T mViewDataBinding;
    private V mViewModel;
    private ProgressDialog pDialog;

    public SessionManager sessionManager;

    /**
     * Override for set binding variable
     *
     * @return variable id
     */
    public abstract int getBindingVariable();

    /**
     * Gets layout id.
     *
     * @return layout resource id
     */
    public abstract
    @LayoutRes
    int getLayoutId();

    /**
     * Override for set view model
     *
     * @return view model instance
     */
    public abstract V getViewModel();

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BaseActivity) {
            BaseActivity activity = (BaseActivity) context;
            this.mActivity = activity;
            activity.onFragmentAttached();
        }
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mViewModel = getViewModel();
        setHasOptionsMenu(false);
        sessionManager = new SessionManager(mActivity);
    }

    /**
     * Show progress bar.
     */
/*    public void showProgressBar() {

        pDialog = ProgressHUD.init(getActivity(), false, false);
        pDialog.show();
        pDialog.show();
    }*/

    /**
     * Hide progress bar.
     */
    public void hideProgressBar() {

        if (pDialog != null)
            pDialog.dismiss();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mViewDataBinding = DataBindingUtil.inflate(inflater, getLayoutId(), container, false);
        mRootView = mViewDataBinding.getRoot();
        return mRootView;
    }

    @Override
    public void onDetach() {
        mActivity = null;
        super.onDetach();
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewDataBinding.setVariable(getBindingVariable(), mViewModel);
        mViewDataBinding.executePendingBindings();
    }

    /**
     * Gets base activity.
     *
     * @return the base activity
     */
    public BaseActivity getBaseActivity() {
        return mActivity;
    }

    /**
     * Gets view data binding.
     *
     * @return the view data binding
     */
    public T getViewDataBinding() {
        return mViewDataBinding;
    }

    /*    */

    /**
     * Hide keyboard.
     *//*
    public void hideKeyboard() {
        if (mActivity != null) {
            mActivity.hideKeyboard();
        }
    }*/
    @Override
    public void onDestroy() {
        hideProgressBar();
        super.onDestroy();

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * The interface Callback.
     */
    public interface Callback {

        /**
         * On fragment attached.
         */
        void onFragmentAttached();

        /**
         * On fragment detached.
         *
         * @param tag the tag
         */
        void onFragmentDetached(String tag);
    }


    /**
     * Rx permission granted.
     */
    /*invoked when permission granted*/
    protected void rxPermissionGranted() {

    }

    /**
     * Rx permission denied.
     */
    /*invoked when permission denied*/
    protected void rxPermissionDenied() {

    }


}
