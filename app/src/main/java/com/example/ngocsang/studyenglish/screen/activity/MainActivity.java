package com.example.ngocsang.studyenglish.screen.activity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.res.Configuration;
import android.speech.RecognizerIntent;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.ngocsang.studyenglish.R;
import com.example.ngocsang.studyenglish.callback.OnReceiveVoice;
import com.example.ngocsang.studyenglish.screen.fragment.forum.ForumFragment;
import com.example.ngocsang.studyenglish.screen.fragment.home.HomeFragment;
import com.example.ngocsang.studyenglish.screen.fragment.study.StudyFragment;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle toggle;
    private final int REQ_CODE_SPEECH_INPUT = 100;
    private OnReceiveVoice receiveVoice;
    private View headerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
        init();
        declareClicks();
    }
    public void startVoice() {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.UK);
        intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
                "Giọng Nói");
        try {
            startActivityForResult(intent, REQ_CODE_SPEECH_INPUT);
        } catch (ActivityNotFoundException a) {
            Toast.makeText(getApplicationContext(),
                    "không hỗ trợ",
                    Toast.LENGTH_SHORT).show();
        }
    }

    public void setReceiveVoice(OnReceiveVoice receiveVoice) {
        this.receiveVoice = receiveVoice;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch (requestCode) {
            case REQ_CODE_SPEECH_INPUT: {
                if (resultCode == RESULT_OK && null != data) {

                    ArrayList<String> result = data
                            .getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
                    if(receiveVoice!=null)
                    {
                        receiveVoice.onResult(result.get(0));
                    }

                }
                break;
            }

        }
    }
    private void findViews()
    {
        drawerLayout=(DrawerLayout)findViewById(R.id.drawerLayout);
        navigationView=(NavigationView)findViewById(R.id.navigationView);
        toolbar=(Toolbar)findViewById(R.id.tool_bar);

    }
    private void init()
    {
          toggle=new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open_drawer,R.string.close_drawer);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.icon_menu);
        StudyFragment studyFragment=new StudyFragment();
        replaceMainFragment(studyFragment,false,"study");
        toolbar.setTitle("Học Tập");
    }
    private void declareClicks()
    {
          drawerLayout.setDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        toggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home)
        {
            drawerLayout.openDrawer(GravityCompat.START);
            return true;
        }
         return super.onOptionsItemSelected(item);


    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START))
        {
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }




    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.study:
                StudyFragment studyFragment=new StudyFragment();
                replaceMainFragment(studyFragment,false,"study");
                toolbar.setTitle("Học Tập");
                break;
            case R.id.setting:
                toolbar.setTitle("Cài Đặt");
                break;
        }
        item.setChecked(true);
        drawerLayout.closeDrawer(GravityCompat.START);
        return false;
    }
    public void replaceMainFragment(Fragment fragment,boolean addBackToStack,String tag)
    {
        FragmentManager manager=getSupportFragmentManager();
        FragmentTransaction transaction=manager.beginTransaction();
        if(addBackToStack)
        {
            transaction.addToBackStack(null);
        }
        transaction.replace(R.id.frame_container,fragment,tag);
        transaction.commit();
    }
    public void replaceFullScreen(Fragment fragment,boolean addBackToStack,String tag)
    {
        FragmentManager manager=getSupportFragmentManager();
        FragmentTransaction transaction=manager.beginTransaction();
        if(addBackToStack)
        {
            transaction.addToBackStack(null);
        }
        transaction.replace(R.id.main_root,fragment,tag);
        transaction.commit();
    }
}
