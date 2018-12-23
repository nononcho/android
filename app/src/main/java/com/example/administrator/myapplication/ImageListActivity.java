package com.example.administrator.myapplication;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.administrator.myapplication.model.ImageTitleVO;

import java.util.ArrayList;
import java.util.List;

public class ImageListActivity extends AppCompatActivity {
    private ListView listView;
    private List<ImageTitleVO> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mage_list);
        listView = findViewById(R.id.listView);

        ImageTitleVO vo1 = new ImageTitleVO("https://kr.best-wallpaper.net/wallpaper/m/1607/Danube-River-Szechenyi-Chain-Bridge-night-lights-Budapest-Hungary_m.jpg", "부타페스트");
        ImageTitleVO vo2 = new ImageTitleVO("https://travelblog.expedia.co.kr/wp-content/uploads/2016/12/08.jpg", "비행기");
        ImageTitleVO vo3 = new ImageTitleVO("http://hello-e1.com/wp-content/uploads/2016/05/%EB%AD%94%EA%B0%80-%EB%82%A8%EB%8A%94-%EC%97%AC%ED%96%89-%ED%95%98%EB%8A%94-3%EA%B0%80%EC%A7%80-%EB%B0%A9%EB%B2%95-1024x683.jpg", "YOLO");
        ImageTitleVO vo4 = new ImageTitleVO("https://wonderfulmind.co.kr/wp-content/uploads/2018/05/travel-alone-600x336.jpg", "YOLO2");
        ImageTitleVO vo6 = new ImageTitleVO("https://wonderfulmind.co.kr/wp-content/uploads/2018/05/travel-alone-600x336.jpg", "YOLO2");

        list.add(vo1);
        list.add(vo2);
        list.add(vo3);
        list.add(vo4);
        list.add(vo6);

        list.add(new ImageTitleVO("https://wonderfulmind.co.kr/wp-content/uploads/2018/05/travel-alone-600x336.jpg", "YOLO3"));
    }
}

class ImageListAdapter extends ArrayAdapter<ImageTitleVO> {
    private List<ImageTitleVO> list;

    public ImageListAdapter(Context ctx, List<ImageTitleVO> list) {
        super(ctx, 0, list);
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public View getView(int position, View cv, ViewGroup parent) {
        return cv;
    }
}
