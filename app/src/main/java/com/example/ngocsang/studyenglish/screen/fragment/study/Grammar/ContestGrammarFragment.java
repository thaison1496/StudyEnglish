package com.example.ngocsang.studyenglish.screen.fragment.study.Grammar;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.example.ngocsang.studyenglish.R;
import com.example.ngocsang.studyenglish.constant.Constant;
import com.example.ngocsang.studyenglish.model.ItemGrammar;
import com.example.ngocsang.studyenglish.model.ItemQuestion;
import com.example.ngocsang.studyenglish.screen.fragment.base.BaseFragment;
import com.example.ngocsang.studyenglish.screen.fragment.base.BaseFullScreenFragment;
import com.example.ngocsang.studyenglish.widget.ItemBtnSelect;

import java.util.ArrayList;

/**
 * Created by PhamVanLong on 11/8/2016.
 */

public class ContestGrammarFragment extends BaseFragment {
    private ItemBtnSelect btnA, btnB, btnC, btnD;
    private TextView tvCountQuestion, tvContentQuestion;
    private ArrayList<ItemBtnSelect> arrBtn;
    private ArrayList<ItemQuestion> arrQuestion;
    private ItemGrammar itemGrammar;

    public void setItemGrammar(ItemGrammar itemGrammar) {
        this.itemGrammar = itemGrammar;
    }

    private int currentPosition = -1;
    private int countQuestion=0;
    private int score=0;

    public TextView getTvContentQuestion() {
        return tvContentQuestion;
    }

    private int currentBtn = -1;
    private boolean isSelect=false;
    private Button btnCheck;
    private int status = -1;

    public void setArrQuestion(ArrayList<ItemQuestion> arrQuestion) {
        this.arrQuestion = arrQuestion;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        contentView = inflater.inflate(R.layout.contest_grammar_fragment, container, false);
        return contentView;
    }

    @Override
    protected void findViews() {
        super.findViews();
        btnCheck = (Button) contentView.findViewById(R.id.btn_check_question);
        btnA = (ItemBtnSelect) contentView.findViewById(R.id.btn_a);
        btnA.setDefaultBtn();
        btnA.setTvTitle("A");
        btnB = (ItemBtnSelect) contentView.findViewById(R.id.btn_b);
        btnB.setDefaultBtn();
        btnB.setTvTitle("B");
        btnC = (ItemBtnSelect) contentView.findViewById(R.id.btn_c);
        btnC.setDefaultBtn();
        btnC.setTvTitle("C");
        btnD = (ItemBtnSelect) contentView.findViewById(R.id.btn_d);
        btnD.setDefaultBtn();
        btnD.setTvTitle("D");
        arrBtn = new ArrayList<>();
        arrBtn.add(btnA);
        arrBtn.add(btnB);
        arrBtn.add(btnC);
        arrBtn.add(btnD);
        tvContentQuestion = (TextView) contentView.findViewById(R.id.tv_content_question);
        tvCountQuestion = (TextView) contentView.findViewById(R.id.tv_count_question);


    }

    @Override
    protected void setUpScreen() {
        super.setUpScreen();

    }

    @Override
    protected void init() {
        super.init();
        initDataQuestion(0);
        status = Constant.STATUS_CHECK;
        score=0;


    }

