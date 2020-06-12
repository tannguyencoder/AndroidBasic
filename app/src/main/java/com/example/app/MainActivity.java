package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for(int i= 0;i<10;i++) {
            Log.d("Tan", i + "");
        }
        //Create new Array
        ArrayList<String> arr = new ArrayList<>();

        //Add element
        arr.add("Ha Noi"); //index=0
        arr.add("Quy Nhon");//index =1
        arr.add("Sai Gon"); // index =2

        //Size of array
        Log.d("tan","Size: "+arr.size());

        //Access Element
        Log.d("tan","Element: "+arr.get(1));

        //Remove Element
        arr.remove(0);
        for(int i=0;i<arr.size();i++){
            Log.d("tan",arr.get(i));
        }
        //similar foreach
        for(String ten: arr){
            Log.d("tan",ten);
        }
        Notice("Tan");
        Notice("helo");
        Notice("Oke");
    }
    private void Notice(String ten){
        Log.d("tan","Hello"+"-"+ten);
    }

}
