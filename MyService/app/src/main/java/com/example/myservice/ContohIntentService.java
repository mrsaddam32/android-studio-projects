package com.example.myservice;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

public class ContohIntentService extends IntentService {
    public static String EXTRA_DURATION = "extra_duration";
    public static final String TAG = "ContohIntentService";

    public ContohIntentService() {
        super("ContohIntentService.");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if(intent != null) {
            int duration = intent.getIntExtra(EXTRA_DURATION,0);
            try {
                Thread.sleep(duration);
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy()");
    }
}