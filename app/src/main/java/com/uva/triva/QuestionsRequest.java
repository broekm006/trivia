package com.uva.triva;

import android.content.Context;
import android.text.Html;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class QuestionsRequest implements Response.Listener<JSONObject>, Response.ErrorListener {

    private Context context;
    private ArrayList<Question> newList;
    private Callback callback;

    @Override
    public void onErrorResponse(VolleyError error) {
        callback.gotQuestionsError(error.getMessage());
    }

    @Override
    public void onResponse(JSONObject response) {
        try{
            JSONArray array = response.getJSONArray("results");
            newList = new ArrayList<Question>();

            for (int i = 0; i < array.length(); i++){
                JSONObject specific = array.getJSONObject(i);

                String category = specific.getString("category");
                String question = Html.fromHtml(specific.getString("question")).toString();
                String correct_answer = Html.fromHtml(specific.getString("correct_answer")).toString();
                String incorrect_answers = specific.getString("incorrect_answers");
                String difficulty = specific.getString("difficulty");
                String type = specific.getString("type");
                newList.add(new Question(category, question, correct_answer, incorrect_answers, difficulty, type));
            }
        }
        catch (JSONException e){
            System.out.println(e.getMessage());
        }

        callback.gotQuestions(newList);
    }

    public interface Callback {
        void gotQuestions(ArrayList<Question> questions);
        void gotQuestionsError(String message);
    }

    public QuestionsRequest(Context c){
        this.context = c;
    }

    void getQuestions(Callback activity){
        RequestQueue queue = Volley.newRequestQueue(context);
        // post data to ide url >
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest("https://opentdb.com/api.php?amount=10&type=multiple", null, this, this);
        queue.add(jsonObjectRequest);

        callback = activity;
    }

}
