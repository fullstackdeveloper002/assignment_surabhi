package com.mentor.pipes.activity.pendingList;

import com.mentor.pipes.base.BaseViewModel;

/**
 * The type Sign in view model.
 */
public class ProductListViewModel extends BaseViewModel<ProductListNavigator> {

    public void forgetPasswordClick() {
        getNavigator().forgetPasswordClick();
    }

    public void onLoginClick() {
        getNavigator().onLoginClick();
    }
    public void onResendOTP() {
        getNavigator().onResendOTP();
    }

    public void onBackPress() {
        getNavigator().onLoginClick();
    }

    public void onSignupClick() {
        getNavigator().onSignupClick();
    }

}
