package com.kpi.job_search.main;

import com.kpi.job_search.exceptions.NotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

	@Value("${swagger.enable}")
	private boolean swaggerEnable;

	@GetMapping
	public String api() {
		if (swaggerEnable)
			return "redirect:/swagger-ui.html";
		throw new NotFoundException();
	}


}
