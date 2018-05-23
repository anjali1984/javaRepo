package com.optum.tops.J5427HC1.controllers.Imp;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.optum.tops.J5427HC1.controllers.IIndexController;

@Controller
public class IndexController implements IIndexController {
	
	/* (non-Javadoc)
	 * @see com.optum.tops.J5427HC1.controllers.IIndexController#index()
	 */
	@Override
	@RequestMapping("/home")
	public String index(){
		return "index";
	}
}

