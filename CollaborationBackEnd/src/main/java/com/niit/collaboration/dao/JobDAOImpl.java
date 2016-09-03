package com.niit.collaboration.dao;

import org.springframework.stereotype.Repository;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.niit.collaboration.model.Job;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;

@Repository("jobDAO")
public class JobDAOImpl implements JobDAO{
	
	public JobDAOImpl()
	{
		System.out.println("*************default constructor called in JobDAOImpl***************");
	}
	
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public JobDAOImpl(SessionFactory sessionFactory)
	{
		this.sessionFactory = sessionFactory;
	}
	
	@Transactional
	public void saveOrUpdate(Job job)
	{
		System.out.println("*************saveOrUpdate called in JobDAOImpl***************");
		sessionFactory.getCurrentSession().saveOrUpdate(job);
	}
	
	@Transactional
	public List<Job> list()
	{
		System.out.println("*************list called in JobDAOImpl***************");
		@SuppressWarnings("unchecked")
		List<Job> list = (List<Job>) sessionFactory.getCurrentSession().createCriteria(Job.class).setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY).list();
		return list;
	}
	
	@Transactional
	public Job get(String id)
	{
		System.out.println("*************get called in JobDAOImpl***************");
		String hql = "from Job where id="+ "'" + id + "'";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		
		@SuppressWarnings("unchecked")
		List<Job> list = (List<Job>) query.list();
		
		if(list!=null && !list.isEmpty())
		{
			return list.get(0);
		}
		return null;
	}
	
	@Transactional
	public void delete(String id)
	{
		System.out.println("*************delete called in JobDAOImpl***************");
		Job job = new Job();
		job.setId(id);
		sessionFactory.getCurrentSession().delete(job);
	}

}
