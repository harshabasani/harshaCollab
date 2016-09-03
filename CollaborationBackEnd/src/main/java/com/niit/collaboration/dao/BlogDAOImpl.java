package com.niit.collaboration.dao;

import org.springframework.stereotype.Repository;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.niit.collaboration.model.Blog;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;


@Repository("blogDAO")
public class BlogDAOImpl implements BlogDAO{
	
	public BlogDAOImpl()
	{
		System.out.println("*************default constructor called in BlogDAOImpl***************");
	}
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public BlogDAOImpl(SessionFactory sessionFactory)
	{
		this.sessionFactory = sessionFactory;
	}
	
	
	@Transactional
	public void saveOrUpdate(Blog blog)
	{
		System.out.println("*************saveOrUpdate called in BlogDAOImpl***************");
		sessionFactory.getCurrentSession().saveOrUpdate(blog);
	}
	
	@Transactional
	public List<Blog> list()
	{
		System.out.println("*************list called in BlogDAOImpl***************");
		@SuppressWarnings("unchecked")
		List<Blog> list = (List<Blog>) sessionFactory.getCurrentSession().createCriteria(Blog.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		return list;
	}
	
	@Transactional
	public Blog get(String id)
	{
		System.out.println("*************get called in BlogDAOImpl***************");
		String hql = "from Blog where id="+ "'" +id+ "'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		
		@SuppressWarnings("unchecked")
		List<Blog> list = (List<Blog>) query.list();
		
		if(list!=null && !list.isEmpty())
		{
			return list.get(0);
		}
		return null;
	}
	
	@Transactional
	public void delete(String id)
	{
		System.out.println("*************delete called in BlogDAOImpl***************");
		Blog blog = new Blog();
		blog.setId(id);
		sessionFactory.getCurrentSession().delete(blog);
	}
	
}
