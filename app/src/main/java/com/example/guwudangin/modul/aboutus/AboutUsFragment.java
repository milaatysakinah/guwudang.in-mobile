package com.example.guwudangin.modul.aboutus;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.guwudangin.R;
import com.example.guwudangin.base.BaseFragment;
import com.example.guwudangin.data.model.Product;
import com.example.guwudangin.data.model.ProductDetail;
import com.example.guwudangin.data.source.session.ProductDetailSessionRepository;
import com.example.guwudangin.data.source.session.ProductSessionRepository;
import com.example.guwudangin.modul.login.LoginActivity;
import com.example.guwudangin.modul.productdetail.ProductDetailActivity;
import com.example.guwudangin.modul.qrscanner.QRScannerActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by fahrul on 13/03/19.
 */

public class AboutUsFragment extends BaseFragment<AboutUsActivity, AboutUsContract.Presenter> implements AboutUsContract.View {
    Button back;
//    FloatingActionButton QRfab;
//    TextView productID, productName, productPrice, productDesc, productStock;
//    ImageView imageView;

    public AboutUsFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        fragmentView = inflater.inflate(R.layout.activity_aboutus, container, false);
        mPresenter = new AboutUsPresenter(this, new ProductSessionRepository(getActivity()), new ProductDetailSessionRepository(getActivity()));                      //add
        mPresenter.start();

        back = fragmentView.findViewById(R.id.bt_backk);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                redirectToProduct();
            }
        });

        return fragmentView;
    }

    public void setBtBackClick(){
        mPresenter.logout();
    }

    @Override
    public void setPresenter(AboutUsContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void redirectToProduct() {
        Intent intent = new Intent(activity, ProductDetailActivity.class);
        startActivity(intent);
        activity.finish();
    }
}
