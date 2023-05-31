package com.example.financebudgetingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.media.Image;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class SupportPage extends AppCompatActivity {

    boolean expanded = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.support_page);

        //scrollView that holds all containers
        LinearLayout llBody = (LinearLayout) findViewById(R.id.llBody);
        //Container drawable
        @SuppressLint("UseCompatLoadingForDrawables") Drawable llContainerDrawable = getResources().getDrawable(R.drawable.support_foreground);



        //selected number of times
        for (int i = 0; i < 3; i++){
            //container for holding all components
            LinearLayout llContainer = new LinearLayout(this);
            //llContainer.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            //llContainer.getLayoutParams().height = LinearLayout.LayoutParams.MATCH_PARENT;
            //llContainer.getLayoutParams().width = LinearLayout.LayoutParams.WRAP_CONTENT;
            llContainer.setOrientation(LinearLayout.VERTICAL);
            llContainer.setBackground(llContainerDrawable);
            llContainer.setPadding(10, 0, 0, 0);
            //add margin bottom

            LinearLayout llContainerHeader = new LinearLayout(this);
            llContainerHeader.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            //LinearLayout.LayoutParams llContainerHeaderParams = (LinearLayout.LayoutParams) llContainerHeader.getLayoutParams();
            //llContainerHeaderParams.setMargins(0, 0, 0, 10);
            llContainerHeader.setOrientation(LinearLayout.HORIZONTAL);
            //llContainerHeader.setLayoutParams(llContainerHeaderParams);

            //adding ContainerHeader to Main Container
            llContainer.addView(llContainerHeader);


            ImageView ivHeaderIcon = new ImageView(this);
            ivHeaderIcon.setLayoutParams(new ViewGroup.LayoutParams(dpToPx(24), dpToPx(24)));
            ivHeaderIcon.setImageDrawable(getResources().getDrawable(R.drawable.investion_icon));

            TextView tvHeading = new TextView(this);
            tvHeading.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            tvHeading.setText("Investing");

            //View vSpacer = new View(this);
            //View.LayoutParams vSpacerParams = (LinearLayout.LayoutParams) vSpacer.getLayoutParams();
            //vSpacerParams.weight = 1;
            //vSpacer.setLayoutParams(vSpacerParams);

            ImageButton ibExpandText = new ImageButton(this);
            ibExpandText.setLayoutParams(new ViewGroup.LayoutParams(dpToPx(24), dpToPx(24)));
            ibExpandText.setImageDrawable(getResources().getDrawable(R.drawable.arrow_downwards));


            //adding header compnents to header container
            llContainerHeader.addView(ivHeaderIcon);
            llContainerHeader.addView(tvHeading);
            //llContainerHeader.addView(vSpacer);

            llBody.addView(llContainer);


        }

        //navigation Components
        ImageView btnSupportPage = (ImageView) findViewById(R.id.btnHomePage);
        ImageView btnGoalsPage = (ImageView) findViewById(R.id.btnGoalsPage);
        ImageButton ibProfileButton = (ImageButton) findViewById(R.id.ibProfileButton);
        ImageView ibCalculatorsPage = (ImageView) findViewById(R.id.ibCalculatorsPage);

        //navigation
        btnSupportPage.setOnClickListener(v -> startOverviewPage());
        btnGoalsPage.setOnClickListener(v -> startGoalsPage());
        ibProfileButton.setOnClickListener(v -> startProfilePage());
        ibCalculatorsPage.setOnClickListener(v -> startCalculatorsPage());

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

    public void startCalculatorsPage(){
        startActivity(new Intent(this, CalculatorsPage.class));
    }

    private int dpToPx(int dp) {
        float scale = getApplicationContext().getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }


}