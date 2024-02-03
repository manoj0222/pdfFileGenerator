/**
 * 
 */
package com.LearnWithFun.PdfGenerator.Template;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;

import com.LearnWithFun.PdfGenerator.Repository.InstrumentRepo;
import com.LearnWithFun.PdfGenerator.entities.Instrument;

import lombok.extern.slf4j.Slf4j;

/**
 * @author Manoj kumar.B
 *
 */
@Component
@Slf4j
public class pdfGeneratorserviceImpl implements pdfGeneratorService {
	
	
    @Autowired
    private TemplateEngine templateEngine;

    @Autowired
    private InstrumentRepo instrumentRepo;

    @Value("${pdf.directory}")
    private String pdfDirectory;

	@Override
	public void generatePdfFile(String templateName, Map<String, Object> list_of_instrument) {
		Context context = new Context();
		context.setVariables(list_of_instrument);
		try {
			String pdfFileName = generateUniqueFileName();
			String htmlContent = templateEngine.process(templateName, context);
			FileOutputStream fileOutputStream = new FileOutputStream(pdfDirectory + pdfFileName);
			ITextRenderer renderer = new ITextRenderer();
			renderer.setDocumentFromString(htmlContent);
			renderer.layout();
			renderer.createPDF(fileOutputStream, false);
			renderer.finishPDF();
		} catch (FileNotFoundException e) {
			log.error(e.getMessage(), e);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
	}

	
	 private String generateUniqueFileName() {
	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
	        String timestamp = dateFormat.format(new Date());
	        return "securities" + timestamp + ".pdf";
	    }
}
