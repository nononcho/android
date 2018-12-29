package com.example.administrator.myapplication;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.administrator.myapplication.sql.UserDbHelper;
import com.example.administrator.myapplication.sql.UserEntry;

public class SqlActivity extends AppCompatActivity {

    private EditText et_id, et_pw, et_nm, et_age;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sql);

        setTitle(R.string.title_sql);

        UserDbHelper dbHelper = new UserDbHelper(getBaseContext());
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        et_id = findViewById(R.id.et_id);
        et_pw = findViewById(R.id.et_pw);
        et_nm = findViewById(R.id.et_nm);
        et_age = findViewById(R.id.et_age);


    }

    public void clkSave(View v) {
        String id = et_id.getText().toString();
        String pw = et_pw.getText().toString();
        String nm = et_nm.getText().toString();
        String age = et_age.getText().toString();
        int iage = Integer.parseInt(age);

        UserDbHelper dbHelper = new UserDbHelper(getBaseContext());
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(UserEntry.LOG_ID, id);
        values.put(UserEntry.LOG_PW, pw);
        values.put(UserEntry.NM, nm);
        values.put(UserEntry.AGE, iage);

        long newRowId = db.insert(UserEntry.T_NM, null, values);
        et_id.setText("");
        et_pw.setText("");
        et_nm.setText("");
        et_age.setText("");

    }

    public void clkMove(View v) {
        Intent intent = new Intent(this, SqlListActivity.class);
        startActivity(intent);
    }
}
