package com.example.demo.Repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.Entity.Lead;
import com.example.demo.Entity.fileUpload;

public interface LeadRepo extends JpaRepository<Lead, Integer> {
	
	  @Query("SELECT p FROM Lead p WHERE " +
	            "p.fname LIKE CONCAT('%',:query, '%')" +
	            "Or p.email_id LIKE CONCAT('%', :query, '%')")

	List<Lead> searchProductsSQL(String query);

	

}
