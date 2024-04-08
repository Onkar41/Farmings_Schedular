package com.example.farmings_schedular;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class BackgroundTask extends AsyncTask<Void, Void, Void> {

    private final Context context;

    public BackgroundTask(Context context) {
        this.context = context;
    }

    @Override
    protected Void doInBackground(Void... voids) {
        // Your background processing inside the loop
        dbManager db = new dbManager(context);
        Cursor cursor1 = db.getAllTasks();

        if (cursor1.moveToFirst()) {
            do {
                String storedDateTime = cursor1.getString(2);
                SimpleDateFormat dateFormat = new SimpleDateFormat("d-M-yyyy", Locale.getDefault());
                Date currentDate = new Date();
                String storedTime = cursor1.getString(3);
                String crt = dateFormat.format(currentDate);

                if (crt.equals(storedDateTime)) {
                    SimpleDateFormat timef = new SimpleDateFormat("HH:mm", Locale.getDefault());
                    String currentTime = timef.format(currentDate);

                    if (currentTime.equals(storedTime)) {
                        // Trigger alarm
                        String date = cursor1.getString(2);
                        String time = cursor1.getString(3);
                        String text = cursor1.getString(1);
                        // Run UI-related code on the main thread
                        new Handler(Looper.getMainLooper()).post(() -> {
                            Toast.makeText(context, "Alarm", Toast.LENGTH_SHORT).show();
                        });

                        AlarmManager am = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
                        Intent intent = new Intent(context, myreciver.class);

                        intent.putExtra("event", text);
                        intent.putExtra("date", date);
                        intent.putExtra("time", time);
                        String dateAndTime = date + " " + time;
                        DateFormat formatter = new SimpleDateFormat("d-M-yyyy hh:mm");

                        try {
                            Date date1 = formatter.parse(dateAndTime);

                            PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_ONE_SHOT | PendingIntent.FLAG_IMMUTABLE);
                            am.set(AlarmManager.RTC_WAKEUP, date1.getTime(), pendingIntent);

                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                    }
                }

            } while (cursor1.moveToNext());
        }
        cursor1.close();
        return null;
    }
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            // Any UI updates or post-processing can be done here (runs on the UI thread)
        }
    }

