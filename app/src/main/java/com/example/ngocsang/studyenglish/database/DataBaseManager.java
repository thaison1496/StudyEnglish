package com.example.ngocsang.studyenglish.database;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

import com.example.ngocsang.studyenglish.constant.Constant;
import com.example.ngocsang.studyenglish.model.ItemGrammar;
import com.example.ngocsang.studyenglish.model.ItemQuestion;
import com.example.ngocsang.studyenglish.model.ItemWord;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by Ngoc Sang on 11/6/2016.
 */

public class DataBaseManager {
    private String DATABASE_NAME = "tienganh.sqlite";
    private String DATABASE_PATH = Environment.getDataDirectory().getPath()
            + "/data/com.example.ngocsang.studyenglish/tienganh.sqlite";

    private Context context;
    private SQLiteDatabase database;
    public DataBaseManager(Context context)
    {
        this.context=context;
        copyDatabase(context);
    }
    private void copyDatabase(Context context) {
        try {
            InputStream inputStream=context.getAssets().open("tienganh.sqlite");
            File file=new File(DATABASE_PATH);
            if (!file.exists()){
                File parent=file.getParentFile();
                parent.mkdirs();
                file.createNewFile();
                FileOutputStream outputStream=new FileOutputStream(file);
                byte[] b=new byte[1024];
                int count=inputStream.read(b);
                while (count!=-1){
                    outputStream.write(b,0,count);
                    count=inputStream.read(b);
                }
                outputStream.close();
            }
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openDatabase(){
        database = context.openOrCreateDatabase(DATABASE_PATH,Context.MODE_APPEND,null);
    }

    public void cloaseDatabase(){
        database.close();
    }
    public ArrayList<ItemWord> getWordFromMyList()
    {
        openDatabase();
        ArrayList<ItemWord> arr=new ArrayList<>();
        Cursor c = database.rawQuery("select * from tudien where selected="+1,null);
        ItemWord itemWord=null;
        c.moveToFirst();
        while (!c.isAfterLast())
        {
            int id=c.getInt(c.getColumnIndex("id"));
            String name=c.getString(c.getColumnIndex("name"));
            String spelling=c.getString(c.getColumnIndex("spelling"));
            String contain=c.getString(c.getColumnIndex("contain"));
            String audio=c.getString(c.getColumnIndex("audio"));
            int selected =c.getInt(c.getColumnIndex("selected"));
            String topicName=c.getString(c.getColumnIndex("topicName"));
            itemWord=new ItemWord(id,name,spelling,contain,audio,selected,topicName);
            arr.add(itemWord);
            c.moveToNext();
        }
        cloaseDatabase();
        return arr;
    }
    public ArrayList<ItemWord> getCommunicationOrderTopic(int index)
    {  openDatabase();
        ArrayList<ItemWord> arr=new ArrayList<>();
        Cursor c = database.rawQuery("select * from comunication where kind="+index,null);
        ItemWord itemWord=null;
        c.moveToFirst();
        while (!c.isAfterLast())
        {
            int id=c.getInt(c.getColumnIndex("id"));
            String name=c.getString(c.getColumnIndex("key"));
            String spelling="";
            String contain=c.getString(c.getColumnIndex("value"));
            String audio=c.getString(c.getColumnIndex("audio"));
            int selected =c.getInt(c.getColumnIndex("isSelect"));
            String topicName="";
            itemWord=new ItemWord(id,name,spelling,contain,audio,selected,topicName);
            arr.add(itemWord);
            c.moveToNext();
        }
        cloaseDatabase();
        return arr;
    }
    public ArrayList<ItemWord> getWordOrderTopic(int index)
    {  openDatabase();
        ArrayList<ItemWord> arr=new ArrayList<>();
        Cursor c = database.rawQuery("select * from tudien where topicID="+index,null);
        ItemWord itemWord=null;
        c.moveToFirst();
        while (!c.isAfterLast())
        {
            int id=c.getInt(c.getColumnIndex("id"));
            String name=c.getString(c.getColumnIndex("name"));
            String spelling=c.getString(c.getColumnIndex("spelling"));
            String contain=c.getString(c.getColumnIndex("contain"));
            String audio=c.getString(c.getColumnIndex("audio"));
            int selected =c.getInt(c.getColumnIndex("selected"));
            String topicName=c.getString(c.getColumnIndex("topicName"));
            itemWord=new ItemWord(id,name,spelling,contain,audio,selected,topicName);
            arr.add(itemWord);
            c.moveToNext();
        }
        cloaseDatabase();
        return arr;
    }

    public void addWordToMyList(ItemWord itemWord)
    {
        openDatabase();
        ContentValues values=new ContentValues();
        values.put("name",itemWord.getName());
        values.put("spelling",itemWord.getSpelling());
        values.put("contain",itemWord.getContain());
        values.put("audio",itemWord.getAudio());
        values.put("topicID",itemWord.getTopicId());
        values.put("selected",itemWord.isSelect());
        database.insert("myword",null,values);
        cloaseDatabase();
    }
    public void deleteWordFromList(ItemWord itemWord)
    {

        openDatabase();
        database.delete("myword","name = "+itemWord.getName(), null);
    }
    public void updateValueSelect(ItemWord itemWord,boolean isSelect)
    {
        openDatabase();
        int id=itemWord.getId();
        ContentValues values=new ContentValues();
        values.put("id",id);
        values.put("name",itemWord.getName());
        values.put("audio",itemWord.getAudio());
        if(isSelect)
        {
            values.put("selected",1);
        }
        else {
            values.put("selected",0);
        }

        values.put("contain",itemWord.getContain());
        values.put("spelling",itemWord.getSpelling());
        int row=database.update("tudien",values,"id = ?",new String[]{String.valueOf(itemWord.getId())});
        cloaseDatabase();
    }
    public ArrayList<ItemGrammar> getAllGrammar()
    {
        ArrayList<ItemGrammar> arr=new ArrayList<>();
        openDatabase();
        ItemGrammar itemGrammar=null;
        Cursor cursor = database.rawQuery(
                "SELECT * FROM " +"nguphap",
                null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast())
        {
            int indexGrammar=cursor.getInt(cursor.getColumnIndex("indexGrammar"));
            int id=cursor.getInt(cursor.getColumnIndex("id"));
                    int level=cursor.getInt(cursor.getColumnIndex("level"));
          String titleGrammar=cursor.getString(cursor.getColumnIndex("titleGrammar"));
            String contentGrammar=cursor.getString(cursor.getColumnIndex("contentGrammar"));
            itemGrammar=new ItemGrammar(id,indexGrammar,level,titleGrammar,contentGrammar);
            arr.add(itemGrammar);
            cursor.moveToNext();

        }
      cloaseDatabase();
        return arr;
    }
    public int updateLevelGrammar(ItemGrammar grammar)
    {  openDatabase();
           int index=grammar.getIndexGrammar();
        ContentValues values=new ContentValues();
        values.put(Constant.LEVEL_GRAMMAR,grammar.getLevel());
        values.put(Constant.ID_GRAMMAR,grammar.getId());
        values.put(Constant.INDEX_GRAMMAR,grammar.getIndexGrammar());
        values.put(Constant.CONTENT_GRAMMAR,grammar.getContentGrammar());
       int row=database.update(Constant.TABLE_NGU_PHAP,values,Constant.INDEX_GRAMMAR+" = ?",new String[]{String.valueOf(index)});
        cloaseDatabase();
return row;

    }
    public ArrayList<ItemQuestion> getQuestionContest()
    {  openDatabase();
        ArrayList<ItemQuestion> arr=new ArrayList<>();
        int count=0;
        ItemQuestion question=null;
        Cursor cursor = database.rawQuery(
                "SELECT * FROM " +"tracnghiem",
                null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast())
        {
           String title=cursor.getString(cursor.getColumnIndex("title"));
            String answerA=cursor.getString(cursor.getColumnIndex("answerA"));
            String answerB=cursor.getString(cursor.getColumnIndex("answerB"));
            String answerC=cursor.getString(cursor.getColumnIndex("answerC"));
            String answerD=cursor.getString(cursor.getColumnIndex("answerD"));
            String trueAnswer=cursor.getString(cursor.getColumnIndex("trueAnswer"));
            String subAnswer=cursor.getString(cursor.getColumnIndex("subAnswer"));
            question=new ItemQuestion();
            question.setTitle(title);
            question.setAnswerA(answerA);
            question.setAnswerB(answerB);
            question.setAnswerC(answerC);
            question.setAnswerD(answerD);
            question.setSubAnswer(subAnswer);
            question.setTrueAnswer(trueAnswer);
            arr.add(question);
            count++;
            if(count==20)
            {
                return arr;
            }
            cursor.moveToNext();

        }
       cloaseDatabase();
        return null;
    }

}
