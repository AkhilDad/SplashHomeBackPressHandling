package com.akhil.splashhomebackpresshandling;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;


public class SplashActivity extends AppCompatActivity {

    private boolean mIsShowNextScreen;
    private boolean mIsPaused =  false;
    private Handler mHandler;
    private final int SPLASH_TIME_OUT = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        mIsShowNextScreen = false;
        mIsPaused =  false;
        mHandler = new Handler();
        mHandler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mHandler.removeCallbacksAndMessages(null);
                if (!mIsPaused) {
                    startNextActivity();
                } else {
                    mIsShowNextScreen = true;
                }
            }
        }, SPLASH_TIME_OUT);

    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mIsShowNextScreen) {
            mIsPaused = false;
            startNextActivity();
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        mIsPaused = true;
    }


    private synchronized void startNextActivity() {
        if (!mIsPaused) {
            startActivity(new Intent(this, MainActivity.class));
            finish();
        }
    }
}

