package com.example.administrator.myapplication.join;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

import com.example.administrator.myapplication.R;

public class JoinActivity extends AppCompatActivity {

    private EditText et_name, et_age;
    private Spinner spin_habit;
    private RadioButton rb_1, rb_2;

    private String habitValue = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_join);

        setTitle(R.string.title_join);

//        getString(R.string.app_name);
        et_name = findViewById(R.id.et_name);
        et_age = findViewById(R.id.et_age);
        spin_habit = findViewById(R.id.spin_habit);
        spin_habit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.i("sbs", parent.getItemAtPosition(position).toString());
                habitValue = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        rb_1 = findViewById(R.id.rb_1);
        rb_2 = findViewById(R.id.rb_2);
    }

    public void clkJoin(View v) {
        String name = et_name.getText().toString();
        String age = et_age.getText().toString();
        String sex = "";
        if (rb_1.isChecked()) {
            sex = "여자";
        } else {
            sex = "남자";
        }

        Log.i("sbs", "name: " + name);
        Log.i("sbs", "age: " + age);
        Log.i("sbs", "habit: " + habitValue);
        Log.i("sbs", "sex: " + sex);

        Intent intent = new Intent(JoinActivity.this, JoinResultActivity.class);

                            //레이블, 실제 값
        intent.putExtra("name", name);
        intent.putExtra("age", age);
        intent.putExtra("habit", habitValue);
        intent.putExtra("sex", sex);
        startActivity(intent);
    }
}
