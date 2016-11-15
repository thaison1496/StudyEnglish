package com.example.ngocsang.studyenglish.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.ngocsang.studyenglish.R;
import com.example.ngocsang.studyenglish.constant.TagFragment;
import com.example.ngocsang.studyenglish.database.DataBaseManager;
import com.example.ngocsang.studyenglish.model.ItemGrammar;
import com.example.ngocsang.studyenglish.screen.activity.MainActivity;
import com.example.ngocsang.studyenglish.screen.fragment.study.Grammar.ContestGrammarFragment;
import com.example.ngocsang.studyenglish.screen.fragment.study.Grammar.DetailGrammarFragment;
import com.example.ngocsang.studyenglish.utils.CommonUtils;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Ngoc Sang on 11/7/2016.
 */

public class AdapterGrammar extends EnglishBaseAdapter<ItemGrammar>{
    private MainActivity mainActivity;
    private DataBaseManager dataBaseManager;
    public AdapterGrammar(List<ItemGrammar> mData, Context context) {
        super(mData, context);
        mainActivity=(MainActivity)context;
        dataBaseManager=new DataBaseManager(context);
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolderGrammar holderGrammar;
        if(convertView==null)
        {
            convertView= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_grammar,parent,false);
            holderGrammar=new ViewHolderGrammar();
            holderGrammar.btnPractice=(Button)convertView.findViewById(R.id.btn_practice);
            holderGrammar.imageView=(CircleImageView)convertView.findViewById(R.id.img_icon_grammar);
            holderGrammar.percent=(TextView)convertView.findViewById(R.id.tv_percent_level);
            holderGrammar.title=(TextView)convertView.findViewById(R.id.tv_title_grammar);
            holderGrammar.progressBar=(ProgressBar)convertView.findViewById(R.id.progress_bar_grammar);
            holderGrammar.root=(RelativeLayout)convertView.findViewById(R.id.root_item_grammar);
            convertView.setTag(holderGrammar);
        }
        else {
            holderGrammar= (ViewHolderGrammar) convertView.getTag();
        }
        holderGrammar.title.setText(mData.get(position).getTitleGrammar());
        holderGrammar.progressBar.setProgress(mData.get(position).getLevel());
        holderGrammar.percent.setText(mData.get(position).getLevel()+"%");
        holderGrammar.imageView.setImageResource(R.drawable.item_grammar);
        holderGrammar.btnPractice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ContestGrammarFragment grammarFragment=new ContestGrammarFragment();
                grammarFragment.setItemGrammar(mData.get(position));
                grammarFragment.setArrQuestion(dataBaseManager.getQuestionContest());
                mainActivity.replaceFullScreen(grammarFragment,true, TagFragment.CONTEST_FRAGMENT);

            }
        });
        holderGrammar.root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DetailGrammarFragment detailGrammarFragment=new DetailGrammarFragment();
                detailGrammarFragment.setItemGrammar(mData.get(position));
                mainActivity.replaceFullScreen(detailGrammarFragment,true, TagFragment.DETAIL_GRAMMAR);
            }
        });
        return convertView;
    }
    public class ViewHolderGrammar{
        private TextView title,percent;
        private CircleImageView imageView;
        private ProgressBar progressBar;
        private Button btnPractice;
        private RelativeLayout root;
    }
}
