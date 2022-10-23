package com.example.facebookloginsample;

import androidx.activity.result.ActivityResult;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;

import com.example.facebookloginsample.home.HomeViewPagerAdapter;
import com.example.facebookloginsample.home.Repository.HomePageRepository;
import com.example.facebookloginsample.util.FaceBookLoginSharedPreferenceManager;
import com.facebook.FacebookSdk;
import com.facebook.appevents.AppEventsLogger;
import com.google.android.material.tabs.TabLayout;

import javax.inject.Inject;

import navigation.ActivityRouter;

public class MainActivity extends AppCompatActivity {

    @Inject
    ActivityRouter activityRouter;

    @Inject
    FaceBookLoginSharedPreferenceManager sharedPreferenceManager;

    @Inject
    HomePageRepository homePageRepository;

    TabLayout tabs;
    ViewPager viewpager;

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
        setupTabs();
    }

    private void setupTabs() {
        tabs = findViewById(R.id.tabs);
        viewpager = findViewById(R.id.viewpager);
        viewpager.setAdapter(new HomeViewPagerAdapter(homePageRepository, getSupportFragmentManager()));
        tabs.setupWithViewPager(viewpager);
        tabs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                tab.view.setSelected(true);
                tab.view.setBackgroundResource(R.drawable.tabs_background);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                tab.view.setSelected(false);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                tab.view.setSelected(true);
                tab.view.setBackgroundResource(R.drawable.tabs_background);
            }
        });

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                tabs.selectTab(tabs.getTabAt(0));
            }
        }, 500);


       // tabs.setSelectedTabIndicator(ResourcesCompat.getDrawable(getResources(),R.drawable.tabs_background, null));
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