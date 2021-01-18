package com.example.guwudangin.modul.qrscanner;

import com.example.guwudangin.base.BasePresenter;
import com.example.guwudangin.base.BaseView;
import com.example.guwudangin.data.model.Product;


public interface QRScannerContract {
    interface View extends BaseView<Presenter> {
        void requestCamera();
        void startZxingCamera();
        void redirectToProduct();
        void redirectToLogin();
        void displayToast(String text);
    }

    interface Presenter extends BasePresenter {
        //void performLogin();
        void logout();
        void setIdProductSession(String id);
        void setProductDetail(Product product);
    }
}
