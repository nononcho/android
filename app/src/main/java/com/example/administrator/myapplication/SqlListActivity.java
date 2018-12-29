package com.example.administrator.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.administrator.myapplication.model.UserVO;

import java.util.ArrayList;
import java.util.List;

public class SqlListActivity extends AppCompatActivity {

    private List<UserVO> list = new ArrayList();    //list는 여러개를 담을 수 있는 배열이라고 생각하면 된다. 크기가 가변적이다.
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sql_list);

        listView = findViewById(R.id.listView);
    }
}
