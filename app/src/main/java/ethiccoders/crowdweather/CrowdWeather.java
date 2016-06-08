package ethiccoders.crowdweather;

import com.firebase.client.Firebase;

/**
 * Created by Santhosh on 3/30/2016.
 */
public class CrowdWeather extends android.app.Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Firebase.setAndroidContext(this);

    }
}
