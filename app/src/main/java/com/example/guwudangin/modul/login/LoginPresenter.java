package com.example.guwudangin.modul.login;

import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
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
        if(sessionRepository.getSessionData() != null){                                             //new
            view.redirectToProfile();                                                               //jika sudah login langsung masuk profile
        }
    }

    @Override
    public void performLogin(final String email, final String password){
        //proses login
        AndroidNetworking.post("http://api.guwudangin.me/api/login")
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
