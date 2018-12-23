package com.example.administrator.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.administrator.myapplication.join.JoinActivity;

public class MainActivity extends AppCompatActivity {
    Button btn_Calc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_Calc = findViewById(R.id.btn_Calc);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    //    public(기본) void(기본) 임의 (View(기본) 임의)
    //    makeText static 이기 때문에 class.makeText 사용가능함
    public void btn_Calc(View v) {

        Intent intent = null;
        switch (v.getId()) {
            case R.id.btn_Calc:
                intent = new Intent(MainActivity.this, SimpleCalcActivity.class);
                break;
            case  R.id.btn_join:
                intent = new Intent(MainActivity.this, JoinActivity.class);
                break;
            case R.id.btn_ajaxList:
                intent = new Intent(MainActivity.this, AjaxListActivity.class);
                break;
            case  R.id.btn_imageView:
                intent = new Intent(MainActivity.this, ImageViewActivity.class);
                break;
        }

        if (intent != null ) {
            startActivity(intent);
        }

    }
}
