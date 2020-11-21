package com.example.guwudangin.data.source.session;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.guwudangin.data.model.Product;
import com.example.guwudangin.data.model.ProductDetail;
import com.google.gson.Gson;

public class ProductDetailSessionRepository implements SessionRepository<ProductDetail> {
    private static String SESSION_PRODUCT_DETAIL = "SessionProductDetail";
    private SharedPreferences sharedPref;

    public ProductDetailSessionRepository(Context context) {
        sharedPref = context.getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
    }

    @Override
    public ProductDetail initialize(ProductDetail sessionData) {
        //save to shared preference
        setSessionData(sessionData);

        //load from shared preference
        return getSessionData();
    }

    @Override
    public ProductDetail getSessionData() {
        String sessionDataJson = sharedPref.getString(SESSION_PRODUCT_DETAIL, null);
        if (sessionDataJson != null) {
            return new Gson().fromJson(sessionDataJson, ProductDetail.class);
        }
        return null;
    }

    @Override
    public void setSessionData(ProductDetail sessionData) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(SESSION_PRODUCT_DETAIL, new Gson().toJson(sessionData));
        editor.apply();
    }

    @Override
    public void destroy() {
        sharedPref.edit().clear().apply();
    }

    @Override
    public void update(ProductDetail sessionData) {
        destroy();
        setSessionData(sessionData);
    }
}
