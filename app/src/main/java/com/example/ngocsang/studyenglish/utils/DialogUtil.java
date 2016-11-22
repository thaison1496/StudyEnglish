package com.example.ngocsang.studyenglish.utils;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.Window;
import android.view.WindowManager;

import com.example.ngocsang.studyenglish.R;

/**
 * Created by Ngoc Sang on 10/7/2016.
 */
public class DialogUtil {
private static DialogUtil mInstance;
    private static Dialog dialog;
    private static ProgressDialog progressDialog;
    private static boolean isShowing=false;
    private DialogUtil()
    {

    }
    public static DialogUtil getInstance()
    {
        if(mInstance==null)
        {
            mInstance=new DialogUtil();
        }
        return mInstance;
    }
    private static Dialog getDialog(Context context)
    {
        if(dialog==null)
        {
            dialog=new Dialog(context);
            dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        }
        return dialog;
    }
    public static void showProgressDialog(Context mContext)
    {
        if(mContext!=null)
        {
            progressDialog=new ProgressDialog(mContext);
            progressDialog.setCancelable(false);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.setProgressStyle(R.attr.indeterminateProgressStyle);
            progressDialog.setIndeterminate(true);
            progressDialog.show();
        }
    }
    public static void showLoading(Context context)
    {
        if(context!=null&&isShowing==false)
    {
        dialog=getDialog(context);
        dialog.getWindow().setType(WindowManager.LayoutParams.TYPE_SYSTEM_ALERT);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.dialog_loading);
        dialog.setCancelable(false);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

    }

    }
    public static void disMissLoading()
    {
        if(dialog!=null&&dialog.isShowing())
        {
            dialog.dismiss();
            dialog=null;
        }
    }

}
