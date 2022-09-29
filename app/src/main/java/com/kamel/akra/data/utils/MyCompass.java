package com.kamel.akra.data.utils;
import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;

public class MyCompass implements SensorEventListener {
    public interface CompassListener {
        void onNewAzimuth(float azimuth);
    }

    // Mecca position
    private final double MECCA_LONGITUDE = 39.826206;
    private final double MECCA_LATITUDE = Math.toRadians(21.422487);

    private CompassListener listener;

    private final SensorManager sensorManager;
    private final Sensor gsensor;
    private final Sensor msensor;

    private final float[] mGravity = new float[3];
    private final float[] mGeomagnetic = new float[3];
    private float[] R = new float[9];
    private float[] I = new float[9];

    private float azimuth;
    private float azimuthFix;

    public MyCompass(Context context) {
        sensorManager = (SensorManager) context
                .getSystemService(Context.SENSOR_SERVICE);
        gsensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        msensor = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
    }

    public void start() {
        sensorManager.registerListener(this, gsensor,
                SensorManager.SENSOR_DELAY_GAME);
        sensorManager.registerListener(this, msensor,
                SensorManager.SENSOR_DELAY_GAME);
    }

    public void stop() {
        sensorManager.unregisterListener(this);
    }

    public void setAzimuthFix(float fix) {
        azimuthFix = fix;
    }

    public void resetAzimuthFix() {
        setAzimuthFix(0);
    }

    public void setListener(CompassListener l) {
        listener = l;
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        final float alpha = 0.97f;

        synchronized (this) {
            if (event.sensor.getType() == Sensor.TYPE_ACCELEROMETER) {

                mGravity[0] = alpha * mGravity[0] + (1 - alpha)
                        * event.values[0];
                mGravity[1] = alpha * mGravity[1] + (1 - alpha)
                        * event.values[1];
                mGravity[2] = alpha * mGravity[2] + (1 - alpha)
                        * event.values[2];
            }

            if (event.sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD) {
                mGeomagnetic[0] = alpha * mGeomagnetic[0] + (1 - alpha)
                        * event.values[0];
                mGeomagnetic[1] = alpha * mGeomagnetic[1] + (1 - alpha)
                        * event.values[1];
                mGeomagnetic[2] = alpha * mGeomagnetic[2] + (1 - alpha)
                        * event.values[2];
            }

            boolean success = SensorManager.getRotationMatrix(R, I, mGravity,
                    mGeomagnetic);

            if (success) {
                float[] orientation = new float[3];
                SensorManager.getOrientation(R, orientation);
                azimuth = (float) Math.toDegrees(orientation[0]); // orientation
                azimuth = (azimuth + azimuthFix + 360) % 360;

                if (listener != null)
                    listener.onNewAzimuth(azimuth);
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) {
    }

    public float getQiblaDirection(Location location){
        double currentLong = location.getLongitude();
        double currentLat = location.getLatitude();
        double Lat = Math.toRadians(currentLat);
        double longDiff = Math.toRadians(MECCA_LONGITUDE - currentLong);
        double y = Math.sin(longDiff) * Math.cos(MECCA_LATITUDE);
        double x = Math.cos(Lat) * Math.sin(MECCA_LATITUDE) - Math.sin(Lat) * Math.cos(MECCA_LATITUDE) * Math.cos(longDiff);
        double result = (Math.toDegrees(Math.atan2(y, x)) + 360) % 360;
        return (float) result;
    }
}
