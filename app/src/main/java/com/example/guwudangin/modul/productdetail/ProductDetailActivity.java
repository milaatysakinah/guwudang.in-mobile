package com.example.guwudangin.modul.productdetail;

import com.example.guwudangin.base.BaseFragmentHolderActivity;

public class ProductDetailActivity extends BaseFragmentHolderActivity {
    ProductDetailFragment productDetailFragment;
    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_detail);
    }*/
    @Override
    protected void initializeFragment() {
        initializeView();

        //btBack.setVisibility(View.GONE);
        //btOptionMenu.setVisibility(View.GONE);
//        ivIcon.setImageResource(R.drawable.....);
        //ivIcon.setVisibility(View.VISIBLE);

        productDetailFragment = new ProductDetailFragment();
        setCurrentFragment(productDetailFragment, false);
    }
}