package net.tayutaedomo.applocation;

import android.app.Activity;
import android.location.*;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import butterknife.ButterKnife;
import butterknife.InjectView;

public class MyActivity extends Activity implements LocationListener {

    private final String LOG_TAG = "applocation";

    private LocationManager mLocationManager;

    @InjectView(R.id.textView)
    TextView textView;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        ButterKnife.inject(this);

        // Refer: http://www.adakoda.com/android/000125.html
        mLocationManager = (LocationManager) getSystemService(LOCATION_SERVICE);

        //logLocation();
    }

    private void logLocation() {
        // Refer: http://techbooster.org/android/device/11340/

        // Criteriaオブジェクトを生成
        Criteria criteria = new Criteria();

        // Accuracyを指定(低精度)
        criteria.setAccuracy(Criteria.ACCURACY_COARSE);

        // PowerRequirementを指定(低消費電力)
        criteria.setPowerRequirement(Criteria.POWER_LOW);

        // ロケーションプロバイダの取得
        String provider = mLocationManager.getBestProvider(criteria, true);

        // 取得したロケーションプロバイダを表示
        textView.setText("Provider: " + provider);
    }

    @Override
    protected void onResume() {
        Log.i(LOG_TAG, "onResume");

        if (mLocationManager != null) {
            mLocationManager.requestLocationUpdates(
                //LocationManager.GPS_PROVIDER,
                LocationManager.NETWORK_PROVIDER,
                0,
                0,
                this);
        }

        super.onResume();
    }

    @Override
    protected void onPause() {
        if (mLocationManager != null) {
            mLocationManager.removeUpdates(this);
        }

        super.onPause();
    }

    @Override
    public void onLocationChanged(Location location) {
        Log.i("----------", "----------");
        Log.i("Latitude", String.valueOf(location.getLatitude()));
        Log.i("Longitude", String.valueOf(location.getLongitude()));
        Log.i("Accuracy", String.valueOf(location.getAccuracy()));
        Log.i("Altitude", String.valueOf(location.getAltitude()));
        Log.i("Time", String.valueOf(location.getTime()));
        Log.i("Speed", String.valueOf(location.getSpeed()));
        Log.i("Bearing", String.valueOf(location.getBearing()));
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
        Log.i("Provider", provider);
        switch (status) {
            case LocationProvider.AVAILABLE:
                Log.i("Status", "AVAILABLE");
                break;
            case LocationProvider.OUT_OF_SERVICE:
                Log.i("Status", "OUT_OF_SERVICE");
                break;
            case LocationProvider.TEMPORARILY_UNAVAILABLE:
                Log.i("Status", "TEMPORARILY_UNAVAILABLE");
                break;
        }
    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}
