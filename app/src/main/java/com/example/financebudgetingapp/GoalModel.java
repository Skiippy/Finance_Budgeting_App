package com.example.financebudgetingapp;

public class GoalModel {

    String goalName;
    double goalAmount;
    int id;


    public GoalModel(String goalName, double goalAmount) {
        this.goalName = goalName;
        this.goalAmount = goalAmount;
    }
    public GoalModel(String goalName, double goalAmount,int id) {
        this.goalName = goalName;
        this.goalAmount = goalAmount;
        this.id = id;
    }
    public String getGoalName() {
        return goalName;
    }

    public double getGoalAmount() {
        return goalAmount;
    }

    public int getId() {
        return id;
    }

    public void setGoalName(String goalName) {
        this.goalName = goalName;
    }

    public void setGoalAmount(double goalAmount) {
        this.goalAmount = goalAmount;
    }

    public void setId(int id) {
        this.id = id;
    }
}
