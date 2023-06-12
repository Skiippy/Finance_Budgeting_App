package com.example.financebudgetingapp;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseController extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "my_database.db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseController(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        //db.execSQL("CREATE TABLE users(email TEXT PRIMARY KEY, password TEXT, RetirementFund REAL, EmergencyFund REAL)");
        db.execSQL("DROP TABLE Finances");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {



        //db.setVersion(newVersion);

    }
}


