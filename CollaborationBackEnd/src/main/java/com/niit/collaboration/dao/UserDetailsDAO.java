package com.niit.collaboration.dao;

import com.niit.collaboration.model.UserDetails;

import java.util.List;

public interface UserDetailsDAO {
	
	public void saveOrUpdate(UserDetails userDetails); // for saving and updating both
	
	public List<UserDetails> list();                   // for retrieving the list 
	
	public UserDetails get(String id);                 // get by using id
	
	public void delete(String id);                     // for deletion by using id

	public boolean isValidUserDetails(String id, String password);  // for authentication

}
