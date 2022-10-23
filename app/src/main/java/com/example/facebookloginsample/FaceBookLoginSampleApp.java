package com.example.facebookloginsample;

import android.app.Application;

import com.example.facebookloginsample.dependency.DaggerFaceBookLoginSampleComponent;
import com.example.facebookloginsample.dependency.FaceBookLoginSampleComponent;


public class FaceBookLoginSampleApp extends Application {

    private FaceBookLoginSampleComponent faceBookLoginSampleComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        faceBookLoginSampleComponent = DaggerFaceBookLoginSampleComponent.builder()
                                         .applicationContext(this).build();
    }

    public FaceBookLoginSampleComponent getComponent() {
        return faceBookLoginSampleComponent;
    }
}
