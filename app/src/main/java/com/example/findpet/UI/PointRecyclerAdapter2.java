package com.example.findpet.UI;

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
import com.example.findpet.ForData.DB2;
import com.example.findpet.ForData.Data;
import com.example.findpet.ForData.Utilities;
import com.example.findpet.Map.NewPoint;
import com.example.findpet.Map.Point2;
import com.example.findpet.Map.ShowOneOnly;
import com.example.findpet.R;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.util.ArrayList;

public class PointRecyclerAdapter2 extends  RecyclerView.Adapter<RecyclerView.ViewHolder>{
    private LayoutInflater inflater;
    private Context context;
    public PointRecyclerAdapter2(Context context)
    {
        this.inflater = LayoutInflater.from(context);

        this.context = context;

    }
    private class MyHolder2 extends RecyclerView.ViewHolder{
        private TextView tvDescription ;
        private ImageView ImPhoto;

        public MyHolder2(@NonNull View itemView) {
            super(itemView);
            tvDescription =itemView.findViewById(R.id.tvDescription);
            ImPhoto =itemView.findViewById(R.id.ImPhoto);



        }
    }
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view  = inflater.inflate(R.layout.adapter_item, parent, false );


        return new PointRecyclerAdapter2.MyHolder2(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        DB2 op = new DB2(this.context);

        ArrayList<Point2> points = op.findPointsBySpecialParametr(Data.status2);


        Point2 point = null;
        try {
            point = points.get(position);
            ((PointRecyclerAdapter2.MyHolder2)holder).tvDescription.setText(point.description);
//            Bitmap b= Utilities.getImage(point.photo);
//            ((PointRecyclerAdapter2.MyHolder2)holder).ImPhoto.setImageBitmap(b);
            Picasso.with(context).load(point.photo).into(((MyHolder2) holder).ImPhoto);
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Data.type = 2;
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
        DB2 op = new DB2(this.context);

        ArrayList<Point2> points = op.findPointsBySpecialParametr(Data.status2);
        return points.size();
    }
}
