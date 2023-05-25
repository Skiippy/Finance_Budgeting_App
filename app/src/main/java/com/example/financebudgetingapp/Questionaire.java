package com.example.financebudgetingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Questionaire extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quesrtionnaire_page);

        Button btnSubmit = (Button) findViewById(R.id.btnQuestionnaireSubmit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startSupportPage();
            }
        });

    }

    public void startSupportPage(){
        Intent intent = new Intent(this, SupportPage.class);
        startActivity(intent);
    }

}