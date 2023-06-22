package com.example.financebudgetingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.database.Cursor;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
public class OverviewPage extends AppCompatActivity {


    @SuppressLint({"ResourceAsColor", "Range"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.overview);

        DatabaseController dbHelper = new DatabaseController(getApplicationContext());
        String userEmail = getIntent().getStringExtra("userEmail");

        double TotalExpenses= 0;
        LinearLayout linearNeeds= findViewById(R.id.linearNeeds);
        LinearLayout linearWants = findViewById(R.id.linearWants);
        TextView tvTotalExpense = findViewById(R.id.tvTotalExpense);


        //needs
        Cursor needCursor = dbHelper.getNeedsByEmail(userEmail);
        while (needCursor.moveToNext()){
            @SuppressLint("Range") String Name = needCursor.getString(needCursor.getColumnIndex("financeName"));
            @SuppressLint("Range") String Amount = needCursor.getString(needCursor.getColumnIndex("financeAmount"));
            TotalExpenses += Double.parseDouble(Amount);
            linearNeeds.addView(createProgressBar(Name, Amount));
        }

        //wants
        Cursor wantCursor = dbHelper.getWantByEmail(userEmail);
        while (wantCursor.moveToNext()){
            @SuppressLint("Range") String Name = wantCursor.getString(wantCursor.getColumnIndex("financeName"));
            @SuppressLint("Range") String Amount = wantCursor.getString(wantCursor.getColumnIndex("financeAmount"));
            TotalExpenses += Double.parseDouble(Amount);
            linearWants.addView(createProgressBar(Name, Amount));
        }

        tvTotalExpense.setText("Expenditure: "+Double.toString(TotalExpenses));

        //investments
        LinearLayout LinearInvest= findViewById(R.id.LinearInvest);
        Cursor InvestmentsCursor = dbHelper.getInvestmentsByEmail(userEmail);
        while (InvestmentsCursor.moveToNext()){
            @SuppressLint("Range") String Name = InvestmentsCursor.getString(InvestmentsCursor.getColumnIndex("financeName"));
            @SuppressLint("Range") String Amount = InvestmentsCursor.getString(InvestmentsCursor.getColumnIndex("financeAmount"));
            LinearInvest.addView(createProgressBar(Name, Amount));
        }

        TextView TotalIncome = findViewById(R.id.TotalIncome);
        Cursor IncomeCursor = dbHelper.getIncomeByEmail(userEmail);
        if (IncomeCursor.moveToFirst()){
            TotalIncome.setText(IncomeCursor.getString(IncomeCursor.getColumnIndex("financeAmount")));
        }

        ImageView btnSupportPage = findViewById(R.id.btnSupportPage);
        ImageView btnGoalsPage = findViewById(R.id.btnGoalsPage);
        ImageView btnCalculatorPage = findViewById(R.id.btnCalculatorPage);

        btnSupportPage.setOnClickListener(v -> startSupportPage());
        btnGoalsPage.setOnClickListener(v -> startGoalsPage());
        btnCalculatorPage.setOnClickListener(v -> {
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

    public LinearLayout createProgressBar(String name, String Amount){
        LinearLayout ProgressBarContainer= new LinearLayout(this);
        ProgressBarContainer.setOrientation(LinearLayout.VERTICAL);

        TextView ProgressBarName= new TextView(this);
        ProgressBarName.setText(name);
        ProgressBarName.setTextColor(getColor(R.color.text ));
        ProgressBar ProgressNeeds= new ProgressBar(this, null, android.R.attr.progressBarStyleHorizontal);
        ProgressNeeds.setId(R.id.expense_progress); // Set the ID
        ProgressNeeds.setLayoutParams(new LinearLayout.LayoutParams(300, 11)); // Set width and height
        ProgressNeeds.setMax(1000);
        ProgressNeeds.setProgressTintList(ColorStateList.valueOf(Color.parseColor("#4AA800")));


        ProgressNeeds.setProgress(Integer.parseInt(Amount));
        ProgressBarContainer.addView(ProgressBarName);
        ProgressBarContainer.addView(ProgressNeeds);
        return  ProgressBarContainer;
    }
}