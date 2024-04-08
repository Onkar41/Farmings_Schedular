package com.example.farmings_schedular;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.farmings_schedular.Admin.AddSuggestions;
import com.example.farmings_schedular.Admin.Home;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Login extends AppCompatActivity {
    Button btn;
    EditText u, p;
    TextView tv;
    FirebaseDatabase database;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        btn = findViewById(R.id.login_button);
        u = (EditText) findViewById(R.id.uname);
        p = (EditText) findViewById(R.id.pass);
        tv=(TextView)findViewById(R.id.signUpRedirectText);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 String uname = u.getText().toString().trim();
                 String pass = p.getText().toString().trim();
                if (uname.equals("onkar") && pass.equals("4141")) {
                    Intent i = new Intent(getApplicationContext(), Home.class);
                    startActivity(i);

                }else if (uname.isEmpty() || pass.isEmpty()) {

                        Toast.makeText(Login.this, "Please enter Uname No or Password", Toast.LENGTH_SHORT).show();

                    } else {

                        database = FirebaseDatabase.getInstance();
                        reference = database.getReference();
                        reference.child("farmers").addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                if (dataSnapshot.hasChild(uname)) {

                                    final String upass = dataSnapshot.child(uname).child("upass").getValue(String.class);
                                    if (upass.equals(pass)) {
                                        Toast.makeText(Login.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                                        Intent i = new Intent(getApplicationContext(), MainActivity.class);
                                        SharedPreferences preferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
                                        SharedPreferences.Editor editor = preferences.edit();
                                        editor.putString("Uname", uname);
                                        editor.apply();
                                        startActivity(i);
                                    }
                                    else {
                                        Toast.makeText(Login.this, "Wrong Password", Toast.LENGTH_SHORT).show();
                                    }
                                } else {
                                    Toast.makeText(Login.this, "Wrong UserName", Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });

                    }

                }

        });
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i=new Intent(getApplicationContext(),register.class);
                startActivity(i);
                Toast.makeText(Login.this, "Please Do Registration First", Toast.LENGTH_SHORT).show();
            }
        });


    }
}


