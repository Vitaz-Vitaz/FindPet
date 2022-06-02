package com.example.findpet.UI;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.findpet.ForData.Data;
import com.example.findpet.Map.NewPoint;
import com.example.findpet.R;

import java.util.ArrayList;

public class MyPointAdapter extends ArrayAdapter<NewPoint> {

    public MyPointAdapter(Context context, ArrayList<NewPoint> points) {
        super(context, R.layout.adapter_item, points);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final NewPoint point = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.adapter_item, null);
        }

// Заполняем адаптер
        ((TextView) convertView.findViewById(R.id.tvDescription)).setText(point.description);

// Выбираем картинку для месяца


            ((ImageView) convertView.findViewById(R.id.ImPhoto)).setImageBitmap(BitmapFactory.
                    decodeByteArray(point.photo, 0,point.photo.length));



        return convertView;
    }
}