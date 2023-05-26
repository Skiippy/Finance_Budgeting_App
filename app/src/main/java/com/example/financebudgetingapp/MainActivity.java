package com.example.financebudgetingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
private  androidx.appcompat.widget.AppCompatButton btnSignup;
private TextView loginText;
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
        loginText = (TextView) findViewById(R.id.loginText);
        loginText.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                openLogin();
            }
        });
    }
        public void openQuestionnaire() {
        Intent intent = new Intent(this, Questionaire.class);
        startActivity(intent);
    }

    public void openLogin() {
        Intent intent = new Intent(this, LoginPage.class);
        startActivity(intent);
    }
}