package com.mentor.pipes.activity.login;

import com.mentor.pipes.base.BaseViewModel;

/**
 * The type Sign in view model.
 */
public class SignInViewModel extends BaseViewModel<SignInNavigator> {

    public void forgetPasswordClick() {
        getNavigator().forgetPasswordClick();
    }

    public void onLoginClick() {
        getNavigator().onLoginClick();
    }
    public void onForgetpassword() {
        getNavigator().onForgetpassword();
    }

    public void onBackPress() {
        getNavigator().onLoginClick();
    }

    public void onSignupClick() {
        getNavigator().onSignupClick();
    }

}
