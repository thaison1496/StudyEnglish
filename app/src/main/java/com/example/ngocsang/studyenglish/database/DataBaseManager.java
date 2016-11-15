package com.example.ngocsang.studyenglish.database;

import android.content.Context;
import android.content.res.AssetManager;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;

import com.example.ngocsang.studyenglish.model.ItemGrammar;
import com.example.ngocsang.studyenglish.model.ItemQuestion;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Ngoc Sang on 11/6/2016.
 */

public class DataBaseManager {
    private String DATABASE_NAME = "tienganh.sqlite";
    private String DATABASE_PATH = Environment.getDataDirectory().getAbsolutePath()
            + "/data/com.example.ngocsang.studyenglish/";

    private Context context;
    private SQLiteDatabase sqLiteDatabase;
    public DataBaseManager(Context context)
    {
        this.context=context;
        copyDatabase();
    }
    private void copyDatabase() {
        try {
            File file = new File(DATABASE_PATH + DATABASE_NAME);
            if (file.exists()) {
                return;
            }
            AssetManager assetManager = context.getAssets();
            DataInputStream in = new DataInputStream(assetManager.open(DATABASE_NAME));
            DataOutputStream out = new DataOutputStream(new FileOutputStream(file));

            byte[] b = new byte[1024];
            int length;
            while ((length = in.read(b)) != -1) {
                out.write(b, 0, length);
            }

            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void openDatabase() {
        if (sqLiteDatabase == null || !sqLiteDatabase.isOpen()) {
            sqLiteDatabase = SQLiteDatabase.openDatabase(
                    DATABASE_PATH + DATABASE_NAME,
                    null,
                    SQLiteDatabase.OPEN_READONLY
            );
        }
    }

    private void closeDatabase() {
        if (sqLiteDatabase != null && sqLiteDatabase.isOpen()) {
            sqLiteDatabase.close();
        }
    }
    public ArrayList<ItemGrammar> getAllGrammar()
    {
        ArrayList<ItemGrammar> arr=new ArrayList<>();
        openDatabase();
        ItemGrammar itemGrammar=null;
        Cursor cursor = sqLiteDatabase.rawQuery(
                "SELECT * FROM " +"nguphap",
                null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast())
        {
            int indexGrammar=cursor.getInt(cursor.getColumnIndex("indexGrammar"));
                    int level=cursor.getInt(cursor.getColumnIndex("level"));
          String titleGrammar=cursor.getString(cursor.getColumnIndex("titleGrammar"));
            String contentGrammar=cursor.getString(cursor.getColumnIndex("contentGrammar"));
            itemGrammar=new ItemGrammar(indexGrammar,level,titleGrammar,contentGrammar);
            arr.add(itemGrammar);
            cursor.moveToNext();

        }
        closeDatabase();
        return arr;
    }
    public ArrayList<ItemQuestion> getQuestionContest()
    {  openDatabase();
        ArrayList<ItemQuestion> arr=new ArrayList<>();
        int count=0;
        ItemQuestion question=null;
        Cursor cursor = sqLiteDatabase.rawQuery(
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
        closeDatabase();
        return null;
    }

}
