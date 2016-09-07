package com.niit.collaboration.dao;

import org.springframework.stereotype.Repository;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.niit.collaboration.model.UserDetails;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;

@Repository("userDetailsDAO")
public class UserDetailsDAOImpl implements UserDetailsDAO{
	
	public UserDetailsDAOImpl()
	{
		System.out.println("*************default constructor called in UserDetailsDAOImpl***************");
	}
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public UserDetailsDAOImpl(SessionFactory sessionFactory)
	{
		this.sessionFactory = sessionFactory;
	}
	
	
	@Transactional
	public void saveOrUpdate(UserDetails userDetails)
	{
		System.out.println("*************saveOrUpdate called in UserDetailsDAO*************");
		sessionFactory.getCurrentSession().saveOrUpdate(userDetails);
	}
	
	@Transactional
	public List<UserDetails> list()
	{
		System.out.println("*************list called in UserDetailsDAO*************");
		@SuppressWarnings("unchecked")
		List<UserDetails> list = (List<UserDetails>) sessionFactory.getCurrentSession().createCriteria(UserDetails.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		return list;
	}
	
	@Transactional
	public UserDetails get(String id)
	{
		System.out.println("*************get called in UserDetailsDAO*************");
		String hql = "from UserDetails where id =" + "'"+ id + "'" ;
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		
		@SuppressWarnings("unchecked")
		List<UserDetails> list = (List<UserDetails>) query.list();
		
		if(list!=null && !list.isEmpty())
		{
			return list.get(0);
		}
		
		return null;
	}
	
	@Transactional
	public void delete(String id)
	{
		System.out.println("*************delete called in UserDetailsDAO*************");
		UserDetails userDetails = new UserDetails();
		userDetails.setId(id);
		sessionFactory.getCurrentSession().delete(userDetails);
	}
	
	@Transactional
	public boolean isValidUserDetails(String id,String password)
	{
		String hql = "from User where id= '" + id + "' and " + " password ='" + password+"'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		
		@SuppressWarnings("unchecked")
		List<UserDetails> list = (List<UserDetails>) query.list();
		
		if (list != null && !list.isEmpty()) {
			return true;
		}
		
		return false;
	}

}
