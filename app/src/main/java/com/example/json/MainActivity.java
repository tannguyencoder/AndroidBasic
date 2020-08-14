package com.example.json;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //     new  ReadJSONObject().execute("https://khoapham.vn/KhoaPhamTraining/json/tien/demo1.json");
        new ReadJSONArray().execute("https://khoapham.vn/KhoaPhamTraining/json/tien/demo2.json");
    }


    //Read Json Array
    private class ReadJSONArray extends AsyncTask<String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            StringBuilder content = new StringBuilder();
            try {
                URL url = new URL(strings[0]);
                InputStreamReader inputStreamReader = new InputStreamReader(url.openConnection().getInputStream());

                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                String line = "";
                while ((line = bufferedReader.readLine()) != null) {
                    content.append(line);
                }
                bufferedReader.close();

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return content.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            try {
                JSONObject object = new JSONObject(s);
                JSONArray array = object.getJSONArray("danhsach");
                for (int i = 0; i < array.length(); i++) {
                   JSONObject obj = array.getJSONObject(i);
                   String name = obj.getString("khoahoc");
                    Toast.makeText(MainActivity.this, name + "", Toast.LENGTH_SHORT).show();
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }

        }
    }

    //Read Json Obecject
//    private class ReadJSONObject extends AsyncTask<String, Void, String> {
//
//        @Override
//        protected String doInBackground(String... strings) {
//            StringBuilder content = new StringBuilder();
//            try {
//                URL url = new URL(strings[0]);
//                InputStreamReader inputStreamReader = new InputStreamReader(url.openConnection().getInputStream());
//
//                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
//
//                String line = "";
//                while ((line = bufferedReader.readLine()) != null) {
//                    content.append(line);
//                }
//                bufferedReader.close();
//
//            } catch (MalformedURLException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//
//            return content.toString();
//        }
//
//        @Override
//        protected void onPostExecute(String s) {
//            super.onPostExecute(s);
//            try {
//                JSONObject object = new JSONObject(s);
//                String monhoc = object.getString("monhoc");
//                Toast.makeText(MainActivity.this, monhoc, Toast.LENGTH_SHORT).show();
//            } catch (JSONException e) {
//                e.printStackTrace();
//            }
//
//
//        }
//    }
}