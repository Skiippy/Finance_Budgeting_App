package com.example.financebudgetingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;

public class GoalsPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.goals_page);

        ImageButton ibProfileButton = findViewById(R.id.ibProfileButton);
        ImageView btnSupportPage = findViewById(R.id.btnSupportPage);
        ImageView btnHomePage = findViewById(R.id.btnHomePage);

        ibProfileButton.setOnClickListener(v -> startProfilePage());
        btnSupportPage.setOnClickListener(v -> startSupportPage());
        btnHomePage.setOnClickListener(v -> startOverviewPage());

    }

    public void startSupportPage(){
        Intent intent = new Intent(this, SupportPage.class);
        startActivity(intent);
    }
    public void startProfilePage() {
        startActivity(new Intent(this, ProfilePage.class));

    }
    public void startOverviewPage(){
            startActivity(new Intent(this, OverviewPage.class));
    }

}
