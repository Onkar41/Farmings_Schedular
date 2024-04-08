package com.example.farmings_schedular.Admin;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.farmings_schedular.CalculatorActivity;
import com.example.farmings_schedular.DisplaySuggestions;
import com.example.farmings_schedular.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class AdminSuggestionsView extends AppCompatActivity {
    CardView graps,groundnut,cotton,sugarcan,corn,harbara;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_suggestions_view);


        graps = findViewById(R.id.graps_admin);
        groundnut = findViewById(R.id.groundnut_admin);
        cotton = findViewById(R.id.cotton_admin);
        sugarcan = findViewById(R.id.sugarcan_admin);
        corn = findViewById(R.id.corn_admin);
        harbara = findViewById(R.id.harbara_admin);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setSelectedItemId(R.id.bottom_home);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId()==R.id.bottom_home){
                startActivity(new Intent(getApplicationContext(), Home.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                finish();
                return true;
            } else if (item.getItemId()==R.id.bottom_add) {
                startActivity(new Intent(getApplicationContext(), AddSuggestions.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                finish();
                return true;
            } else if (item.getItemId()==R.id.bottom_clculate) {
                return true;

            }

            return false;
        });


        graps.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(getApplicationContext(), DisplaySuggestionsAdmin.class);
                i.putExtra("crop", "graps");
                startActivity(i);
            }
        });
        groundnut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), DisplaySuggestionsAdmin.class);
                i.putExtra("crop", "groundnut");
                startActivity(i);
            }
        });
        cotton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), DisplaySuggestionsAdmin.class);
                i.putExtra("crop", "cotton");
                startActivity(i);
            }
        });
        sugarcan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), DisplaySuggestionsAdmin.class);
                i.putExtra("crop", "sugarcan");
                startActivity(i);
            }
        });
        corn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), DisplaySuggestionsAdmin.class);
                i.putExtra("crop", "corn");
                startActivity(i);
            }
        });
        harbara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), DisplaySuggestionsAdmin.class);
                i.putExtra("crop", "harbara");
                startActivity(i);
            }
        });

    }
}