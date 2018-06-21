package com.example.franciscoandrade.calendarmobile.presentation.view;

import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.franciscoandrade.calendarmobile.R;
import com.example.franciscoandrade.calendarmobile.model.Remainder;
import com.example.franciscoandrade.calendarmobile.presentation.EventContract;
import com.example.franciscoandrade.calendarmobile.presentation.presenter.EventActivityPresenter;
import com.example.franciscoandrade.calendarmobile.presentation.recyclerView.EventAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class EventsActivity extends AppCompatActivity implements EventContract.View {


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
    EventAdapter adapter;
    @BindView(R.id.events_rv)
    RecyclerView eventsRv;
    @BindView(R.id.endtime_textView)
    TextView endtimeTextView;
    @BindView(R.id.pick_TimeEnd)
    Button pickTimeEnd;
    @BindView(R.id.retry_button)
    Button retryButton;

    private int remainderTotal;
    private EventContract.Presenter presenter;
    private int currentDayNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);
        ButterKnife.bind(this);
        int dayNumber = getIntentData();
        currentDayNumber=dayNumber;
        presenter = new EventActivityPresenter(this);
        getRemainders(dayNumber);
    }

    private void getRemainders(int dayNumber) {
        if (isNetworkAvailable()) {
            Log.d("==", "getRemainders: "+dayNumber);
            presenter.getEventsList(dayNumber);
            retryButton.setVisibility(View.INVISIBLE);
        } else {
            retryButton.setVisibility(View.VISIBLE);
            addEventBtn.setVisibility(View.INVISIBLE);
            showToast("No Connection");

        }
    }

    private int getIntentData() {
        Intent i = getIntent();
        String day = i.getExtras().getString("weekDay");
        String month = i.getExtras().getString("month");
        int dayNumber = i.getExtras().getInt("dayNumber");
        remainderTotal = i.getExtras().getInt("remainderTotal");
        String current = day + ", " + month + " " + (dayNumber) + ", 2018";
        currentDay.setText(current);
        return dayNumber--;
    }


    @OnClick({R.id.pick_Time, R.id.addEvent_btn, R.id.pick_TimeEnd, R.id.retry_button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.pick_Time:
                presenter.setStartTime();
                break;
            case R.id.addEvent_btn:
                String title = eventTitle.getText().toString();
                String timeStart = timeTextView.getText().toString();
                String timeEnd = endtimeTextView.getText().toString();
                presenter.addEvent(title, timeStart, timeEnd);
                break;
            case R.id.pick_TimeEnd:
                presenter.setEndTime();
                break;
            case R.id.retry_button:
                getRemainders(currentDayNumber);
                break;
        }
    }

    @Override
    public void setRecyclerView(List<Remainder> remainderList) {
        adapter = new EventAdapter(remainderList);
        eventsRv.setAdapter(adapter);
        eventsRv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
    }

    @Override
    public void setRecyclerViewEmpty() {
        adapter = new EventAdapter();
        eventsRv.setAdapter(adapter);
    }

    @Override
    public void onEventAdded() {
        eventTitle.setText(null);
        timeTextView.setText(null);
        endtimeTextView.setText(null);
    }


    @Override
    public void addRemainderToList(Remainder remainder) {
        adapter.addRemainder(remainder);
    }

    @Override
    public void showToast(String message) {
        Toast.makeText(EventsActivity.this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setEndTimeTextView(String endTime) {
        endtimeTextView.setText(endTime);
    }

    @Override
    public void setStartTimeTV(String startTime) {
        timeTextView.setText(startTime);
    }

    @Override
    public void showTimeDialog(TimePickerDialog.OnTimeSetListener onTimeSetListener, int hour, int minute, boolean is24Hour) {
        TimePickerDialog timePickerDialog = new TimePickerDialog(EventsActivity.this, onTimeSetListener, hour, minute, is24Hour);
        timePickerDialog.setTitle("Please select time.");
        timePickerDialog.show();
    }

    @Override
    public void addEventVisible() {
        addEventBtn.setVisibility(View.VISIBLE);
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
