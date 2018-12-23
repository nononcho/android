package com.example.administrator.myapplication.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BitcoinListVO {
    @SerializedName("data")
    private List<BitcoinVO> data = null;

    public List<BitcoinVO> getData() {
        return data;
    }

    public void setData(List<BitcoinVO> data) {
        this.data = data;
    }
}
