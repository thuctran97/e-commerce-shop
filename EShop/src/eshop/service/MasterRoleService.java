package eshop.service;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import eshop.entity.MasterRole;

@Transactional
@Component
public class MasterRoleService {
	@Autowired
	SessionFactory factory;
	
	public void insert(MasterRole masterRole) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(masterRole);
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
	
	public void update(MasterRole masterRole) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.update(masterRole);
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
	
	public void delete(MasterRole masterRole) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.delete(masterRole);
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
	
	public void refresh(MasterRole masterRole) {
		Session session = factory.getCurrentSession();
		session.refresh(masterRole);
	}
	
	public MasterRole get(Integer id) {
		Session session = factory.getCurrentSession();
		MasterRole masterRole = (MasterRole) session.get(MasterRole.class, id);
		return masterRole;
	}
	
	public List<MasterRole> list() {
		String hql = "FROM MasterRole";
		Session session = factory.getCurrentSession();
		Query query = session.createQuery(hql);
		List<MasterRole> list = query.list();
		return list;
	}
}
