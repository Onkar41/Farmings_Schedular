package com.example.farmings_schedular;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SuggestionActivity extends AppCompatActivity {
CardView graps,groundnut,cotton,sugarcan,corn,harbara;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggestion);

        graps = findViewById(R.id.graps);
        groundnut = findViewById(R.id.groundnut);
        cotton = findViewById(R.id.cotton);
        sugarcan = findViewById(R.id.sugarcan);
        corn = findViewById(R.id.corn);
        harbara = findViewById(R.id.harbara);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setSelectedItemId(R.id.bottom_home);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId()==R.id.bottom_home){
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                finish();
                return true;
            } else if (item.getItemId()==R.id.bottom_suggetion) {
                return true;
            }else if (item.getItemId()==R.id.bottom_add) {
                startActivity(new Intent(getApplicationContext(), AddActivity.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                finish();
                return true;
            } else if (item.getItemId()==R.id.bottom_clculate) {
                startActivity(new Intent(getApplicationContext(), CalculatorActivity.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                finish();
                return true;
            }
            else if (item.getItemId()==R.id.bottom_feedback) {
                startActivity(new Intent(getApplicationContext(),Feedback.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                finish();
                return true;
            }

            return false;
        });

        graps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(), DisplaySuggestions.class);
                i.putExtra("crop", "graps");
                startActivity(i);
            }
        });
        groundnut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), DisplaySuggestions.class);
                i.putExtra("crop", "groundnut");
                startActivity(i);
            }
        });
        cotton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), DisplaySuggestions.class);
                i.putExtra("crop", "cotton");
                startActivity(i);
            }
        });
        sugarcan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), DisplaySuggestions.class);
                i.putExtra("crop", "sugarcan");
                startActivity(i);
            }
        });
        corn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), DisplaySuggestions.class);
                i.putExtra("crop", "corn");
                startActivity(i);
            }
        });
        harbara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), DisplaySuggestions.class);
                i.putExtra("crop", "harbara");
                startActivity(i);
            }
        });

    }
}