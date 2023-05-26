package com.example.financebudgetingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;

public class Questionaire extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quesrtionnaire_page);

        //Retirement Fund Information Visibility
        RadioButton rbEmergencyFundYes = (RadioButton) findViewById(R.id.rbEmergencyYes);
        RadioButton rbEmergencyFundNo = (RadioButton) findViewById(R.id.rbEmergencyNo);
        LinearLayout llEmergencyFund = (LinearLayout) findViewById(R.id.llEmergencyFund);

        rbEmergencyFundYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rbEmergencyFundYes.isChecked()){
                    llEmergencyFund.setVisibility(View.VISIBLE);
                }
            }
        });

        rbEmergencyFundNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rbEmergencyFundNo.isChecked()){
                    llEmergencyFund.setVisibility(View.GONE);
                }
            }
        });


        //Retirement Fund Information Visibility
        RadioButton rbRetirementFundYes = (RadioButton) findViewById(R.id.rbRetirementYes);
        RadioButton rbRetirementFundNo = (RadioButton) findViewById(R.id.rbRetirementNo);
        LinearLayout llRetirementFund = (LinearLayout) findViewById(R.id.llRetirementFund);

        rbRetirementFundYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rbRetirementFundYes.isChecked()){
                    llRetirementFund.setVisibility(View.VISIBLE);
                }
            }
        });

        rbRetirementFundNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rbRetirementFundNo.isChecked()){
                    llRetirementFund.setVisibility(View.GONE);
                }
            }
        });



        //navigation components
        Button btnSubmit = (Button) findViewById(R.id.btnQuestionnaireSubmit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startOverviewPage();
            }
        });

    }

    public void startOverviewPage(){
        Intent intent = new Intent(this, OverviewPage.class);
        startActivity(intent);
    }
}