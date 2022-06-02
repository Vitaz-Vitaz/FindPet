package com.example.findpet;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import com.example.findpet.ForData.DB;
import com.example.findpet.ForData.Data;
import com.example.findpet.Map.MapsActivity;
import com.example.findpet.Map.NewPoint;
import com.example.findpet.Map.NewPointActivity;
import com.example.findpet.Map.ShowOneOnly;
import com.example.findpet.UI.MyPointAdapter;

import java.util.ArrayList;

public class show extends ListActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_show);
//        ArrayAdapter<String> monthAdapter =
//                new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, Data.description);
//
//
        try{
        MyPointAdapter adapter = new MyPointAdapter(this, makePoint());

        //lv.setAdapter(adapter);
        setListAdapter(adapter);
        }catch (Exception e)
        {
            setListAdapter(null);
        }


    }
    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {


      Data.i = position;
       Intent intent=new Intent(show.this, ShowOneOnly.class);
      startActivity(intent);
    }
    ArrayList<NewPoint> makePoint() {
//        ArrayList<NewPoint> points = new ArrayList<>();
        try {
            DB op = new DB(this);

            ArrayList<NewPoint> p1 = op.findAllPoints();
            return p1;
        }catch (Exception e)
        {
            return null;
        }


//        for (int i = 0; i < Data.x.size(); i++) {
//            NewPoint p = new NewPoint();
//            p.x = Data.x.get(i);
//            p.y = Data.y.get(i);
//            p.description = Data.description.get(i);
//           // p.photo = String.valueOf(Data.photo.get(i));
//            points.add(p);
//        }

    }

}