
package com.example.farmings_schedular;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Feedback extends AppCompatActivity {
        Button feedback;
        EditText edt1, edt2;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_feedback);
            edt1=(EditText)findViewById(R.id.edt1);
            edt2=(EditText)findViewById(R.id.edt2);
            feedback=(Button) findViewById(R.id.feedback);

            BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);
            bottomNavigationView.setSelectedItemId(R.id.bottom_home);
            bottomNavigationView.setOnItemSelectedListener(item -> {
                if (item.getItemId()==R.id.bottom_home){
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    finish();
                    return true;
                } else if (item.getItemId()==R.id.bottom_suggetion) {
                    startActivity(new Intent(getApplicationContext(), SuggestionActivity.class));
                    overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                    finish();
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

                    return true;
                }

                return false;
            });


            feedback.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(Intent.ACTION_SEND);
                    i.setType("text/html"); // Set the type to text/html for sending HTML content

                    // Set email address
                    i.putExtra(Intent.EXTRA_EMAIL, new String[] { "swapnilkadam43789@gmail.com" });

                    // Set email subject
                    i.putExtra(Intent.EXTRA_SUBJECT, "Feedback from app");

                    // Get user input for name and message
                    String name = edt1.getText().toString();
                    String msg = edt2.getText().toString();

                    // Set email body with name and message
                    i.putExtra(Intent.EXTRA_TEXT, "Name: " + name + "\nMessage: " + msg);

                    try {
                        // Start email client chooser
                        startActivity(Intent.createChooser(i, "Please select email"));
                    } catch (android.content.ActivityNotFoundException ex) {
                        // Display error if no email client is available
                        Toast.makeText(Feedback.this, "There are no email clients installed.", Toast.LENGTH_SHORT).show();
                    }
                }
   });
}
    }