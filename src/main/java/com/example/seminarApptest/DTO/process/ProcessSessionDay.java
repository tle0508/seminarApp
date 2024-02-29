package com.example.seminarApptest.DTO.process;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ProcessSessionDay {
    private LocalDate date  ;
    private List<ProcessSchedule> processSchedule = new ArrayList<>();

    public ProcessSessionDay(LocalDate date, List<ProcessSchedule> processSchedule) {
        this.date = date;
        this.processSchedule = processSchedule;
    }
    public ProcessSessionDay(LocalDate currentDate) {
        this.date = currentDate;
    }
    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public List<ProcessSchedule> getProcessSchedule() {
        return processSchedule;
    }

    public void setProcessSchedule(List<ProcessSchedule> processSchedule) {
        this.processSchedule = processSchedule;
    }
}
