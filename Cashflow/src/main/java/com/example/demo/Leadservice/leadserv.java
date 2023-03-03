package com.example.demo.Leadservice;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.Entity.Lead;
import com.example.demo.Repository.LeadRepo;

@Service
public class leadserv implements servsim {
   @Autowired
  private LeadRepo repo;

   //Insert data method
@Override
public void insertData(Lead lead) {
	// TODO Auto-generated method stub
	
	repo.save(lead);
	
}

   //Show all data method
@Override
public List<Lead> getAllLead() {
	// TODO Auto-generated method stub
	List<Lead> lead = repo.findAll();
	return lead;
}

    //Delete data by Id Method
public void deleted(Integer id) {
	// TODO Auto-generated method stub
	repo.deleteById(id);
	
}
   
    //Update data by Id Method
@Override
public void updatedata(Lead lead, Integer id) {
	// TODO Auto-generated method stub
	
	repo.save(lead);
	
}

  //Update data by Id Method
@Override
public void saveOrUpdate(Lead lead) {
	// TODO Auto-generated method stub
	repo.save(lead);
	
}
   //Show data by Id Method
@Override
public Lead getById(Integer id) {
	// TODO Auto-generated method stub
	return repo.findById(id).get();
}
   // Search data by fname & Id Method
public List<Lead> searchProducts(String query) {
	// TODO Auto-generated method stub
	List<Lead> lead = repo.searchProductsSQL(query);
    return lead;
	
//	List<Lead> lead=this.repo.findByTitleContaning(query);
}

public void fileupload(Lead lead) {
	// TODO Auto-generated method stub
	repo.save(lead);
}

@Override
public String uploadImage(String path, MultipartFile file) throws IOException {
//	// TODO Auto-generated method stub
	
	String name =file.getOriginalFilename();
	
	String filepath = path+ File.separator+name;
	
	File f = new File(path);
	if(!f.exists()) {
		f.mkdir();
	}
	
	Files.copy(file.getInputStream(), Paths.get(filepath));
	
	return name;
	
}

//public void uploadImage(fileUpload f, MultipartFile file) {
//	
//	repo.save(f);
//	
//}



}


	
	



