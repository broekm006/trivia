package com.uva.triva;

import android.text.Html;

import com.android.volley.toolbox.StringRequest;

import java.util.ArrayList;
import java.util.List;

public class Question {

    private String category, question, correct_answer,difficulty, type, one, two, three, incorrect_answers;
    private List<String> list = new ArrayList<String>();

    public Question(String category, String question, String correct_answer, String incorrect_answers, String difficulty, String type){
        this.category = category;
        this.question = question;
        this.correct_answer = correct_answer;
        this.incorrect_answers = incorrect_answers.replace("\"", "").replace("[", "").replace("]", "");
        this.difficulty = difficulty;
        this.type = type;

        String[] ss = this.incorrect_answers.split(","); // splitten op , kan niet in verband met vragen die een, gebruiken zoals datums > "21 april, 2014"

        one =  Html.fromHtml(ss[0]).toString();
        two =  Html.fromHtml(ss[1]).toString();
        three =  Html.fromHtml(ss[2]).toString();

        list.add(this.correct_answer);
        list.add(one);
        list.add(two);
        list.add(three);
    }

    public String getCategory(){
        return category;
    }

    public String getQuestion() {
        return question;
    }

    public String getCorrect_answer() {
        return correct_answer;
    }

    public String getIncorrect_answers() {
        return incorrect_answers;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public String getType() {
        return type;
    }

    public String getOne() {
        return one;
    }

    public String getTwo() {
        return two;
    }

    public String getThree() {
        return three;
    }

    public List getList() {
        return list;
    }

}
