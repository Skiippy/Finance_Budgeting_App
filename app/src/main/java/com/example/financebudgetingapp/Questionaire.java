package com.example.financebudgetingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;

public class Questionaire extends AppCompatActivity {
    @SuppressLint({"ResourceType", "UseCompatLoadingForDrawables"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quesrtionnaire_page);

        //Emergency Fund components
        RadioButton rbEmergencyFundYes = (RadioButton) findViewById(R.id.rbEmergencyYes);
        RadioButton rbEmergencyFundNo = (RadioButton) findViewById(R.id.rbEmergencyNo);
        LinearLayout llEmergencyFund = (LinearLayout) findViewById(R.id.llEmergencyFund);

        //setting Emergency fund components visibility based on radio button inputs
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


        //Retirement Fund Components
        RadioButton rbRetirementFundYes = (RadioButton) findViewById(R.id.rbRetirementYes);
        RadioButton rbRetirementFundNo = (RadioButton) findViewById(R.id.rbRetirementNo);
        LinearLayout llRetirementFund = (LinearLayout) findViewById(R.id.llRetirementFund);

        //setting Retirement fund components visibility based on radio button inputs
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
        //navigation
        btnSubmit.setOnClickListener(v -> startOverviewPage());


        //adding 'need' fields
        ImageButton ibAddNeed = (ImageButton) findViewById(R.id.ibAddNeed);
        //drawable for edit component
        @SuppressLint("UseCompatLoadingForDrawables") Drawable edit_backkground = getResources().getDrawable(R.drawable.edit_text_background);
        //text colour
        Context context = this;
        Resources resources = context.getResources();
        int color = resources.getColor(R.color.text);


        ibAddNeed.setOnClickListener(v -> {
            LinearLayout llNeed = new LinearLayout(getApplicationContext());
            llNeed.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            llNeed.setPadding(0, 0, 10, 0);

            //adding layout to LinearLayout
            LinearLayout llNeedsList = (LinearLayout) findViewById(R.id.llNeedsList);
            llNeedsList.addView(llNeed);


            //EditText for adding want
            EditText edtNeedName = new EditText(getApplicationContext());
            edtNeedName.setWidth(dpToPx(110));
            edtNeedName.setHeight(dpToPx(48));
            edtNeedName.setPadding(dpToPx(10), 0, 0, 0);
            edtNeedName.setBackground(edit_backkground);
            edtNeedName.setTextColor(color);
            edtNeedName.setEllipsize(TextUtils.TruncateAt.END);
            setMargins(edtNeedName, 0, 10, 10, 0);

            //EditText for adding want amount
            EditText edtNeedAmount = new EditText(getApplicationContext());
            edtNeedAmount.setWidth(dpToPx(110));
            edtNeedAmount.setHeight(dpToPx(48));
            edtNeedAmount.setPadding(dpToPx(10), 0, 0, 0);
            edtNeedAmount.setBackground(edit_backkground);
            edtNeedAmount.setTextColor(color);
            edtNeedAmount.setEllipsize(TextUtils.TruncateAt.END);

            //ImageButton for removing the component
            ImageButton ibRemoveNeed = new ImageButton(this);
            ibRemoveNeed.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            ibRemoveNeed.setBackground(null);
            ibRemoveNeed.setImageDrawable(getResources().getDrawable(R.drawable.remove_icon));

            //Removing components when ibRemove is clicked
            ibRemoveNeed.setOnClickListener(view -> {
                llNeed.removeView(edtNeedName);
                llNeed.removeView(edtNeedAmount);
                llNeed.removeView(ibRemoveNeed);
            });

            //add component to layout
            llNeed.addView(edtNeedName);
            llNeed.addView(edtNeedAmount);
            llNeed.addView(ibRemoveNeed);

        });

        //Adding 'want' field
        ImageButton ibAddWant = (ImageButton) findViewById(R.id.ibAddWant);

        ibAddWant.setOnClickListener(v -> {
            LinearLayout llWant = new LinearLayout(getApplicationContext());
            llWant.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            llWant.setPadding(0, 0, 10, 0);

            //adding layout to LinearLayout
            LinearLayout llWantsList = (LinearLayout) findViewById(R.id.llWantsList);
            llWantsList.addView(llWant);


            //EditText for adding want
            EditText edtWantName = new EditText(getApplicationContext());
            edtWantName.setWidth(dpToPx(110));
            edtWantName.setHeight(dpToPx(48));
            edtWantName.setPadding(dpToPx(10), 0, 0, 0);
            edtWantName.setBackground(edit_backkground);
            edtWantName.setTextColor(color);
            edtWantName.setEllipsize(TextUtils.TruncateAt.END);
            setMargins(edtWantName, 0, 10, 10, 0);

            //EditText for adding want amount
            EditText edtWantAmount = new EditText(getApplicationContext());
            edtWantAmount.setWidth(dpToPx(110));
            edtWantAmount.setHeight(dpToPx(48));
            edtWantAmount.setPadding(dpToPx(10), 0, 0, 0);
            edtWantAmount.setBackground(edit_backkground);
            edtWantAmount.setTextColor(color);
            edtWantAmount.setEllipsize(TextUtils.TruncateAt.END);

            //ImageButton for removing the component
            ImageButton ibRemoveNeed = new ImageButton(this);
            ibRemoveNeed.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            ibRemoveNeed.setBackground(null);
            ibRemoveNeed.setImageDrawable(getResources().getDrawable(R.drawable.remove_icon));

            //Removing components when ibRemove is clicked
            ibRemoveNeed.setOnClickListener(view -> {
                llWant.removeView(edtWantName);
                llWant.removeView(edtWantAmount);
                llWant.removeView(ibRemoveNeed);
            });

            //add component to layout
            llWant.addView(edtWantName);
            llWant.addView(edtWantAmount);
            llWant.addView(ibRemoveNeed);

        });



    }

    public void startOverviewPage(){
        Intent intent = new Intent(this, OverviewPage.class);
        startActivity(intent);
    }

    private int dpToPx(int dp) {
        float scale = getApplicationContext().getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    private void setMargins (View view, int left, int top, int right, int bottom) {
        if (view.getLayoutParams() instanceof ViewGroup.MarginLayoutParams) {
            ViewGroup.MarginLayoutParams p = (ViewGroup.MarginLayoutParams) view.getLayoutParams();
            p.setMargins(left, top, right, bottom);
            view.setLayoutParams(p);
        }
    }

}