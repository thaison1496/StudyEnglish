package com.example.ngocsang.studyenglish.screen.fragment.study.Grammar;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.ngocsang.studyenglish.R;
import com.example.ngocsang.studyenglish.screen.fragment.base.BaseFragment;

import org.seniorzhai.scoreboard.ScoreBoard;

import at.grabner.circleprogress.CircleProgressView;

/**
 * Created by Ngoc Sang on 11/13/2016.
 */

public class ScoreFragment extends BaseFragment {
    private TextView tvResult,tvPercent,tvCount;
    private Button btnOption;
    private CircleProgressView scoreBoard;
    private int score;
    private RatingBar ratingBar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        contentView = inflater.inflate(R.layout.fragment_score, container, false);
        return contentView;
    }

    @Override
    protected void findViews() {
        super.findViews();
        tvResult = (TextView) contentView.findViewById(R.id.tv_result);
        btnOption = (Button) contentView.findViewById(R.id.btn_option);
        scoreBoard = (CircleProgressView) contentView.findViewById(R.id.score_board);
        ratingBar = (RatingBar) contentView.findViewById(R.id.rate_board);
        tvPercent=(TextView)contentView.findViewById(R.id.tv_percent_score);
        tvCount=(TextView)contentView.findViewById(R.id.tv_count_score);
    }

    public void setScore(int score) {
        this.score = score;
    }

    @Override
    protected void init() {
        super.init();
        float percen = (((float) score / 20) * 100);
        scoreBoard.setValue(percen);
        ratingBar.setMax(100);
        tvCount.setText(score+"/20 câu");
        tvPercent.setText((int)percen+"%");
       ratingBar.setProgress((int) percen);
    }

    @Override
    protected void declareClick() {
        super.declareClick();
        btnOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}
