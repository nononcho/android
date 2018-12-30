package com.example.administrator.myapplication.model;

public class UserVO {
    //private 안 써도되는것은 final(상수) 일 때
    private long _id;   //DB 인덱스 값을 받을 때 long 값이여서
    private String log_id;
    private String log_pw;
    private String nm;
    private int age;

    public UserVO(){}

    //은닉화 후 접근 방법: 1. 생성자 2. 메소드를 통해서
    public UserVO(long _id, String log_id, String log_pw, String nm, int age) {
        this._id = _id;
        this.log_id = log_id;
        this.log_pw = log_pw;
        this.nm = nm;
        //this.age = age;
        setAge(age);
    }

    public long get_id() { return _id; }

    public void set_id(long _id) {
        this._id = _id;
    }

    public String getLog_id() {
        return log_id;
    }

    public void setLog_id(String log_id) {
        this.log_id = log_id;
    }

    public String getLog_pw() {
        return log_pw;
    }

    public void setLog_pw(String log_pw) {
        this.log_pw = log_pw;
    }

    public String getNm() {
        return nm;
    }

    public void setNm(String nm) {
        this.nm = nm;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if(age < 150 && age >= 0) {
            this.age = age;
        }
    }
}
