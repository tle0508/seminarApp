package com.example.seminarApptest.service;

import com.example.seminarApptest.DTO.process.ProcessSessionDay;
import com.example.seminarApptest.DTO.read.SessionDay;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class SeminarService {
    public List<ProcessSessionDay> processFile(MultipartFile userFile) throws IOException {
        //read
        ReadInput reader = new ReadInput();
        SessionDay sessionDay =reader.read(userFile.getInputStream());
        // Process the data
        ProcessFile processSchedule = new ProcessFile();
        List<ProcessSessionDay> processedDataList = processSchedule.process(sessionDay);
        return processedDataList;
    }
    public List<ProcessSessionDay> processJson(SessionDay sessionDay)  {
        // Process the data
        ProcessFile processSchedule = new ProcessFile();
        List<ProcessSessionDay> processedDataList = processSchedule.process(sessionDay);
        return processedDataList;
    }
}
