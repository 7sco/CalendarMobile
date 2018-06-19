package com.example.franciscoandrade.calendarmobile.model;

import java.util.List;

public class Day {
    
        private short dayNumber;
        private DayDetails dayDetailsList;



        public short getDayNumber() {
            return dayNumber;
        }

        public void setDayNumber(short dayNumber) {
            this.dayNumber = dayNumber;
        }

        public DayDetails getDayDetailsList() {
            return dayDetailsList;
        }

        public void setDayDetailsList(DayDetails dayDetailsList) {
            this.dayDetailsList = dayDetailsList;
        }
    }



