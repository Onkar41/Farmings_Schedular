package com.example.farmings_schedular;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class DeleteRemainders extends AppCompatActivity {
    private dbManager db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = new dbManager(this);
        Intent ri = getIntent();
        final String rdelete=ri.getStringExtra("rid");
        if(rdelete != "") {
            db.delete(rdelete);
            Intent intentBack = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intentBack);
        }
    }
}