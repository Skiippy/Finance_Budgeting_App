package com.example.financebudgetingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class OverviewPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.overview);

        ImageView btnSupportPage = (ImageView) findViewById(R.id.btnSupportPage);
        ImageView btnGoalsPage = (ImageView) findViewById(R.id.btnGoalsPage);

        btnSupportPage.setOnClickListener(v -> startSupportPage());
        btnGoalsPage.setOnClickListener(v -> startGoalsPage());

    }

    public void startSupportPage(){
        Intent intent = new Intent(this, SupportPage.class);
        startActivity(intent);
    }
    public void startGoalsPage(){
        Intent intent = new Intent(this, GoalsPage.class);
        startActivity(intent);
    }

}