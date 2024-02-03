package com.LearnWithFun.PdfGenerator.ServiceImpl;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.LearnWithFun.PdfGenerator.Repository.InstrumentRepo;
import com.LearnWithFun.PdfGenerator.Service.InstrumentService;
import com.LearnWithFun.PdfGenerator.Template.pdfGeneratorService;
import com.LearnWithFun.PdfGenerator.Template.pdfGeneratorserviceImpl;
import com.LearnWithFun.PdfGenerator.entities.Instrument;
import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBeanBuilder;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class InstrumentServiceImpl implements InstrumentService {

	    @Autowired
	    private InstrumentRepo instrumentRepo;
	    
	    @Autowired
	    private  pdfGeneratorService  pdfGeneratorService;
	
	@Override
	public void getPdf() {
	       Map<String,Object> list_of_instrument = new HashMap<>();
	        Iterable<Instrument> all_instruments = instrumentRepo.findAll();
	        list_of_instrument.put("instruments", new ArrayList<>());
	        for (Instrument instrument : all_instruments) {
	            List<Instrument> list = (List<Instrument>) list_of_instrument.get("instruments");
	            list.add(instrument);
	        }
	        pdfGeneratorService.generatePdfFile("SecurityListTemplate", list_of_instrument);

	}

	@Override
	public void parseCSV(MultipartFile file) {
	     try {
	            CSVReader csvReader = new CSVReader(new InputStreamReader(file.getInputStream()));
	            List<Instrument> instruments =  new CsvToBeanBuilder<Instrument>(csvReader)
	                    .withType(Instrument.class)
	                    .build()
	                    .parse(); 
	            instrumentRepo.saveAll(instruments);
	            log.info("Data Saved-->");
	            
	        } catch (IOException e) {
	            e.printStackTrace();
	        }

	}

}
