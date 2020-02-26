package com.example.gpsservice;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;

public class GPSMovementService extends AppCompatActivity implements View.OnClickListener {
    public double latitude, last_latitude; //현위치위도
    String latitudeStr,longitudeStr;
    public double longitude, last_longtitude;
    Button button;
    TextView LatitudeText, LongitudeText;
    public String mlocation;
    public ArrayList<String> mlongitudeStrArray;
    public ArrayList<String> mlatitudeStrArray;
    String mLocationStr;
    public boolean running;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gps_movement_service);

        initObject();


        mlongitudeStrArray = new ArrayList<>();
        mlatitudeStrArray = new ArrayList<>();



        getCurrentPosition();




    }


    public void getCurrentPosition() {
        final LocationManager lm = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        if (Build.VERSION.SDK_INT >= 23 &&
                ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION},
                    0);
        } else {

            Location location = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);

            longitude = location.getLongitude();
            latitude = location.getLatitude();



            lm.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                    1000,
                    1,
                    gpsLocationListener);
            lm.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,
                    1000,
                    1,
                    gpsLocationListener);


        }

            LocationStr();

    }

    final LocationListener gpsLocationListener = new LocationListener() {
        public void onLocationChanged(Location location) {

            last_latitude = location.getLongitude();
            last_longtitude = location.getLatitude();
         /*   Double lastlongitude = location.getLongitude();
            String lastlongitudeStr = String.valueOf(lastlongitude);
            Double lastlatitude = location.getLatitude();
            String lastlatitudeStr = String.valueOf(lastlatitude);

            int size1 = mlatitudeStrArray.size();
            String last = mlatitudeStrArray.get(size1-1).toString();
            int size2 = mlongitudeStrArray.size();
            String last2 = mlongitudeStrArray.get(size2-1).toString();

            if(lastlatitudeStr.equals(last) && lastlongitudeStr.equals(last2)){
                lastlatitude = latitude;
                lastlongitude = longitude;
                getCurrentPosition();
            }
            else {
                lastlongitude = null;
                lastlatitude = null;
                getCurrentPosition();
            }*/

           //
        }

        public void onStatusChanged(String provider, int status, Bundle extras) {
        }

        public void onProviderEnabled(String provider) {
        }

        public void onProviderDisabled(String provider) {
        }
    };

    //현재위치 받아와서 Array에 계속 저장 및 String으로 변환
    public void LocationStr(){
        longitudeStr = String.valueOf(longitude);
        latitudeStr = String.valueOf(latitude);

        if(last_latitude == latitude && last_longtitude == longitude){
            longitudeStr = null;
            latitudeStr = null;
        }
        else{
            mlongitudeStrArray.add(longitudeStr);
            mlatitudeStrArray.add(latitudeStr);

        }

        String longitude_list = String.join("#",mlongitudeStrArray);
        String latitude_list = String.join("#",mlatitudeStrArray);

        LongitudeText.setText(longitude_list);
        LatitudeText.setText(latitude_list);

        getCurrentPosition();
    }


    public void initObject(){

        button = (Button) findViewById(R.id.button1);
        button.setOnClickListener(this);
        LongitudeText = (TextView) findViewById(R.id.longitudetext);
        LatitudeText = (TextView)findViewById(R.id.latitudetext);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button1:
              // getCurrentPosition();
                /*String longtitude_list = String.join("#",mlongitudeStrArray);
                String latitude_list = String.join("#",mlatitudeStrArray);
                LongitudeText.setText(longtitude_list);
               LatitudeText.setText(latitude_list);*/
               // running = false;
                break;

        }
    }
}
