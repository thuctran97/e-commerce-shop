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
import eshop.entity.Master;

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

	public List<Integer> getWebActionIds(String roleId) {
		String hql = "SELECT webAction.id FROM ActionRole WHERE role.id=:rid";
		Session session = factory.getCurrentSession();
		Query query = session.createQuery(hql);
		query.setParameter("rid", roleId);
		List<Integer> list = query.list();
		return list;
	}

	public void insertOrDelete(ActionRole actionRole) {
		Session session = factory.getCurrentSession();
		try {
			String hql = "FROM ActionRole " +
					" WHERE role.id=:rid AND webAction.id=:aid";
			Query query = session.createQuery(hql);
			query.setParameter("rid", actionRole.getRole().getId());
			query.setParameter("aid", actionRole.getWebAction().getId());
			ActionRole ar = (ActionRole) query.uniqueResult();
			
			session.delete(ar);
		} 
		catch (Exception e) {
			session.save(actionRole);
		}
	}


	public boolean exist(Master master, String actionname) {
		String hql = "SELECT COUNT(ar) FROM ActionRole ar WHERE ar.webAction.name=:aname" +
				" AND ar.role.id IN (SELECT mr.role.id FROM MasterRole mr WHERE mr.master.id=:mid)";
		//đếm trong ActionRole, nơi mà có tên action trùng với actionname nhập vô (tìm WebActionId có name = actionname)
		//đồng thời cái role được phép sử dụng action đó phải là 1 trong những cái role mà master có quyền sở hữu
		//đếm =1 -> tồn tại 
		Session session = factory.getCurrentSession();
		Query query = session.createQuery(hql);
		query.setParameter("aname", actionname);
		query.setParameter("mid", master.getId());
		long count = (Long) query.uniqueResult();
		return count > 0; //count=1 -> tồn tại
	}
}
