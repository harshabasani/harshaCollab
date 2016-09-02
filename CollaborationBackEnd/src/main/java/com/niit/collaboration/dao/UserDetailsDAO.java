package com.niit.collaboration.dao;

import com.niit.collaboration.model.UserDetails;

import java.util.List;

public interface UserDetailsDAO {
	
	public void saveOrUpdate(UserDetails userDetails);
	
	public List<UserDetails> list();

}
