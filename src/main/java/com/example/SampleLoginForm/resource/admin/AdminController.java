package com.example.SampleLoginForm.resource.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AdminController {

	
	@RequestMapping("/login_admin")
	public String loginPage()
	{
		return "admin_login";
	}
}
 