package mkharsa.com.weatherformation;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by mkharsa on 08/06/2016.
 */
public class MyApplication extends Application {
    private RequestQueue mVolleyRequestQueue;

    @Override
    public void onCreate() {
        super.onCreate();

        // On initialise notre Thread-Pool et notre ImageLoader
        mVolleyRequestQueue = Volley.newRequestQueue(getApplicationContext());
        mVolleyRequestQueue.start();
    }

    public RequestQueue getVolleyRequestQueue() {
        return mVolleyRequestQueue;
    }


    @Override
    public void onTerminate() {
        mVolleyRequestQueue.stop();
        super.onTerminate();
    }

}
