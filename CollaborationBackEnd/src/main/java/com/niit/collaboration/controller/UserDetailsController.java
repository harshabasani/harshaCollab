package com.niit.collaboration.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.niit.collaboration.dao.UserDetailsDAO;
import com.niit.collaboration.model.Role;
import com.niit.collaboration.model.UserDetails;
import com.niit.collaboration.model.UserRole;

@RestController
public class UserDetailsController {
	
	@Autowired
	UserDetails userDetails;
	
	@Autowired
	UserDetailsDAO userDetailsDAO;
	
	@RequestMapping(value="/UserDetails", method=RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<UserDetails>> listAllUserDetails()
	{
		System.out.println("***********listAllUserDetails called in UserDetailsController**********");
		List<UserDetails> userDetails = userDetailsDAO.list();
		if(userDetails.isEmpty())
		{
			return new ResponseEntity<List<UserDetails>>(HttpStatus.NO_CONTENT);
					
		}
		return new ResponseEntity<List<UserDetails>>(userDetails,HttpStatus.OK);
	}

	@RequestMapping(value = "/UserDetails/{id}", method = RequestMethod.GET, consumes = "application/x-www-form-urlencoded", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserDetails> getUserDetails(@RequestParam("id") String id)
	{
		UserDetails userDetails = userDetailsDAO.get(id);
		if (userDetails == null)
		{
			return new ResponseEntity<UserDetails>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<UserDetails>(userDetails, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/UserDetails/", method = RequestMethod.POST, consumes = "application/json")
	public ResponseEntity<Void> createUserDetails(@RequestBody UserDetails userDetails, UriComponentsBuilder ucBuilder)
	{
		if (userDetailsDAO.get(userDetails.getId())!=null)
		{
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		
		userDetails.setId("2");
		userDetails.setName("harsha");
		userDetails.setPassword("harsha11");
		userDetails.setEmail("harsha@gmail.com");
		userDetails.setGender("male");
		userDetails.setAddress("hyderabad");
		userDetails.setMobile("9948922554");
		userDetails.setStatus('N');
		userDetails.setRole("ROLE_USER");
		userDetails.setReason("is a student");
		userDetailsDAO.saveOrUpdate(userDetails);
		
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/UserDetails/{id}").buildAndExpand(userDetails.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/UserDetails/{id}", method = RequestMethod.PUT,consumes="application/x-www-form-urlencoded",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserDetails> updateUserDetails(@PathVariable("id") String id, @RequestBody UserDetails userDetails) {
		if (userDetailsDAO.get(id) == null) 
		{
			return new ResponseEntity<UserDetails>(HttpStatus.NOT_FOUND);
		}

		userDetailsDAO.saveOrUpdate(userDetails);
		return new ResponseEntity<UserDetails>(userDetails, HttpStatus.OK);
	}

	@RequestMapping(value = "/UserDetails/{id}", method = RequestMethod.DELETE,consumes="application/x-www-form-urlencoded")
	public ResponseEntity<UserDetails> deleteUserDetails(@PathVariable("id") String id)
	  {
		UserDetails UserDetails = userDetailsDAO.get(id);
		if (UserDetails == null) {
			return new ResponseEntity<UserDetails>(HttpStatus.NOT_FOUND);
		}

		userDetailsDAO.delete(id);
		return new ResponseEntity<UserDetails>(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(value = "/authenticate/", method = RequestMethod.POST,consumes="application/x-www-form-urlencoded",produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<UserDetails> authenticate(@RequestParam("username") String id, @RequestParam("password") String password) {
	
		if (userDetailsDAO.isValidUserDetails(id, password)==false) 
		{
			return new ResponseEntity<UserDetails>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<UserDetails>(HttpStatus.OK);
	}
	
}
