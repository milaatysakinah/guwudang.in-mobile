package com.example.guwudangin.modul.qrscanner;

import com.example.guwudangin.base.BaseFragmentHolderActivity;

public class QRScannerActivity extends BaseFragmentHolderActivity {
    QRScannerFragment QRScannerFragment;

    /*@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_login);
        AndroidNetworking.initialize(getApplicationContext());

        initializeFragment();
    }*/


    @Override
    protected void initializeFragment() {
        initializeView();

        //btBack.setVisibility(View.GONE);
        //btOptionMenu.setVisibility(View.GONE);
//        ivIcon.setImageResource(R.drawable.....);
        //ivIcon.setVisibility(View.VISIBLE);

        QRScannerFragment = new QRScannerFragment();
        setCurrentFragment(QRScannerFragment, false);
        //loginFragment.onCreateView();
    }
}