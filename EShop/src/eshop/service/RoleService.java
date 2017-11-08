package eshop.service;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import eshop.entity.Role;

@Transactional
@Component
public class RoleService {
	@Autowired
	SessionFactory factory;
	
	public void insert(Role role) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(role);
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
	
	public void update(Role role) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.update(role);
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
	
	public void delete(Role role) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.delete(role);
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
	
	public void refresh(Role role) {
		Session session = factory.getCurrentSession();
		session.refresh(role);
	}
	
	public Role get(String id) {
		Session session = factory.getCurrentSession();
		Role role = (Role) session.get(Role.class, id);
		return role;
	}
	
	public List<Role> list() {
		String hql = "FROM Role";
		Session session = factory.getCurrentSession();
		Query query = session.createQuery(hql);
		List<Role> list = query.list();
		return list;
	}
}
