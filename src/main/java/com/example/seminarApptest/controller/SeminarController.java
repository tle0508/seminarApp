package com.example.seminarApptest.controller;

import com.example.seminarApptest.DTO.process.ProcessData;
import com.example.seminarApptest.DTO.process.ProcessSessionDay;
import com.example.seminarApptest.DTO.read.SessionDay;
import com.example.seminarApptest.service.ProcessFile;
import com.example.seminarApptest.service.ReadInput;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
public class SeminarController {

	@PostMapping(value="/uploadFile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ProcessSessionDay> uploadFile(@RequestParam("file") MultipartFile userFile) throws IOException {
		//read
		ReadInput reader = new ReadInput();
		reader.read(userFile.getInputStream());
		SessionDay sessionDay = reader.getSessionDay();
		// Process the data
		ProcessFile processSchedule = new ProcessFile();
		processSchedule.process(sessionDay);

		// Retrieve the processed data
		ProcessData processData = processSchedule.getProcessDataList();

		return processData.getProcessSessionDayList();
	}

	@PostMapping(value = "/processJsonInput")
	public List<ProcessSessionDay> processJsonInput(@RequestBody SessionDay sessionDay) {
		ProcessFile processSchedule = new ProcessFile();
		processSchedule.process(sessionDay);

		// Retrieve the processed data
		ProcessData processData = processSchedule.getProcessDataList();

		return processData.getProcessSessionDayList();
	}
}
