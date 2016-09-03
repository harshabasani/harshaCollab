package com.niit.collaboration.dao;
import com.niit.collaboration.model.Job;
import java.util.List;

public interface JobDAO {
	
	public void saveOrUpdate(Job job);
	
    public List<Job> list();
    
    public Job get(String id);
    
    public void delete(String id);

}
