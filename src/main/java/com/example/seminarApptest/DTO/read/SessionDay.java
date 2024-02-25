package com.example.seminarApptest.DTO.read;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class SessionDay {
    @JsonProperty("day")
    private LocalDate day ;
    @JsonProperty("data")
    private List<Schedule> data = new ArrayList<>();

    public SessionDay() {

    }

    public LocalDate getDay() {
        return day;
    }

    public void setDay(LocalDate day) {
        this.day = day;
    }

    public List<Schedule> getScheduleList() {
        return data;
    }

    public void setScheduleList(List<Schedule> scheduleList) {
        this.data = scheduleList;
    }

}
