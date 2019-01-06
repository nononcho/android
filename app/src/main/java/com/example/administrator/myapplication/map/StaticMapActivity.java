package com.example.administrator.myapplication.map;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.administrator.myapplication.R;

public class StaticMapActivity extends AppCompatActivity {

    public static final String MAP_API_KEY = "AIzaSyBiud-UNKafzaE4AGMpu9-YMPIidsWHY00";

    private EditText et_addr;
    private ImageView iv_map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_static_map);

        et_addr = findViewById(R.id.et_addr);
        iv_map = findViewById(R.id.iv_map);

    }

    public void search(View v) {

    }
}
