package com.example.guwudangin.modul.productdetail;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.example.guwudangin.data.model.Product;
import com.example.guwudangin.data.model.User;
import com.example.guwudangin.data.source.session.SessionRepository;

import org.json.JSONObject;

public class ProductDetailPresenter implements ProductDetailContract.Presenter{
    private final ProductDetailContract.View view;
    private final SessionRepository sessionRepository;                                              //new

    public ProductDetailPresenter(ProductDetailContract.View view, SessionRepository sessionRepository) {           //add
        this.view = view;
        this.sessionRepository = sessionRepository;                                                 //new
    }

    @Override
    public void start() {

    }

    @Override
    public Product getProduct() {
        return (Product) sessionRepository.getSessionData();
    }

    @Override
    public void logout() {
        sessionRepository.destroy();
        view.redirectToLogin();
    }
}
