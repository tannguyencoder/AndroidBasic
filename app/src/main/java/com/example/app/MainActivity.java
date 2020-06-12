package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String hoten ="Nguyen Duy Tan";
        //Show in logcat
        Log.d("Hello",hoten);

        int a=1;
        while(a<10){
            Log.d("Tan",""+a);
            a++;
        }
    }
}
