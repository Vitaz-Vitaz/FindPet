package com.example.findpet.UI;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.findpet.ForData.DB;
import com.example.findpet.ForData.Data;
import com.example.findpet.ForData.Utilities;
import com.example.findpet.Map.NewPoint;
import com.example.findpet.Map.ShowOneOnly;
import com.example.findpet.R;


import java.util.ArrayList;
import java.util.List;

public class PointRecyclerAdapter extends  RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private LayoutInflater inflater;

    private Context context;
    public PointRecyclerAdapter(Context context)
    {
        this.inflater = LayoutInflater.from(context);

        this.context = context;

    }

    private class MyHolder extends RecyclerView.ViewHolder{
        private TextView tvDescription ;
        private ImageView ImPhoto;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            tvDescription =itemView.findViewById(R.id.tvDescription);
            ImPhoto =itemView.findViewById(R.id.ImPhoto);



        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = inflater.inflate(R.layout.adapter_item, parent, false );

        Log.e("Point_2", "bvefhvb");
        return new MyHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        DB op = new DB(this.context);

        ArrayList<NewPoint> points = op.findPointsBySpecialParametr(Data.status);


        NewPoint point = null;
        try {
            point = points.get(position);
            ((MyHolder)holder).tvDescription.setText(point.description);
            Bitmap b= Utilities.getImage(point.photo);
            ((MyHolder)holder).ImPhoto.setImageBitmap(b);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Data.type = 1;
                    Data.i = holder.getAdapterPosition();
                    Intent intent=new Intent(context, ShowOneOnly.class);
                    context.startActivity(intent);
                }
            });
        }
        catch (Exception e){

        }





    }




    @Override
    public int getItemCount() {

        DB op = new DB(this.context);
        ArrayList<NewPoint> points = op.findPointsBySpecialParametr(Data.status);
        return points.size();
    }
}
