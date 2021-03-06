package com.example.guwudangin.modul.productdetail;

import com.example.guwudangin.base.BasePresenter;
import com.example.guwudangin.base.BaseView;
import com.example.guwudangin.data.model.Product;
import com.example.guwudangin.data.model.ProductDetail;


public interface ProductDetailContract {
    interface View extends BaseView<Presenter> {
        void redirectToLogin();
    }

    interface Presenter extends BasePresenter {
        void logout();
        Product getProduct();
        ProductDetail getProductDetail();
    }
}
