package com.example.guwudangin.modul.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.example.guwudangin.MainActivity;
import com.example.guwudangin.R;
import com.example.guwudangin.base.BaseFragment;
import com.example.guwudangin.data.source.session.UserSessionRepositoryRepository;
import com.example.guwudangin.modul.productdetail.ProductDetailActivity;
import com.example.guwudangin.modul.qrscanner.QRScannerActivity;

/**
 * Created by fahrul on 13/03/19.
 */

public class LoginFragment extends BaseFragment<LoginActivity, LoginContract.Presenter> implements LoginContract.View {

    EditText etEmail;
    EditText etPassword;
    Button btnLogin;


    public LoginFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        fragmentView = inflater.inflate(R.layout.activity_login, container, false);
        mPresenter = new LoginPresenter(this, new UserSessionRepositoryRepository(getActivity()));                      //add
        mPresenter.start();

        etEmail = fragmentView.findViewById(R.id.et_email);
        etPassword = fragmentView.findViewById(R.id.et_password);
        btnLogin = fragmentView.findViewById(R.id.bt_login);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setBtLoginClick();
            }
        });

        return fragmentView;
    }

    public void setBtLoginClick(){
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();
        //mPresenter.performLogin(email,password);
        //Toast.makeText(activity, "Berhasil", Toast.LENGTH_LONG).show();
        mPresenter.performLogin(email, password);
    }

    @Override
    public void falseLogin(){
        Toast.makeText(activity, "Email / Password is Wrong", Toast.LENGTH_LONG).show();
    }

    public void showError(String error){
        Toast.makeText(activity, error, Toast.LENGTH_LONG).show();
    }

    @Override
    public void setPresenter(LoginContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void redirectToProfile() {
            Intent intent = new Intent(activity, QRScannerActivity.class);
            startActivity(intent);
            Toast.makeText(activity, "Moving To Scanner", Toast.LENGTH_LONG).show();
            activity.finish();
    }


}
