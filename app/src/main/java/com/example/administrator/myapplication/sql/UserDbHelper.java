package com.example.administrator.myapplication.sql;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class UserDbHelper extends SQLiteOpenHelper {

    public static final int DB_VER = 1;
    public static final String DB_NM = "TestUser.db";   //상수값(대문자(개발자간에 약속)) 설정

    public UserDbHelper(Context context) {  //생성자
        super(context, DB_NM, null, DB_VER);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQL_CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(SQL_DROP_TABLE);
        onCreate(db);
    }

    private static final String SQL_CREATE_TABLE =
            "CREATE TABLE " + UserEntry.T_NM + " (" +
                    UserEntry._ID + " INTEGER PRIMARY KEY," +
                    UserEntry.LOG_ID + " TEXT," +
                    UserEntry.LOG_PW + " TEXT," +
                    UserEntry.NM + " TEXT," +
                    UserEntry.AGE + " INTEGER)";

    private static final String SQL_DROP_TABLE =
            "DROP TABLE IF EXISTS " + UserEntry.T_NM;
}
