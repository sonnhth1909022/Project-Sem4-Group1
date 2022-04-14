package com.movieapp.movienavigation.event;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SaveAccountType {
    static final String ID = "";

    static SharedPreferences getSharedPreferences(Context ctx) {
        return PreferenceManager.getDefaultSharedPreferences(ctx);
    }

    public static void setAccountType(Context ctx, String value) {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(ID, value);
        editor.commit();
    }

    public static String getAccountType(Context ctx) {
        return getSharedPreferences(ctx).getString(ID,"0");
    }
}
