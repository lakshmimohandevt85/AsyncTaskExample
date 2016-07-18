package com.sdsu.cs646.lakshmi.restcall;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class DetailedViewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_view);


    Intent intent = getIntent();
    String content = ""+intent.getStringExtra("content");
        TextView  textview = (TextView) findViewById(R.id.textView_detail);
        textview.setText(content);

    }
}
