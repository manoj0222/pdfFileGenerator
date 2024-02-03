package com.LearnWithFun.PdfGenerator.Service;

import org.springframework.web.multipart.MultipartFile;

public interface InstrumentService {

	void getPdf();

	void parseCSV(MultipartFile file);

}
