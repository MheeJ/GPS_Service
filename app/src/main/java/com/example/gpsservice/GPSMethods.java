package com.example.gpsservice;

import android.content.Context;
import android.content.Intent;
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

public class GPSMethods extends AppCompatActivity implements View.OnClickListener {
    public double My_Latitude_Dou; //현위치위도
    public double My_Longitude;
    String My_latitude_Str,My_longitude_Str;
    Button My_Location;
    TextView My_Latitude_Text, My_Longtitude_Text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gps_method);

        initObject();


    }





    public void initObject(){

        My_Location = (Button) findViewById(R.id.my_location);
        My_Location.setOnClickListener(this);
        My_Longtitude_Text = (TextView) findViewById(R.id.longitudetext);
        My_Latitude_Text = (TextView)findViewById(R.id.latitudetext);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode,resultCode,data);
        switch(requestCode){
            case 1:
                if(resultCode==RESULT_OK){//서브Activity에서 보내온 resultCode와 비교
                    //서브액티비티에서 인텐트에 담아온 정보 꺼내기
                    String log=data.getStringExtra("Now_Longitude");
                    String lat = data.getStringExtra("Now_Latitude");
                    //텍스트뷰에 수정된 문자열 넣기
                    My_Longtitude_Text.setText(log);
                    My_Latitude_Text.setText(lat);
                }else{

                }
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.my_location:

                break;

        }
    }
}

