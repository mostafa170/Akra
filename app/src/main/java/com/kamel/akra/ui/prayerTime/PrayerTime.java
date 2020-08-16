package com.kamel.akra.ui.prayerTime;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;

import android.Manifest;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.kamel.akra.R;
import com.kamel.akra.base.BaseActivity;
import com.kamel.akra.databinding.ActivityPrayerTimeBinding;

import com.kamel.akra.network.PrayerTimeAPI;
import com.kamel.akra.ui.Azkar.SwitchActivity;
import com.kamel.akra.ui.home.MainActivity;
import com.kamel.akra.ui.prayerTime.prayerModel.PrayerTimeResponse;
import com.kamel.akra.ui.qablaWSebha.qabla.MyLocationProvider;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PrayerTime extends BaseActivity implements LocationListener {

    ActivityPrayerTimeBinding binding;
    private static final int MY_PERMISSIONS_REQUEST_LOCATION_CODE = 500;
    MyLocationProvider myLocationProvider;
    Location currentLocation;
    double latitude,longitude;
    DateFormat df2;
    String date2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_prayer_time);
        getSupportActionBar().hide();
        DateFormat df = new SimpleDateFormat("EEE, yyyy-MM-dd");
        df2 = new SimpleDateFormat("yyyy-MM-dd");
        String date = df.format(Calendar.getInstance().getTime());
        date2 = df2.format(Calendar.getInstance().getTime());
        Log.e("date", "onCreate: "+date +"_______"+date2);

        binding.textViewDayTime.setText(date);
        myLocationProvider=new MyLocationProvider(activity);

        binding.activityRadioBack.setOnClickListener(view -> {
            Intent intent=new Intent(PrayerTime.this, MainActivity.class);
            startActivity(intent);
        });


        if (isLocationPermessionGranted()){

            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(activity);
            alertDialogBuilder.setTitle("رسالة تأكيد");
            alertDialogBuilder.setMessage("من فضلك قم بتشغيل ال GPS الخاص بالهاتف");
            alertDialogBuilder.setCancelable(true);

            alertDialogBuilder.setPositiveButton("حسنا", new DialogInterface.OnClickListener() {

                @Override
                public void onClick(DialogInterface dialog, int arg1) {
                    startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                    dialog.cancel();
                    Log.e("log","you  are here");

                }
            });
            alertDialogBuilder.setNegativeButton("لا", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();

                }
            });

            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();



            if (myLocationProvider.isGpsEnabled()){
                Log.e("stateDilog" , "enabeld");
                alertDialog.cancel();
            }else {
                Log.e("stateDilog" , "desibled");
                alertDialog.show();
            }



            //call function
            try {
                //Toast.makeText(this, "opened", Toast.LENGTH_SHORT).show();


                currentLocation=myLocationProvider.getUserLocation(this);





                Geocoder geocoder;
                List<Address> addresses = new ArrayList();


                try {

                    latitude = currentLocation.getLatitude();
                    longitude = currentLocation.getLongitude();
                    getPrayerTime(String.valueOf(longitude), String.valueOf(latitude), date2);
                    Locale mLocale = new Locale("ar");
                    Locale.setDefault(mLocale);
                    geocoder = new Geocoder(activity, Locale.getDefault());
                    addresses = geocoder.getFromLocation(currentLocation.getLatitude(), currentLocation.getLongitude(), 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
                } catch (IOException e) {
                    e.printStackTrace();
                }

                String area =  addresses.get(0).getAdminArea();
                String country = addresses.get(0).getCountryName();
                if (country == null || area == null){

                    binding.cityGPS.setText("غير متوفر");
                }
                else {
                    binding.cityGPS.setText(country+" - "+area);
                    //UserLocation.setText(session.getLocationCode());
                }
            }catch (Exception e) {
                //Toast.makeText(this, "wrong", Toast.LENGTH_SHORT).show();
            }

        }else {
            requestLocationPersmission();
        }
    }


    private void requestLocationPersmission() {
        // Permission is not granted
        // Should we show an explanation?
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity,
                Manifest.permission.ACCESS_FINE_LOCATION)) {
            // Show an explanation to the user *asynchronously* -- don't block
            // this thread waiting for the user's response! After the user
            // sees the explanation, try again to request the permission.+
            showConfirmationMessage(R.string.warning,
                    R.string.message_request_location_permission,
                    R.string.ok, new MaterialDialog.SingleButtonCallback() {
                        @Override
                        public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                            ActivityCompat.requestPermissions(activity,
                                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                                    MY_PERMISSIONS_REQUEST_LOCATION_CODE);
                        }
                    });

        } else {
            // No explanation needed; request the permission
            ActivityCompat.requestPermissions(activity,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION},
                    MY_PERMISSIONS_REQUEST_LOCATION_CODE);

        }
    }

    public boolean isLocationPermessionGranted(){
        if (ContextCompat.checkSelfPermission(activity,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted
            return false;
        }
        return true;
    }

    @Override
    public void onResume() {
        super.onResume();

        try {
            if(isLocationPermessionGranted()){
                //call function
                if (myLocationProvider.isGpsEnabled()) {
                    AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(activity);
                    alertDialogBuilder.setTitle("رسالة تأكيد");
                    alertDialogBuilder.setMessage("من فضلك قم بتشغيل ال GPS الخاص بالهاتف");
                    alertDialogBuilder.setCancelable(true);

                    alertDialogBuilder.setPositiveButton("حسنا", new DialogInterface.OnClickListener() {

                        @Override
                        public void onClick(DialogInterface dialog, int arg1) {
                            startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                            dialog.cancel();
                            Log.e("log", "you  are here");

                        }
                    });
                    alertDialogBuilder.setNegativeButton("لا", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();

                        }
                    });

                    AlertDialog alertDialog = alertDialogBuilder.create();
                    alertDialog.show();


                    if (myLocationProvider.isGpsEnabled()) {
                        Log.e("stateDilog", "enabeld");
                        alertDialog.cancel();
                    } else {
                        Log.e("stateDilog", "desibled");
                        alertDialog.show();
                    }
                    myLocationProvider = new MyLocationProvider(activity);
                    currentLocation = myLocationProvider.getUserLocation(this);



                    Geocoder geocoder;
                    List<Address> addresses = new ArrayList();


                    try {
                        latitude = currentLocation.getLatitude();
                        longitude = currentLocation.getLongitude();
                        getPrayerTime(String.valueOf(longitude), String.valueOf(latitude), date2);

                        Locale mLocale = new Locale("ar");
                        Locale.setDefault(mLocale);
                        geocoder = new Geocoder(activity, Locale.getDefault());
                        addresses = geocoder.getFromLocation(currentLocation.getLatitude(), currentLocation.getLongitude(), 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    String area = addresses.get(0).getAdminArea();
                    String country = addresses.get(0).getCountryName();
                    if (country == null || area == null) {

                        binding.cityGPS.setText("غير متوفر");
                    } else {
                        binding.cityGPS.setText(country + " - " + area);

                    }

                }else {
                    binding.cityGPS.setText("غير متوفر");

                }

            }else {
                requestLocationPersmission();
            }


        }catch (Exception e){
            Log.e("ResumeLocPermissions","isLocationPermissionsNotGranted");
        }

    }



    public void getPrayerTime(String longitude , String latitude  ,String date ){
        PrayerTimeAPI.getApis()
                .getPrayerTime(longitude,latitude,"333",date,"1").enqueue(new Callback<PrayerTimeResponse>() {
            @Override
            public void onResponse(Call<PrayerTimeResponse> call, Response<PrayerTimeResponse> response) {
                if (response.isSuccessful()){
                    binding.tvPrayerFajrName.setText(response.body().getResults().getDatetime().get(0).getTimes().getFajr());
                    binding.tvPrayerSunriseName.setText(response.body().getResults().getDatetime().get(0).getTimes().getSunrise());
                    binding.tvPrayerDhuhrName.setText(response.body().getResults().getDatetime().get(0).getTimes().getDhuhr());
                    binding.tvPrayerAsrName.setText(response.body().getResults().getDatetime().get(0).getTimes().getAsr());
                    binding.tvPrayerMaghribName.setText(response.body().getResults().getDatetime().get(0).getTimes().getMaghrib());
                    binding.tvPrayerIshaName.setText(response.body().getResults().getDatetime().get(0).getTimes().getIsha());

                }else {
                    showMessage(R.string.error,R.string.cannot_connect_to_server);
                }
            }

            @Override
            public void onFailure(Call<PrayerTimeResponse> call, Throwable t) {
                showMessage(getString(R.string.error),t.getLocalizedMessage());

            }
        });


    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_LOCATION_CODE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay! Do the

                    try {
                        if (isLocationPermessionGranted()) {
                            //call function
                            if (myLocationProvider.isGpsEnabled()) {
                                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(activity);
                                alertDialogBuilder.setTitle("رسالة تأكيد");
                                alertDialogBuilder.setMessage("من فضلك قم بتشغيل ال GPS الخاص بالهاتف");
                                alertDialogBuilder.setCancelable(true);

                                alertDialogBuilder.setPositiveButton("حسنا", new DialogInterface.OnClickListener() {

                                    @Override
                                    public void onClick(DialogInterface dialog, int arg1) {
                                        startActivity(new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS));
                                        dialog.cancel();
                                        Log.e("log", "you  are here");

                                    }
                                });
                                alertDialogBuilder.setNegativeButton("لا", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.cancel();

                                    }
                                });

                                AlertDialog alertDialog = alertDialogBuilder.create();
                                alertDialog.show();


                                if (myLocationProvider.isGpsEnabled()) {
                                    Log.e("stateDilog", "enabeld");
                                    alertDialog.cancel();
                                } else {
                                    Log.e("stateDilog", "desibled");
                                    alertDialog.show();
                                }
                                myLocationProvider = new MyLocationProvider(activity);
                                currentLocation = myLocationProvider.getUserLocation(this);
                                latitude = currentLocation.getLatitude();
                                longitude = currentLocation.getLongitude();
                                getPrayerTime(String.valueOf(longitude), String.valueOf(latitude), date2);


                                Geocoder geocoder;
                                List<Address> addresses = new ArrayList();


                                try {

                                    Locale mLocale = new Locale("ar");
                                    Locale.setDefault(mLocale);
                                    geocoder = new Geocoder(activity, Locale.getDefault());
                                    addresses = geocoder.getFromLocation(currentLocation.getLatitude(), currentLocation.getLongitude(), 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }

                                String area = addresses.get(0).getAdminArea();
                                String country = addresses.get(0).getCountryName();
                                if (country == null || area == null) {

                                    binding.cityGPS.setText("غير متوفر");
                                } else {
                                    binding.cityGPS.setText(country + " - " + area);

                                }

                            } else {
                                binding.cityGPS.setText("غير متوفر");

                            }

                        } else {
                            requestLocationPersmission();
                        }


                    } catch (Exception e) {
                        Log.e("ResumeLocPermissions", "isLocationPermissionsNotGranted");
                    }
                }
                return;
            }
            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        currentLocation = location;
        latitude = currentLocation.getLatitude();
        longitude = currentLocation.getLongitude();


        Geocoder geocoder;
        List<Address> addresses = new ArrayList();


        try {
            Locale mLocale = new Locale("ar");
            Locale.setDefault(mLocale);
            geocoder = new Geocoder(activity, Locale.getDefault());
            addresses = geocoder.getFromLocation(currentLocation.getLatitude(), currentLocation.getLongitude(), 1); // Here 1 represent max location result to returned, by documents it recommended 1 to 5
        } catch (IOException e) {
            e.printStackTrace();
        }

        String area =  addresses.get(0).getAdminArea();
        String country = addresses.get(0).getCountryName();
        if (country == null || area == null){

            binding.cityGPS.setText("غير متوفر");
        }
        else {
            binding.cityGPS.setText(country+" - "+area);
            //UserLocation.setText(session.getLocationCode());
        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }
}