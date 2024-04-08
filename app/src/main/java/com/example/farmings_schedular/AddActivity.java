package com.example.farmings_schedular;

import static androidx.core.content.ContextCompat.getSystemService;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class AddActivity extends AppCompatActivity {

    Button btn,date,time ;
    TextView tv;
    EditText task;
    String timeTonotify;

    private dbManager db;
    myadapter adapter;
    ArrayList<String> t,d,ti;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        btn=findViewById(R.id.updatebtnr);
        time=findViewById(R.id.time_update);
        date=findViewById(R.id.date_update);
        task = findViewById(R.id.task_update);
        db = new dbManager(this);



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
        // end of bottom navigation bar

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                ed1 = findViewById(R.id.time);
//                ed2 = findViewById(R.id.date);

                String task1 = task.getText().toString().trim();
                String date1 = date.getText().toString().trim();
                String time1 = time.getText().toString().trim();

                if (time.equals("time") || date.equals("date")) {
                    Toast.makeText(getApplicationContext(), "Please select date and time", Toast.LENGTH_SHORT).show();
                } else {
                    processinsert(task1,date1,time1);

                }



            }
        });

        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectTime();
            }
        });

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectDate();
            }
        });



    }



    private void processinsert(String task, String date, String time) {
        String result = new dbManager(this).addreminder(task, date, time);
        setAlarm(task, date, time);

//        task1.setText("");
        Toast.makeText(getApplicationContext(), result, Toast.LENGTH_SHORT).show();
    }
    private void selectTime() {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                timeTonotify = i + ":" + i1;

                time.setText(timeTonotify);
            }
        }, hour, minute, true);
        timePickerDialog.show();
    }

    private void selectDate() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                date.setText(day + "-" + (month + 1) + "-" + year);
            }
        }, year, month, day);
        datePickerDialog.show();
    }
   


    private void setAlarm(String text, String date, String time) {
        AlarmManager am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        Intent intent = new Intent(getApplicationContext(), myreciver .class);
        intent.putExtra("event", text);
        intent.putExtra("date", date);
        intent.putExtra("time", time);

        PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, intent, PendingIntent.FLAG_ONE_SHOT | PendingIntent.FLAG_IMMUTABLE);
        String dateandtime = date + " " + timeTonotify;
        DateFormat formatter = new SimpleDateFormat("d-M-yyyy hh:mm");
        try {
            Date date1 = formatter.parse(dateandtime);

            am.set(AlarmManager.RTC_WAKEUP, date1.getTime(), pendingIntent);
            Toast.makeText(getApplicationContext(), "Task Set", Toast.LENGTH_SHORT).show();

        } catch (ParseException e) {
            e.printStackTrace();
        }

        Intent intentBack = new Intent(getApplicationContext(), MainActivity.class);
        intentBack.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intentBack);




    }
}