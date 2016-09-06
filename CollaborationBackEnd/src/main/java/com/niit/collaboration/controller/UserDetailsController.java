package com.niit.collaboration.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.niit.collaboration.dao.UserDetailsDAO;
import com.niit.collaboration.model.UserDetails;

@RestController
public class UserDetailsController {
	
	@Autowired
	UserDetails userDetails;
	
	@Autowired
	UserDetailsDAO userDetailsDAO;
	
	@RequestMapping(value="/UserDetails/", method=RequestMethod.GET)
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

}
