package com.example.seminarApptest.service;

import com.example.seminarApptest.DTO.process.ProcessSchedule;
import com.example.seminarApptest.DTO.process.ProcessSessionDay;
import com.example.seminarApptest.DTO.read.Schedule;
import com.example.seminarApptest.DTO.read.SessionDay;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


public class ProcessFile {
    private static final LocalTime AFTERNOON_SESSION = LocalTime.of(13, 0);
    private static final LocalTime MORNING_SESSION = LocalTime.of(9, 0);
    private static final LocalTime LUNCH_TIME = LocalTime.of(12, 0);
    private static final LocalTime NETWORKING_START_TIME = LocalTime.of(16, 0);
    private static final LocalTime NETWORKING_END_TIME = LocalTime.of(17, 0);
    private LocalTime scheduleTime = MORNING_SESSION;
    private LocalDate currentDate = null;

    public List<ProcessSessionDay> process(SessionDay sessionDay) {
        currentDate = sessionDay.getDay();
        List<ProcessSessionDay> processedDataList = new ArrayList<>();
        ProcessSessionDay processSessionDay = new ProcessSessionDay(currentDate, new ArrayList<>());
        for (Schedule schedule : sessionDay.getScheduleList()) {
            int duration = schedule.getDuration();
            // Check if lunchtime
            if (scheduleTime.equals(LUNCH_TIME)) {
                ProcessSchedule lunchSchedule = new ProcessSchedule(scheduleTime, "Lunch", null);
                processSessionDay.getProcessSchedule().add(lunchSchedule);
                scheduleTime = AFTERNOON_SESSION;
            }
            // Check if networking
            if (!scheduleTime.isBefore(NETWORKING_START_TIME) && scheduleTime.isBefore(NETWORKING_END_TIME)) {
                ProcessSchedule networkSchedule = new ProcessSchedule(scheduleTime, "Networking Event", null);
                startNewDay();
                processSessionDay.getProcessSchedule().add(networkSchedule);
                processedDataList.add(processSessionDay);
                processSessionDay = new ProcessSessionDay(currentDate);
            }
            ProcessSchedule processSchedule = new ProcessSchedule(scheduleTime, schedule.getSessionDescription(), schedule.getDuration());
            processSessionDay.getProcessSchedule().add(processSchedule);
            scheduleTime = scheduleTime.plusMinutes(duration);
        }
        processedDataList.add(processSessionDay);
        return processedDataList;
    }

    private void startNewDay() {
        currentDate = currentDate.plusDays(1);
        if (isWeekend(currentDate)) {
            currentDate = currentDate.plusDays(2);
        }
        scheduleTime = MORNING_SESSION;
    }

    private boolean isWeekend(LocalDate date) {
        DayOfWeek dayOfWeek = date.getDayOfWeek();
        return dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY;
    }
}

