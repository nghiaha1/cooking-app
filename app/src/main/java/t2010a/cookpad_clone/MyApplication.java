package t2010a.cookpad_clone;

import android.app.Application;

import t2010a.cookpad_clone.shared_preference.DataLocalManager;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        DataLocalManager.init(getApplicationContext());
    }
}
