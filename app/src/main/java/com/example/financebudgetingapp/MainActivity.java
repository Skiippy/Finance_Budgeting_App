package com.example.financebudgetingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {
private  androidx.appcompat.widget.AppCompatButton btnSignup;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);

        btnSignup = (AppCompatButton) findViewById(R.id.buttonSignup);
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openQuestionnaire();
            }
        });
    }
        public void openQuestionnaire() {
        Intent intent = new Intent(this, Questionaire.class);
        startActivity(intent);
    }
}