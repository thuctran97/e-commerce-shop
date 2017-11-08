package eshop.service;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import eshop.entity.ActionRole;

@Transactional
@Component
public class ActionRoleService {
	@Autowired
	SessionFactory factory;
	
	public void insert(ActionRole actionRole) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(actionRole);
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
	
	public void update(ActionRole actionRole) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.update(actionRole);
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
	
	public void delete(ActionRole actionRole) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.delete(actionRole);
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
	
	public void refresh(ActionRole actionRole) {
		Session session = factory.getCurrentSession();
		session.refresh(actionRole);
	}
	
	public ActionRole get(Integer id) {
		Session session = factory.getCurrentSession();
		ActionRole actionRole = (ActionRole) session.get(ActionRole.class, id);
		return actionRole;
	}
	
	public List<ActionRole> list() {
		String hql = "FROM ActionRole";
		Session session = factory.getCurrentSession();
		Query query = session.createQuery(hql);
		List<ActionRole> list = query.list();
		return list;
	}
}
