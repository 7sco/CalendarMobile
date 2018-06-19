package com.example.franciscoandrade.calendarmobile;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.franciscoandrade.calendarmobile.model.Day;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CalendarAdapter extends RecyclerView.Adapter<CalendarAdapter.CalendarViewHolder> {

    List<Day> listDay;


    public CalendarAdapter(List<Day> listDay) {
        this.listDay = listDay;
    }

    @NonNull
    @Override
    public CalendarViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View viewHolder = LayoutInflater.from(parent.getContext()).inflate(R.layout.calendar_itemview, parent, false);
        return new CalendarViewHolder(viewHolder);
    }

    @Override
    public void onBindViewHolder(@NonNull CalendarViewHolder holder, int position) {
        Day day = listDay.get(position);
        holder.bind(day);
    }

    @Override
    public int getItemCount() {
        return listDay.size();
    }

    public class CalendarViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        @BindView(R.id.day)
        TextView dayTV;
        @BindView(R.id.number)
        TextView number;
        @BindView(R.id.remainders)
        TextView remainders;

        Day itemViewDay;



        public CalendarViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);

        }

        public void bind(Day day) {
            itemViewDay= day;
            dayTV.setText(day.getDayDetailsList().getWeekDay());
            number.setText(day.getDayNumber()+"");
            remainders.setText(day.getDayDetailsList().getListRemainders().size()+"");

        }


        @Override
        public void onClick(View v) {
            Intent view = new Intent(v.getContext(), EventsActivity.class);
            view.putExtra("weekDay", itemViewDay.getDayDetailsList().getWeekDay());
            view.putExtra("dayNumber", itemViewDay.getDayNumber()+"");
            v.getContext().startActivity(view);
        }
    }
}
