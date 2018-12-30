package com.example.administrator.myapplication;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.myapplication.model.UserVO;
import com.example.administrator.myapplication.sql.UserDbHelper;
import com.example.administrator.myapplication.sql.UserEntry;

import java.util.ArrayList;
import java.util.List;

public class SqlListActivity extends AppCompatActivity {

    private UserDbAdapter adapter;
    private List<UserVO> list = new ArrayList();    //list는 여러개를 담을 수 있는 배열이라고 생각하면 된다. 크기가 가변적이다.
    private ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sql_list);

        setTitle(R.string.title_sqllist);

        adapter = new UserDbAdapter(this, list);

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

    public UserDbAdapter(@NonNull Context context, @NonNull List<UserVO> list) {
        super(context, 0, list);
        this.inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    class ViewHolder {
        TextView tv_id, tv_pw, tv_nm, tv_age;
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

            cv.setTag(vh);
        } else {
            vh = (ViewHolder)cv.getTag();
        }

        UserVO vo = getItem(position);
        vh.tv_id.setText(vo.getLog_id());
        vh.tv_pw.setText(vo.getLog_pw());
        vh.tv_nm.setText(vo.getNm());
        vh.tv_age.setText(Integer.toString(vo.getAge()));

        return cv;
    }
}