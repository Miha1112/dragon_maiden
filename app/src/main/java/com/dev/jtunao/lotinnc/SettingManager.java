package com.dev.jtunao.lotinnc;

import android.content.Context;
import android.content.SharedPreferences;

public class SettingManager {
    private static final String PREFS_NAME = "MyPrefs";
    private static final String SOUND_ENABLED = "sound_enabled";
    private SharedPreferences preferences;

    public SettingManager(Context context){
        preferences = context.getSharedPreferences(PREFS_NAME,Context.MODE_PRIVATE);
    }

    public boolean isSoundEnabled(){
        return preferences.getBoolean(SOUND_ENABLED, true);
    }

    public void setSoundEnabled(boolean isEnabled){
        preferences.edit().putBoolean(SOUND_ENABLED, isEnabled).apply();
    }

}
