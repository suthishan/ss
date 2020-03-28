package com.example.ss;

import android.content.Intent;
import android.os.Bundle;

import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class HomeMenu extends AppCompatActivity
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_menu);
    }


    public void ViewData(View v)
    {
        Intent i = new Intent(HomeMenu.this, donar.class);
        startActivity(i);
    }

    public void Donar(View v)
    {
        Intent i = new Intent(HomeMenu.this, DonarRegister.class);
        startActivity(i);
    }

}
