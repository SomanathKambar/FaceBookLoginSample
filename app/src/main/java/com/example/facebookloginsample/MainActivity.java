package com.example.facebookloginsample;

import androidx.activity.result.ActivityResult;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.os.Bundle;

import com.example.facebookloginsample.util.FaceBookLoginSharedPreferenceManager;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;

import javax.inject.Inject;

import navigation.ActivityRouter;

public class MainActivity extends AppCompatActivity {

    @Inject
    ActivityRouter activityRouter;

    @Inject
    FaceBookLoginSharedPreferenceManager sharedPreferenceManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ((FaceBookLoginSampleApp)getApplication()).getComponent().inject(this);
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(getApplication());
        if(!sharedPreferenceManager.isUerLoggedIn()) {
            activityRouter.goToLoginActivity(this);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == ActivityRouter.REQUEST_CODE_LOGIN) {
            if(resultCode == Activity.RESULT_OK) {

            }
        }
    }
}