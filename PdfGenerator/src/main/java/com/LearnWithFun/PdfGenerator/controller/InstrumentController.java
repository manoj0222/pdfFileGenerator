package com.LearnWithFun.PdfGenerator.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.LearnWithFun.PdfGenerator.Service.InstrumentService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/instrument")
@CrossOrigin("*")
@Slf4j
public class InstrumentController {
	
	 @Autowired
	 private InstrumentService instrumentService;
	
	
	@GetMapping("/getPdf")
    void  getPdf(){
        instrumentService.getPdf();
     }
    
     
     @PostMapping("/csv")
      public String parseCSV(@RequestParam MultipartFile File)  {
         log.debug("file"+File);
         instrumentService.parseCSV(File);
        return "received";
      } 
	
	

}
