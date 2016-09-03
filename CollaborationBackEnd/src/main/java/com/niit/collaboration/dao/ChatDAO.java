package com.niit.collaboration.dao;

import com.niit.collaboration.model.Chat;
import java.util.List;

public interface ChatDAO {
	
	public void saveOrUpdate(Chat chat);
	
	public List<Chat> list();
	
	public Chat get(String id);
	
	public void delete(int id);

}
