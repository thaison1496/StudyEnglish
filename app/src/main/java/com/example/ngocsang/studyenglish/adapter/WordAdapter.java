package com.example.ngocsang.studyenglish.adapter;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.speech.tts.TextToSpeech;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ngocsang.studyenglish.R;
import com.example.ngocsang.studyenglish.database.DataBaseManager;
import com.example.ngocsang.studyenglish.model.ItemWord;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

/**
 * Created by Ngoc Sang on 11/27/2016.
 */

public class WordAdapter extends EnglishBaseAdapter<ItemWord> implements TextToSpeech.OnInitListener {
    private TextToSpeech tts;
    private DataBaseManager dataBaseManager;
    private HashMap<String, String> map;

    public WordAdapter(List<ItemWord> mData, Context context) {
        super(mData, context);
        tts = new TextToSpeech(context, this);
        dataBaseManager = new DataBaseManager(context);
        map = new HashMap<>();

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_word, parent, false);
            holder = new ViewHolder();
            holder.sound = (ImageView) convertView.findViewById(R.id.img_sound);
            holder.btnStar = (ImageView) convertView.findViewById(R.id.img_btn_star);
            holder.tvKey = (TextView) convertView.findViewById(R.id.tv_key);
            holder.tvValue = (TextView) convertView.findViewById(R.id.tv_value);
            convertView.setTag(holder);


        }
        holder = (ViewHolder) convertView.getTag();

        holder.sound.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tts.speak(mData.get(position).getName(), TextToSpeech.QUEUE_FLUSH, null);

            }
        });
        holder.tvKey.setText(mData.get(position).getName());
        holder.tvValue.setText(mData.get(position).getContain());
        if (map.containsKey(mData.get(position).getName())) {
            holder.btnStar.setImageResource(R.drawable.btn_star_active);
        }
        final ViewHolder finalHolder = holder;
        holder.btnStar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mData.get(position).isSelect() == 1) {
                    map.remove(mData.get(position).getName());
                    finalHolder.btnStar.setImageResource(R.drawable.btn_star_not_active);
                    mData.get(position).setSelect(0);
                    dataBaseManager.updateValueSelect(mData.get(position), false);
                    notifyDataSetChanged();
                    Toast.makeText(mContext, "Đã xóa từ khỏi danh sách từ của bạn", Toast.LENGTH_LONG).show();
                } else {
                    map.put(mData.get(position).getName(), mData.get(position).getContain());
                    finalHolder.btnStar.setImageResource(R.drawable.btn_star_active);
                    mData.get(position).setSelect(1);
                    dataBaseManager.updateValueSelect(mData.get(position), true);
                    notifyDataSetChanged();
                    Toast.makeText(mContext, "Đã thêm từ danh sách từ của bạn", Toast.LENGTH_LONG).show();
                }
            }
        });

        return convertView;
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

    public class ViewHolder {
        private ImageView sound, btnStar;
        private TextView tvKey, tvValue;
    }
}
