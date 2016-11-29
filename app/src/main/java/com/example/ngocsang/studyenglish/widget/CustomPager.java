package com.example.ngocsang.studyenglish.widget;

import android.content.Context;
import android.speech.tts.TextToSpeech;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ngocsang.studyenglish.R;
import com.example.ngocsang.studyenglish.callback.OnReceiveVoice;
import com.example.ngocsang.studyenglish.database.DataBaseManager;
import com.example.ngocsang.studyenglish.model.ItemWord;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Created by Ngoc Sang on 11/29/2016.
 */

public class CustomPager extends PagerAdapter implements TextToSpeech.OnInitListener{
    private Context context;
    private TextToSpeech tts;
    private DataBaseManager dataBaseManager;
    private boolean isInWord;

    public boolean isInWord() {
        return isInWord;
    }

    public void setInWord(boolean inWord) {
        isInWord = inWord;
    }

    private ArrayList<ItemWord> arr;
    public CustomPager(Context context,ArrayList<ItemWord> arr)
    {
        this.context=context;
        this.arr=arr;
        tts = new TextToSpeech(context, this);
        dataBaseManager=new DataBaseManager(context);
    }
    @Override
    public int getCount() {
        return arr.size();
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.screen_view_pager_word, container, false);
        final ImageView btnStar=(ImageView)view.findViewById(R.id.btn_start_practive_word);
        ImageView btnVolum=(ImageView)view.findViewById(R.id.btn_volum);
        TextView tvKey=(TextView)view.findViewById(R.id.tv_key_word);
        TextView tvSpelling=(TextView)view.findViewById(R.id.tv_spelling);
        TextView tvMean=(TextView)view.findViewById(R.id.tv_mean_word);
        if(!isInWord)
        {
            tvSpelling.setVisibility(View.GONE);
        }
        if(arr.get(position).isSelect()==1)
        {
         btnStar.setImageResource(R.drawable.btn_star_active);
        }
        tvKey.setText(arr.get(position).getName());
        tvSpelling.setText("/"+arr.get(position).getSpelling()+"/");
        tvMean.setText(arr.get(position).getContain());
        btnStar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (arr.get(position).isSelect() == 1) {
                    btnStar.setImageResource(R.drawable.btn_star_not_active);
                    arr.get(position).setSelect(0);
                    dataBaseManager.updateValueSelect(arr.get(position), false);
                    notifyDataSetChanged();
                    Toast.makeText(context, "Đã xóa từ khỏi danh sách từ của bạn", Toast.LENGTH_LONG).show();
                } else {

                    btnStar.setImageResource(R.drawable.btn_star_active);
                    arr.get(position).setSelect(1);
                    dataBaseManager.updateValueSelect(arr.get(position), true);
                    notifyDataSetChanged();
                    Toast.makeText(context, "Đã thêm từ danh sách từ của bạn", Toast.LENGTH_LONG).show();
                }
            }
        });
        btnVolum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tts.speak(arr.get(position).getName(), TextToSpeech.QUEUE_FLUSH, null);
            }
        });
        container.addView(view);
        return view;

    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @Override
    public void onInit(int status) {
        if (status == TextToSpeech.SUCCESS) {

            int result = tts.setLanguage(Locale.US);

            if (result == TextToSpeech.LANG_MISSING_DATA
                    || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e("TTS", "This Language is not supported");
            }

        } else {
            Log.e("TTS", "Initilization Failed!");
        }
    }

}
