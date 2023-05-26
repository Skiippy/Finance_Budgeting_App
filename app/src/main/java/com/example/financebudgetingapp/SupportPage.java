package com.example.financebudgetingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class SupportPage extends AppCompatActivity {

    boolean expanded = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.support_page);

        ImageView btnSupportPage = (ImageView) findViewById(R.id.btnHomePage);
        ImageView btnGoalsPage = (ImageView) findViewById(R.id.btnGoalsPage);

        btnSupportPage.setOnClickListener(v -> startOverviewPage());
        btnGoalsPage.setOnClickListener(v -> startGoalsPage());

        //defining variable for changing drawables
        @SuppressLint("UseCompatLoadingForDrawables") Drawable arrow_left_image = getResources().getDrawable(R.drawable.arrow_left);
        @SuppressLint("UseCompatLoadingForDrawables") Drawable arrow_downwards_image = getResources().getDrawable(R.drawable.arrow_downwards);

        ImageButton IbtnExpandText = (ImageButton) findViewById(R.id.btnExpandText0);
        TextView descriptionText = (TextView) findViewById(R.id.descriptionText);

        IbtnExpandText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!expanded) {
                    descriptionText.setMaxLines(Integer.MAX_VALUE);
                    IbtnExpandText.setImageDrawable(arrow_left_image);
                    expanded = true;
                }else{
                    descriptionText.setLines(3);
                    IbtnExpandText.setImageDrawable(arrow_downwards_image);
                    expanded = false;
                }
            }
        });

    }

    public void startOverviewPage(){
        Intent intent = new Intent(this, OverviewPage.class);
        startActivity(intent);
    }

    public void startGoalsPage(){
        Intent intent = new Intent(this, GoalsPage.class);
        startActivity(intent);
    }

}