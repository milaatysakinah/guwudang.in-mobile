package com.example.guwudangin.modul.login;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.core.app.ActivityCompat;

import com.example.guwudangin.R;
import com.example.guwudangin.base.BaseFragment;
import com.example.guwudangin.data.source.session.UserSessionRepository;
import com.example.guwudangin.modul.qrscanner.QRScannerActivity;
import com.google.common.util.concurrent.ListenableFuture;


public class LoginFragment extends BaseFragment<LoginActivity, LoginContract.Presenter> implements LoginContract.View {

    EditText etEmail;
    EditText etPassword;
    Button btnLogin;
    final int PERMISSION_REQUEST_CAMERA = 0;
    ListenableFuture<ProcessCameraProvider> cameraProviderFuture;


    public LoginFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        fragmentView = inflater.inflate(R.layout.activity_login, container, false);
        mPresenter = new LoginPresenter(this, new UserSessionRepository(getActivity()));                      //add
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
        cameraProviderFuture = ProcessCameraProvider.getInstance(getContext());
        requestCamera();
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
           // Toast.makeText(activity, "Moving To Scanner", Toast.LENGTH_LONG).show();
            activity.finish();
    }



    public void requestCamera() {
        if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.CAMERA)) {
                ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.CAMERA}, PERMISSION_REQUEST_CAMERA);
            } else {
                ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.CAMERA}, PERMISSION_REQUEST_CAMERA);
            }
        }
    }


}
