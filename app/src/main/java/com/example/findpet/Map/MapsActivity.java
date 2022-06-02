package com.example.findpet.Map;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import com.example.findpet.ForData.DB;
import com.example.findpet.ForData.DB2;
import com.example.findpet.ForData.Data;
import com.example.findpet.R;
import com.example.findpet.UI.ActivityMenu;
import com.example.findpet.UI.ShowPoint;
import com.example.findpet.databinding.ActivityMapsBinding;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;

    public Button b;
    private static final int request_Code = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);

    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        try {
            if(ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED
            ){
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, request_Code );
            }
            googleMap.setMyLocationEnabled(true);
        }
        catch (Exception e){}

        mMap = googleMap;

//        MAP_TYPE_NONE – карта не будет отображаться
//
//        MAP_TYPE_NORMAL – обычный режим, в нем карта стартует по умолчанию.
//
//                MAP_TYPE_SATELLITE – снимки со спутника
//
//        MAP_TYPE_TERRAIN – карта рельефа местности
//
//        MAP_TYPE_HYBRID – снимки со спутника + инфа о улицах и транспорте
//
//        Получить текущий тип можно методом getMapType.
       // mMap.setMapType(GoogleMap.MAP_TYPE_SATELLITE);

        b = findViewById(R.id.BackToMenu);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MapsActivity.this, ActivityMenu.class);

                startActivity(intent);
            }
        });
        LatLng msc = new LatLng(55,37);

        mMap.moveCamera(CameraUpdateFactory.newLatLng(msc));


            DB op = new DB(this);
            ArrayList<NewPoint> p1 = op.findAllPoints();
            DB2 op2 = new DB2(this);
            ArrayList<Point2> p2 = op2.findAllPoints();
        if(p2 != null)
        {
            for (int i = 0; i < p2.size(); i++) {
                LatLng n = new LatLng(p2.get(i).x, p2.get(i).y);
                mMap.addMarker(new MarkerOptions().position(n).title(p2.get(i).description));
            }
        }
            if (p1.size() == 0)
            {
                Log.e("points", "0");
            }
            if(p1 != null)
            {
                for (int i = 0; i < p1.size(); i++) {
                    LatLng n = new LatLng(p1.get(i).x, p1.get(i).y);
                    mMap.addMarker(new MarkerOptions().position(n).title(p1.get(i).description));
                }
            }






        mMap.setOnMapLongClickListener(new GoogleMap.OnMapLongClickListener() {
            @Override
            public void onMapLongClick(@NonNull LatLng latLng) {
                Intent intent=new Intent(MapsActivity.this, NewPointActivity.class);
                Data.dataX = latLng.latitude;
                Data.dataY = latLng.longitude;

                startActivity(intent);

            }

        });
        mMap.setOnInfoWindowClickListener(new GoogleMap.OnInfoWindowClickListener()
        {

            @Override
            public void onInfoWindowClick(Marker arg0) {
                Data.dataXForShow = arg0.getPosition().latitude;
                Data.dataYForShow = arg0.getPosition().longitude;

                Intent intent=new Intent(MapsActivity.this, ShowPoint.class);
                startActivity(intent);
            }
        });


    }
    public void n(String text)
    {
        Toast.makeText(this,
                text,
                Toast.LENGTH_SHORT).show();
    }
}

