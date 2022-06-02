package com.example.findpet.Map;

import androidx.fragment.app.FragmentActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.findpet.ForData.DB;
import com.example.findpet.ForData.DB2;
import com.example.findpet.ForData.Data;
import com.example.findpet.R;
import com.example.findpet.UI.ActivityMenu;
import com.example.findpet.databinding.ActivityShowOneOnlyBinding;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;


public class ShowOneOnly extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityShowOneOnlyBinding binding;
    Button b;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityShowOneOnlyBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
      mMap = googleMap;
        b = findViewById(R.id.BackToMenu1);
        b.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               Intent intent=new Intent(ShowOneOnly.this, ActivityMenu.class);

                startActivity(intent);
            }
        });
     if(Data.type == 1)
     {
         DB op = new DB(this);
         ArrayList<NewPoint> p1 = op.findAllPoints();
         int i = (int) Data.i;
         LatLng n = new LatLng(p1.get(i).x, p1.get(i).y);
         mMap.addMarker(new MarkerOptions().position(n));
         mMap.moveCamera(CameraUpdateFactory.newLatLng(n));
     }
     else {
         DB2 op = new DB2(this);
         ArrayList<Point2> p1 = op.findAllPoints();
         int i = (int) Data.i;
         LatLng n = new LatLng(p1.get(i).x, p1.get(i).y);
         mMap.addMarker(new MarkerOptions().position(n));
         mMap.moveCamera(CameraUpdateFactory.newLatLng(n));
     }



    }
}