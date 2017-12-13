package com.optum.tops.J5427HC1.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.optum.tops.J5427HC1.models.HC1Response;
import com.optum.tops.J5427HC1.models.Hc1Request;
import com.optum.tops.J5427HC1.services.RequestProcessor;

@Controller
public class HC1Controller {

	@Autowired
	RequestProcessor request_processor_service ; 
	
	
	@RequestMapping(value = "/COB" , method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<HC1Response> get_COB_details(@RequestBody Hc1Request request){
		System.out.println("Hit HC1 Controller for a HTTP POST method");
		HC1Response response = request_processor_service.process(request) ; //to be sent back to the calling program as a ResponseEntity
		return new ResponseEntity<HC1Response>(response, HttpStatus.OK);
	}
}
