package com.niit.collaboration.controller;

import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.niit.collaboration.dao.BlogDAO;
import com.niit.collaboration.dao.UserDetailsDAO;
import com.niit.collaboration.model.Blog;
import com.niit.collaboration.model.UserDetails;

@RestController
public class BlogController {
	
	@Autowired
	Blog blog;
	
	@Autowired
	BlogDAO blogDAO;
	
	@GetMapping("/blog")
	//@RequestMapping(value="/blog", method=RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<Blog>> listBlog()
	{
		System.out.println("***********listBlog called in BlogController**********");
		List<Blog> blog = blogDAO.list();
		if(blog.isEmpty())
		{
			return new ResponseEntity<List<Blog>>(HttpStatus.NO_CONTENT);
					
		}
		return new ResponseEntity<List<Blog>>(blog,HttpStatus.OK);
	}

	@GetMapping("/blog/{id}")
	public ResponseEntity<Blog> getBlog(@RequestParam("id") String id)
	{
		Blog blog = blogDAO.get(id);
		if (blog == null)
		{
			return new ResponseEntity<Blog>(HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Blog>(blog, HttpStatus.OK);
	}
	
	@PostMapping("/blog")
	public ResponseEntity<Void> createBlog(@RequestBody Blog blog, UriComponentsBuilder ucBuilder)
	{
		if (blogDAO.get(blog.getId())!=null)
		{
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
		
		blog.setId("2");
		blog.setTitle("placement");
		blog.setDescription("about placement");
		blog.setStatus('A');
		blog.setUser_id("1");
		blog.setReason("this is about the placement");
		blog.setDate_time(Calendar.getInstance().getTime());
		
		blogDAO.saveOrUpdate(blog);
		
		HttpHeaders headers = new HttpHeaders();
		headers.setLocation(ucBuilder.path("/blog/{id}").buildAndExpand(blog.getId()).toUri());
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
	}
	
	@PutMapping("/blog")
	public ResponseEntity<Blog> updateBlog(@PathVariable("id") String id, @RequestBody Blog blog) {
		if (blogDAO.get(id) == null) 
		{
			return new ResponseEntity<Blog>(HttpStatus.NOT_FOUND);
		}

		blogDAO.saveOrUpdate(blog);
		return new ResponseEntity<Blog>(blog, HttpStatus.OK);
	}

	@DeleteMapping("/blog")
	public ResponseEntity<Blog> deleteBlog(@PathVariable("id") String id)
	  {
		Blog blog = blogDAO.get(id);
		if (blog == null) {
			return new ResponseEntity<Blog>(HttpStatus.NOT_FOUND);
		}

		blogDAO.delete(id);
		return new ResponseEntity<Blog>(HttpStatus.NO_CONTENT);
	}

}
