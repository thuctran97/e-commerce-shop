package eshop.service;

import java.util.Collections;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import eshop.entity.Supplier;

@Transactional
@Component
public class SupplierService {
	@Autowired
	SessionFactory factory;
	
	public void insert(Supplier supplier) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(supplier);
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
	
	public void update(Supplier supplier) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.update(supplier);
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
	
	public void delete(Supplier supplier) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.delete(supplier);
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
	
	public void refresh(Supplier supplier) {
		Session session = factory.getCurrentSession();
		session.refresh(supplier);
		// Nap kem Products voi supplier
		Hibernate.initialize(supplier.getProducts());
	}
	
	public Supplier get(String id) {
		Session session = factory.getCurrentSession();
		Supplier supplier = (Supplier) session.get(Supplier.class, id);
		// Nap kem Products voi supplier
		Hibernate.initialize(supplier.getProducts());
		return supplier;
	}
	
	public List<Supplier> list() {
		String hql = "FROM Supplier";
		Session session = factory.getCurrentSession();
		Query query = session.createQuery(hql);
		List<Supplier> list = query.list();
		return list;
	}

	public List<Supplier> get5Items() {
		String hql = "FROM Supplier WHERE SIZE(products)>=4";
		Session session = factory.getCurrentSession();
		Query query = session.createQuery(hql);
		List<Supplier> list = query.list();
		Collections.shuffle(list);
		List<Supplier> sup5 = list.subList(0, 5);
		for (Supplier s: sup5) {
			Hibernate.initialize(s.getProducts());
		}
		return sup5;
		
	}

	public List<Object[]> inventoryBySupplier() {
		String hql = "SELECT p.supplier.name, SUM(p.unitPrice*p.quantity),SUM(p.quantity),"+
				" MIN(p.unitPrice), MAX(p.unitPrice), AVG(p.unitPrice)"+
				"FROM Product p GROUP BY p.supplier.name";
		Session session = factory.getCurrentSession();
		Query query = session.createQuery(hql);
		List<Object[]> list = query.list();
		return list;
	}
}
