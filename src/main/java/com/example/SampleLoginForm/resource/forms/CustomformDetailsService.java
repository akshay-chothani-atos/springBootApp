package com.example.SampleLoginForm.resource.forms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service 
public class CustomformDetailsService implements UserDetailsService {
 
    @Autowired
    private FormRepository formRepo;
     
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	FormDetails form = formRepo.findByformNo(username);
        if (form == null) {
            throw new UsernameNotFoundException("User not found");
        }
        return new CustomFormDetails(form);
    }
    
}