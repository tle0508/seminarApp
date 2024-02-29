package com.example.seminarApptest.DTO.process;

import java.time.LocalTime;

public class ProcessSchedule {
    private LocalTime time ;
    private String sessionDescription;
    private Integer duration;

    public ProcessSchedule(LocalTime time, String sessionDescription, Integer duration) {
        this.time = time;
        this.sessionDescription = sessionDescription;
        this.duration = duration;
    }
    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public String getSessionDescription() {
        return sessionDescription;
    }

    public void setSessionDescription(String sessionDescription) {
        this.sessionDescription = sessionDescription;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }
}

