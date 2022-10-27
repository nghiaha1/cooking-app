package t2010a.cookpad_clone;

import android.app.Application;

import t2010a.cookpad_clone.local_data.LocalDataManager;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        LocalDataManager.init(getApplicationContext());
    }
}
