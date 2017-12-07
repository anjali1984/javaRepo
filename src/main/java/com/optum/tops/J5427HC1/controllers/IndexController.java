package com.optum.tops.J5427HC1.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {
	
	@RequestMapping("/home")
	public String index(){
		return "index";
	}
}

