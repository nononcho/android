package com.example.administrator.myapplication.sql;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.example.administrator.myapplication.R;

public class SqlModActivity extends AppCompatActivity {

    private EditText et_id, et_pw, et_nm, et_age;
    private long _id;
    private  UserDbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mod_sql);

        setTitle(R.string.title_sqlmod);

//        UserDbHelper dbHelper = new UserDbHelper(getBaseContext());
//        SQLiteDatabase db = dbHelper.getWritableDatabase();

        et_id = findViewById(R.id.et_id);
        et_pw = findViewById(R.id.et_pw);
        et_nm = findViewById(R.id.et_nm);
        et_age = findViewById(R.id.et_age);

        dbHelper = new UserDbHelper(SqlModActivity.this);

        Intent intent = getIntent();
        _id = intent.getLongExtra("_id", 0);
        getData(_id);


    }

    private void getData(long _id) {
        if(_id > 0) {
            SQLiteDatabase db = dbHelper.getReadableDatabase();
            String query = "SELECT log_id, log_pw, nm, age FROM " + UserEntry.T_NM + " WHERE _id = " + _id;
            Cursor cursor = db.rawQuery(query, null);

            if(cursor.moveToNext()) {
                String log_id = cursor.getString(cursor.getColumnIndexOrThrow(UserEntry.LOG_ID));
                String log_pw = cursor.getString(cursor.getColumnIndexOrThrow(UserEntry.LOG_PW));
                String nm = cursor.getString(cursor.getColumnIndexOrThrow(UserEntry.NM));
                int age = cursor.getInt(cursor.getColumnIndexOrThrow(UserEntry.AGE));

                et_id.setText(log_id);
                et_pw.setText(log_pw);
                et_nm.setText(nm);
                et_age.setText(Integer.toString(age));
            }
        }
    }

    public void clkSave(View v) {
        String id = et_id.getText().toString();
        String pw = et_pw.getText().toString();
        String nm = et_nm.getText().toString();
        String age = et_age.getText().toString();
        int iage = Integer.parseInt(age);

        UserDbHelper dbHelper = new UserDbHelper(getBaseContext());
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        //String query = "UPDATE " + UserEntry.T_NM + " SET " + UserEntry.LOG_ID + " = '" + _id + "' " + " , log_pw = '" + pw + "' " + " , nm = '" + nm + "' " + " , age = '" + age;

        ContentValues values = new ContentValues();
        values.put(UserEntry.LOG_ID, id);
        values.put(UserEntry.LOG_PW, pw);
        values.put(UserEntry.NM, nm);
        values.put(UserEntry.AGE, iage);

        String selection = UserEntry._ID + " = ?";
        String[] selectionArgs = { Long.toString(_id)};

        int count = db.update( UserEntry.T_NM, values, selection, selectionArgs);

        finish();

    }

}
