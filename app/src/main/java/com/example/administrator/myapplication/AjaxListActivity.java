package com.example.administrator.myapplication;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.administrator.myapplication.model.BitcoinListVO;
import com.example.administrator.myapplication.model.BitcoinVO;
import com.example.administrator.myapplication.retrofit.APIClient;
import com.example.administrator.myapplication.retrofit.BitcoinService;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AjaxListActivity extends AppCompatActivity {

    private BitcoinAdapter adapter;
    private List<BitcoinVO> list = new ArrayList<BitcoinVO>();
    private ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajax_list);

        setTitle(R.string.title_ajaxlist);

        adapter = new BitcoinAdapter(this, list);
        listview = findViewById(R.id.listview);
        listview.setAdapter(adapter);



        //통신부분(가료가져오는 부분) - start
        BitcoinService service = APIClient.getClient().create(BitcoinService.class);
        Call<BitcoinListVO> call = service.getBitcoin();
        call.enqueue(new Callback<BitcoinListVO>() {
            @Override
            public void onResponse(Call<BitcoinListVO> call, Response<BitcoinListVO> response) {
                if(response.body() != null) {
                    BitcoinListVO listVO = response.body();
                    List<BitcoinVO> data = listVO.getData();

                    list.addAll(data);
//                    for(BitcoinVO vo : data) {
//                        list.add(vo);
//                    }
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onFailure(Call<BitcoinListVO> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}

class BitcoinAdapter extends ArrayAdapter<BitcoinVO> {
    private List<BitcoinVO> list;
    public BitcoinAdapter(Context ctx, List<BitcoinVO> list) { //생성자(특징: 리턴타입이 없음)
        super(ctx, 0, list);
        this.list = list;
    }

    @Override
    public int getCount() {
        Log.i("sbs", "list.size()" + list.size());
        return list.size(); //돌아갈 수(100을 넣으면 100번 돌아감)
    }

    @Override
    public BitcoinVO getItem(int position) {
        return list.get(position);
    }

    //정의 하지 않으면 매번 새로 만든다.
    //성능향상을 위해
    class ViewHolder {
        TextView tv_id, tv_name, tv_symbol, tv_website;
    }

    @Override
    public View getView(int position, View cv, ViewGroup parent) { //cv는 첫 값은 null
        ViewHolder vh;
        if(cv == null) {    //cv가 null이라는 것은 첫음이라는 뜻
            final Context context = parent.getContext();
            LayoutInflater inflater = LayoutInflater.from(context);
            cv = inflater.inflate(R.layout.item_bitcoin, parent, false);

            vh = new ViewHolder();
            vh.tv_id = cv.findViewById(R.id.tv_id);
            vh.tv_name = cv.findViewById(R.id.tv_name);
            vh.tv_symbol = cv.findViewById(R.id.tv_symbol);
            vh.tv_website = cv.findViewById(R.id.tv_website);

            cv.setTag(vh);
        } else {
            vh = (ViewHolder)cv.getTag();
        }

        BitcoinVO vo = getItem(position);
        vh.tv_id.setText(Integer.toString(vo.getId()));
        vh.tv_name.setText(vo.getName());
        vh.tv_symbol.setText(vo.getSymbol());
        vh.tv_website.setText(vo.getWebsite_slug());

        return cv;
    }
}
