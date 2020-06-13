package com.example.app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView txt_result;
    EditText edt_Start,edt_End;
    Button btn_Random;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Maping();

        btn_Random.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //trim using cut space end or start
                String start = edt_Start.getText().toString().trim();
                String end = edt_End.getText().toString().trim();
                //check impty
                if(start.isEmpty()||end.length() ==0){
                    Toast.makeText(MainActivity.this,"Iput number plz!!",Toast.LENGTH_SHORT).show();
                }else{
                    //cast string to int
                    int num1= Integer.parseInt(start);
                    int num2 = Integer.parseInt(end);

                    Random random = new Random();
                    //random 0 to num2-num1+1
                    int num = random.nextInt(num2-num1+1)+num1;
                    //cast num to string
                    txt_result.setText(String.valueOf(num));
                }

            }
        });
    }
    private void Maping(){
        txt_result = findViewById(R.id.textView);
        edt_Start = findViewById(R.id.editText);
        edt_End = findViewById(R.id.editText2);
        btn_Random= findViewById(R.id.button);
    }
}
