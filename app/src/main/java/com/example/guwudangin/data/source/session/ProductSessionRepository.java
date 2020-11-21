package com.example.guwudangin.data.source.session;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.guwudangin.data.model.Product;
import com.example.guwudangin.data.model.User;
import com.google.gson.Gson;

public class ProductSessionRepository implements SessionRepository<Product>{
    private static String SESSION_PRODUCT = "SessionProduct";
    private SharedPreferences sharedPref;

    public ProductSessionRepository(Context context) {
        sharedPref = context.getSharedPreferences(SHARED_PREFERENCE_NAME, Context.MODE_PRIVATE);
    }

    @Override
    public Product initialize(Product sessionData) {
        //save to shared preference
        setSessionData(sessionData);

        //load from shared preference
        return getSessionData();
    }

    @Override
    public Product getSessionData() {
        String sessionDataJson = sharedPref.getString(SESSION_PRODUCT, null);
        if (sessionDataJson != null) {
            return new Gson().fromJson(sessionDataJson, Product.class);
        }
        return null;
    }

    @Override
    public void setSessionData(Product sessionData) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString(SESSION_PRODUCT, new Gson().toJson(sessionData));
        editor.apply();
    }

    @Override
    public void destroy() {
        sharedPref.edit().clear().apply();
    }

    @Override
    public void update(Product sessionData) {
        destroy();
        setSessionData(sessionData);
    }
}
