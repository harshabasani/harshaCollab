package com.niit.collaboration.dao;

import java.util.List;
import com.niit.collaboration.model.Blog;

public interface BlogDAO {
	
	public void saveOrUpdate(Blog blog);
	
	public List<Blog> list();
	
	public Blog get(String id);

}
