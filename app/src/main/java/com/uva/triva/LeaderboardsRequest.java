package com.uva.triva;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class LeaderboardsRequest implements Response.Listener<JSONArray>, Response.ErrorListener {
    private Context context;
    private ArrayList<Leaderboard> newList;
    private Callback callback;

    @Override
    public void onErrorResponse(VolleyError error) {
        callback.gotScoreError(error.getMessage());
    }

    @Override
    public void onResponse(JSONArray response) {
        try{

            JSONArray scoreboard = response;
            newList = new ArrayList<Leaderboard>();

            for (int i = 0; i < scoreboard.length(); i++){
                JSONObject jsonObject = scoreboard.getJSONObject(i);
                String name = jsonObject.getString("name");
                String points = jsonObject.getString("points");
                newList.add(new Leaderboard(name, points));
            }
        }

        catch (JSONException e){
            System.out.println(e.getMessage());
        }

        callback.gotScore(newList);
    }

    public interface Callback {
        void gotScore(ArrayList<Leaderboard> Score);
        void gotScoreError(String message);
    }

    public LeaderboardsRequest(Context c){
        this.context = c;
    }

    void getScore (Callback activity){
        RequestQueue queue = Volley.newRequestQueue(context);
        JsonArrayRequest jsonObjectRequest = new JsonArrayRequest("https://ide50-marcyman1.cs50.io:8080/list", this, this);
        queue.add(jsonObjectRequest);

        callback = activity;
    }

    void postData(final String name, final String points){
        RequestQueue myQueue = Volley.newRequestQueue(context);
        String url = "https://ide50-marcyman1.cs50.io:8080/list";
        StringRequest strings = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //This code is executed if the server responds, whether or not the response contains data.
                //The String 'response' contains the server's response.
            }
        }, new Response.ErrorListener() { //Create an error listener to handle errors appropriately.
            @Override
            public void onErrorResponse(VolleyError error) {
                System.out.println(error.getMessage());
            }
        }) {
            protected Map<String, String> getParams() {
                Map<String, String> MyData = new HashMap<String, String>();
                MyData.put("name", name); //Add the data you'd like to send to the server.
                MyData.put("points", points);
                return MyData;
            }
        };
        myQueue.add(strings);
    }
}