package com.example.administrator.goalee;

import android.app.DatePickerDialog;
import android.app.Notification;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

public class Home extends AppCompatActivity  {

    int num=6;
    Button[] btnWord;
    LinearLayout linear;
    View.OnClickListener btnClicked;
    String[] varsity;
    Button viewAllBtn;

    Map mp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        varsity = new String[]{"CUET", "BUET",  "KUET","CU", "SUST", "JU",
                "du","ru","ku","mu","su","lu","pu"};
        num = 6;
        btnWord = new Button[num];
        buttonBuilder();

        mp = new HashMap();
        mp.put("CUET", "http://readingbd.com/cuet-admission-circular/");
        mp.put("BUET", "http://readingbd.com/buet-admission-circular/");
        mp.put("KUET", "http://readingbd.com/kuet-admission-circular/");
        mp.put("CU", "https://readingbd.com/chittagong-university-admission-notice/");
        mp.put("SUST", "http://readingbd.com/sust-admission-circular/");
        mp.put("JU", "https://readingbd.com/jahangirnagar-university-admission-notice/");

        mp.put("du", "https://readingbd.com/jahangirnagar-university-admission-notice/");
        mp.put("ru", "https://readingbd.com/jahangirnagar-university-admission-notice/");
        mp.put("ku", "https://readingbd.com/jahangirnagar-university-admission-notice/");
        mp.put("mu", "https://readingbd.com/jahangirnagar-university-admission-notice/");
        mp.put("su", "https://readingbd.com/jahangirnagar-university-admission-notice/");
        mp.put("lu", "https://readingbd.com/jahangirnagar-university-admission-notice/");
        mp.put("pu", "https://readingbd.com/jahangirnagar-university-admission-notice/");





        viewAllBtn = findViewById(R.id.button);
        viewAllBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Home.this, MyListView.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void buttonBuilder() {
        linear = (LinearLayout) findViewById(R.id.linear);
        //i=0;
        for (int i = 0; i < btnWord.length; i++) {
            btnWord[i] = new Button(this);
            btnWord[i].setHeight(50);
            btnWord[i].setWidth(50);
            btnWord[i].setId(i);
            btnWord[i].setText(varsity[i]);
            btnWord[i].setTextColor(getResources().getColor(R.color.white));
            if(i%2==0){btnWord[i].setBackgroundColor(getResources().getColor(R.color.colorPrimary));}
            else{btnWord[i].setBackgroundColor(getResources().getColor(R.color.colorPrimaryDark));}
            btnWord[i].setTag(i);
            // btnWord[i].setOnClickListener(btnClicked);
            btnWord[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // display circular
                    String key = varsity[view.getId()];
                    Models.exam = key;
                    Models.site = (String)mp.get(key);
                    Intent intent = new Intent(Home.this, browser.class);
                    startActivity(intent);
                    finish();
                }
            });

            linear.addView(btnWord[i]);
        }
    }



}
