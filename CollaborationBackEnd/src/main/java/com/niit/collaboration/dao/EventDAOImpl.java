package com.niit.collaboration.dao;

import org.springframework.stereotype.Repository;
import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.niit.collaboration.model.Event;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import org.hibernate.Query;

@Repository("eventDAO")
public class EventDAOImpl implements EventDAO{
	
	public EventDAOImpl()
	{
		System.out.println("*************default constructor called in EventDAOImpl***************");
	}
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public EventDAOImpl(SessionFactory sessionFactory)
	{
		this.sessionFactory = sessionFactory;
	}
	
	@Transactional
	public void saveOrUpdate(Event event)
	{
		System.out.println("*************saveOrUpdate called in EventDAOImpl***************");
		sessionFactory.getCurrentSession().saveOrUpdate(event);
	}
	
	@Transactional
	public List<Event> list()
	{
		System.out.println("*************list called in EventDAOImpl***************");
		@SuppressWarnings("unchecked")
		List<Event> list = (List<Event>) sessionFactory.getCurrentSession().createCriteria(Event.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		
		return list;
	}
	
	@Transactional
	public Event get(String id)
	{
		System.out.println("*************get called in EventDAOImpl***************");
		String hql = "from Event where id="+ "'"+ id +"'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		
		@SuppressWarnings("unchecked")
		List<Event> list = (List<Event>) query.list();
		
		if(list!=null && !list.isEmpty())
		{
			return list.get(0);
		}
		return null;
	}
	
	@Transactional
	public void delete(String id)
	{
		System.out.println("*************delete called in EventDAOImpl***************");
		Event event = new Event();
		event.setId(id);
		sessionFactory.getCurrentSession().delete(event);
	}

}
