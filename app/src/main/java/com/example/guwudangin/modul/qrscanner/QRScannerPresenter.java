package com.example.guwudangin.modul.qrscanner;

import android.util.Log;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.example.guwudangin.data.model.Product;
import com.example.guwudangin.data.model.User;
import com.example.guwudangin.data.source.session.SessionRepository;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

import org.json.JSONArray;
import org.json.JSONObject;

public class QRScannerPresenter implements QRScannerContract.Presenter{
    private final QRScannerContract.View view;
    private final SessionRepository sessionRepository;                                              //new

    public QRScannerPresenter(QRScannerContract.View view, SessionRepository sessionRepository) {           //add
        this.view = view;
        this.sessionRepository = sessionRepository;                                                 //new
    }

    @Override
    public void start() {
//        if(sessionRepository.getSessionData() != null){                                             //new
//            view.redirectToProfile();                                                               //jika sudah login langsung masuk profile
//        }
    }

    @Override
    public void setIdProductSession(final String id){
        if(!(id.equals(""))) {

           /*AndroidNetworking.get("192.168.2.4:8000/api/product/{id}")*/
            AndroidNetworking.get("http://192.168.2.4:8000/api/product/{id}")
                    .addPathParameter("id", id)
                    .build()
                    .getAsObject(Product.class, new ParsedRequestListener<Product>() {
                        @Override
                        public void onResponse(Product product) {
                            // do anything with response
                            Product save = product;
                            sessionRepository.setSessionData(save);
                            Log.d("Sukses", "Sukses");
                            view.redirectToProduct();
                        }
                        @Override
                        public void onError(ANError anError) {
                            // handle error
                            Log.d("Gagal", anError.toString());
                        }
                    });

//            AndroidNetworking.get("http://192.168.2.4:8000/api/product/{productId}")
//                    .addPathParameter("productId", "1")
//                    .build()
//                    .getAsJSONArray(new JSONArrayRequestListener() {
//                        @Override
//                        public void onResponse(JSONArray response) {
//                            // do anything with response
//                            Log.d("SUKSES", response.toString());
//                        }
//                        @Override
//                        public void onError(ANError error) {
//                            // handle error
//                            Log.d("GAGAL", error.toString());
//                        }
//                    });


//            Product product = new Product(id,"TOKEN123456");
//            sessionRepository.setSessionData(product);
//            view.redirectToProduct();
        }
    }
}
