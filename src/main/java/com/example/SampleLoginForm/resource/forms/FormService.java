package com.example.SampleLoginForm.resource.forms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class FormService {

	@Autowired
	FormRepository formRepository;

	public void delete(long id) {
		formRepository.deleteById(id);
	}

	public void save(FormDetails forms) {
		formRepository.save(forms);
	}

	public FormDetails get(long id) {
		return formRepository.findById(id).get();
	}
	
}