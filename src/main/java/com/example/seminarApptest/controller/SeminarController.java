package com.example.seminarApptest.controller;

import com.example.seminarApptest.DTO.process.ProcessSessionDay;
import com.example.seminarApptest.DTO.read.SessionDay;
import com.example.seminarApptest.service.SeminarService;
import org.springframework.beans.factory.annotation.Autowired;
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
	//
	@Autowired
	private SeminarService seminarService;

	@PostMapping(value="/uploadFile", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ProcessSessionDay> uploadFile(@RequestParam("file") MultipartFile userFile) throws IOException {
		return seminarService.processFile(userFile);
	}

	@PostMapping(value = "/processJsonInput")
	public List<ProcessSessionDay> processJsonInput(@RequestBody SessionDay sessionDay)  {
		return seminarService.processJson(sessionDay);
	}
}
