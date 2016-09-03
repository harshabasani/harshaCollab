package com.niit.collaboration.dao;

import org.springframework.stereotype.Repository;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import com.niit.collaboration.model.Chat;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;

@Repository
public class ChatDAOImpl implements ChatDAO{
	
	public ChatDAOImpl()
	{
		System.out.println("*************default constructor called in ChatDAOImpl***************");
	}
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public ChatDAOImpl(SessionFactory sessionFactory)
	{
		this.sessionFactory = sessionFactory;
	}
	
	
	@Transactional
	public void saveOrUpdate(Chat chat)
	{
		System.out.println("*************saveOrUpdate called in ChatDAOImpl***************");
		sessionFactory.getCurrentSession().saveOrUpdate(chat);
	}
	
	@Transactional
	public List<Chat> list()
	{
		System.out.println("*************list called in ChatDAOImpl***************");
		@SuppressWarnings("unchecked")
		List<Chat> list = (List<Chat>) sessionFactory.getCurrentSession().createCriteria(Chat.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		return list;
	}
	
    @Transactional
	public Chat get(String id)
	{
    	System.out.println("*************get called in ChatDAOImpl***************");
		String hql = "from Chat where id="+ "'" +id+ "'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		
		@SuppressWarnings("unchecked")
		List<Chat> list = (List<Chat>) query.list();
		
		if(list!=null && !list.isEmpty())
		{
			return list.get(0);
		}
		return null;
	}
    
    @Transactional
    public void delete(int id)
    {
    	System.out.println("*************delete called in ChatDAOImpl***************");
    	Chat chat = new Chat();
    	chat.setId(id);
    	sessionFactory.getCurrentSession().delete(chat);
    }
}




















