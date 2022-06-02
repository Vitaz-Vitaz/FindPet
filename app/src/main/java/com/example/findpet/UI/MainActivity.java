package com.example.findpet.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.CursorWindow;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.example.findpet.ForData.DB;
import com.example.findpet.ForData.DB2;
import com.example.findpet.ForData.Data;
import com.example.findpet.ForData.Utilities;
import com.example.findpet.Map.NewPoint;
import com.example.findpet.Map.NewPointActivity;
import com.example.findpet.Map.Point2;
import com.example.findpet.R;
import com.example.findpet.UI.ActivityMenu;
import com.example.findpet.rest.LibraryApiVolley;

import java.lang.reflect.Field;
import android.database.CursorWindow;
import java.lang.reflect.Field;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private PointRecyclerAdapter pointAdapter;
    private RecyclerView rvBook;


    LibraryApiVolley libraryApiVolley;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("=====", "==========================================");
        libraryApiVolley = new LibraryApiVolley(this);
        DB op = new DB(this);
        Drawable s = getResources().getDrawable(R.drawable.nophoto);










        //end

        Log.e("hbjcec11", "ihbi");
        byte[] a = Utilities.getBytes(((BitmapDrawable)s).getBitmap());


        AnotherThread anotherThread=new AnotherThread();
        anotherThread.start();


        setContentView(R.layout.activity_main);


        NewPoint p = new NewPoint("пропала черная кошка, метро Aлексеевская, отзывается на кличку анифса", a, 55.8,37.6, "Анфиса","черно-белая","89255697747", "Я потерял(а)");



    //   op.deleteAll();

        if (Data.k == 0)
        {
            Utilities.n(this, "Включите пожалуйста геоданные в настройках");
            Data.k++;
        }




        op.insert(p);
        Intent intent=new Intent(this, ActivityMenu.class);

        startActivity(intent);

    }
    class AnotherThread extends Thread {
        @Override
        public void run() {

            try{




                libraryApiVolley.fillPoint();


            } catch(Exception e) {}

        }

    }

}