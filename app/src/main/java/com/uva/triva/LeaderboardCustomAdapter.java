package com.uva.triva;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class LeaderboardCustomAdapter extends ArrayAdapter<Leaderboard> {
    private ArrayList<Leaderboard> scores;

    public LeaderboardCustomAdapter(@NonNull Context context, @NonNull ArrayList<Leaderboard> objects) {
        super(context, R.layout.score, objects);
        this.scores = objects;
        sortArrayList();
    }

    private void sortArrayList(){
        Collections.sort(scores, new Comparator<Leaderboard>() {
            @Override
            public int compare(Leaderboard t1, Leaderboard t2) {
                return t1.getPoints().compareTo(t2.getPoints());
            }
        });
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.score, parent, false);
        }

        // gather the correct fields / views
        TextView name = convertView.findViewById(R.id.text_name);
        TextView score = convertView.findViewById(R.id.text_score);

        Leaderboard leaderboard = scores.get(position);

        name.setText(leaderboard.getName());
        score.setText(leaderboard.getPoints());

        return convertView;
    }
}
