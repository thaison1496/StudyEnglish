package com.example.ngocsang.studyenglish.screen.fragment.study.Grammar;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.GridView;

import com.example.ngocsang.studyenglish.R;
import com.example.ngocsang.studyenglish.adapter.AdapterGrammar;
import com.example.ngocsang.studyenglish.database.DataBaseManager;
import com.example.ngocsang.studyenglish.model.ItemGrammar;
import com.example.ngocsang.studyenglish.screen.fragment.base.BaseFullScreenFragment;

import java.util.ArrayList;

/**
 * Created by Ngoc Sang on 11/6/2016.
 */

public class GrammarFragment extends BaseFullScreenFragment {
    private GridView lvGrammar;
    private AdapterGrammar adapterGrammar;
    private ArrayList<ItemGrammar> arrGrammar;
    private DataBaseManager dataBaseManager;


    @Override
    protected void addView(LayoutInflater inflater, ViewGroup container) {
        contentView = inflater.inflate(R.layout.fragment_grammar, container, false);
        containerView.addView(contentView);
    }

    @Override
    protected void declareClick() {
        super.declareClick();
    }

    @Override
    protected void findViews() {
        super.findViews();
        lvGrammar = (GridView) contentView.findViewById(R.id.lv_list_grammar);
    }

    @Override
    protected void init() {
        super.init();
        dataBaseManager=new DataBaseManager(mActivity);
        arrGrammar=new ArrayList<>();
        arrGrammar.addAll(dataBaseManager.getAllGrammar());
        adapterGrammar=new AdapterGrammar(arrGrammar,mActivity);
        lvGrammar.setAdapter(adapterGrammar);
    }

    @Override
    protected void setUpScreen() {
        super.setUpScreen();
        setTitle("Học Ngữ Pháp");
    }
}
