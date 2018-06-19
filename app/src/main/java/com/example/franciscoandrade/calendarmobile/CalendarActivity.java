package com.example.franciscoandrade.calendarmobile;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.example.franciscoandrade.calendarmobile.model.Day;
import com.example.franciscoandrade.calendarmobile.model.DayDetails;
import com.example.franciscoandrade.calendarmobile.model.Month;
import com.example.franciscoandrade.calendarmobile.model.Remainder;
import com.example.franciscoandrade.calendarmobile.model.Year;


import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CalendarActivity extends AppCompatActivity {

    @BindView(R.id.year)
    TextView yearTV;
    @BindView(R.id.month)
    TextView monthTV;
    @BindView(R.id.calendar_rv)
    RecyclerView calendarRv;

    private CalendarAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);
        ButterKnife.bind(this);


        String[] months= {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};


        List<String> listDaysWeek=new ArrayList<>();
        listDaysWeek.add("Sunday");
        listDaysWeek.add("Monday");
        listDaysWeek.add("Tuesday");
        listDaysWeek.add("Wednesday");
        listDaysWeek.add("Thursday");
        listDaysWeek.add("Friday");
        listDaysWeek.add("Saturday");

        List<Remainder> remainderList= new ArrayList<>();
        remainderList.add(new Remainder("Remainder Title", "5:00"));

        List<DayDetails> dayDetailsList= new ArrayList<>();
        for (int i = 0; i <listDaysWeek.size() ; i++) {
            dayDetailsList.add(new DayDetails(listDaysWeek.get(i), remainderList));
        }

        List<Day> listDay= new ArrayList<>();
        int counter=0;
        for (short i = 1; i <29; i++) {
            Day day= new Day();
            day.setDayNumber(i);
            if (counter>6){
                counter=0;
            }
            day.setDayDetailsList(dayDetailsList.get(counter));
            listDay.add(day);
            counter++;
        }

        List<Month> listMonth= new ArrayList<>();
        for (int i = 0; i <12 ; i++) {
            Month month= new Month();
            month.setMonth(months[i]);
            month.setDayList(listDay);
            listMonth.add(month);
        }

        Year year= new Year();
        year.setMonthsList(listMonth);
        year.setYearNumber(2018);

        Log.d("Year=", "onCreate: "+year.getYearNumber());
        for (int i = 0; i <year.getMonthsList().size() ; i++) {
            Log.d("Months=", "onCreate: "+year.getMonthsList().get(i).getMonth());
            for (int j = 0; j < +year.getMonthsList().get(i).getDayList().size(); j++) {
                //Log.d("Days=", "onCreate: "+year.getMonthsList().get(i).getDayList().get(j).getDayNumber());
                    Log.d("Days=", "onCreate: "+year.getMonthsList().get(i).getDayList().get(j).getDayNumber()+" : "+year.getMonthsList().get(i).getDayList().get(j).getDayDetailsList().getWeekDay());
//                for (int k = 0; k < 7; k++) {
//                }
            }
        }

        yearTV.setText("2018 / ");
        monthTV.setText("January");
        adapter= new CalendarAdapter(listDay);
        calendarRv.setAdapter(adapter);
        calendarRv.setHasFixedSize(false);
        calendarRv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL));
        calendarRv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));



    }
}
