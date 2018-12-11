package com.uva.triva;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Questions extends AppCompatActivity implements QuestionsRequest.Callback {
    private int id = 0;
    private int points = 0;
    ArrayList<Question> temp_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);
        QuestionsRequest x = new QuestionsRequest(this);
        x.getQuestions(this);
    }

    @Override
    public void gotQuestions(ArrayList<Question> questions) {
        temp_list = questions;
        if (id < questions.size()){
            TextView txt = findViewById(R.id.textView);
            txt.setText(questions.get(id).getQuestion());

            Button btn1 = findViewById(R.id.answer1);
            Button btn2 = findViewById(R.id.answer2);
            Button btn3 = findViewById(R.id.answer3);
            Button btn4 = findViewById(R.id.answer4);

            Random rand = new Random();
            System.out.println(questions.get(id).getList());

            List<String> newList = questions.get(id).getList();

            int numberOfTimes = questions.get(id).getList().size();

            for (int q = 0; q < numberOfTimes; q++){
                int randomize = rand.nextInt(newList.size());
                String random_element = newList.get(randomize);
                if (q == 0){
                    btn1.setText(random_element);
                }
                else if (q == 1){
                    btn2.setText(random_element);
                }

                else if (q == 2){
                    btn3.setText(random_element);
                }

                else if (q == 3){
                    btn4.setText(random_element);
                }
                newList.remove(randomize);
            }
        }

        else {
            Intent intent = new Intent(Questions.this, Leaderboards.class);
            intent.putExtra("points", points);
            startActivity(intent);
            }
        }
//        ArrayAdapter<Question> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, questions);
//        ListView listView = findViewById(R.id.question_list);
//        listView.setAdapter(adapter);
//        listView.setOnItemClickListener(new Questions.ListViewClickListener());

    public void answerGiven(View view){
        Button button = (Button) view;
        String text = button.getText().toString();
        if (temp_list.get(id).getCorrect_answer() == text){
            points += 1;
        }

        id += 1;

        System.out.println("ID: " +  id);
        System.out.println("Points: " + points);
        gotQuestions(temp_list);
    }

    @Override
    public void gotQuestionsError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

//    private class ListViewClickListener implements AdapterView.OnItemClickListener{
//        @Override
//        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
//            Intent intent = new Intent(Questions.this, Trivia.class);
//            intent.putExtra("questions", adapterView.getItemAtPosition(i).toString());
//            startActivity(intent);
//        }
//    }
}
