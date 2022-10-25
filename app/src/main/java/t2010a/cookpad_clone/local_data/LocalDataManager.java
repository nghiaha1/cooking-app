package t2010a.cookpad_clone.local_data;

import android.content.Context;

import java.util.List;
import java.util.Vector;

import t2010a.cookpad_clone.model.user.User;

public class LocalDataManager {
    private static final String PREFERENCE_FIRST_INSTALL = "PREFERENCE_FIRST_INSTALL";
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
        LocalDataManager.getInstance().mySharePreference.putBooleanValue(PREFERENCE_FIRST_INSTALL, isFirstInstall);
    }

    public static boolean getFirstInstall() {
        return LocalDataManager.getInstance().mySharePreference.getBooleanValue(PREFERENCE_FIRST_INSTALL);
    }
}
