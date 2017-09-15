package com.smart_app.smartapp.util;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by lenovo on 2017-09-02.
 */

public class CustomSharedPreference {

    public static String PREFERENCE_NAME = "UserInfo";

    public static void putUserProfile(Activity activity,String key,String value){

        SharedPreferences sharedPreferences = activity.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key,value);
        editor.commit();
    }

    public static void putUserProfile(Activity activity,String key,int value){

        SharedPreferences sharedPreferences = activity.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(key,value);
        editor.commit();
    }

    public static String getString(Activity activity,String key){
        SharedPreferences sharedPreferences = activity.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE);
        String val = sharedPreferences.getString(key,null);
        return sharedPreferences.getString(key,null);
    }

    public static void deletePreference(Activity activity) {
        SharedPreferences shared = activity.getSharedPreferences(PREFERENCE_NAME,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor edt = shared.edit().clear();
        edt.commit();
    }

    public static void deletePreferenceByKey(Activity activity, String key ){
        SharedPreferences shared = activity.getSharedPreferences(PREFERENCE_NAME,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = shared.edit();
        editor.remove(key);
        editor.apply();
    }
}
