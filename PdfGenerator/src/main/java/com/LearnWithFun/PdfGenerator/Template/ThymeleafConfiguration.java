package com.LearnWithFun.PdfGenerator.Template;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class ThymeleafConfiguration {

	
	   
    @Bean
    public ClassLoaderTemplateResolver emailTemplateResolver() {
        ClassLoaderTemplateResolver pdfTemplateResolver = new ClassLoaderTemplateResolver();
        pdfTemplateResolver.setPrefix("templates/pdf-template/");
        pdfTemplateResolver.setSuffix(".html");
        pdfTemplateResolver.setTemplateMode("HTML5");
        pdfTemplateResolver.setCharacterEncoding("UTF-8");
        pdfTemplateResolver.setOrder(1);
        
        log.info("Resolver Prefix: " + pdfTemplateResolver.getPrefix());
        log.info("Resolver Suffix: " + pdfTemplateResolver.getSuffix());
        log.info("Resolver Order: " + pdfTemplateResolver.getOrder());

        return pdfTemplateResolver;
    }
}
