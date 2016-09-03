package com.niit.collaboration.dao;

import com.niit.collaboration.model.Event;
import java.util.List;

public interface EventDAO {
	
	public void saveOrUpdate(Event event);
	
	public List<Event> list();
	
	public Event get(String id);
	
	public void delete(String id);

}
