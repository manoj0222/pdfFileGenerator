/**
 * 
 */
package com.LearnWithFun.PdfGenerator.Template;

import java.util.Map;

/**
 * @author Manoj
 *
 */
public interface pdfGeneratorService {

	void generatePdfFile(String templateName, Map<String, Object> list_of_instrument);

}
