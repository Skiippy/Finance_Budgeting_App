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


        //navigation Components
        ImageView btnSupportPage = (ImageView) findViewById(R.id.btnHomePage);
        ImageView btnGoalsPage = (ImageView) findViewById(R.id.btnGoalsPage);
        ImageButton ibProfileButton = (ImageButton) findViewById(R.id.ibProfileButton);

        //navigation
        btnSupportPage.setOnClickListener(v -> startOverviewPage());
        btnGoalsPage.setOnClickListener(v -> startGoalsPage());
        ibProfileButton.setOnClickListener(v -> startProfilePage());

        //defining variable for changing drawables
        @SuppressLint("UseCompatLoadingForDrawables") Drawable arrow_left_image = getResources().getDrawable(R.drawable.arrow_left);
        @SuppressLint("UseCompatLoadingForDrawables") Drawable arrow_downwards_image = getResources().getDrawable(R.drawable.arrow_downwards);

        //expandable text components
        ImageButton IbtnExpandText = (ImageButton) findViewById(R.id.btnExpandText0);
        TextView descriptionText = (TextView) findViewById(R.id.descriptionText);

        //expanding text when button clicked
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
        startActivity(new Intent(this, OverviewPage.class));
    }

    public void startGoalsPage(){
        startActivity(new Intent(this, GoalsPage.class));
    }

    public void startProfilePage(){
        startActivity(new Intent(this, ProfilePage.class));
    }

}