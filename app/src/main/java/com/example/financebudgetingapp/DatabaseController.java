package com.example.financebudgetingapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

public class DatabaseController extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "my_database.db";
    private static final int DATABASE_VERSION = 1;

    SQLiteDatabase db = this.getWritableDatabase();

    public DatabaseController(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //db.execSQL("CREATE TABLE users(email TEXT PRIMARY KEY, password TEXT, RetirementFund REAL, EmergencyFund REAL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS Finances (financeId INTEGER PRIMARY KEY AUTOINCREMENT, email TEXT, financeName TEXT, financeAmount REAL, financeType TEXT, FOREIGN KEY (email) REFERENCES users(email))");
        //db.execSQL("DROP TABLE Finances");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {



        //db.setVersion(newVersion);

    }


    public Cursor getAll(){
        return this.db.rawQuery("SELECT * FROM Finances", null);
    }

    public Cursor getColumnValue(String columnName, String condition){
        String[] columns = {columnName};
        String whereClause = columnName + " = ?";
        String[] whereArgs = {condition};

        return db.query("Finances", columns, whereClause, whereArgs, null, null, null);
    }

    public void updateRecord(String columnName, String columnValue, String condition){
        ContentValues values = new ContentValues();
        values.put(columnName, columnValue);
        db.update("Finances", values, condition, null);

    }

    public void insert(String email, String financeName, double financeAmount, String type){
        this.db.execSQL("INSERT INTO Finances (email, financeName,  financeAmount, financeType) VALUES(?, ?, ?, ?)",
                new Object[]{email, financeName, financeAmount, type});
    }

    public void addGoal(GoalModel goalModel) {
    }

    public ArrayList<GoalModel> getAllGoals() {
        return null;
    }

    public void updateGoal(GoalModel updatedGoal) {
    }
}


