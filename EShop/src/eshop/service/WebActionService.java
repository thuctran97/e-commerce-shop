package eshop.service;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import eshop.entity.WebAction;

@Transactional
@Component
public class WebActionService {
	@Autowired
	SessionFactory factory;
	
	public void insert(WebAction webAction) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(webAction);
			t.commit();
		} 
		catch (Exception e) {
			t.rollback();
			throw new RuntimeException(e);
		}
		finally{
			session.close();
		}
	}
	
	public void update(WebAction webAction) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.update(webAction);
			t.commit();
		} 
		catch (Exception e) {
			t.rollback();
			throw new RuntimeException(e);
		}
		finally{
			session.close();
		}
	}
	
	public void delete(WebAction webAction) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.delete(webAction);
			t.commit();
		} 
		catch (Exception e) {
			t.rollback();
			throw new RuntimeException(e);
		}
		finally{
			session.close();
		}
	}
	
	public void refresh(WebAction webAction) {
		Session session = factory.getCurrentSession();
		session.refresh(webAction);
	}
	
	public WebAction get(Integer id) {
		Session session = factory.getCurrentSession();
		WebAction webAction = (WebAction) session.get(WebAction.class, id);
		return webAction;
	}
	
	public List<WebAction> list() {
		String hql = "FROM WebAction";
		Session session = factory.getCurrentSession();
		Query query = session.createQuery(hql);
		List<WebAction> list = query.list();
		return list;
	}

	public boolean exist(String actionname) {
		String hql = "SELECT COUNT(w) FROM WebAction w WHERE w.name=:aname";
		Session session = factory.getCurrentSession();
		Query query = session.createQuery(hql);
		query.setParameter("aname", actionname);
		long count = (Long) query.uniqueResult();
		return count > 0; //>0-> =1 ton tai -> true 
	}
	
}
