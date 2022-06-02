package com.example.findpet.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

import com.example.findpet.ForData.DB;
import com.example.findpet.ForData.Data;
import com.example.findpet.ForData.Utilities;
import com.example.findpet.Map.NewPoint;
import com.example.findpet.R;

import java.util.ArrayList;

public class RecycleShow extends AppCompatActivity {
    private RecyclerView rvPoint;


    private PointRecyclerAdapter pointAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycle_view);
        getSupportActionBar().hide();
        Spinner spinner = findViewById(R.id.spinnerRecycle);

        Button butRec = findViewById(R.id.findPointsByStatus);
        butRec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String status = spinner.getSelectedItem().toString();
                //Utilities.n(RecycleShow.this, status);
                 if(status.equals("Найденные")){
                    Data.status = "Я нашёл(а)";
                }
                else if(status.equals("Потерянные"))
                {
                    Data.status = "Я потерял(а)";
                }

                else
                {
                    Data.status = "Все";
                }

                pointAdapter.notifyDataSetChanged();
            }
        });
        DB op = new DB(this);
        ArrayList<NewPoint> points = op.findAllPoints();
//        if(points.size() > 0)
//        {
//            Utilities.n(this, "Ok");
//        }


        rvPoint = findViewById(R.id.rv_points);
        //Log.e("j1", "OK1");

        try {
            pointAdapter = new PointRecyclerAdapter(this);
            rvPoint.setAdapter(pointAdapter);
        }catch (Exception e)
        {
            rvPoint.setAdapter(null);
        }

       // Log.e("j2", "OK2");

    }
    public void updateAdapter()
    {
        pointAdapter.notifyDataSetChanged();
    }
}