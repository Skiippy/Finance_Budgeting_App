package com.example.financebudgetingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

public class GoalsPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.goals_page);

        ImageView btnSupportPage = findViewById(R.id.btnSupportPage);
        ImageView btnHomePage = findViewById(R.id.btnHomePage);
        ImageView addbtn_goals = findViewById(R.id.addbtn_goals);

        btnSupportPage.setOnClickListener(v -> startSupportPage());
        btnHomePage.setOnClickListener(v -> startOverviewPage());
        addbtn_goals.setOnClickListener(v -> startGoalPage());

        RecyclerView recyclerView=findViewById(R.id.recyclerView);
        DatabaseController databaseController=new DatabaseController(this);
        ArrayList<GoalModel> goalModelArrayList=databaseController.getAllGoals();
        if (goalModelArrayList.size()>0){
            PrimaryAdapter primaryAdapter=new PrimaryAdapter(this,goalModelArrayList,databaseController);
            recyclerView.setAdapter(primaryAdapter);
            LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);

            recyclerView.setLayoutManager(layoutManager);
        }else {

            Toast.makeText(this, "You not have any goals!", Toast.LENGTH_SHORT).show();
        }


    }

    private void startGoalPage() {
        startActivity(new Intent(GoalsPage.this,GoalSettingsActivity.class));
    }

    public void startSupportPage(){
        Intent intent = new Intent(this, SupportPage.class);
        startActivity(intent);
    }
    public void startOverviewPage(){
        startActivity(new Intent(this, OverviewPage.class));
    }

}
