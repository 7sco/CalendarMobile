package com.example.franciscoandrade.calendarmobile;

import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EventsActivity extends AppCompatActivity {


    @BindView(R.id.currentDay)
    TextView currentDay;
    @BindView(R.id.event_title)
    EditText eventTitle;
    @BindView(R.id.time_textView)
    TextView timeTextView;
    @BindView(R.id.pick_Time)
    Button pickTime;
    @BindView(R.id.addEvent_btn)
    Button addEventBtn;
    @BindView(R.id.event_info)
    TextView eventInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);
        ButterKnife.bind(this);


        Intent i = getIntent();
        String day = i.getExtras().getString("weekDay");
        String dayNumber = i.getExtras().getString("dayNumber");

        currentDay.setText(day + ":" + dayNumber);


    }


    private void showTimePicker() {

        TimePickerDialog.OnTimeSetListener onTimeSetListener = new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker timePicker, int hour, int minute) {
                StringBuffer strBuf = new StringBuffer();
                strBuf.append(hour);
                strBuf.append(":");
                strBuf.append(minute);
                timeTextView.setText(strBuf.toString());
            }
        };


        Calendar now = Calendar.getInstance();
        int hour = now.get(Calendar.HOUR_OF_DAY);
        int minute = now.get(Calendar.MINUTE);

        // Whether show time in 24 hour format or not.
        boolean is24Hour = true;

        TimePickerDialog timePickerDialog = new TimePickerDialog(EventsActivity.this, onTimeSetListener, hour, minute, is24Hour);

        //timePickerDialog.setIcon(R.drawable.if_snowman);
        timePickerDialog.setTitle("Please select time.");

        timePickerDialog.show();


    }

    @OnClick(R.id.pick_Time)
    public void onViewClicked() {
    }

    @OnClick({R.id.pick_Time, R.id.addEvent_btn})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.pick_Time:
                showTimePicker();
                break;
            case R.id.addEvent_btn:
                addEvent();
                break;
        }
    }

    private void addEvent() {
        String title= eventTitle.getText().toString();
        String time= timeTextView.getText().toString();
        eventInfo.setText(title+" : "+time);
    }
}
