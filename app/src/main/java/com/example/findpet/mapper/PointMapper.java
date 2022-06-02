package com.example.findpet.mapper;

import com.example.findpet.Map.NewPoint;
import com.google.common.reflect.TypeToken;
import com.google.gson.JsonIOException;

import org.json.JSONException;
import org.json.JSONObject;
import com.google.gson.Gson;

import java.util.Arrays;
import java.util.List;

public class PointMapper {
    public static NewPoint pointFromJson(JSONObject jsonObject){
        NewPoint point = null;
        List<String> stringArrayList = null;
        try {
            stringArrayList = Arrays.asList(
                    jsonObject.getString("photo").split(" "));
        } catch (JSONException e) {
            e.printStackTrace();
        }
        byte[] byteArray = new byte[stringArrayList.size()];
        for (int i = 0; i < stringArrayList.size(); i++) {
            try {
                byteArray[i] = Byte.valueOf(stringArrayList.get(i));
            }catch (Exception e){

                System.out.println("Error");
            }
        }
        try{
          //  byte[] b = new Gson().fromJson(String.valueOf(jsonObject.getJSONArray("photo")), new TypeToken<List<Byte>>(){}.getType());
            //byte[] ar = (byte[])jsonObject.getJSONArray("photo");
            point = new NewPoint(
                    jsonObject.getString("description"),
                    byteArray,
                    jsonObject.getDouble("x"),
                    jsonObject.getDouble("y"),
                    jsonObject.getString("petName"),
                    jsonObject.getString("petColor"),
                    jsonObject.getString("petContact"),
                    jsonObject.getString("status"));
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }
        return point;
    }
}
