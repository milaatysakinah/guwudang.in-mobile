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
import com.example.guwudangin.data.source.session.UserSessionRepositoryRepository;
import com.example.guwudangin.modul.login.LoginActivity;

/**
 * Created by fahrul on 13/03/19.
 */

public class ProductDetailFragment extends BaseFragment<ProductDetailActivity, ProductDetailContract.Presenter> implements ProductDetailContract.View {
    Button btnBack;
    TextView productID;

    public ProductDetailFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        fragmentView = inflater.inflate(R.layout.activity_product_detail, container, false);
        mPresenter = new ProductDetailPresenter(this, new ProductSessionRepository(getActivity()));                      //add
        mPresenter.start();

        btnBack = fragmentView.findViewById(R.id.bt_back);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setBtBackClick();
            }
        });

        productID = fragmentView.findViewById(R.id.textView4);

        getData();

        return fragmentView;
    }

    public void setBtBackClick(){
        mPresenter.logout();
    }

    private void getData() {
        Product product = mPresenter.getProduct();
        productID.setText(product.getId());
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
}
