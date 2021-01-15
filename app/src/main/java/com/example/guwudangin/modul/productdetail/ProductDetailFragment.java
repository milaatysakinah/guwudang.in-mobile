package com.example.guwudangin.modul.productdetail;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.guwudangin.R;
import com.example.guwudangin.base.BaseFragment;
import com.example.guwudangin.data.model.Product;
import com.example.guwudangin.data.model.ProductDetail;
import com.example.guwudangin.data.source.session.ProductDetailSessionRepository;
import com.example.guwudangin.data.source.session.ProductSessionRepository;
import com.example.guwudangin.modul.aboutus.AboutUsActivity;
import com.example.guwudangin.modul.login.LoginActivity;
import com.example.guwudangin.modul.qrscanner.QRScannerActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class ProductDetailFragment extends BaseFragment<ProductDetailActivity, ProductDetailContract.Presenter> implements ProductDetailContract.View {
    ImageButton btnLogout, btnAbout;
    FloatingActionButton QRfab;
    TextView productID, productName, productPrice, productDesc, productStock;
    ImageView imageView;

    public ProductDetailFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        fragmentView = inflater.inflate(R.layout.activity_product_detail, container, false);
        mPresenter = new ProductDetailPresenter(this, new ProductSessionRepository(getActivity()), new ProductDetailSessionRepository(getActivity()));                      //add
        mPresenter.start();

        btnLogout = fragmentView.findViewById(R.id.bt_logout);
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setBtBackClick();
            }
        });

        btnAbout = fragmentView.findViewById(R.id.bt_about);
        btnAbout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                redirectToAbout();
            }
        });

        productID = fragmentView.findViewById(R.id.textView4);
        productName = fragmentView.findViewById(R.id.textView3);
        productPrice = fragmentView.findViewById(R.id.textView6);
        productStock = fragmentView.findViewById(R.id.textView7);
        productDesc = fragmentView.findViewById(R.id.textView8);
        QRfab = fragmentView.findViewById(R.id.bt_back);
        imageView = fragmentView.findViewById(R.id.productIMG);

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
        ProductDetail detail = mPresenter.getProductDetail();
        productID.setText(productID.getText() + " : " + product.getId());
        productName.setText(product.getProduct_name());
        productDesc.setText(product.getDescription());
        productPrice.setText(productPrice.getText() + " : " + product.getPrice());
        productStock.setText(productStock.getText() + " " + detail.getProduct_quantity());
//        imageView.setImageBitmap(getImageBitmap(product.getProduct_picture()));
        new DownloadImageTask(imageView)
                .execute(product.getProduct_picture());
    }

    private Bitmap getImageBitmap(String surl) {
        try {
            URL url = new URL(surl);
            HttpURLConnection urlcon = (HttpURLConnection) url.openConnection();
            urlcon.setDoInput(true);
            urlcon.connect();
            InputStream in = urlcon.getInputStream();
            Bitmap mIcon = BitmapFactory.decodeStream(in);
            return  mIcon;
        } catch (Exception e) {
//            Log.e("Error", e.getMessage());
            e.printStackTrace();
            return null;
        }
    }

    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
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

    public void redirectToAbout() {
        Intent intent = new Intent(activity, AboutUsActivity.class);
        startActivity(intent);
        activity.finish();
    }
}
