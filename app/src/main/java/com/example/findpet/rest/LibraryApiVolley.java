package com.example.findpet.rest;

import android.content.Context;
import android.util.Log;

import androidx.annotation.Nullable;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.findpet.ForData.DB;
import com.example.findpet.Map.NewPoint;
import com.example.findpet.UI.RecycleShow;
import com.example.findpet.mapper.PointMapper;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LibraryApiVolley implements LibraryApi{
    public static final String API_TEST = "API_TEST";
    private final Context context;
    private com.android.volley.Response.ErrorListener errorListener;
    public final static String BASE_URL = "http://10.22.83.179:8080";
    public LibraryApiVolley(Context context) {
        this.context = context;
        errorListener = new com.android.volley.Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("API_TEST", error.toString());
                error.printStackTrace();
            }
        };
    }
    @Override
    public void fillPoint() {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = BASE_URL + "/point";
        JsonArrayRequest arrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            for (int i = 0; i < response.length(); i++) {
                                JSONObject jsonObject = response.getJSONObject(i);
                                NewPoint point = PointMapper.pointFromJson(jsonObject);
                                DB op = new DB(context);
                                if(point.description.equals("rgbtbrtb"))
                                {
                                    Log.e("rg", "prishel");
                                }
                                Log.e("server", String.valueOf(response.length()));
                                Log.e("something", "yes");

                                op.insert(point);


                                Log.e("server2", point.description);
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(API_TEST, error.toString());
            }
        });
        queue.add(arrayRequest);
    }

    @Override
    public void addPoint(NewPoint point) {

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        String url = BASE_URL + "/point";
        byte[] arr = point.photo;
        Log.e("kolvo", String.valueOf(arr.length));
        String s = "";
//        for (int i = 0; i < arr.length; i++) {
//            s += arr[i] + " ";
//        }
        Log.e("ifOk", "Ok");
        String newS = "";
//        for (int i = 0; i < s.length() -1; i++) {
//            newS += s.charAt(i);
//        }

        StringBuffer strBuffer = new StringBuffer(s);

        for (int i = 0; i < arr.length; i++) {

            strBuffer.append(arr[i] + " ");

        }
        newS = strBuffer.toString().substring(0, strBuffer.toString().length() - 1);
        Log.e("ifSecondOk", "SecondOk");
        if(newS.length() > 0)
        {
            Log.e("kolvo", "Yes");
        }
        else{
            Log.e("kolvo", "No");
        }
        Log.e("kolvo", String.valueOf(newS.length()));
        JSONObject json = new JSONObject();
                try {
                    json.put("id", point.id);
                    json.put("description",point.description);
                    json.put("photo",newS);
                    json.put("x",point.x);
                    json.put("y",point.y);
                    json.put("petName", point.petName);
                    json.put("petColor", point.petColor);
                    json.put("petContact", point.petContact);
                    json.put("status", point.status);
                }
                catch (JSONException e) {
                    e.printStackTrace();
                }
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.POST, url, json, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                Log.d("API_TEST_ADD_POINT", response.toString());
            }
        }, errorListener);



        requestQueue.add(jsonObjectRequest);

    }

        @Override
    public void updatePoint(int id, NewPoint point) {

            String url = BASE_URL + "/point/" + id;
            byte[] arr = point.photo;
            String s = "";
            for (int i = 0; i < arr.length; i++) {
                s += arr[i] + " ";
            }
            String newS = "";
            for (int i = 0; i < s.length() -1; i++) {
                newS += s.charAt(i);
            }
            Log.e("kolvo", String.valueOf(newS.length()));
            JSONObject json = new JSONObject();
            try {
                json.put("id", point.id  );
                json.put("description",point.description);
                json.put("photo",newS);
                json.put("x",point.x);
                json.put("y",point.y);
                json.put("petName", point.petName);
                json.put("petColor", point.petColor);
                json.put("petContact", point.petContact);
                json.put("status", point.status);
            }
            catch (JSONException e) {
                e.printStackTrace();
            }
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                    Request.Method.PUT, url, json, new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(JSONObject response) {

                    Log.d("API_TEST_ADD_POINT", response.toString());
                }
            }, errorListener);
        }

    @Override
    public void deletePoint(int id) {
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = BASE_URL + "/point" + "/" + id;
        StringRequest request = new StringRequest(Request.Method.DELETE, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                fillPoint();
                Log.d(API_TEST, response);
            }
        },  new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d(API_TEST, error.toString());
            }
        }
        );
        queue.add(request);
    }
}
