package com.example.nikita.androidhomework;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener, View.OnClickListener {

    AlarmManager am;
    int hour;
    int minute;
    Toast toast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView tvTime = findViewById(R.id.tv_time);
        final Calendar c = Calendar.getInstance();
        hour = getIntent().getIntExtra("hour", c.get(Calendar.HOUR_OF_DAY));
        minute = getIntent().getIntExtra("minute", c.get(Calendar.MINUTE));
        tvTime.setText(hour + ":" + minute);
        am = (AlarmManager) getSystemService(Context.ALARM_SERVICE);

        Button btnStart = findViewById(R.id.btn_start);
        Button btnStop = findViewById(R.id.btn_stop);
        btnStart.setOnClickListener(this);
        btnStop.setOnClickListener(this);

        // Create a new instance of TimePickerDialog and return it
        new TimePickerDialog(MainActivity.this, this, hour, minute,
                DateFormat.is24HourFormat(this));
    }

    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
        // Do something with the time chosen by the user
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_start:
                startNotify();
                break;
            case R.id.btn_stop:
                stopNotify();
                break;
        }
    }

    public void showTimePickerDialog(View v) {
        DialogFragment newFragment = new TimePickerFragment();
        newFragment.show(getSupportFragmentManager(), "timePicker");
    }

    public void startNotify() {
        Calendar newCal = Calendar.getInstance();
        newCal.set(Calendar.HOUR_OF_DAY, hour);
        newCal.set(Calendar.MINUTE, minute);
        newCal.set(Calendar.SECOND, 0);
        Intent intent = new Intent(this, TimeNotification.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0,
                intent, PendingIntent.FLAG_CANCEL_CURRENT);
        am.cancel(pendingIntent);
        am.set(AlarmManager.RTC_WAKEUP,
                newCal.getTimeInMillis(),
                pendingIntent);
        toast = Toast.makeText(this, "Будильник запущен!", Toast.LENGTH_SHORT);
        toast.show();
    }

    public void stopNotify() {
        Intent intent = new Intent(this, TimeNotification.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0,
                intent, PendingIntent.FLAG_CANCEL_CURRENT);
        am.cancel(pendingIntent);
        toast = Toast.makeText(this, "Будильник отменен!", Toast.LENGTH_SHORT);
        toast.show();
    }
}
