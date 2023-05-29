package com.example.financebudgetingapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

    public class LoginPage extends AppCompatActivity {
        private  androidx.appcompat.widget.AppCompatButton btnLogin;
        private TextView signupText;
        @SuppressLint("MissingInflatedId")
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.login);

            btnLogin = (AppCompatButton) findViewById(R.id.loginButton);
            btnLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    openOverview();
                }
            });

            signupText = (TextView) findViewById(R.id.signupText);
            signupText.setOnClickListener(new View.OnClickListener() {
                public void onClick(View view) {
                    openSignup();
                }
            });
        }
        public void openOverview() {
            Intent intent = new Intent(this, OverviewPage.class);
            startActivity(intent);
        }

        public void openSignup() {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        }
    }