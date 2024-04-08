package com.example.farmings_schedular.Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;


import com.example.farmings_schedular.CalculatorActivity;


import com.example.farmings_schedular.R;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Home extends AppCompatActivity {


   private ArrayList<Farmers> list;
    RecyclerView recyclerView;
    MyUsersAdapter adapter;

    DatabaseReference database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);




        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setSelectedItemId(R.id.bottom_home);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId()==R.id.bottom_home){
                return true;
            } else if (item.getItemId()==R.id.bottom_add) {
                startActivity(new Intent(getApplicationContext(), AddSuggestions.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                finish();
                return true;
            } else if (item.getItemId()==R.id.bottom_clculate) {
                startActivity(new Intent(getApplicationContext(), AdminSuggestionsView.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                finish();
                return true;
            }

            return false;
        });



        recyclerView = findViewById(R.id.recyclerviewFarmers);
        database = FirebaseDatabase.getInstance().getReference().child("farmers");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(Home.this));

        list = new ArrayList<>();


        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for(DataSnapshot users : snapshot.getChildren()){
                    final String un= users.getKey();
                    final String email=users.child("uemail").getValue(String.class);
                    final String pno=users.child("upno").getValue(String.class);
                    Farmers farmers = new Farmers(un,email,pno);
                    list.add(farmers);
                }
                recyclerView.setAdapter(new MyUsersAdapter(list,Home.this));
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });





    }


}