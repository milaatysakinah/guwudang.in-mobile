package com.example.guwudangin.modul.qrscanner;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.util.Size;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.camera.core.Camera;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ImageAnalysis;
import androidx.camera.core.Preview;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.PreviewView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.LifecycleOwner;

import com.example.guwudangin.data.source.session.ProductDetailSessionRepository;
import com.example.guwudangin.data.source.session.ProductSessionRepository;
import com.example.guwudangin.modul.productdetail.ProductDetailActivity;
import com.example.guwudangin.util.QRCodeFoundListener;
import com.example.guwudangin.util.QRCodeImageAnalyzer;
import com.example.guwudangin.R;
import com.example.guwudangin.base.BaseFragment;
import com.example.guwudangin.data.source.session.UserSessionRepository;
import com.google.common.util.concurrent.ListenableFuture;

import java.util.concurrent.ExecutionException;

/**
 * Created by fahrul on 13/03/19.
 */

public class QRScannerFragment extends BaseFragment<QRScannerActivity, QRScannerContract.Presenter> implements QRScannerContract.View {

    final int PERMISSION_REQUEST_CAMERA = 0;
    PreviewView previewView;
    ListenableFuture<ProcessCameraProvider> cameraProviderFuture;
    Button qrCodeFoundButton;
    String qrCode;


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

        cameraProviderFuture = ProcessCameraProvider.getInstance(getContext());
        requestCamera();

        return fragmentView;
    }

    private void setQRCodeFoundButtonClick() {
        mPresenter.setIdProductSession(qrCode);
    }

    @Override
    public void redirectToProduct() {
        Intent intent = new Intent(activity, ProductDetailActivity.class);
        Toast.makeText(activity, "Moving To Product", Toast.LENGTH_LONG).show();
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
            startCamera();
        } else {
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity, Manifest.permission.CAMERA)) {
                ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.CAMERA}, PERMISSION_REQUEST_CAMERA);
            } else {
                ActivityCompat.requestPermissions(activity, new String[]{Manifest.permission.CAMERA}, PERMISSION_REQUEST_CAMERA);
            }
        }
    }

    @Override
    public void startCamera() {
        cameraProviderFuture.addListener(() -> {
            try {
                ProcessCameraProvider cameraProvider = cameraProviderFuture.get();
                bindCameraPreview(cameraProvider);
            } catch (ExecutionException | InterruptedException e) {
                Toast.makeText(getContext(), "Error starting camera " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }, ContextCompat.getMainExecutor(getContext()));
    }

    @Override
    public void bindCameraPreview(@NonNull ProcessCameraProvider cameraProvider) {
        previewView.setPreferredImplementationMode(PreviewView.ImplementationMode.SURFACE_VIEW);

        Preview preview = new Preview.Builder()
                .build();

        CameraSelector cameraSelector = new CameraSelector.Builder()
                .requireLensFacing(CameraSelector.LENS_FACING_BACK)
                .build();

        preview.setSurfaceProvider(previewView.createSurfaceProvider());

        ImageAnalysis imageAnalysis =
                new ImageAnalysis.Builder()
                        .setTargetResolution(new Size(1280, 720))
                        .setBackpressureStrategy(ImageAnalysis.STRATEGY_KEEP_ONLY_LATEST)
                        .build();

        imageAnalysis.setAnalyzer(ContextCompat.getMainExecutor(getContext()), new QRCodeImageAnalyzer(new QRCodeFoundListener() {
            @Override
            public void onQRCodeFound(String _qrCode) {
                qrCode = _qrCode;
                qrCodeFoundButton.setVisibility(View.VISIBLE);
//                qrCode = "1";
//                qrCodeFoundButton.setVisibility(View.VISIBLE);
                //Toast.makeText(getContext(), qrCode, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void qrCodeNotFound() {
                qrCodeFoundButton.setVisibility(View.INVISIBLE);
//                qrCode = "1";
//                qrCodeFoundButton.setVisibility(View.VISIBLE);
            }
        }));

        Camera camera = cameraProvider.bindToLifecycle((LifecycleOwner)this, cameraSelector, imageAnalysis, preview);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if (requestCode == PERMISSION_REQUEST_CAMERA) {
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startCamera();
            } else {
                Toast.makeText(getContext(), "Camera Permission Denied", Toast.LENGTH_SHORT).show();
            }
        }
    }
}
