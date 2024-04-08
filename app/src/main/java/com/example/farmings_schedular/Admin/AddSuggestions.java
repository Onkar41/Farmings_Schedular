package com.example.farmings_schedular.Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.farmings_schedular.CalculatorActivity;
import com.example.farmings_schedular.Login;
import com.example.farmings_schedular.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class AddSuggestions extends AppCompatActivity {
    EditText cname,disease,precaution;
    Button btn1;
    FirebaseDatabase db;
    DatabaseReference reference;
    long maxid= 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_suggestions);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setSelectedItemId(R.id.bottom_home);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId()==R.id.bottom_home){
                startActivity(new Intent(getApplicationContext(), Home.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                finish();
                return true;
            } else if (item.getItemId()==R.id.bottom_add) {
                return true;
            } else if (item.getItemId()==R.id.bottom_clculate) {
                startActivity(new Intent(getApplicationContext(), AdminSuggestionsView.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                finish();
                return true;
            }

            return false;
        });
        // end of bottom navigatio bar

        cname=(EditText)findViewById(R.id.c_name);
        disease=(EditText)findViewById(R.id.disease);
        precaution=(EditText)findViewById(R.id.precaution);
        btn1=(Button)findViewById(R.id.btn1);


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String crop_name=cname.getText().toString();
                String dis=disease.getText().toString();
                String precau=precaution.getText().toString();

                if (crop_name.isEmpty() || dis.isEmpty() || precau.isEmpty() ){
                    Toast.makeText(AddSuggestions.this, "please fill Details ", Toast.LENGTH_SHORT).show();
                }
                else {
                    db = FirebaseDatabase.getInstance();
                    reference = db.getReference();
                    reference.child("Suggestions").addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            maxid=snapshot.getChildrenCount()+1;
                            reference.child("Suggestions").child(String.valueOf(maxid+1)).child("crop_name").setValue(crop_name);
                            reference.child("Suggestions").child(String.valueOf(maxid+1)).child("disease").setValue(dis);
                            reference.child("Suggestions").child(String.valueOf(maxid+1)).child("precaution").setValue(precau);
                            finish();



                                Toast.makeText(AddSuggestions.this, "Data filled successfully", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(getApplicationContext(), AddSuggestions.class);
                                startActivity(i);
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });
                }
            }
        });

}
}