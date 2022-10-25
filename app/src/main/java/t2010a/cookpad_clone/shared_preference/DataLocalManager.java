package t2010a.cookpad_clone.shared_preference;

import android.content.Context;

public class DataLocalManager {
    private static final String PREFERENCE_FIRST_INSTALL = "PREFERENCE_FIRST_INSTALL";
    private static DataLocalManager instance;
    private MySharePreference mySharePreference;

    public static void init(Context context) {
        instance = new DataLocalManager();
        instance.mySharePreference = new MySharePreference(context);
    }

    public static DataLocalManager getInstance() {
        if (instance == null) {
            instance = new DataLocalManager();
        }
        return instance;
    }

    public static void setFirstInstall(boolean isFirstInstall) {
        DataLocalManager.getInstance().mySharePreference.putBooleanValue(PREFERENCE_FIRST_INSTALL, isFirstInstall);
    }

    public static boolean getFirstInstall() {
        return DataLocalManager.getInstance().mySharePreference.getBooleanValue(PREFERENCE_FIRST_INSTALL);
    }
}
