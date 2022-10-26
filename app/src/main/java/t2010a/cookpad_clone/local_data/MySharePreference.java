package t2010a.cookpad_clone.local_data;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.List;
import java.util.Vector;

import t2010a.cookpad_clone.model.user.User;

public class MySharePreference {
    private static final String MY_SHARE_PREFERENCE = "MY_SHARE_PREFERENCE";
    private Context mContext;

    public MySharePreference(Context mContext) {
        this.mContext = mContext;
    }

    public void putBooleanValue(String key, boolean v) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(MY_SHARE_PREFERENCE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(key, v);
        editor.apply();
    }

    public boolean getBooleanValue(String key) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(MY_SHARE_PREFERENCE, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean(key, false);
    }

    public void putStringValue(String key, String v) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(MY_SHARE_PREFERENCE, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, v);
        editor.apply();
    }

    public String getStringValue(String key) {
        SharedPreferences sharedPreferences = mContext.getSharedPreferences(MY_SHARE_PREFERENCE, Context.MODE_PRIVATE);
        return sharedPreferences.getString(key, "");
    }


}
