package com.example.guwudangin.modul.productdetail;

import com.example.guwudangin.base.BasePresenter;
import com.example.guwudangin.base.BaseView;
import com.example.guwudangin.data.model.Product;

/**
 * Created by fahrul on 13/03/19.
 */

public interface ProductDetailContract {
    interface View extends BaseView<Presenter> {
        void redirectToLogin();
    }

    interface Presenter extends BasePresenter {
        void logout();
        Product getProduct();
    }
}
