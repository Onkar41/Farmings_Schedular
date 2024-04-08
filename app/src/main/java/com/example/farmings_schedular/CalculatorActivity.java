package com.example.farmings_schedular;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import org.joda.time.Period;
import org.joda.time.PeriodType;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class CalculatorActivity extends AppCompatActivity {
    private dbManager db;
    Button btndate1,btndate2,calulate,find ;
    TextView tv,findedDate;
    EditText getDays;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);
        btndate1=findViewById(R.id.date1);
        btndate2=findViewById(R.id.date2);
        calulate=findViewById(R.id.calculate);
        tv=findViewById(R.id.result);
        find=findViewById(R.id.find);
        findedDate=findViewById(R.id.findedDate);
        getDays=findViewById(R.id.getDate);
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
                startActivity(new Intent(getApplicationContext(), CalculatorActivity.class));
                overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
                finish();
                return true;
            } else if (item.getItemId()==R.id.bottom_clculate) {
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


        btndate1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectDate1();
            }
        });
        btndate2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectDate2();
            }
        });
        calulate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String sdate = btndate1.getText().toString();
                String edate = btndate2.getText().toString();
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("d-M-yyyy", Locale.getDefault());
                try {
                    Date date1 = simpleDateFormat.parse(sdate);
                    Date date2 = simpleDateFormat.parse(edate);

                    long startdate = date1.getTime();
                    long enddate = date2.getTime();

//                    if (startdate <= enddate){
                        Period period = new Period(startdate,enddate, PeriodType.yearMonthDay());
                        int years = period.getYears();
                        int months = period.getMonths();
                        int days = period.getDays();

                        tv.setText(years + " Years | "+months+" Months | "+days+" Days ");
//                    }else {
//                        Toast.makeText(getApplicationContext(),"should not larger than todays date ",Toast.LENGTH_SHORT).show();
//                    }
                } catch (ParseException e) {
                   e.printStackTrace();
                }

            }
        });

        find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int days = Integer.parseInt(getDays.getText().toString());
                String result = CalculatorActivity.calculateDate(days);
                findedDate.setText(result);
            }
        });

    }
    private void selectDate1() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                btndate1.setText(day + "-" + (month + 1) + "-" + year);
            }
        }, year, month, day);
        datePickerDialog.show();
    }
    private void selectDate2() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                btndate2.setText(day + "-" + (month + 1) + "-" + year);
            }
        }, year, month, day);
        datePickerDialog.show();
    }
    public static String calculateDate(int daysToAdd) {
        // Get the current date
        Calendar calendar = Calendar.getInstance();
        Date currentDate = calendar.getTime();

        // Add the specified number of days
        calendar.add(Calendar.DAY_OF_YEAR, daysToAdd);

        // Get the new date
        Date newDate = calendar.getTime();

        // Format the date as per your requirement
        SimpleDateFormat sdf = new SimpleDateFormat("d-MM-yyyy");
        String formattedDate = sdf.format(newDate);

        return formattedDate;
    }
}