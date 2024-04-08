package com.example.farmings_schedular;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.farmings_schedular.Admin.AdminSuggestionsView;
import com.example.farmings_schedular.Admin.Farmers;
import com.example.farmings_schedular.Admin.Home;
import com.example.farmings_schedular.Admin.MyUsersAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class DisplaySuggestions extends AppCompatActivity {
TextView tv;
ImageView back;

    private ArrayList<ListSuggestions> list;
    RecyclerView recyclerView;
    AdapterForSuggestions adapter;

    DatabaseReference database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_suggestions);
        back=findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(), SuggestionActivity.class);
                startActivity(i);
            }
        });

        Intent i = getIntent();

        final String crop=i.getStringExtra("crop");



        recyclerView = findViewById(R.id.recyclerviewForSuggestions);
        database = FirebaseDatabase.getInstance().getReference().child("Suggestions");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(DisplaySuggestions.this));

        list = new ArrayList<>();


        database.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                list.clear();
                for (DataSnapshot users : snapshot.getChildren()) {

                    String cn = users.child("crop_name").getValue(String.class);

                    if (cn != null && cn.equals(crop)) {
                        String disease = users.child("disease").getValue(String.class);
                        String precaution = users.child("precaution").getValue(String.class);
                        ListSuggestions suggestions = new ListSuggestions(cn, disease, precaution);
                        list.add(suggestions);
                    }
                }
                recyclerView.setAdapter(new AdapterForSuggestions(list, DisplaySuggestions.this));
}

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}