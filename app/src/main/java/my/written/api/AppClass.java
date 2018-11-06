package my.written.api;

import android.app.Application;
import android.content.Context;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by NIK on 30-10-2018.
 */

public class AppClass extends Application {
    public static RequestQueue volleyQueue;
    public static Context applicationContext;


    @Override
    public void onCreate() {
        super.onCreate();
        applicationContext = getApplicationContext();
        volleyQueue = Volley.newRequestQueue(this);

    }
    public static Context getAppContext() {
        return AppClass.applicationContext;
    }
}
