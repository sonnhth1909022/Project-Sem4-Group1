package com.movieapp.movienavigation.event;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class SaveID {
    static final String ID = "ID";

    static SharedPreferences getSharedPreferences(Context ctx) {
        return PreferenceManager.getDefaultSharedPreferences(ctx);
    }

    public static void setId(Context ctx, int value) {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putInt(ID, value);
        editor.commit();
    }

    public static int getId(Context ctx) {
        return getSharedPreferences(ctx).getInt(ID, 0);
    }

}
