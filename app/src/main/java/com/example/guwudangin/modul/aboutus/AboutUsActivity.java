package com.example.guwudangin.modul.aboutus;

import com.example.guwudangin.base.BaseFragmentHolderActivity;

public class AboutUsActivity extends BaseFragmentHolderActivity {
    AboutUsFragment aboutUsFragment;
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

        aboutUsFragment = new AboutUsFragment();
        setCurrentFragment(aboutUsFragment, false);
    }
}