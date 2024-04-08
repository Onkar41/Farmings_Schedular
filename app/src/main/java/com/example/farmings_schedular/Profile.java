package com.example.farmings_schedular;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Profile extends AppCompatActivity {
Button logout;

TextView un,pno,email;
    FirebaseDatabase database;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        logout =(Button) findViewById(R.id.logoutbtn);
        un=findViewById(R.id.u_name);
        pno = findViewById(R.id.phnoedt);
        email=findViewById(R.id.u_email);

        Intent i = getIntent();

        final String Uname=i.getStringExtra("uname");
        un.setText(Uname);
        database = FirebaseDatabase.getInstance();
        reference = database.getReference();
        reference.child("farmers").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild(Uname)) {
                    
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database = FirebaseDatabase.getInstance();
                reference = database.getReference();
                reference.child("farmers").child(Uname).removeValue();

                Intent redirect=new Intent(getApplicationContext(),firstpage.class);

                startActivity(redirect);
            }
        });


    }
}