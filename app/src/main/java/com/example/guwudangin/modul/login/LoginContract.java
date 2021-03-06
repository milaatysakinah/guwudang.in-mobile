package com.example.guwudangin.modul.login;

import com.example.guwudangin.base.BasePresenter;
import com.example.guwudangin.base.BaseView;


public interface LoginContract {
    interface View extends BaseView<Presenter> {
        void redirectToProfile();
        void falseLogin();
        void showError(String error);
        void redirectToLogin();
    }

    interface Presenter extends BasePresenter {
        //void performLogin();
        void performLogin(String email, String password);
    }
}
