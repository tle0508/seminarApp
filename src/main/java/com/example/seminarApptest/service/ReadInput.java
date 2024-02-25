package com.example.seminarApptest.service;

import com.example.seminarApptest.DTO.read.Schedule;
import com.example.seminarApptest.DTO.read.SessionDay;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;

public class ReadInput {
    public SessionDay sessionDay = new SessionDay();
    public void read(InputStream inputStream){

        try (
                InputStreamReader in = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(in);
        ) {
            String firstLine = bufferedReader.readLine();
            LocalDate day = LocalDate.parse(firstLine);
            sessionDay.setDay(day);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                Schedule schedule = parseSchedule(line);
                sessionDay.getScheduleList().add(schedule);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }  public Schedule parseSchedule(String line) {
        int duration = Integer.parseInt(line.replaceAll("\\D", ""));
        String sessionDescription = line.replaceAll("\\d+min", "");
        return new Schedule(sessionDescription, duration);
    }
    public SessionDay getSessionDay() {
        return sessionDay;
    }
}
