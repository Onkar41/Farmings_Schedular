package com.example.farmings_schedular;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.farmings_schedular.Admin.Home;

public class firstpage extends AppCompatActivity {
Button singup;
TextView tv2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firstpage);
        singup = (Button) findViewById(R.id.signup);
        tv2 = (TextView) findViewById(R.id.textView2);
//        startService(new Intent(firstpage.this,ReminderService.class));
        singup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(), register.class);
                startActivity(i);
            }
        });
        tv2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(getApplicationContext(), Login.class);
                startActivity(i);
            }
        });
    }
}