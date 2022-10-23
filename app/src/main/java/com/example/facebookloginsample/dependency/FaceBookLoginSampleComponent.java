package com.example.facebookloginsample.dependency;

import android.content.Context;

import com.example.facebookloginsample.MainActivity;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;

@Singleton
@Component(modules = FaceBookLoginSampleModule.class)
public abstract class FaceBookLoginSampleComponent {

    public abstract void inject(MainActivity activity);

    @Component.Builder
    public  interface Builder {
        public FaceBookLoginSampleComponent build();
        @BindsInstance public Builder applicationContext(Context applicationContext);
    }

}
