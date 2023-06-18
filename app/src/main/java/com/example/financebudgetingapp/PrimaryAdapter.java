/*
 * by AwoApp
 */

package com.example.financebudgetingapp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;



import java.util.ArrayList;


public class PrimaryAdapter extends RecyclerView.Adapter<PrimaryAdapter.PrimaryAdapters> {

    LayoutInflater layoutInflater;
    Activity activity;
    ArrayList<GoalModel> dataModelArrayList;
    DatabaseController databaseController;




    public PrimaryAdapter(Activity context, ArrayList<GoalModel> dataModelArrayList,
                          DatabaseController databaseController) {
        layoutInflater = LayoutInflater.from(context);
        this.activity = context;

        this.dataModelArrayList = dataModelArrayList;
        this.databaseController = databaseController;
    }

    @NonNull
    @Override
    public PrimaryAdapters onCreateViewHolder(@NonNull ViewGroup viewGroup, final int i) {
        View view = layoutInflater.inflate(R.layout.layout_recyclerview, viewGroup, false);
        PrimaryAdapters recyclerviewHolder = new PrimaryAdapters(view, i);

        return recyclerviewHolder;
    }

    /*For same return item*/
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public void onBindViewHolder(@NonNull PrimaryAdapters recyclerviewHolder, @SuppressLint("RecyclerView") final int i) {

        recyclerviewHolder.tvTitle.setText(dataModelArrayList.get(i).getGoalName());
        recyclerviewHolder.tvPerc.setText(String.valueOf(dataModelArrayList.get(i).getGoalAmount()));
        recyclerviewHolder.progress_bar.setProgress(50,true);
        recyclerviewHolder.constraint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createAlertDialog(i);
            }
        });
    }

    // Create Alert Dialog
    private void createAlertDialog(int position) {
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        LayoutInflater inflater = activity.getLayoutInflater();
        View view = inflater.inflate(R.layout.layout_edit_goal, null); // replace with your layout file name

        EditText editTextGoalName = view.findViewById(R.id.editTextTextGoalName);
        EditText editTextGoalAmount = view.findViewById(R.id.editgoalamount);

        // Populate EditTexts with existing data
        editTextGoalName.setText(dataModelArrayList.get(position).getGoalName());
        editTextGoalAmount.setText(String.valueOf(dataModelArrayList.get(position).getGoalAmount()));

        AppCompatButton btnSubmit = view.findViewById(R.id.btnSubmit);
        AppCompatButton btnCancel = view.findViewById(R.id.btnCancel);

        builder.setView(view);

        AlertDialog dialog = builder.create();

        // Setting up click listener for custom buttons
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Update operations
                String goalName = editTextGoalName.getText().toString();
                double goalAmount = Double.parseDouble(editTextGoalAmount.getText().toString());

                // Update the database
                GoalModel updatedGoal = new GoalModel(goalName, goalAmount);
                databaseController.updateGoal(updatedGoal); // updateGoal: your own update method

                // Update the ArrayList
                dataModelArrayList.set(position, updatedGoal);

                // Notify the adapter
                notifyItemChanged(position);

                dialog.dismiss();
            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }



    @Override
    public int getItemCount() {
        return dataModelArrayList.size();
    }


    class PrimaryAdapters extends RecyclerView.ViewHolder {

        TextView tvTitle, tvPerc;
        ProgressBar progress_bar;
        ConstraintLayout constraint;
        public PrimaryAdapters(@NonNull final View itemView, final int i) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvPerc = itemView.findViewById(R.id.tvPerc);
            progress_bar = itemView.findViewById(R.id.progress_bar);
            constraint = itemView.findViewById(R.id.constraint);




        }

    }


}
