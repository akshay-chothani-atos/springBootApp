package com.example.SampleLoginForm.resource.forms;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FormRepository extends JpaRepository<FormDetails,Long>{

	//@Query("SELECT u FROM User u WHERE u.form_no = ?1")
    public FormDetails findByformNo(String formNo);
    
    //@Query("SELECT * FROM form_details  WHERE email = ?1")
   	public List<FormDetails> findByEmail(String email);
   
    
}
