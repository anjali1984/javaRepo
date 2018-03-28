package com.optum.tops.J5427HC1.controllers;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.optum.tops.J5427HC1.models.HC1Response;
import com.optum.tops.J5427HC1.models.Hc1Request;
import com.optum.tops.J5427HC1.services.RequestProcessor;

@Controller
public class HC1Controller {

	@Autowired
	RequestProcessor requestProcessorService; 
	Logger logger=Logger.getLogger("genLogger");

	
	@RequestMapping(value = "/COB" , method = RequestMethod.POST,
			consumes="application/json",produces="application/json")
	@ResponseBody
	public ResponseEntity<HC1Response> getCOBDetails(@RequestBody Hc1Request request){
		
		logger.info("request in controller:"+request);
		HC1Response response = requestProcessorService.process(request) ; //to be sent back to the calling program as a Response
		return new ResponseEntity<HC1Response>(response, HttpStatus.OK);
	}
}
