package com.mentor.app.activity.drawer;

import com.mentor.app.base.BaseViewModel;

public class DrawerViewModel extends BaseViewModel<DrawerNavigator> {

    public void home() {
        getNavigator().home();
    }

    public void timer() {
        getNavigator().timer();
    }

    public void profile() {
        getNavigator().profile();
    }

    public void orders() {
        getNavigator().order();
    }

    public void products() {
        getNavigator().products();
    }

    public void earning() {
        getNavigator().earning();
    }

    public void transaction() {
        getNavigator().transaction();
    }

    public void addDealer() {
        getNavigator().addDealer();
    }

    public void dealerList() {
        getNavigator().dealerList();
    }

    public void distributorDetail() {
        getNavigator().distributorDetail();
    }

    public void setting() {
        getNavigator().setting();
    }

    public void support() {
        getNavigator().support();
    }

    public void aboutus() {
        getNavigator().aboutus();
    }

    public void complain() {
        getNavigator().complain();
    }

    public void logout() {
        getNavigator().logout();
    }

    public void menu() {
        getNavigator().menu();
    }

}
