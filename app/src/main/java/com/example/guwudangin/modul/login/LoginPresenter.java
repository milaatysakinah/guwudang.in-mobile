package com.example.guwudangin.modul.login;

import android.util.Log;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.androidnetworking.interfaces.ParsedRequestListener;
import com.example.guwudangin.constant.ApiConstant;
import com.example.guwudangin.data.model.Product;
import com.example.guwudangin.data.model.User;
import com.example.guwudangin.data.source.session.SessionRepository;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Console;

public class LoginPresenter implements com.example.guwudangin.modul.login.LoginContract.Presenter{
    private final LoginContract.View view;
    private final SessionRepository sessionRepository;                                              //new

    public LoginPresenter(LoginContract.View view, SessionRepository sessionRepository) {           //add
        this.view = view;
        this.sessionRepository = sessionRepository;                                                 //new
    }

    @Override
    public void start() {
        User userSession = (User) sessionRepository.getSessionData();
        if(userSession != null){
            AndroidNetworking.get(ApiConstant.BASE_URL + "authUser")
                    .addHeaders("authorization","Bearer " + userSession.getToken())
                    .build()
                    .getAsObject(User.class, new ParsedRequestListener<User>() {
                        @Override
                        public void onResponse(User user) {
                            view.redirectToProfile();
                        }
                        @Override
                        public void onError(ANError anError) {
                            // handle error
                            Log.d("Gagal Product", anError.toString());
                            sessionRepository.destroy();
                            view.redirectToLogin();
                        }
                    });
                                                                         //jika sudah login langsung masuk profile
        }
    }

    @Override
    public void performLogin(final String email, final String password){
        //proses login
        Log.d("Login" ,ApiConstant.BASE_URL + "login");
        AndroidNetworking.post(ApiConstant.BASE_URL + "login")
//       AndroidNetworking.post("http://api.guwudangin.me/api/login")
                                    .addBodyParameter("email", email)
                                    .addBodyParameter("password", password)
                                    .build()
                                    .getAsJSONObject(new JSONObjectRequestListener() {
                                        @Override
                                        public void onResponse(JSONObject response) {
                                                //if login success
                                                view.showError("login success");
                                            User loggedUser = null;                                    //new
                                            try {
                                                loggedUser = new User(email, (String) response.get("token"));
                                            } catch (JSONException e) {
                                                view.showError(String.valueOf(e));
                                            }
                                            sessionRepository.setSessionData(loggedUser);                                               //new

                                                //then call redirect to profile
                                                view.redirectToProfile();
                                        }

                                        @Override
                                        public void onError(ANError anError) {
                                            view.showError(anError.getErrorCode() + " " + anError.getErrorDetail());
                                            view.falseLogin();
                                        }
                                    });



        /*if (email.equals("kucing1") && password.equals("kucing1")){
            //if login success
            User loggedUser = new User(email, "TOKEN123456");                                    //new
            sessionRepository.setSessionData(loggedUser);                                               //new

            //then call redirect to profile
            view.redirectToProfile();
        }else
            view.falseLogin();
        */
    }
}
