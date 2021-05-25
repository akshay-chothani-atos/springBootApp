package com.example.SampleLoginForm.resource.admin;

import org.springframework.data.jpa.repository.JpaRepository;


public interface AdminRepository extends JpaRepository<Admin, Long> {
   // @Query("SELECT u FROM admin u WHERE u.email = ?1")
    public Admin findByEmail(String email);
     
}