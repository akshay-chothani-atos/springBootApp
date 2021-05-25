package com.example.SampleLoginForm.resource.forms;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.SampleLoginForm.resource.users.CustomUserDetails;

@Controller
public class FormController {

	@Autowired
	private FormRepository formRepo;

	@Autowired
	private FormService service;

	
	@RequestMapping("/login_form")
	public String loginPage()
	{
		return "form_login";
	}
	
	
	@GetMapping("/addFormDetails")
	public String addFormDetails(Model model) {
		model.addAttribute("form1", new FormDetails());

		return "form_Details_signup";
	}

	@PostMapping("/process_formDetails")
	public String processFormDetails(FormDetails formDetails) {

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();
		String email = userDetails.getEmail();

		formDetails.setEmail(email);

		formRepo.save(formDetails);

		return "form_success";
	}

	@GetMapping("/forms")
	public String listUsers(Model model, FormDetails formDetails) {
		List<FormDetails> listForms = formRepo.findAll();

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();
		String email = userDetails.getEmail();
		listForms = formRepo.findByEmail(email);
		model.addAttribute("listForms", listForms);

		return "forms";
	}
	
	public long countForms() {
		List<FormDetails> listForms = formRepo.findAll();

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();
		String email = userDetails.getEmail();

		listForms = formRepo.findByEmail(email);
		long noOfForms= listForms.size();
		return noOfForms;
		
	}

	/*
	 * @GetMapping("/forms1") public String listForms(Model model) {
	 * List<FormDetails> listForms = formRepo.findAll();
	 * model.addAttribute("listForms", listForms);
	 * 
	 * return "forms"; }
	 */
	@RequestMapping("/delete/{id}")
	private String deleteBook(@PathVariable("id") int id) {
		service.delete(id);
		return "redirect:/forms";
	}

	@GetMapping("/edit/{id}")
	public String showUpdateForm(@PathVariable("id") long id, Model model) {
		FormDetails forms = formRepo.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("Invalid user Id:" + id));

		model.addAttribute("forms", forms);
		return "edit_form";
	}
	
	@PostMapping("/update/{id}")
	public String updateUser(@PathVariable("id") long id, @Validated FormDetails formDetails, BindingResult result,
			Model model) {
		if (result.hasErrors()) {
			formDetails.setId(id);
			return "edit_form";
		}
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		CustomUserDetails userDetails = (CustomUserDetails) auth.getPrincipal();
		String email = userDetails.getEmail();
		formDetails.setEmail(email);
		formRepo.save(formDetails);
		return "redirect:/forms";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveProduct(@ModelAttribute("forms") FormDetails forms) {
		service.save(forms);

		return "redirect:/forms";
	}

}
