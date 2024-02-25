package com.example.seminarApptest.service;

import com.example.seminarApptest.DTO.process.ProcessData;
import com.example.seminarApptest.DTO.process.ProcessSchedule;
import com.example.seminarApptest.DTO.process.ProcessSessionDay;
import com.example.seminarApptest.DTO.read.Schedule;
import com.example.seminarApptest.DTO.read.SessionDay;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;


public class ProcessFile {
    private LocalDate currentDate = LocalDate.now();
    private final LocalTime afternoonSession= LocalTime.of(13,0) ;
    private final LocalTime morningSession = LocalTime.of(9,0) ;
    private final LocalTime lunchTime = LocalTime.of(12,0) ;
    private final LocalTime networkingEventTime  = LocalTime.of(16,59) ;
    private  LocalTime scheduleTime = morningSession ;
    ProcessData processData = new ProcessData();
    ProcessSessionDay processSessionDay = new ProcessSessionDay(currentDate, new ArrayList<>());
    public ProcessData getProcessDataList() {
        return processData;
    }
    public void process(SessionDay sessionDay) {
        currentDate = sessionDay.getDay();
        processSessionDay.setDate(currentDate);
        for (Schedule schedule : sessionDay.getScheduleList()) {
            int duration = schedule.getDuration();
            // Check if lunchtime
            if (scheduleTime.equals(lunchTime)) {
                isLunchTime();
            }
            //Check if networking time
            if (scheduleTime.isAfter(networkingEventTime))  {
                isNetworkTime();
            }
            ProcessSchedule processSchedule = new ProcessSchedule(scheduleTime, schedule.getSessionDescription(), schedule.getDuration());
            processSessionDay.getProcessSchedule().add(processSchedule);
            scheduleTime = scheduleTime.plusMinutes(duration);
        }
        processData.getProcessSessionDayList().add(processSessionDay);
    }
    private void isLunchTime(){
        ProcessSchedule lunchSchedule = new ProcessSchedule(scheduleTime, "Lunch",0);
        processSessionDay.getProcessSchedule().add(lunchSchedule);
        scheduleTime = afternoonSession;
    }
    private void isNetworkTime(){
        ProcessSchedule networkSchedule = new ProcessSchedule(scheduleTime, "Networking Event",0);
        startNewDay();
        processSessionDay.getProcessSchedule().add(networkSchedule);
        processData.getProcessSessionDayList().add(processSessionDay);
        processSessionDay = new ProcessSessionDay(currentDate, new ArrayList<>());
    }
    private void startNewDay() {
        currentDate = currentDate.plusDays(1);
        if (isWeekend(currentDate)){
            currentDate = currentDate.plusDays(2);
        }
        scheduleTime = morningSession;
    }
    private boolean isWeekend(LocalDate date) {
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        return dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY;
    }
}


