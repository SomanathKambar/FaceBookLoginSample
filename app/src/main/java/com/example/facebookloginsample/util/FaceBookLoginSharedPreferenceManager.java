package com.example.facebookloginsample.util;

import android.content.Context;
import android.content.SharedPreferences;

public class FaceBookLoginSharedPreferenceManager {

    private static SharedPreferences sharedPreferences;
    private static final String PREFERENCES_FILE = "preferences_file";
    private static final String USER_LOGGED_IN = "user_logged_in";
    private static final String lock = "lock";



    public FaceBookLoginSharedPreferenceManager(Context context) {
        sharedPreferences = context.getSharedPreferences(PREFERENCES_FILE, Context.MODE_PRIVATE);
    }

    public boolean isUerLoggedIn() {
        return sharedPreferences.getBoolean(USER_LOGGED_IN, false);
    }

    public void setUserLoggedIn(boolean isLoggedIn) {
        sharedPreferences.edit().putBoolean(USER_LOGGED_IN, isLoggedIn).apply();
    }
}
