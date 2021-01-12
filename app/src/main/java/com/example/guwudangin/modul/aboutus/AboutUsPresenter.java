package com.example.guwudangin.modul.aboutus;

import com.example.guwudangin.data.model.Product;
import com.example.guwudangin.data.model.ProductDetail;
import com.example.guwudangin.data.source.session.SessionRepository;

public class AboutUsPresenter implements AboutUsContract.Presenter{
    private final AboutUsContract.View view;
    private final SessionRepository sessionRepository;                                              //new
    private final  SessionRepository productDetailSessionRepository;

    public AboutUsPresenter(AboutUsContract.View view, SessionRepository sessionRepository, SessionRepository productDetailSessionRepository) {           //add
        this.view = view;
        this.sessionRepository = sessionRepository;                                                 //new
        this.productDetailSessionRepository = productDetailSessionRepository;
    }

    @Override
    public void start() {

    }

    @Override
    public Product getProduct() {
        return (Product) sessionRepository.getSessionData();
    }

    @Override
    public ProductDetail getProductDetail() {
        return (ProductDetail) productDetailSessionRepository.getSessionData();
    }

    @Override
    public void logout() {
        sessionRepository.destroy();
//        view.redirectToLogin();
    }
}
