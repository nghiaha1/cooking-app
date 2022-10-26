package t2010a.cookpad_clone.local_data;

import android.content.Context;

import com.google.gson.Gson;

import t2010a.cookpad_clone.model.LoginResponse;
import t2010a.cookpad_clone.model.user.User;

public class LocalDataManager {
    private static final String PREF_FIRST_INSTALL = "PREF_FIRST_INSTALL";
    private static final String PREF_OBJECT_USER = "PREF_OBJECT_USER";
    private static final String PREF_OBJECT_LOGIN_RESPONSE = "PREF_OBJECT_LOGIN_RESPONSE";
    private static LocalDataManager instance;
    private MySharePreference mySharePreference;

    public static void init(Context context) {
        instance = new LocalDataManager();
        instance.mySharePreference = new MySharePreference(context);
    }

    public static LocalDataManager getInstance() {
        if (instance == null) {
            instance = new LocalDataManager();
        }
        return instance;
    }

    public static void setFirstInstall(boolean isFirstInstall) {
        LocalDataManager.getInstance().mySharePreference.putBooleanValue(PREF_FIRST_INSTALL, isFirstInstall);
    }

    public static boolean getFirstInstall() {
        return LocalDataManager.getInstance().mySharePreference.getBooleanValue(PREF_FIRST_INSTALL);
    }

    public static void setUser(User user) {
        Gson gson = new Gson();
        String strJsonUser = gson.toJson(user);
        LocalDataManager.getInstance().mySharePreference.putStringValue(PREF_OBJECT_USER, strJsonUser);
    }

    public static User getUser() {
        String strJsonUser = LocalDataManager.getInstance().mySharePreference.getStringValue(PREF_OBJECT_USER);
        Gson gson = new Gson();
        User user = gson.fromJson(strJsonUser, User.class);
        return user;
    }

    public static void setLoginResponse(LoginResponse loginResponse) {
        Gson gson = new Gson();
        String strLoginResponse = gson.toJson(loginResponse);
        LocalDataManager.getInstance().mySharePreference.putStringValue(PREF_OBJECT_LOGIN_RESPONSE, strLoginResponse);
    }

    public static LoginResponse getLoginResponse() {
        String strJsonAccessToken = LocalDataManager.getInstance().mySharePreference.getStringValue(PREF_OBJECT_LOGIN_RESPONSE);
        Gson gson = new Gson();
        LoginResponse loginResponse = gson.fromJson(strJsonAccessToken, LoginResponse.class);
        return loginResponse;
    }
}
