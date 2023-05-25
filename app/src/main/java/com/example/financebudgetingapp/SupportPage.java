package com.example.financebudgetingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class SupportPage extends AppCompatActivity {

    boolean expanded = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.support_page);

        @SuppressLint("UseCompatLoadingForDrawables") Drawable arrow_left_image = getResources().getDrawable(R.drawable.arrow_left);
        @SuppressLint("UseCompatLoadingForDrawables") Drawable arrow_downwards_image = getResources().getDrawable(R.drawable.arrow_downwards);

        ImageButton IbtnExpandText = (ImageButton) findViewById(R.id.btnExpandText);
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
}