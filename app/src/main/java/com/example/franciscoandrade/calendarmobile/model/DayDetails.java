package com.example.franciscoandrade.calendarmobile.model;

import java.util.List;

public class DayDetails {
   

        private String weekDay;
        private List<Remainder> listRemainders;

        public DayDetails(String weekDay, List<Remainder> listRemainders) {
            this.weekDay = weekDay;
            this.listRemainders = listRemainders;
        }


        public String getWeekDay() {
            return weekDay;
        }

        public void setWeekDay(String weekDay) {
            this.weekDay = weekDay;
        }

        public List<Remainder> getListRemainders() {
            return listRemainders;
        }

        public void setListRemainders(List<Remainder> listRemainders) {
            this.listRemainders = listRemainders;
        }
    }

