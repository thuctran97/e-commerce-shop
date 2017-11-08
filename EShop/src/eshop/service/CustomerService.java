package eshop.service;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import eshop.entity.Customer;

@Transactional
@Component
public class CustomerService {
	@Autowired
	SessionFactory factory;
	
	public void insert(Customer customer) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(customer);
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
	
	public void update(Customer customer) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.update(customer);
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
	
	public void delete(Customer customer) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.delete(customer);
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
	
	public void refresh(Customer customer) {
		Session session = factory.getCurrentSession();
		session.refresh(customer);
	}
	
	public Customer get(String id) {
		Session session = factory.getCurrentSession();
		Customer customer = (Customer) session.get(Customer.class, id);
		return customer;
	}
	
	public List<Customer> list() {
		String hql = "FROM Customer";
		Session session = factory.getCurrentSession();
		Query query = session.createQuery(hql);
		List<Customer> list = query.list();
		return list;
	}
}
