package com.example.farmings_schedular;

import static android.content.ContentValues.TAG;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private dbManager db;
    TextView showUname;
    ImageView profile,UpdateR;
    ArrayList<String> t,d,ti,fid;
    RecyclerView recyclerView;
    myadapter adapter;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        showUname = (TextView) findViewById(R.id.Uname);
        profile = (ImageView) findViewById(R.id.profile);

        //delete remainders


//        BackgroundTask backgroundTask = new BackgroundTask(getApplicationContext());
//        backgroundTask.execute();

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setSelectedItemId(R.id.bottom_home);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            if (item.getItemId() == R.id.bottom_home) {
                return true;
            } else if (item.getItemId() == R.id.bottom_suggetion) {
                startActivity(new Intent(getApplicationContext(), SuggestionActivity.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                finish();
                return true;
            } else if (item.getItemId() == R.id.bottom_add) {
                startActivity(new Intent(getApplicationContext(), AddActivity.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                finish();
                return true;
            } else if (item.getItemId() == R.id.bottom_clculate) {
                startActivity(new Intent(getApplicationContext(), CalculatorActivity.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                finish();
                return true;
            } else if (item.getItemId() == R.id.bottom_feedback) {
                startActivity(new Intent(getApplicationContext(), Feedback.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                finish();
                return true;
            }

            return false;
        });

        // end of bottom navigation bar

        db = new dbManager(this);
        t=new ArrayList<>();
        fid=new ArrayList<>();
        d=new ArrayList<>();
        ti=new ArrayList<>();
        recyclerView = findViewById(R.id.recyclerview);
        adapter = new myadapter(this,t,d,ti,fid);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        displaydata();
        set();



        SharedPreferences preferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        String savedUname = preferences.getString("Uname", "");
        showUname.setText(savedUname);

        String Uname = savedUname;
        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent redirect=new Intent(getApplicationContext(),Profile.class);
                redirect.putExtra("uname", Uname);
                startActivity(redirect);

            }
        });


    }


    private void set() {
        Cursor cursor1 = db.getAllTasks();
        if (cursor1 != null && cursor1.moveToFirst()) {
            do {
                String text = cursor1.getString(1);
                String storedDateTime = cursor1.getString(2);
                String storedTime = cursor1.getString(3);

                // Handling date parsing
                SimpleDateFormat dateFormat = new SimpleDateFormat("d-M-yyyy", Locale.getDefault());
                SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
                try {
                    Date currentDate = new Date();
                    String currentDateStr = dateFormat.format(currentDate);
                    String currentTimeStr = timeFormat.format(currentDate);

                    if (currentDateStr.equals(storedDateTime) && currentTimeStr.equals(storedTime)) {
                        // Trigger alarm
                        setAlarm(text, storedDateTime, storedTime);
                    }
                } catch (Exception e) {
                    // Handle parsing exceptions
                    e.printStackTrace();
                }

            } while (cursor1.moveToNext());
        }
        cursor1.close();
    }

    private void displaydata() {
        Cursor cursor = db.getAllTasks();


        if (cursor.getCount()==0){
            Toast.makeText(getApplicationContext(), "no entry exist", Toast.LENGTH_SHORT).show();
            return;
        }else {
            while (cursor.moveToNext()){

                fid.add(String.valueOf(cursor.getInt(0)));
                t.add(cursor.getString(1));
                d.add(cursor.getString(2));
                ti.add(cursor.getString(3));


            }
        }
    }

    private void setAlarm(String text,String date , String time) {
        // Use AlarmManager to set an alarm
        AlarmManager am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        Intent intent = new Intent(getApplicationContext(), myreciver .class);
        intent.putExtra("event", text);
        intent.putExtra("date", date);
        intent.putExtra("time", time);
        String timeTonotify;
        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, intent, PendingIntent.FLAG_ONE_SHOT | PendingIntent.FLAG_IMMUTABLE);
        String dateandtime = date + " " + time;
        DateFormat formatter = new SimpleDateFormat("d-M-yyyy hh:mm");
        try {
            Date date1 = formatter.parse(dateandtime);

            am.set(AlarmManager.RTC_WAKEUP, date1.getTime(), pendingIntent);
            Toast.makeText(getApplicationContext(), "Alarm", Toast.LENGTH_SHORT).show();

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

}