    private void initDataQuestion(int position) {
        currentPosition = position;
        countQuestion=position+1;
        isSelect=false;
        tvCountQuestion.setText("Câu " + (position+1) + "/20");
        if(arrQuestion!=null)
        {
            tvContentQuestion.setText(arrQuestion.get(position).getTitle());
            btnA.setTvContentBtn(arrQuestion.get(position).getAnswerA());
            btnB.setTvContentBtn(arrQuestion.get(position).getAnswerB());
            btnC.setTvContentBtn(arrQuestion.get(position).getAnswerC());
            btnD.setTvContentBtn(arrQuestion.get(position).getAnswerD());
        }

        currentBtn=-1;
        btnA.clearAnimation();
        btnB.clearAnimation();
        btnC.clearAnimation();
        btnD.clearAnimation();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    protected void declareClick() {

        super.declareClick();
        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isSelect)
                {
                    if (status == Constant.STATUS_CHECK) {
                        status = Constant.STATUS_NEXT_QUESTION;
                        btnCheck.setText("Câu Tiếp");
                        Drawable drawable = mActivity.getResources().getDrawable(R.drawable.background_button);
                        btnCheck.setBackground(drawable);
                        checkQuestion();
                    } else {
                        status = Constant.STATUS_CHECK;
                        clearBtn();
                        btnCheck.setText("Kiểm Tra");
                        Drawable drawable = mActivity.getResources().getDrawable(R.drawable.background_btn);
                        btnCheck.setBackground(drawable);
                        initDataQuestion(currentPosition + 1);
                    }
                }
                else {
                    return;
                }

            }
        });
        btnA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSelectPosition(0);
            }
        });
        btnB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSelectPosition(1);
            }
        });
        btnC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSelectPosition(2);
            }
        });
        btnD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setSelectPosition(3);
            }
        });
        btnA.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus)
                {
                    btnA.setFocusBtn();
                }
                else {
                    btnA.setDefaultBtn();
                }
            }
        });
        btnB.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus)
                {
                    btnB.setFocusBtn();
                }
                else {
                    btnB.setDefaultBtn();
                }
            }
        });
        btnC.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus)
                {
                    btnC.setFocusBtn();
                }
                else {
                    btnC.setDefaultBtn();
                }
            }
        });
        btnD.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus)
                {
                    btnD.setFocusBtn();
                }
                else {
                    btnD.setDefaultBtn();
                }
            }
        });
    }

    private void setSelectPosition(int position) {
        currentBtn = position;
        isSelect=true;
        for (int i = 0; i < arrBtn.size(); i++) {
            if (i == position) {
                arrBtn.get(position).selectBtn();
            } else {
                arrBtn.get(i).setDefaultBtn();
            }

        }

    }

    private void checkQuestion() {
        int positionAnswer=getTrueAnswer(currentPosition);
        if(positionAnswer==(currentBtn))
        {
             score++;
            arrBtn.get(currentBtn).setStatus(arrQuestion.get(currentPosition).getSubAnswer(),true);
        }
        else {
            arrBtn.get(positionAnswer).setStatusTrue();
            arrBtn.get(currentBtn).setStatus(arrQuestion.get(currentPosition).getSubAnswer(),false);


        }
        if(countQuestion==20)
        {
            ScoreFragment scoreFragment=new ScoreFragment();
            scoreFragment.setScore(score);
            scoreFragment.setItemGrammar(itemGrammar);
            mActivity.replaceFullScreen(scoreFragment,true,"scorefragment");
        }


    }

    private int getTrueAnswer(int currentPosition) {
        if (arrQuestion.get(currentPosition).getTrueAnswer().equals("A")||arrQuestion.get(currentPosition).getTrueAnswer().equals("1")) {
            return 0;
        } else if (arrQuestion.get(currentPosition).getTrueAnswer().equals("B")||arrQuestion.get(currentPosition).getTrueAnswer().equals("2")) {
            return 1;
        } else if (arrQuestion.get(currentPosition).getTrueAnswer().equals("C")||arrQuestion.get(currentPosition).getTrueAnswer().equals("3")) {
            return 2;
        } else if (arrQuestion.get(currentPosition).getTrueAnswer().equals("D")||arrQuestion.get(currentPosition).getTrueAnswer().equals("4")) {
            return 3;
        }
        return 1;

    }
    private void clearBtn()
    {  currentBtn=-1;
        isSelect=false;
        for (int i=0;i<arrBtn.size();i++)
        {
            arrBtn.get(i).setDefaultBtn();
        }
    }

}
