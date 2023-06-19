package com.example.financebudgetingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class OverviewPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.overview);

        String userEmail = getIntent().getStringExtra("userEmail");

        ImageView btnSupportPage = findViewById(R.id.btnSupportPage);
        ImageView btnGoalsPage = findViewById(R.id.btnGoalsPage);
        ImageView btnCalculatorPage = findViewById(R.id.btnCalculatorPage);

        btnSupportPage.setOnClickListener(v -> startSupportPage());
        btnGoalsPage.setOnClickListener(v -> startGoalsPage());
        btnCalculatorPage.setOnClickListener(v ->{
            Intent intent = new Intent(this, CalculatorsPage.class);
            intent.putExtra("userEmail", userEmail);
            startActivity(intent);
        });


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