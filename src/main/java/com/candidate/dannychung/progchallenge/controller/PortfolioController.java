package com.candidate.dannychung.progchallenge.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.candidate.dannychung.progchallenge.listener.DetailReportSubsciber;

@RestController
@RequestMapping("/")
public class PortfolioController {
	
	@Value("${csvoutput.path}")
	private String csvOutPath;
	
	@Autowired
	private DetailReportSubsciber detailReportSubsciber;
	
	@GetMapping("/textfileprinter")
	public String consoleProgram() throws IOException
	{	
		try {
			detailReportSubsciber.exportReport();
			DateTimeFormatter dtFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.SSS");
			return LocalDateTime.now().format(dtFormatter)+"  "+"Market values for all position were stored at: " +csvOutPath;
		} catch (FileNotFoundException e) {
			return "Cannot open file "+csvOutPath+ " . The file may be already opened. Please close the file and try again.";
		}
	}

}
