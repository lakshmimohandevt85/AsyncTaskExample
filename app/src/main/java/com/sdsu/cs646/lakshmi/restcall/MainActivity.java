package com.sdsu.cs646.lakshmi.restcall;

import android.os.Bundle;

import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.view.View;


public class MainActivity extends AppCompatActivity implements View.OnClickListener
{

    //Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    @Override
    public void onClick(View view) {

    }





    public void ViewReports(View button)
    {
        Intent ViewReports = new Intent(this, com.sdsu.cs646.lakshmi.restcall.ListActivity.class);
        startActivity(ViewReports);
        //ViewReports.putExtra(output);

    }


}
