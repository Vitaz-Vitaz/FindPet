package com.example.findpet.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

import com.example.findpet.ForData.DB;
import com.example.findpet.ForData.DB2;
import com.example.findpet.ForData.Data;
import com.example.findpet.Map.NewPoint;
import com.example.findpet.Map.Point2;
import com.example.findpet.R;

import java.util.ArrayList;

public class RecycleShow2 extends AppCompatActivity {
    private RecyclerView rvPoint;


    private PointRecyclerAdapter2 pointAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_show2);

        getSupportActionBar().hide();
        Spinner spinner = findViewById(R.id.spinnerRecycle2);

        Button butRec = findViewById(R.id.findPointsByStatus2);
        butRec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String status = spinner.getSelectedItem().toString();
                //Utilities.n(RecycleShow.this, status);
                if(status.equals("Найденные")){
                    Data.status2 = "Я нашёл(а)";
                }
                else if(status.equals("Потерянные"))
                {
                    Data.status2 = "Я потерял(а)";
                }

                else
                {
                    Data.status2 = "Все";
                }

                pointAdapter.notifyDataSetChanged();
            }


        });




        rvPoint = findViewById(R.id.rv_points2);



        try {
            pointAdapter = new PointRecyclerAdapter2(this);
            rvPoint.setAdapter(pointAdapter);
        }catch (Exception e)
        {
            rvPoint.setAdapter(null);
        }

    }
}