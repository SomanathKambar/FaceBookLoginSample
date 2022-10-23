package com.example.facebookloginsample.util;

import android.content.Context;
import android.content.SharedPreferences;

import com.facebook.AccessToken;
import com.google.gson.Gson;

public class FaceBookLoginSharedPreferenceManager {

    private static SharedPreferences sharedPreferences;
    private static final String PREFERENCES_FILE = "preferences_file";
    private static final String USER_LOGGED_IN = "user_logged_in";
    private static final String FB_ACCESS_TOKEN = "fb_access_token";

    private Gson gson =  new Gson();

    public FaceBookLoginSharedPreferenceManager(Context context) {
        sharedPreferences = context.getSharedPreferences(PREFERENCES_FILE, Context.MODE_PRIVATE);
    }

    public boolean isUerLoggedIn() {
        return sharedPreferences.getBoolean(USER_LOGGED_IN, false);
    }

    public void setUserLoggedIn(boolean isLoggedIn) {
        sharedPreferences.edit().putBoolean(USER_LOGGED_IN, isLoggedIn).apply();
    }

    public void setFBAccessToken(AccessToken accessToken) {
        sharedPreferences.edit().putString(FB_ACCESS_TOKEN, gson.toJson(accessToken,
                AccessToken.class).toString()).apply();
    }

    public AccessToken getFBAccessToken() {
       return gson.fromJson(sharedPreferences.getString(FB_ACCESS_TOKEN, "Token Empty"), AccessToken.class);
    }
}
