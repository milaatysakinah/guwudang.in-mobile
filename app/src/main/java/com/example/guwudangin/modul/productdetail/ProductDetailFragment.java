package com.example.guwudangin.modul.productdetail;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.guwudangin.R;
import com.example.guwudangin.base.BaseFragment;
import com.example.guwudangin.data.model.Product;
import com.example.guwudangin.data.source.session.ProductSessionRepository;
import com.example.guwudangin.modul.login.LoginActivity;
import com.example.guwudangin.modul.qrscanner.QRScannerActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

/**
 * Created by fahrul on 13/03/19.
 */

public class ProductDetailFragment extends BaseFragment<ProductDetailActivity, ProductDetailContract.Presenter> implements ProductDetailContract.View {
    Button btnLogout;
    FloatingActionButton QRfab;
    TextView productID, productName, productPrice, productDesc, productStock;

    public ProductDetailFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        fragmentView = inflater.inflate(R.layout.activity_product_detail, container, false);
        mPresenter = new ProductDetailPresenter(this, new ProductSessionRepository(getActivity()));                      //add
        mPresenter.start();

        btnLogout = fragmentView.findViewById(R.id.bt_logout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setBtBackClick();
            }
        });

        productID = fragmentView.findViewById(R.id.textView4);
        productName = fragmentView.findViewById(R.id.textView3);
        productPrice = fragmentView.findViewById(R.id.textView6);
        productStock = fragmentView.findViewById(R.id.textView7);
        productDesc = fragmentView.findViewById(R.id.textView8);
        QRfab = fragmentView.findViewById(R.id.bt_back);

        QRfab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                redirectToQR();
            }
        });

        getData();

        return fragmentView;
    }

    public void setBtBackClick(){
        mPresenter.logout();
    }

    private void getData() {
        Product product = mPresenter.getProduct();
        productID.setText(productID.getText() + " : " + product.getId());
        productName.setText(product.getProduct_name());
        productDesc.setText(product.getDescription());
        productPrice.setText(productPrice.getText() + " : " + product.getPrice());
    }

    @Override
    public void setPresenter(ProductDetailContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void redirectToLogin() {
            Intent intent = new Intent(activity, LoginActivity.class);
            startActivity(intent);
            activity.finish();
    }

    public void redirectToQR() {
        Intent intent = new Intent(activity, QRScannerActivity.class);
        startActivity(intent);
        activity.finish();
    }
}
