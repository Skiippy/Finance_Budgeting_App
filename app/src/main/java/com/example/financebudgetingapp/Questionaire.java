package com.example.financebudgetingapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Questionaire extends AppCompatActivity {

    private int maxList = 0;
    @SuppressLint({"ResourceType", "UseCompatLoadingForDrawables"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.quesrtionnaire_page);

        //editText average salary
        EditText edtAverageSalary = findViewById(R.id.edtAverageSalary);
        LinearLayout main_container = findViewById(R.id.main_container);

        //Emergency Fund components
        RadioButton rbEmergencyFundYes = (RadioButton) findViewById(R.id.rbEmergencyYes);
        RadioButton rbEmergencyFundNo = (RadioButton) findViewById(R.id.rbEmergencyNo);
        LinearLayout llEmergencyFund = (LinearLayout) findViewById(R.id.llEmergencyFund);
        EditText edtAverageExpense = findViewById(R.id.edtAverageExpense);
        EditText edtEmergencyFund = findViewById(R.id.edtEmergencyFund);

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
        EditText edtRetirementFund = findViewById(R.id.edtRetirementFund);

        //setting Retirement fund components visibility based on radio button inputs

        rbRetirementFundYes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (rbRetirementFundYes.isChecked()){
                    llRetirementFund.setVisibility(View.VISIBLE);
                    //updating
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



        //adding 'need' fields
        ImageButton ibAddNeed = findViewById(R.id.ibAddNeed);
        //drawable for edit component
        @SuppressLint("UseCompatLoadingForDrawables") Drawable edit_backkground = getResources().getDrawable(R.drawable.edit_text_background);
        int color = getApplicationContext().getColor(R.color.text);

        //need components
        LinearLayout llNeedsList = findViewById(R.id.llNeedsList);


        ibAddNeed.setOnClickListener(v -> {

            if (maxList <= 4) {

                maxList++;

                LinearLayout llNeed = new LinearLayout(getApplicationContext());
                llNeed.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                llNeed.setPadding(0, 0, 10, 0);

                //adding layout to LinearLayout
                llNeedsList.addView(llNeed);

                //spinner for adding need
                LinearLayout spinnerContainer = new LinearLayout(this);
                LinearLayout.LayoutParams needContainerLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                needContainerLayoutParams.setMargins(0, dpToPx(5), dpToPx(10), 0);
                spinnerContainer.setLayoutParams(needContainerLayoutParams);


                Spinner spNeedList = new Spinner(getApplicationContext());
                LinearLayout.LayoutParams spNeedParams = new LinearLayout.LayoutParams(dpToPx(110), dpToPx(48));
                spNeedList.setLayoutParams(spNeedParams);
                spNeedList.setPadding(dpToPx(10), 0, 0, 0);
                spNeedList.setBackground(edit_backkground);

                spinnerContainer.addView(spNeedList);

                //initializing spinner component
                String[] spinnerContent = new String[]{
                        "Rent", "Utilities", "Food", "Transport", "Clothing", "Debt", "Other"
                };
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                        android.R.layout.simple_spinner_item, spinnerContent);
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                spNeedList.setAdapter(adapter);


                //EditText for adding want amount
                EditText edtNeedAmount = new EditText(getApplicationContext());
                LinearLayout.LayoutParams editNeedAmountLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                editNeedAmountLayoutParams.setMargins(0, dpToPx(5), dpToPx(10), 0);
                edtNeedAmount.setLayoutParams(editNeedAmountLayoutParams);
                edtNeedAmount.setWidth(dpToPx(110));
                edtNeedAmount.setHeight(dpToPx(48));
                edtNeedAmount.setPadding(dpToPx(10), 0, 0, 0);
                edtNeedAmount.setBackground(edit_backkground);
                edtNeedAmount.setTextColor(color);
                edtNeedAmount.setEllipsize(TextUtils.TruncateAt.END);

                //ImageButton for removing the component
                ImageButton ibRemoveNeed = new ImageButton(this);
                ibRemoveNeed.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));
                ibRemoveNeed.setBackground(null);
                ibRemoveNeed.setImageDrawable(getResources().getDrawable(R.drawable.remove_icon));
                ibRemoveNeed.setForegroundGravity(Gravity.CENTER);

                //Removing components when ibRemove is clicked
                ibRemoveNeed.setOnClickListener(view -> {
                    llNeed.removeView(spinnerContainer);
                    llNeed.removeView(edtNeedAmount);
                    llNeed.removeView(ibRemoveNeed);
                    maxList--;
                });

                //add component to layout
                llNeed.addView(spinnerContainer);
                llNeed.addView(edtNeedAmount);
                llNeed.addView(ibRemoveNeed);

            }

        });


        //want components
        ImageButton ibAddWant = findViewById(R.id.ibAddWant);
        LinearLayout llWant = new LinearLayout(getApplicationContext());
        LinearLayout spinnerWantContainer = new LinearLayout(this);
        LinearLayout.LayoutParams spContainerLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        Spinner spWantList = new Spinner(getApplicationContext());
        EditText edtWantAmount = new EditText(getApplicationContext());
        ImageButton ibRemoveWant = new ImageButton(this);



        ibAddWant.setOnClickListener(v -> {

            llWant.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
            llWant.setPadding(0, 0, 10, 0);

            //adding layout to LinearLayout
            LinearLayout llWantsList = findViewById(R.id.llWantsList);
            llWantsList.addView(llWant);


            //EditText for adding want
            spContainerLayoutParams.setMargins(0, dpToPx(5), dpToPx(10), 0);
            spinnerWantContainer.setLayoutParams(spContainerLayoutParams);


            LinearLayout.LayoutParams spWantParams = new LinearLayout.LayoutParams(dpToPx(110), dpToPx(48));
            spWantList.setLayoutParams(spWantParams);
            spWantList.setPadding(dpToPx(10), 0, 0, 0);
            spWantList.setBackground(edit_backkground);

            spinnerWantContainer.addView(spWantList);

            //initializing spinner component
            String[] spinnerContent = new String[]{
                    "Entertainment", "Eating Out", "Travel", "Hobbies", "Clothing", "Other"
            };
            ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                    android.R.layout.simple_spinner_item, spinnerContent);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            spWantList.setAdapter(adapter);
            spWantList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                }
            });

            //EditText for adding want amount
            LinearLayout.LayoutParams editNeedAmountLayoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            editNeedAmountLayoutParams.setMargins(0, dpToPx(5), dpToPx(10), 0);
            edtWantAmount.setLayoutParams(editNeedAmountLayoutParams);
            edtWantAmount.setWidth(dpToPx(110));
            edtWantAmount.setHeight(dpToPx(48));
            edtWantAmount.setPadding(dpToPx(10), 0, 0, 0);
            edtWantAmount.setBackground(edit_backkground);
            edtWantAmount.setTextColor(color);
            edtWantAmount.setEllipsize(TextUtils.TruncateAt.END);
            //edtWantAmount.setInputType(InputType.TYPE_CLASS_NUMBER);

            //ImageButton for removing the component
            ibRemoveWant.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.MATCH_PARENT));
            ibRemoveWant.setBackground(null);
            ibRemoveWant.setImageDrawable(getResources().getDrawable(R.drawable.remove_icon));
            ibRemoveWant.setForegroundGravity(Gravity.CENTER);


            //Removing components when ibRemove is clicked
            ibRemoveWant.setOnClickListener(view -> {
                llWant.removeView(spinnerWantContainer);
                llWant.removeView(edtWantAmount);
                llWant.removeView(ibRemoveWant);
            });

            //add component to layout
            llWant.addView(spinnerWantContainer);
            llWant.addView(edtWantAmount);
            llWant.addView(ibRemoveWant);

        });

        Button btnSubmit = findViewById(R.id.btnQuestionnaireSubmit);
        btnSubmit.setOnClickListener(v -> {

            /*
                    //need components
                LinearLayout llNeed = new LinearLayout(getApplicationContext());
                LinearLayout llNeedsList = findViewById(R.id.llNeedsList);
                LinearLayout spinnerContainer = new LinearLayout(this);
                Spinner spNeedList = new Spinner(getApplicationContext());
                EditText edtNeedAmount = new EditText(getApplicationContext());
                ImageButton ibRemoveNeed = new ImageButton(this);
             */

            //input validation
            //EditText AverageSalary
            if (edtAverageSalary.getText().toString().isEmpty()){
                edtAverageSalary.setBackground(getDrawable(R.drawable.edit_validation_background));
            }
            edtAverageSalary.setOnClickListener(edtAverageSalaryListener -> {
                edtAverageSalary.setBackground(getDrawable(R.drawable.edit_text_background));
            });

            if (edtAverageExpense.getText().toString().isEmpty()){
                edtAverageExpense.setBackground(getDrawable(R.drawable.edit_validation_background));
            }
            edtAverageExpense.setOnClickListener(edtAverageExpenseListener ->{
                edtAverageExpense.setBackground(getDrawable(R.drawable.edit_text_background));
            });


            if (!rbRetirementFundNo.isChecked() && !rbRetirementFundYes.isChecked()){
                rbRetirementFundYes.setButtonTintList(getColorStateList(R.color.red));
                rbRetirementFundNo.setButtonTintList(getColorStateList(R.color.red));
            }
            rbRetirementFundNo.setOnClickListener(rbRetirementNoListener -> {
                rbRetirementFundNo.setButtonTintList(getColorStateList(R.color.text));
                rbRetirementFundYes.setButtonTintList(getColorStateList(R.color.text));
            });
            rbRetirementFundYes.setOnClickListener(rbRetirementNoListener -> {
                rbRetirementFundNo.setButtonTintList(getColorStateList(R.color.text));
                rbRetirementFundYes.setButtonTintList(getColorStateList(R.color.text));
            });

            if (!rbEmergencyFundNo.isChecked() && !rbEmergencyFundYes.isChecked()){
                rbEmergencyFundNo.setButtonTintList(getColorStateList(R.color.red));
                rbEmergencyFundYes.setButtonTintList(getColorStateList(R.color.red));
            }
            rbEmergencyFundNo.setOnClickListener(rbEmergencyNoListener -> {
                rbEmergencyFundNo.setButtonTintList(getColorStateList(R.color.text));
                rbEmergencyFundYes.setButtonTintList(getColorStateList(R.color.text));
            });
            rbEmergencyFundYes.setOnClickListener(rbEmergencyNoListener -> {
                rbEmergencyFundNo.setButtonTintList(getColorStateList(R.color.text));
                rbEmergencyFundYes.setButtonTintList(getColorStateList(R.color.text));
            });

            DatabaseController dbHelper = new DatabaseController(getApplicationContext());
            SQLiteDatabase db = dbHelper.getWritableDatabase();


            //TODO Fix your mess you bot
            //component variables
            double needAmount = 0;
            String needName = "";

            for (int a = 0; a < llNeedsList.getChildCount(); a++) {
                LinearLayout llNeed = (LinearLayout) llNeedsList.getChildAt(a);
                for (int i = 0; i < llNeed.getChildCount(); i++) {
                    View child = llNeed.getChildAt(i);
                    if (child instanceof EditText) {
                        needAmount = Double.parseDouble(((EditText) child).getText().toString());
                    }
                    if (child instanceof LinearLayout) {
                        LinearLayout spinnerContainer = (LinearLayout) child;
                        Spinner spinnerNeed = (Spinner) spinnerContainer.getChildAt(0);
                        needName = spinnerNeed.getSelectedItem().toString();
                    }
                }
            }


            //inserting need list component values
            if (!isTableExists(db, "Finances")){
                db.execSQL("CREATE TABLE Finances (financeId INTEGER PRIMARY KEY AUTOINCREMENT, email TEXT, financeName TEXT, financeAmount REAL, financeType TEXT, FOREIGN KEY (email) REFERENCES users(email))");
            }else{
                String expenseType = "expense";
                db.execSQL("INSERT INTO Finances (email, financeName,  financeAmount, financeType) VALUES(?, ?, ?, ?)",
                        new Object[]{"email", needName, needAmount, expenseType});
            }

            Cursor cursor = db.rawQuery("SELECT * FROM Finances", null);
            while (cursor.moveToNext()){
                TextView test = new TextView(this);
                test.setText(cursor.getString(cursor.getColumnIndex("financeName")));
                main_container.addView(test);
            }
            cursor.close();

            /*
                Cursor cursor = db.rawQuery("SELECT * FROM users", null);
                while (cursor.moveToNext()){
                    TextView test = new TextView(this);
                    test.setText(cursor.getString(cursor.getColumnIndex("RetirementFund")));
                    main_container.addView(test);
                }
                cursor.close();
            }*/

            /*if (!isTableExists(db, "users")){
                db.execSQL("CREATE TABLE users(email TEXT PRIMARY KEY, password TEXT, RetirementFund REAL, EmergencyFund REAL)");
            }else {
                if (rbRetirementFundYes.isChecked() && (!edtRetirementFund.getText().toString().isEmpty())) {
                    String userEmail = "jondoe@mai.com";
                    double RetirementFund = Double.parseDouble(edtRetirementFund.getText().toString());
                    db.execSQL("UPDATE users SET RetirementFund = ? WHERE email = ?",
                            new Object[]{RetirementFund, userEmail});
                    db.close();
                }
                if (rbEmergencyFundYes.isChecked() && (!edtRetirementFund.getText().toString().isEmpty())) {
                    String userEmail = "jondoe@mai.com";
                    double EmergencyFund = Double.parseDouble(edtEmergencyFund.getText().toString());
                    db.execSQL("UPDATE users SET EmergencyFund = ? WHERE email = ?",
                            new Object[]{EmergencyFund, userEmail});
                    db.close();
                }
            }*/

           /* String email = "JohnDoe@mail.com";

            if (!isTableExists(db, "Finances")){
                db.execSQL("CREATE TABLE Finances (financeId INTEGER PRIMARY KEY AUTOINCREMENT, email TEXT, financeName TEXT, financeAmount REAL, financeType TEXT, FOREIGN KEY (email) REFERENCES users(email))");
            }else{
                if (!edtAverageSalary.getText().toString().isEmpty()) {
                    double averageMonthlyExpenses = Double.parseDouble(edtAverageSalary.getText().toString());
                    String expenseType = "income";
                    String averageMonthlyExpenseName = "Monthly Salary";
                    db.execSQL("INSERT INTO Finances (email, financeName,  financeAmount, financeType) VALUES(?, ?, ?, ?)",
                            new Object[]{email, averageMonthlyExpenseName, averageMonthlyExpenses, expenseType});
                }
                db.close();
            }*/

            //startOverviewPage();

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

    public boolean isTableExists(SQLiteDatabase db, String tableName) {
        Cursor cursor = db.rawQuery("SELECT name FROM sqlite_master WHERE type='table' AND name=?", new String[] { tableName });
        boolean tableExists = cursor.moveToFirst();
        cursor.close();
        return tableExists;
    }


}