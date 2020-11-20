package com.example.guwudangin.modul.login;

import com.example.guwudangin.base.BaseFragmentHolderActivity;

public class LoginActivity extends BaseFragmentHolderActivity {
    LoginFragment loginFragment;

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

        loginFragment = new LoginFragment();
        setCurrentFragment(loginFragment, false);
        //loginFragment.onCreateView();
    }
}