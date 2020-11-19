package com.example.guwudangin.modul.productdetail;

import com.example.guwudangin.data.source.session.SessionRepository;

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
    public void logout() {
        sessionRepository.destroy();
        view.redirectToLogin();
    }
}
