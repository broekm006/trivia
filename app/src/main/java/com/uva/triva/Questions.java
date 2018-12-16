package com.uva.triva;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Questions extends AppCompatActivity implements QuestionsRequest.Callback {
    private int id = 0;
    private int points = 0;
    ArrayList<Question> temp_list;

    Button btn1;
    Button btn2;
    Button btn3;
    Button btn4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions);

        // retrieve questions from request
        QuestionsRequest x = new QuestionsRequest(this);
        x.getQuestions(this);
    }


    @Override
    public void gotQuestions(ArrayList<Question> questions) {
        temp_list = questions;

        // get questions from server and fill in the questions views
        if (id < questions.size()) {
            TextView txt = findViewById(R.id.textView);
            txt.setText(questions.get(id).getQuestion());

            btn1 = findViewById(R.id.answer1);
            btn2 = findViewById(R.id.answer2);
            btn3 = findViewById(R.id.answer3);
            btn4 = findViewById(R.id.answer4);

            Random rand = new Random();
            System.out.println(questions.get(id).getList());

            List<String> newList = questions.get(id).getList();

            int numberOfTimes = questions.get(id).getList().size();

            // choose random button for each answer to make sure the correct answer is not always on the same location
            for (int q = 0; q < numberOfTimes; q++) {
                int randomize = rand.nextInt(newList.size());
                String random_element = newList.get(randomize);
                if (q == 0) {
                    btn1.setText(random_element);
                } else if (q == 1) {
                    btn2.setText(random_element);
                } else if (q == 2) {
                    btn3.setText(random_element);
                } else if (q == 3) {
                    btn4.setText(random_element);
                }
                newList.remove(randomize);
            }
        }

        // when done with questions go the "win" screen
        else {
            Intent intent = new Intent(Questions.this, Leaderboards.class);
            intent.putExtra("points", points);
            startActivity(intent);
        }
    }

    // check when button is pressed if the answer is correct & update the points & ID
    public void answerGiven(View view) {
        Button button = (Button) view;
        String text = button.getText().toString();

        if (temp_list.get(id).getCorrect_answer() == text) {
            points += 1;
            Toast.makeText(this, "Correct", Toast.LENGTH_SHORT).show();
        }

        id += 1;

        //SystemClock.sleep(1000);
        gotQuestions(temp_list);
    }

    @Override
    public void gotQuestionsError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
}