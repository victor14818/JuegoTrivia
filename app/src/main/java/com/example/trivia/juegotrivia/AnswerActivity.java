package com.example.trivia.juegotrivia;

import android.content.Intent;
import android.support.v4.widget.TextViewCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class AnswerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_answer);
        Intent myIntent = getIntent();
        int firstKeyName = myIntent.getIntExtra("pregunta",0);
        TextView tv = (TextView) findViewById(R.id.textView2);
        tv.setText(String.valueOf(firstKeyName));
        tv.setTextSize(40);
    }
}
