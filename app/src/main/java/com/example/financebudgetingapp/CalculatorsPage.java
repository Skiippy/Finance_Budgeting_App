package com.example.financebudgetingapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.slider.Slider;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class CalculatorsPage extends AppCompatActivity {

    @SuppressLint({"SetTextI18n", "Range"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        String userEmail = getIntent().getStringExtra("userEmail");

        DatabaseController dbHelper = new DatabaseController(getApplicationContext());

        /*int entryCount = 0;
        Cursor expenseCursor = dbHelper.getExpensesByEmail(userEmail);
        while (expenseCursor.moveToNext()){
            String Name = expenseCursor.getString(expenseCursor.getColumnIndex("financeName"));
            String AMount = expenseCursor.getString(expenseCursor.getColumnIndex("financeAmount"));
            entryCount++;
            Toast.makeText(this, Name, Toast.LENGTH_SHORT).show();
        }*/

        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculators_page);

        //all calculators
        LinearLayout ll50_30_20 = findViewById(R.id.ll50_30_20);
        LinearLayout llEmergencyFundCalculator = findViewById(R.id.llEmergencyFundCalculator);
        LinearLayout llCarAffordabilityCalculator = findViewById(R.id.llCarAffordabilityCalculator);

        //spinner code for changing calculators
        Spinner spCalculatorType = findViewById(R.id.spCalculatorType);
        String[] spinnerContent = new String[] {
                "50/30/20 Rule", "Emergency Fund", "Car Affordability", "House Affordability"
        };
         ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, spinnerContent);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spCalculatorType.setAdapter(adapter);
        spCalculatorType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (position == 0){
                    ll50_30_20.setVisibility(View.VISIBLE);
                }else{
                    ll50_30_20.setVisibility(View.GONE);
                }
                if (position == 1){
                    llEmergencyFundCalculator.setVisibility(View.VISIBLE);
                }else{
                    llEmergencyFundCalculator.setVisibility(View.GONE);
                }
                if (position == 2){
                    llCarAffordabilityCalculator.setVisibility(View.VISIBLE);
                }else{
                    llCarAffordabilityCalculator.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        //50/30/20
        EditText edtGrossIncome = findViewById(R.id.edtGrossIncome);
        Button btnCalculate503020 = findViewById(R.id.btnCalculate503020);
        EditText edtBreakdownNeeds = findViewById(R.id.edtBreakdownNeeds);
        EditText edtBreakdownWants = findViewById(R.id.edtBreakdownWants);
        EditText edtBreakdownSavingInvesting = findViewById(R.id.edtBreakdownSavingInvesting);

        Cursor fiftyThirtyTwentyCursor = dbHelper.getSalaryByEmail("income", userEmail);
        if (fiftyThirtyTwentyCursor != null && fiftyThirtyTwentyCursor.moveToFirst()) {
            edtGrossIncome.setText(fiftyThirtyTwentyCursor.getString(fiftyThirtyTwentyCursor.getColumnIndex("financeAmount")));
            fiftyThirtyTwentyCursor.close();
        }




        ArrayList<Double> allValues = get503020(Double.parseDouble(edtGrossIncome.getText().toString()));

        if (!edtGrossIncome.getText().toString().isEmpty()){
            edtBreakdownNeeds.setText(Double.toString(allValues.get(0)));
            edtBreakdownWants.setText(Double.toString(allValues.get(1)));
            edtBreakdownSavingInvesting.setText(Double.toString(allValues.get(2)));
        }


        btnCalculate503020.setOnClickListener(v -> {
            String grossIncome = edtGrossIncome.getText().toString();
            if (!grossIncome.isEmpty()){
                edtBreakdownNeeds.setText(Double.toString(Integer.parseInt(grossIncome) * 0.5));
                edtBreakdownWants.setText(Double.toString(Integer.parseInt(grossIncome) * 0.3));
                edtBreakdownSavingInvesting.setText(Double.toString(Integer.parseInt(grossIncome) * 0.2));
            }
        });

        //Emergency Fund
        EditText edtMonthlyIncome = findViewById(R.id.edtMonthlyIncome);
        TextView tvEmergencyFundAmount = findViewById(R.id.tvEmergencyFund);

        Cursor EmergencyFundCursor = dbHelper.getEmergencyFundByEmail(userEmail);
        if (EmergencyFundCursor != null && EmergencyFundCursor.moveToFirst()) {
            edtMonthlyIncome.setText(EmergencyFundCursor.getString(EmergencyFundCursor.getColumnIndex("financeAmount")));
            EmergencyFundCursor.close();

        }

        Slider sdNumMonths = findViewById(R.id.sdNumMonths);
        sdNumMonths.addOnChangeListener(new Slider.OnChangeListener() {
            @Override
            public void onValueChange(@NonNull Slider slider, float value, boolean fromUser) {
                if (!edtMonthlyIncome.toString().isEmpty()){
                    int MonthlyIncome = Integer.parseInt(edtMonthlyIncome.getText().toString());
                    tvEmergencyFundAmount.setText(Integer.toString((int) (value * MonthlyIncome)));
                }
            }
        });

        //create on change for edt


        //Car affordability calculator
        ImageButton ibAddCarCost = findViewById(R.id.ibAddCarCost);
        LinearLayout llCostContainer = findViewById(R.id.llCostsContainer);
        llCostContainer.setOrientation(LinearLayout.VERTICAL);
        //editText Background Drawable
        @SuppressLint("UseCompatLoadingForDrawables") Drawable editTextBackground = getResources().getDrawable(R.drawable.edit_text_background);

        ibAddCarCost.setOnClickListener(v -> {
            LinearLayout llCarCostContainer = new LinearLayout(getApplicationContext());
            LinearLayout.LayoutParams llCarCostContainerParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            llCarCostContainerParams.setMargins(0, dpToPx(10), dpToPx(30), dpToPx(10));
            llCarCostContainer.setLayoutParams(llCarCostContainerParams);

            EditText edtCarCost = new EditText(getApplicationContext());
            edtCarCost.setLayoutParams(new ViewGroup.LayoutParams(dpToPx(200), dpToPx(24)));
            edtCarCost.setBackground(editTextBackground);
            edtCarCost.setInputType(InputType.TYPE_CLASS_NUMBER);
            edtCarCost.setTextColor(getColor(R.color.text));


            llCarCostContainer.addView(edtCarCost);
            llCostContainer.addView(llCarCostContainer);
        });

        //getting edtCarCost's values
        TextView tvMonthlyCarExpenses = findViewById(R.id.tvMonthlyCarExpenses);
        Button btnCalculateCarAffordability = findViewById(R.id.btnCalculateCarAffordability);
        //Array for storing edit values
        ArrayList<Integer> edtValues = new ArrayList<>();
        btnCalculateCarAffordability.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < llCostContainer.getChildCount(); i++){
                    EditText child = (EditText) llCostContainer.getChildAt(i);
                    String edtValue = child.getText().toString();
                    if (!edtValue.isEmpty()) {
                        edtValues.add(Integer.parseInt(edtValue));
                    }
                }

                int monthlyCarExpenses = 0;
                for (Integer i: edtValues){
                    monthlyCarExpenses = monthlyCarExpenses + i;
                }
                tvMonthlyCarExpenses.setText(Integer.toString(monthlyCarExpenses));
                edtValues.clear();


                TextView tvMonthlyBudget = findViewById(R.id.tvMonthlyBudget);
                double monthlyBudget = getMonthlyBudget(userEmail, dbHelper);
                tvMonthlyBudget.setText(Double.toString(monthlyBudget));

                TextView tvPercentageOfBudget = findViewById(R.id.tvPercentageOfBudget);
                double percentageOfBudget = (monthlyCarExpenses/monthlyBudget) * 100;
                tvPercentageOfBudget.setText(Double.toString(percentageOfBudget));

                setCarConclusion(percentageOfBudget);
            }
        });





    }

    private int dpToPx(int dp) {
        float scale = getApplicationContext().getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    private ArrayList<Double> get503020(double grossIncome){
        ArrayList<Double> values = new ArrayList<>();
        values.add(grossIncome * 0.5);
        values.add(grossIncome * 0.3);
        values.add(grossIncome * 0.2);
        return values;
    }

    @SuppressLint("Range")
    private double getMonthlyBudget(String userEmail, DatabaseController dbHelper){
        double totalExpenses = 0;
        Cursor needsCursor = dbHelper.getNeedsByEmail(userEmail);
        while (needsCursor.moveToNext()){
            @SuppressLint("Range") double needsAmount = Double.parseDouble(needsCursor.getString(needsCursor.getColumnIndex("financeAmount")));
            totalExpenses += needsAmount;
        }
        needsCursor.close();

        Cursor wantsCursor = dbHelper.getNeedsByEmail(userEmail);
        while (needsCursor.moveToNext()){
            @SuppressLint("Range") double wantsAmount = Double.parseDouble(wantsCursor.getString(wantsCursor.getColumnIndex("financeAmount")));
            totalExpenses += wantsAmount;
        }
        wantsCursor.close();

        Toast.makeText(this, "Total Expenses: " + totalExpenses, Toast.LENGTH_SHORT).show();

        double totalIncome = 0;
        Cursor incomeCursor = dbHelper.getIncomeByEmail(userEmail);
        if (incomeCursor.moveToFirst()){
            totalIncome = Double.parseDouble(incomeCursor.getString(incomeCursor.getColumnIndex("financeAmount")));
        }

        Toast.makeText(this, "Total Income: " + totalIncome, Toast.LENGTH_SHORT).show();

        return totalIncome - totalExpenses;
    }

    private void setCarConclusion(double percentageOfBudget){
        TextView tvCarConclusion = findViewById(R.id.tvCarConclusion);
        if (percentageOfBudget < 20){
            tvCarConclusion.setText("Can Afford");
            tvCarConclusion.setTextColor(getColor(R.color.green));
        }else if ((percentageOfBudget > 20) && (percentageOfBudget < 30)){
            tvCarConclusion.setText("Not Recommended");
            tvCarConclusion.setTextColor(getColor(R.color.yellow));
        }else{
            tvCarConclusion.setText("Cannot Afford");
            tvCarConclusion.setTextColor(getColor(R.color.red));
        }
    }

}