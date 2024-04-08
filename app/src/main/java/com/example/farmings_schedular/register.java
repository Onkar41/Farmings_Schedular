package com.example.farmings_schedular;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class register extends AppCompatActivity {
Button s_submit;
EditText uname,email,pno,pass;
TextView s_login;
    FirebaseDatabase database;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        s_login=(TextView) findViewById(R.id.loginRedirectText);
       uname=(EditText) findViewById(R.id.username);
        email=(EditText) findViewById(R.id.uemail);
        pno=(EditText) findViewById(R.id.upno);
        pass=(EditText) findViewById(R.id.upass);
        s_submit=(Button) findViewById(R.id.signup_button) ;
        s_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                 String username=uname.getText().toString();
                 String upass=pass.getText().toString();
                 String uemail=email.getText().toString();
                 String upno=pno.getText().toString();

                if (username.isEmpty() || upass.isEmpty() || uemail.isEmpty() || upno.isEmpty()){
                    Toast.makeText(register.this, "Please fill all fields", Toast.LENGTH_SHORT).show();
                }
                else {
                    database = FirebaseDatabase.getInstance();
                    reference = database.getReference();
                    reference.child("farmers").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if(dataSnapshot.hasChild(username)){
                                Toast.makeText(register.this, "farmer alredy exist", Toast.LENGTH_SHORT).show();
                            }else {

                                reference.child("farmers").child(username).child("uemail").setValue(uemail);
                                reference.child("farmers").child(username).child("upno").setValue(upno);
                                reference.child("farmers").child(username).child("upass").setValue(upass);
                                Toast.makeText(register.this, "User Registration Successfully", Toast.LENGTH_SHORT).show();
                                finish();



                                Toast.makeText(register.this, "User Registration Successfully", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(getApplicationContext(), Login.class);
                                startActivity(i);
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });

                }



            }
        });
        s_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), Login.class);
                startActivity(i);
            }
        });
    }
}