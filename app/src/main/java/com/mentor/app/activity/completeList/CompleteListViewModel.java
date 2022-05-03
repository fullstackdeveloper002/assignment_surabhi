package com.mentor.app.activity.completeList;

import com.mentor.app.base.BaseViewModel;

/**
 * The type Sign in view model.
 */
public class CompleteListViewModel extends BaseViewModel<CompleteListNavigator> {

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
