package com.example.demo.Leadcontroller;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.tomcat.util.http.fileupload.FileUpload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.example.demo.Entity.Lead;
import com.example.demo.Entity.fileUpload;
import com.example.demo.Leadservice.leadserv;


import io.micrometer.common.util.StringUtils;
import jakarta.xml.ws.Response;

@RestController
@RequestMapping("/ckpk")
public class lController {

	@Autowired
	private leadserv sev;
	
	@Value("${project.image}")
	private String path;

	private String message;
	
    //Insert the data to database
	@PostMapping
	public void savedata(@RequestBody Lead lead) {

		sev.insertData(lead);

	}
	
     //Show all data
	@GetMapping
	public List<Lead> getall() {
		
		return sev.getAllLead();
	}
	
	// Show data by using id
	@GetMapping("/getdata/{id}")
	public Lead getdatabyId(@PathVariable("id") Integer id) {
		
     return		sev.getById(id);
		
	}
	
    //Delete data by  using id
	@DeleteMapping("/delete/{id}")
	public void deleteData(@PathVariable("id") Integer id) {
		
		sev.deleted(id);
	}
	
     //Update data by using id
	@PutMapping("/update/{id}")
	public void updateone(@RequestBody Lead lead, @PathVariable("id") Integer id) {

		sev.updatedata(lead, id);	

	}
	
	//Search data by using fname & email_id
	 @GetMapping("/search")
	    public ResponseEntity<List<Lead>> searchProducts(@RequestParam("query") String query){
	        return ResponseEntity.ok(sev.searchProducts(query));
	    }
	 @PostMapping("/fileupload")
	 public void savefile(@RequestBody Lead lead) {
		 sev.fileupload(lead);
	 }
	
	 @PostMapping("/upload")
	 public ResponseEntity<fileUpload> filesave(@RequestParam("image") MultipartFile image, String path){
		 
		   
			 
			String filename = null;
			try {
				this.sev.uploadImage(path, image);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				return new ResponseEntity<>(new fileUpload( filename=null, message= "Image not uploaded"),HttpStatus.INTERNAL_SERVER_ERROR);
			}
			 
			 return new ResponseEntity<>(new fileUpload( filename, message="Image uploaded"),HttpStatus.OK);
			 
		
	 }
	 
//	 @PostMapping("/upload")
//	 public String uploadFile(@RequestParam("file") MultipartFile file) {
//		    String fileName = StringUtils.cleanPath(file.getOriginalFilename());
//		    long fileSize = file.getSize();
//		    byte[] fileContent = file.getBytes();	
//
//		    // code to save file to disk or database
//		}
	 
	 

//	     @PostMapping("/uploadFile")
//	     public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file,fileUpload f) {
//	         // Check if file is empty
//	         if (file.isEmpty()) {
//	             return ResponseEntity.badRequest().body("Please upload a file");
//	         }
//
//	         try {
//	             // Get the file bytes
//	             byte[] bytes = file.getBytes();
//	             
//	             // Save the file
//	             // ...
//	             sev.uploadImage(f, file);
//
//	             // Return success response
//	             return ResponseEntity.ok("File uploaded successfully");
//
//	         } catch (Exception e) {
//	             return ResponseEntity.badRequest().body("Failed to upload file");
//	         }
//	     }
//	 }


	
	 
	    

}

