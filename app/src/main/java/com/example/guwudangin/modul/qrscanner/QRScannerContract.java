package com.example.guwudangin.modul.qrscanner;

import androidx.annotation.NonNull;
import androidx.camera.lifecycle.ProcessCameraProvider;

import com.example.guwudangin.base.BasePresenter;
import com.example.guwudangin.base.BaseView;
import com.example.guwudangin.data.model.Product;

/**
 * Created by fahrul on 13/03/19.
 */

public interface QRScannerContract {
    interface View extends BaseView<Presenter> {
        void requestCamera();
        void startCamera();
        void bindCameraPreview(@NonNull ProcessCameraProvider cameraProvider);
        void redirectToProduct();
        void redirectToLogin();
    }

    interface Presenter extends BasePresenter {
        //void performLogin();
        void logout();
        void setIdProductSession(String id);
        void setProductDetail(Product product);
    }
}
