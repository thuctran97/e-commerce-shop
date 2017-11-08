package eshop.service;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import eshop.entity.Master;

@Transactional
@Component
public class MasterService {
	@Autowired
	SessionFactory factory;
	
	public void insert(Master master) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(master);
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
	
	public void update(Master master) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.update(master);
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
	
	public void delete(Master master) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.delete(master);
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
	
	public void refresh(Master master) {
		Session session = factory.getCurrentSession();
		session.refresh(master);
	}
	
	public Master get(String id) {
		Session session = factory.getCurrentSession();
		Master master = (Master) session.get(Master.class, id);
		return master;
	}
	
	public List<Master> list() {
		String hql = "FROM Master";
		Session session = factory.getCurrentSession();
		Query query = session.createQuery(hql);
		List<Master> list = query.list();
		return list;
	}
}
