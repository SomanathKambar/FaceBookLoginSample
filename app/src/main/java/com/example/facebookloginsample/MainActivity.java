package com.example.facebookloginsample;

import androidx.activity.result.ActivityResult;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.facebookloginsample.databinding.ActivityMainBinding;
import com.example.facebookloginsample.home.HomeViewPagerAdapter;
import com.example.facebookloginsample.home.ItemFragment;
import com.example.facebookloginsample.home.Repository.HomePageRepository;
import com.example.facebookloginsample.subscribe.SubscriptionFragment;
import com.example.facebookloginsample.util.FaceBookLoginSharedPreferenceManager;
import com.facebook.FacebookSdk;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.appevents.AppEventsLogger;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.tabs.TabLayout;

import org.json.JSONException;
import org.json.JSONObject;

import javax.inject.Inject;

import navigation.ActivityRouter;

public class MainActivity extends AppCompatActivity {

    @Inject
    ActivityRouter activityRouter;

    @Inject
    FaceBookLoginSharedPreferenceManager sharedPreferenceManager;

    @Inject
    HomePageRepository homePageRepository;


   private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ((FaceBookLoginSampleApp)getApplication()).getComponent().inject(this);
        FacebookSdk.sdkInitialize(getApplicationContext());
        AppEventsLogger.activateApp(getApplication());
        if(!sharedPreferenceManager.isUerLoggedIn()) {
            activityRouter.goToLoginActivity(this);
        }
        handleBottomNavigation();
        addFragment(0);
    }

    private void addFragment(int pos) {
        Fragment fragment = null;
        switch (pos) {
            case 0: fragment = new MainFragment();
            break;
            case 1: fragment = new MainFragment();
            break;
            case 2: fragment = new SubscriptionFragment();
            break;
            default: throw  new IllegalArgumentException();
        }
        getSupportFragmentManager().popBackStack();
        getSupportFragmentManager().
                beginTransaction().replace(R.id.fragment_container, fragment).commitAllowingStateLoss();
    }

    private void handleBottomNavigation() {
        binding.botomNavigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                addFragment(item.getOrder());
                return true;
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == ActivityRouter.REQUEST_CODE_LOGIN) {
            if(resultCode == Activity.RESULT_OK) {
                sharedPreferenceManager.setUserLoggedIn(true);
                UpdateUserInfo();
            }
        }
    }

    private void UpdateUserInfo() {
        GraphRequest request = GraphRequest.newMeRequest(
                sharedPreferenceManager.getFBAccessToken(),
                new GraphRequest.GraphJSONObjectCallback() {
                    @Override
                    public void onCompleted(
                            JSONObject object,
                            GraphResponse response) {
                        try {
                            String name = object.getString("name");
                            Toast.makeText(MainActivity.this, "Welcome: " + name, Toast.LENGTH_SHORT).show();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
        Bundle parameters = new Bundle();
        parameters.putString("fields", "id,name,link");
        request.setParameters(parameters);
        request.executeAsync();
    }
}