package com.example.findpet.UI;

import static com.example.findpet.ForData.Data.dataXForShow;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.findpet.ForData.DB;
import com.example.findpet.ForData.DB2;
import com.example.findpet.ForData.Data;
import com.example.findpet.ForData.Utilities;
import com.example.findpet.Map.NewPoint;
import com.example.findpet.Map.Point2;
import com.example.findpet.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ShowPoint extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_point);
        getSupportActionBar().hide();
        DB op = new DB(ShowPoint.this);
        ArrayList<NewPoint> p = op.findAllPoints();

        DB2 op2 = new DB2(ShowPoint.this);
        ArrayList<Point2> p2 = op2.findAllPoints();
        int i1 = 0;

        for (int i = 0; i < p2.size(); i++) {
            if(p2.get(i).x == dataXForShow && p2.get(i).y == Data.dataYForShow )
            {

                Point2 point = p2.get(i);
                TextView t = findViewById(R.id.showDescription);
                TextView t2 = findViewById(R.id.showPetName);
                TextView t3 = findViewById(R.id.showPetColor);
                TextView t4 = findViewById(R.id.showPetContact);
                TextView t5 = findViewById(R.id.showPetStatus);

                String u = point.description;

                t.setText(u);
                t2.setText(point.petName);
                t3.setText(point.petColor);

                t4.setText(point.petContact);

                if(point.status.equals("Я потерял(а)"))
                {
                    t5.setText("Потерян");
                }
                else
                {
                    t5.setText("Найден");
                }
                ImageView im = findViewById(R.id.showImage);
                Picasso.with(this).load(point.photo).into(im);

                    break;
            }
        }
        for (int i = 0; i < p.size(); i++) {
            if(p.get(i).x == dataXForShow && p.get(i).y == Data.dataYForShow )
            {

                i1=i;
                NewPoint point = p.get(i1);
                TextView t = findViewById(R.id.showDescription);
                TextView t2 = findViewById(R.id.showPetName);
                TextView t3 = findViewById(R.id.showPetColor);
                TextView t4 = findViewById(R.id.showPetContact);
                TextView t5 = findViewById(R.id.showPetStatus);

                String u = p.get(i1).description;


                t.setText(u);
                t2.setText(point.petName);
                t3.setText(point.petColor);

                t4.setText(point.petContact);

                if(point.status.equals("Я потерял(а)"))
                {
                    t5.setText("Потерян");
                }
                else
                {
                    t5.setText("Найден");
                }

                ImageView im = findViewById(R.id.showImage);
                try {
                    im.setImageBitmap(BitmapFactory.
                            decodeByteArray(p.get(i1).photo, 0,p.get(i1).photo.length));
                }catch (Exception e){
                    Bitmap b= BitmapFactory.decodeFile("drawable/nophoto.jpg");
                    im.setImageBitmap(b);
                }


            }

        }








    }
}