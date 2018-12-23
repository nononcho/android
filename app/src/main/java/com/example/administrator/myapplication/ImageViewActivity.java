package com.example.administrator.myapplication;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;

public class ImageViewActivity extends AppCompatActivity {
    private TextView tv_title;      //activity_image_view.xml과 같은 형식으로.. 이름은 같게
    private ImageView iv_1, iv_2, iv_3, iv_4, iv_5;   //activity_image_view.xml과 같은 형식으로.. 이름은 같게

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_view);
        setTitle(R.string.title_imageview);         //상단 파란배경부분 타이틀(strings.xml에서 가져오는 것)
        tv_title = findViewById(R.id.tv_title);     //activity_image_view.xml과 연결
        tv_title.setText(R.string.title_imageview); //상단 타이틀(strings.xml에서 가져오는 것)
        iv_1 = findViewById(R.id.iv_1);             //activity_image_view.xml과 연결
        iv_2 = findViewById(R.id.iv_2);             //activity_image_view.xml과 연결
        iv_3 = findViewById(R.id.iv_3);
        iv_4 = findViewById(R.id.iv_4);
        iv_5 = findViewById(R.id.iv_5);

        iv_1.setImageResource(R.drawable.budapest); //이미지 화면에 출력

        new DownloadImageTask(iv_2).execute("https://kr.best-wallpaper.net/wallpaper/m/1607/Danube-River-Szechenyi-Chain-Bridge-night-lights-Budapest-Hungary_m.jpg");
        //실행과 동시에 이미지 로딩
        new DownloadImageTask(iv_3).execute("https://travelblog.expedia.co.kr/wp-content/uploads/2016/12/08.jpg");
        new DownloadImageTask(iv_4).execute("http://hello-e1.com/wp-content/uploads/2016/05/%EB%AD%94%EA%B0%80-%EB%82%A8%EB%8A%94-%EC%97%AC%ED%96%89-%ED%95%98%EB%8A%94-3%EA%B0%80%EC%A7%80-%EB%B0%A9%EB%B2%95-1024x683.jpg");
        new DownloadImageTask(iv_5).execute("https://wonderfulmind.co.kr/wp-content/uploads/2018/05/travel-alone-600x336.jpg");
    }
                                                //들어오는 값, 나가는 값, 리턴하는 값
    private class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImageTask(ImageView bmImage) {   //생성자, class이름과 같음, 리턴값이 없음)
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return mIcon11;
        }
        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }
}
