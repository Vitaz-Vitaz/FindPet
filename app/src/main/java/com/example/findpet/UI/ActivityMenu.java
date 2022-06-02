package com.example.findpet.UI;

import android.content.Intent;
import android.database.CursorWindow;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.toolbox.ImageLoader;
import com.example.findpet.ForData.DB;
import com.example.findpet.ForData.DB2;
import com.example.findpet.ForData.Utilities;
import com.example.findpet.Map.MapsActivity;
import com.example.findpet.Map.NewPoint;
import com.example.findpet.Map.Point2;
import com.example.findpet.R;
import com.example.findpet.show;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.lang.reflect.Field;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class ActivityMenu extends AppCompatActivity {
    Button btn1;
    Button btn2;
    private PointRecyclerAdapter pointAdapter;
    private RecyclerView rvBook;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.menu);
        DB2 op2 = new DB2(this);
        op2.deleteAll();
        getSupportActionBar().hide();
        ImageView im1 = findViewById(R.id.fon007);
        im1.setImageResource(R.drawable.fon007);
        String url1 = "https://foundpets.ru/v2/cloud/images/0:0:jpeg/2022/5/24/9/165332381057_1653384271_main-image.jpg.x0.jpeg";
        String url2 = "https://foundpets.ru/v2/cloud/images/0:0:jpeg/2022/5/24/9/165332381039_1653383675_main-image.jpg.x0.jpeg";
        String url3 = "https://foundpets.ru/v2/cloud/images/0:0:jpeg/2022/5/24/7/165332380822_1653377672_main-image.jpg.x0.jpeg";
        String url4 = "https://foundpets.ru/v2/cloud/images/0:0:jpeg/2022/5/24/7/165332380819_1653377667_main-image.jpg.x0.jpeg";
        String url5 = "https://foundpets.ru/v2/cloud/images/0:0:jpeg/2022/5/24/6/165332380661_1653374064_main-image.jpg.x0.jpeg";
        String url6 = "https://foundpets.ru/v2/cloud/images/0:0:jpeg/2022/5/24/19/165341282657_1653422064_main-image.jpg.x0.jpeg";

        //start

        Point2 p1 = new Point2("Пропал кот, кличка Бусик вчера не вернулся домой.", url1, 56.1, 38.1, "Бусик", "Серый с белым", "89169431652", "Я потерял(а)" );
        Point2  p2 = new Point2("Найден кот с ошейником, кто потерял?",
                url2, 55.5, 38.3, "", "Серый с черными полосами",
                "https://vk.com/id16967350"
                , "Я нашёл(а)" );
        Point2 p3 = new Point2("Найден кот",
                        url3, 59.4, 29.4, "", "Рыжий",
                        "https://vk.com/id348057208"
                        , "Я нашёл(а)" );
                Point2 p4 = new Point2("Пропал кот, домашний,",
                        url4, 55.6, 38.1, "Эммик", "Серый",

                        "https://vk.com/id585698698"

                        , "Я потерял(а)" );

                Point2 p5 = new Point2("Пропал кот, большой , пушистый",
                        url5, 55.3, 37.8, "Марсик", "Рыжий",
                        "89919486761"

                        , "Я потерял(а)" );
        Point2 p6 = new Point2("Москва, Зюзино",
                url6, 55.6, 37.5, "Страйк", "Белый с серым",
                "https://vk.com/id133705001"


                , "Я потерял(а)" );


        op2.insert(p2);
        op2.insert(p1);
        op2.insert(p6);
        op2.insert(p3);
        op2.insert(p4);
        op2.insert(p5);
        btn1 = (Button) findViewById(R.id.goToMap);
        btn2 = (Button)findViewById(R.id.show);
        Button btn3 = findViewById(R.id.toRecycle2);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ActivityMenu.this, MapsActivity.class);

                startActivity(intent);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(ActivityMenu.this, RecycleShow.class);
                startActivity(intent);
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ActivityMenu.this, RecycleShow2.class);
                startActivity(intent);
            }
        });
    }



}