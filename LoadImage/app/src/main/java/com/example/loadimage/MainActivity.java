package com.example.loadimage;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.annotation.Documented;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {
    Button btn;
    ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = findViewById(R.id.btnLoad);
        img =findViewById(R.id.imgInternet);
    //    new ReadContent().execute("https://mybk.hcmut.edu.vn/my/index.action");
        new ReadRSS().execute("https://vnexpress.net/rss/the-gioi.rss");
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new LoadImg().execute("https://pbs.twimg.com/profile_images/823569976342773760/c2RLAG7h_400x400.jpg");
            }
        });
    }
    private  class LoadImg extends AsyncTask<String, Void, Bitmap>{
        Bitmap bitmapImg=null;
        @Override
        protected Bitmap doInBackground(String... strings) {
            try {
                URL url = new URL(strings[0]);
                InputStream inputStream = url.openConnection().getInputStream();
                bitmapImg = BitmapFactory.decodeStream(inputStream);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return bitmapImg;
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            super.onPostExecute(bitmap);
            img.setImageBitmap(bitmap);
        }
    }


    private class ReadContent extends  AsyncTask<String, Void,String>{

        @Override
        protected String doInBackground(String... strings) {
            //add them duoc du lieu string
            StringBuilder stringBuilder = new StringBuilder();

            try {
                URL url = new URL(strings[0]);
                //Mở kết nối
                URLConnection urlConnection = url.openConnection();
                //Lấy dữ liệu theo Stream
                InputStream inputStream = urlConnection.getInputStream();
                //Đọc dữ liệu từ Stream
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                //Lưu vào buffer
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String line ="";
                //Check null
                while ((line=bufferedReader.readLine())!=null){
                        stringBuilder.append(line+"\n");
                }
                //Đóng buffer
                bufferedReader.close();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return stringBuilder.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Toast.makeText(MainActivity.this, s, Toast.LENGTH_LONG).show();
        }
    }

    private class ReadRSS extends  AsyncTask <String,Void, String>{

        @Override
        protected String doInBackground(String... strings) {
            StringBuilder stringBuilder = new StringBuilder();
            try {
                URL url = new URL(strings[0]);
                InputStreamReader inputStreamReader = new InputStreamReader(url.openConnection().getInputStream());
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String line="";
                while ((line = bufferedReader.readLine())!=null){
                    stringBuilder.append(line);
                }
                bufferedReader.close();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return stringBuilder.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            XMLDOMParser parser = new XMLDOMParser();
            Document document = parser.getDocument(s);
            NodeList nodeList =document.getElementsByTagName("item");
            String title ="";
            for(int i=0;i<nodeList.getLength();i++){
                Element element = (Element) nodeList.item(i);
                title += parser.getValue(element,"title")+"\n";
            }
            Toast.makeText(MainActivity.this, "Item:" +nodeList.getLength()+title, Toast.LENGTH_LONG).show();
        }
    }
}