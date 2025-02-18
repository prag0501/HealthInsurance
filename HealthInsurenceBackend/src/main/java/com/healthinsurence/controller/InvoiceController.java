package com.healthinsurence.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.healthinsurence.serviceimplemention.InVoiceServiceImpl;

import jakarta.servlet.http.HttpServletResponse;


@RestController
@RequestMapping("/invoice")
public class InvoiceController {

    @Autowired
    private  InVoiceServiceImpl service;

    @GetMapping("/create/{paymentId}")
	public void createPdf(HttpServletResponse response, @PathVariable String paymentId) {
	        // Setting content type and response headers
		response.setContentType("application/pdf");
	    response.setHeader(org.springframework.http.HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=RS_PI_invoice.pdf");

	     try {
	            service.export(response, paymentId);
	   		 } catch (Exception  e) {
	            e.printStackTrace();
	      }
	    }
}
