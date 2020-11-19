package com.example.guwudangin.modul.login;

import com.example.guwudangin.base.BasePresenter;
import com.example.guwudangin.base.BaseView;

/**
 * Created by fahrul on 13/03/19.
 */

public interface LoginContract {
    interface View extends BaseView<Presenter> {
        void redirectToProfile();
        void falseLogin();
    }

    interface Presenter extends BasePresenter {
        //void performLogin();
        void performLogin(String email, String password);
    }
}
