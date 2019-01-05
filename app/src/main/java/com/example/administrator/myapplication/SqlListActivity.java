package com.example.administrator.myapplication;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.myapplication.model.UserVO;
import com.example.administrator.myapplication.sql.UserDbHelper;
import com.example.administrator.myapplication.sql.UserEntry;

import java.util.ArrayList;
import java.util.List;

interface _CallBack {
    void _getData();
    void _moveMod(long _id);
}

public class SqlListActivity extends AppCompatActivity {

    private UserDbAdapter adapter;
    private List<UserVO> list = new ArrayList();    //list는 여러개를 담을 수 있는 배열이라고 생각하면 된다. 크기가 가변적이다.
    private ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sql_list);

        _CallBack cb = new _CallBack() {
            @Override
            public void _getData() {
                getData();
            }

            @Override
            public void _moveMod(long _id) {
                Intent intent = new Intent(SqlListActivity.this, SqlModActivity.class);
                intent.putExtra("_id", _id);
                startActivity(intent);
            }
        };

        setTitle(R.string.title_sqllist);

        adapter = new UserDbAdapter(this, list, cb);

        listview = findViewById(R.id.listView); //id값이 listView를 찾는 방법은 위 코드  setContentView(R.layout.activity_sql_list);에서 activity_sql_list로 연결했기때문
        listview.setAdapter(adapter);

        getData();

    }

    private void getData() {
        UserDbHelper dbHelper = new UserDbHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        String[] projection = {
                UserEntry._ID,
                UserEntry.LOG_ID,
                UserEntry.LOG_PW,
                UserEntry.NM,
                UserEntry.AGE
        };

        Cursor cursor = db.query(
                UserEntry.T_NM,     //The table to query
                projection,         //The array of columns to return (pass null to get all)
                null,       //The columns for the WHERE clause
                null,   //The values for the WHERE clause
                null,       //don't group the rows
                null,           //don't filter by row groups
                null            //The sort order
        );

        list.clear();   //삭제 시 리프레쉬되지 않음. 빼고 해보기
        while (cursor.moveToNext()) {
            long _id = cursor.getLong(cursor.getColumnIndexOrThrow(UserEntry._ID));
            String log_id = cursor.getString(cursor.getColumnIndexOrThrow(UserEntry.LOG_ID));
            String log_pw = cursor.getString(cursor.getColumnIndexOrThrow(UserEntry.LOG_PW));
            String nm = cursor.getString(cursor.getColumnIndexOrThrow(UserEntry.NM));
            int age = cursor.getInt(cursor.getColumnIndexOrThrow(UserEntry.AGE));

            UserVO vo = new UserVO(_id, log_id, log_pw, nm, age);
            list.add(vo);
        }
        adapter.notifyDataSetChanged();
    }
}

class UserDbAdapter extends ArrayAdapter<UserVO> {
    private LayoutInflater inflater;
    private UserDbHelper dbHelper;
    private SQLiteDatabase db;
    private _CallBack cb;

    public UserDbAdapter(@NonNull Context context, @NonNull List<UserVO> list, _CallBack cb) {
        super(context, 0, list);
        this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        dbHelper = new UserDbHelper(context);
        db = dbHelper.getWritableDatabase();
        this.cb = cb;
    }

    class ViewHolder {
        TextView tv_id, tv_pw, tv_nm, tv_age;
        Button btn_mod, btn_del; //onClick 대시에 사용
    }
    @Override
    public View getView(int position, View cv, ViewGroup parent) {
        ViewHolder vh;
        if(cv == null && inflater != null) {
            cv = inflater.inflate(R.layout.item_user_db, parent, false);

            vh = new ViewHolder();
            vh.tv_id = cv.findViewById(R.id.tv_id);
            vh.tv_pw = cv.findViewById(R.id.tv_pw);
            vh.tv_nm = cv.findViewById(R.id.tv_nm);
            vh.tv_age = cv.findViewById(R.id.tv_age);
            vh.btn_mod = cv.findViewById(R.id.btn_mod);
            vh.btn_del = cv.findViewById(R.id.btn_del);

            cv.setTag(vh);
        } else {
            vh = (ViewHolder)cv.getTag();
        }


        final UserVO vo = getItem(position);
        final long _id = vo.get_id();
        vh.tv_id.setText(vo.getLog_id());
        vh.tv_pw.setText(vo.getLog_pw());
        vh.tv_nm.setText(vo.getNm());
        vh.tv_age.setText(Integer.toString(vo.getAge()));

        //삭제 버튼
        vh.btn_del.setOnClickListener(new View.OnClickListener() {      //인터페이스는  객체와 안됨. but 여기서 객체화 한 것처럼 보임
            //            vh.btn_del.setOnClickListener(new Click() {   //이렇게 쓸려면 line 141을 정의해주고 써야함.
//            @Override
//            public void onClick(View view) {
//
//            }
            @Override
            public void onClick(View view) {
                Log.i("sbs", "dasfdasdf: " + vo.getNm());   // line 115에 final 붙여야 됨
                delDB(_id);     //line 120 final 쓰지 않으면 Error 뜸.
            }
        });

        //수정 버튼
        vh.btn_mod.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View view) {
                cb._moveMod(_id);
            }
        });


        return cv;
    }
//    class Click implements View.OnClickListener {     //line 127 보충설명
//
//        @Override
//        public void onClick(View view) {
//
//        }
//    }

    private void delDB(long _id) {
        String selection = UserEntry._ID  + " = ?";
        String[] selectionArgs = { Long.toString(_id) };

        int deletedRows = db.delete(UserEntry.T_NM, selection, selectionArgs);
        if(deletedRows > 0) {
            cb._getData();
        }
    }
}