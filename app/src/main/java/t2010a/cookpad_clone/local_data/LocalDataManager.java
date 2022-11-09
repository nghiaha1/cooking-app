package t2010a.cookpad_clone.local_data;

import android.content.Context;

import com.google.gson.Gson;

import t2010a.cookpad_clone.model.LoginResponse;
import t2010a.cookpad_clone.model.client_model.User;

public class LocalDataManager {
    private static final String PREF_LOGIN_RES = "PREF_LOGIN_RES";
    private static final String TOKEN = "TOKEN";
    private static LocalDataManager instance;
    private MySharedPreferences mySharedPreferences;

    public static void init(Context context) {
        instance = new LocalDataManager();
        instance.mySharedPreferences = new MySharedPreferences(context);
    }

    public static LocalDataManager getInstance() {
        if (instance == null) {
            instance = new LocalDataManager();
        }
        return instance;
    }

    public static void setLoginResponse(LoginResponse response) {
        Gson gson = new Gson();
        String strJsonRes = gson.toJson(response);
        LocalDataManager.getInstance().mySharedPreferences.putStringValue(PREF_LOGIN_RES, strJsonRes);
    }

    public static LoginResponse getLoginResponse() {
        String strJsonRes = LocalDataManager.getInstance().mySharedPreferences.getStringValue(PREF_LOGIN_RES);
        Gson gson = new Gson();
        LoginResponse response = gson.fromJson(strJsonRes, LoginResponse.class);
        return response;
    }

    public static void setUserDetail(User user) {
        Gson gson = new Gson();
        String strJsonRes = gson.toJson(user);
        LocalDataManager.getInstance().mySharedPreferences.putStringValue(PREF_LOGIN_RES, strJsonRes);
    }

    public static User getUserDetail() {
        String strJsonRes = LocalDataManager.getInstance().mySharedPreferences.getStringValue(PREF_LOGIN_RES);
        Gson gson = new Gson();
        User user = gson.fromJson(strJsonRes, User.class);
        return user;
    }

    public static void clearData() {
        LocalDataManager.getInstance().mySharedPreferences.delStringValue(PREF_LOGIN_RES);
        LocalDataManager.getInstance().mySharedPreferences.delStringValue(TOKEN);
    }

    public static void setAccessToken(String accessToken) {
        LocalDataManager.getInstance().mySharedPreferences.putStringValue(TOKEN, accessToken);
    }

    public static String getAccessToken() {
        return LocalDataManager.getInstance().mySharedPreferences.getStringValue(TOKEN);
    }
}
