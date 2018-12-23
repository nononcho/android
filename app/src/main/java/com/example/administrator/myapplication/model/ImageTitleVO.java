package com.example.administrator.myapplication.model;

import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class ImageTitleVO {
    private String url, title;
    private List<ImageTitleVO> listArray = new ArrayList();

    public ImageTitleVO(String url, String title){
        this.url = url;
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
