package com.example.ngocsang.studyenglish.screen.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ngocsang.studyenglish.R;
import com.example.ngocsang.studyenglish.constant.Constant;
import com.example.ngocsang.studyenglish.utils.DialogUtil;
import com.example.ngocsang.studyenglish.utils.SharePreferencesUtil;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by Ngoc Sang on 10/7/2016.
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText edtAccount,edtPassWord;
    private TextInputLayout account,pass;
    private Button login,register;
    private FirebaseAuth auth;
    private FirebaseAuth.AuthStateListener listener;
    private CheckBox checkBox;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_screen_login);
        findViews();
        init();
        declareClick();
    }

    @Override
    protected void onStart() {
        super.onStart();
        auth.addAuthStateListener(listener);
    }

    private void findViews()
    {
        edtAccount=(EditText)findViewById(R.id.edit_account);
        edtPassWord=(EditText)findViewById(R.id.edit_pass);
        login=(Button)findViewById(R.id.btn_login);
        account=(TextInputLayout) findViewById(R.id.text_input_account);
        pass=(TextInputLayout) findViewById(R.id.text_input_pass_word);
        checkBox=(CheckBox)findViewById(R.id.check_maintain_login);
        register=(Button)findViewById(R.id.btn_register);
    }
    private void init()
    {
                 auth=FirebaseAuth.getInstance();
        listener=new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser()!=null)
                {
                    SharePreferencesUtil.putBoolean(Constant.CHECK_LOGIN,true);
                    SharePreferencesUtil.putString(Constant.ID_USER,firebaseAuth.getCurrentUser().getProviderId().toString());
                    startActivity(new Intent(LoginActivity.this,MainActivity.class));
                    finish();
                }
            }
        };
    }
    private void declareClick()
    {
        login.setOnClickListener(this);
        register.setOnClickListener(this);
        edtAccount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
              if(charSequence!="")
              {
                  account.setErrorEnabled(false);
              }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
        edtPassWord.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence!="")
                {
                    pass.setErrorEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
    private void doRegister()
    {
        if(edtAccount.getText().toString().equals(""))
        {
            account.setError("Bạn phải nhập tài khoản!");
            return;
        }
        if(edtPassWord.getText().toString().equals(""))
        {
            account.setError("Bạn phải nhập mật khẩu!");
            return;
        }
        DialogUtil.showLoading(getApplicationContext());
        String email=edtAccount.getText().toString();
        String passWord=edtPassWord.getText().toString();
        auth.createUserWithEmailAndPassword(email,passWord).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {   DialogUtil.disMissLoading();
                    Toast.makeText(getApplicationContext(),"Đăng kí thành công! Mời bạn tiếp tục đăng nhập.",Toast.LENGTH_LONG).show();
                }
                else {
                    DialogUtil.disMissLoading();
                    Toast.makeText(getApplicationContext(),"Đăng kí xảy ra lỗi!",Toast.LENGTH_LONG).show();
                }
            }
        });
    }
   private void doLogin()
   {
        if(edtAccount.getText().toString().equals(""))
        {
            account.setError("Bạn phải nhập tài khoản!");
            return;
        }
       if(edtPassWord.getText().toString().equals(""))
       {
           account.setError("Bạn phải nhập mật khẩu!");
           return;
       }
       DialogUtil.showLoading(getApplicationContext());
       String email=edtAccount.getText().toString();
       String passWord=edtPassWord.getText().toString();
                auth.signInWithEmailAndPassword(email,passWord).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                           if(task.isSuccessful())
                           {   DialogUtil.disMissLoading();
                               Intent i = new Intent(LoginActivity.this, MainActivity.class);
                               startActivity(i);
                               overridePendingTransition(R.anim.icon_anim_fade_in, R.anim.icon_anim_fade_out);
                               finish();
                           }
                        else {
                               DialogUtil.disMissLoading();
                               Toast.makeText(getApplicationContext(),"Đăng nhập xảy ra lỗi",Toast.LENGTH_LONG).show();
                           }
                    }
                });
   }
    @Override
    public void onClick(View view)
    {   switch (view.getId())
    {
        case R.id.btn_register:
            doRegister();
            break;
        case R.id.btn_login:
            doLogin();
            break;
    }
        doLogin();
    }
}
