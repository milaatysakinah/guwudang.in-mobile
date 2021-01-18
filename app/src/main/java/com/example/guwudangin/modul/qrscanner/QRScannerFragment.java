package com.example.guwudangin.modul.qrscanner;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.core.app.ActivityCompat;

import com.example.guwudangin.R;
import com.example.guwudangin.base.BaseFragment;
import com.example.guwudangin.data.source.session.ProductDetailSessionRepository;
import com.example.guwudangin.data.source.session.ProductSessionRepository;
import com.example.guwudangin.data.source.session.UserSessionRepository;
import com.example.guwudangin.modul.login.LoginActivity;
import com.example.guwudangin.modul.productdetail.ProductDetailActivity;
import com.google.common.util.concurrent.ListenableFuture;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class QRScannerFragment extends BaseFragment<QRScannerActivity, QRScannerContract.Presenter> implements QRScannerContract.View, ZXingScannerView.ResultHandler {

    final int PERMISSION_REQUEST_CAMERA = 0;
    //PreviewView previewView;
    FrameLayout previewView;
    ListenableFuture<ProcessCameraProvider> cameraProviderFuture;
    Button qrCodeFoundButton, btnLogout;
    String qrCode;
    private ZXingScannerView mScannerView;


    public QRScannerFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        fragmentView = inflater.inflate(R.layout.activity_main, container, false);
        mPresenter = new QRScannerPresenter(this, new UserSessionRepository(getActivity()), new ProductSessionRepository(getActivity()), new ProductDetailSessionRepository(getActivity()));                      //add
        mPresenter.start();

        previewView = fragmentView.findViewById(R.id.activity_main_previewView);

        qrCodeFoundButton = fragmentView.findViewById(R.id.activity_main_qrCodeFoundButton);
        qrCodeFoundButton.setVisibility(View.INVISIBLE);
//        qrCodeFoundButton.setVisibility(View.VISIBLE);
        qrCodeFoundButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), qrCode, Toast.LENGTH_SHORT).show();
                Log.i(QRScannerActivity.class.getSimpleName(), "QR Code Found: " + qrCode);
                setQRCodeFoundButtonClick();
            }
        });

        btnLogout = fragmentView.findViewById(R.id.bt_logout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setBtBackClick();
            }
        });

        cameraProviderFuture = ProcessCameraProvider.getInstance(getContext());
        requestCamera();

        return fragmentView;
    }

    @Override
    public void displayToast(String text){
        Toast.makeText(activity, text, Toast.LENGTH_SHORT).show();
    }

    public void setBtBackClick(){
        mPresenter.logout();
    }

    private void setQRCodeFoundButtonClick() {
        mPresenter.setIdProductSession(qrCode);
    }

    @Override
    public void redirectToProduct() {
        Intent intent = new Intent(activity, ProductDetailActivity.class);
       // Toast.makeText(activity, "Moving To Product", Toast.LENGTH_LONG).show();
        startActivity(intent);
        activity.finish();
    }

    @Override
    public void setPresenter(QRScannerContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void requestCamera() {
        if (ActivityCompat.checkSelfPermission(activity, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED) {
            //startCamera();
            startZxingCamera();
        } else {
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.CAMERA)) {
                ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.CAMERA}, PERMISSION_REQUEST_CAMERA);
            } else {
                ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.CAMERA}, PERMISSION_REQUEST_CAMERA);
            }
        }
    }


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        //global::ZXing.Net.Mobile.Android.PermissionsHandler.OnRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == PERMISSION_REQUEST_CAMERA) {
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                //startCamera();
                startZxingCamera();
            } else {
                Toast.makeText(getContext(), "Camera Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public void startZxingCamera(){
        mScannerView = new ZXingScannerView(getContext());   // Programmatically initialize the scanner view
        mScannerView.setResultHandler(QRScannerFragment.this); // Register ourselves as a handler for scan results.
        previewView.addView(mScannerView);
        //activity.setContentView(mScannerView);

        mScannerView.startCamera();
    }

    @Override
    public void redirectToLogin() {
        Intent intent = new Intent(activity, LoginActivity.class);
        startActivity(intent);
        activity.finish();
    }

    @Override
    public void handleResult(com.google.zxing.Result result) {
        qrCode = result.getText();
        //System.out.println("Hasil QRCODE : " + qrCode);
        setQRCodeFoundButtonClick();
    }
}
