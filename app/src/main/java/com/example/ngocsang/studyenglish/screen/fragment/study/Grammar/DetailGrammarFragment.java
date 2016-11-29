package com.example.ngocsang.studyenglish.screen.fragment.study.Grammar;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.ngocsang.studyenglish.R;
import com.example.ngocsang.studyenglish.constant.TagFragment;
import com.example.ngocsang.studyenglish.database.DataBaseManager;
import com.example.ngocsang.studyenglish.model.ItemGrammar;
import com.example.ngocsang.studyenglish.screen.fragment.base.BaseFullScreenFragment;

/**
 * Created by Ngoc Sang on 11/7/2016.
 */

public class DetailGrammarFragment extends BaseFullScreenFragment{
    private TextView contentGrammar,tvTitle;
    private ItemGrammar itemGrammar;
    private Button btnPractice;
    private DataBaseManager dataBaseManager;
    @Override
    protected void addView(LayoutInflater inflater, ViewGroup container) {
        contentView=inflater.inflate(R.layout.detail_grammar_fragment,container,false);
        containerView.addView(contentView);
    }

    @Override
    protected void findViews() {
        super.findViews();
        contentGrammar=(TextView)contentView.findViewById(R.id.tv_content_grammar);
        btnPractice=(Button)contentView.findViewById(R.id.btn_practice_detail_grammar);
        tvTitle=(TextView)contentView.findViewById(R.id.tv_title);
    }

    public void setItemGrammar(ItemGrammar itemGrammar) {
        this.itemGrammar = itemGrammar;
    }

    @Override
    protected void declareClick() {
        super.declareClick();
        btnPractice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContestGrammarFragment grammarFragment=new ContestGrammarFragment();
                grammarFragment.setArrQuestion(dataBaseManager.getQuestionContest());
                grammarFragment.setItemGrammar(itemGrammar);
                mActivity.replaceFullScreen(grammarFragment,true, TagFragment.CONTEST_FRAGMENT);
            }
        });
    }

    @Override
    protected void setUpScreen() {
        super.setUpScreen();
        setTitle("Ngữ Pháp");


    }

    @Override
    protected void init() {
        super.init();
        contentGrammar.setText(itemGrammar.getContentGrammar());
        dataBaseManager=new DataBaseManager(mActivity);
        tvTitle.setText(itemGrammar.getTitleGrammar());

    }
}
