package com.example.facebookloginsample.dependency;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import com.example.facebookloginsample.util.FaceBookLoginSharedPreferenceManager;

import dagger.Module;
import dagger.Provides;
import navigation.ActivityRouter;

@Module
public interface FaceBookLoginSampleModule {



    @Provides
    public static ActivityRouter providesActivityRouter() {
        return new ActivityRouter();
    }

    @Provides
    public static FaceBookLoginSharedPreferenceManager providesSharedPreferenceManager(Context application) {
        return new FaceBookLoginSharedPreferenceManager(application);
    }
}
