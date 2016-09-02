package com.niit.collaboration.dao;

import org.springframework.stereotype.Repository;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.niit.collaboration.model.UserDetails;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import org.hibernate.Criteria;

@Repository("userDetailsDAO")
public class UserDetailsDAOImpl implements UserDetailsDAO{
	
	public UserDetailsDAOImpl()
	{
		
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
		@SuppressWarnings({ "unchecked" })
		List<UserDetails> list = (List<UserDetails>) sessionFactory.getCurrentSession().createCriteria(UserDetails.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		return list;
	}

}
