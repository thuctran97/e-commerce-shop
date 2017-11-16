package eshop.service;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import eshop.entity.OrderDetail;

@Transactional
@Component
public class OrderDetailService {
	@Autowired
	SessionFactory factory;
	
	public void insert(OrderDetail orderDetail) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(orderDetail);
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
	
	public void update(OrderDetail orderDetail) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.update(orderDetail);
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
	
	public void delete(OrderDetail orderDetail) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.delete(orderDetail);
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
	
	public void refresh(OrderDetail orderDetail) {
		Session session = factory.getCurrentSession();
		session.refresh(orderDetail);
	}
	
	public OrderDetail get(Integer id) {
		Session session = factory.getCurrentSession();
		OrderDetail orderDetail = (OrderDetail) session.get(OrderDetail.class, id);
		return orderDetail;
	}
	
	public List<OrderDetail> list() {
		String hql = "FROM OrderDetail";
		Session session = factory.getCurrentSession();
		Query query = session.createQuery(hql);
		List<OrderDetail> list = query.list();
		return list;
	}

	public List<Object[]> revenueByProduct() {
		String hql = "SELECT d.product.name, SUM(d.unitPrice*d.quantity)*(1-d.discount),"+
					" SUM(d.quantity), MAX(d.unitPrice)," +
					" MIN(d.unitPrice), AVG(d.unitPrice)"+
					" FROM OrderDetail d"
					+"GROUP BY d.product.name";
		Session session = factory.getCurrentSession();
		Query query = session.createQuery(hql);
		List<Object[]> list = query.list();
		return list;
	}
	public List<Object[]> revenueByCategory() {
		String hql = "SELECT d.product.category.nameVN, SUM(d.unitPrice*d.quantity)*(1-d.discount),"+
					" SUM(d.quantity), MAX(d.unitPrice)," +
					" MIN(d.unitPrice), AVG(d.unitPrice)"+
					" FROM OrderDetail d"
					+"GROUP BY d.product.category.nameVN";
		Session session = factory.getCurrentSession();
		Query query = session.createQuery(hql);
		List<Object[]> list = query.list();
		return list;
	}
	public List<Object[]> revenueBySupplier() {
		String hql = "SELECT d.product.supplier.name, SUM(d.unitPrice*d.quantity)*(1-d.discount),"+
					" SUM(d.quantity), MAX(d.unitPrice)," +
					" MIN(d.unitPrice), AVG(d.unitPrice)"+
					" FROM OrderDetail d"
					+"GROUP BY d.product.supplier.name";
		Session session = factory.getCurrentSession();
		Query query = session.createQuery(hql);
		List<Object[]> list = query.list();
		return list;
	}
	public List<Object[]> revenueByCustomer() {
		String hql = "SELECT d.order.customer.id, SUM(d.unitPrice*d.quantity)*(1-d.discount),"+
					" SUM(d.quantity), MAX(d.unitPrice)," +
					" MIN(d.unitPrice), AVG(d.unitPrice)"+
					" FROM OrderDetail d"
					+"GROUP BY d.order.customer.id";
		Session session = factory.getCurrentSession();
		Query query = session.createQuery(hql);
		List<Object[]> list = query.list();
		return list;
	}
	public List<Object[]> revenueByYear() {
		String hql = "SELECT YEAR(d.order.orderDate)," +
				" SUM(d.unitPrice*d.quantity*(1-d.discount))," +
				" SUM(d.quantity)," +
				" MIN(d.unitPrice)," +
				" MAX(d.unitPrice)," +
				" AVG(d.unitPrice)" +
				" FROM OrderDetail d " +
				" GROUP BY YEAR(d.order.orderDate)" +
				" ORDER BY YEAR(d.order.orderDate)";
		Session session = factory.getCurrentSession();
		Query query = session.createQuery(hql);
		List<Object[]> list = query.list();
		return list;
	}
	
	public List<Object[]> revenueByQuarter() {
		String hql = "SELECT CEILING(MONTH(d.order.orderDate)/3.0)," +
				" SUM(d.unitPrice*d.quantity*(1-d.discount))," +
				" SUM(d.quantity)," +
				" MIN(d.unitPrice)," +
				" MAX(d.unitPrice)," +
				" AVG(d.unitPrice)" +
				" FROM OrderDetail d " +
				" GROUP BY CEILING(MONTH(d.order.orderDate)/3.0)" +
				" ORDER BY CEILING(MONTH(d.order.orderDate)/3.0)";
		Session session = factory.getCurrentSession();
		Query query = session.createQuery(hql);
		List<Object[]> list = query.list();
		return list;
	}
	
	public List<Object[]> revenueByMonth() {
		String hql = "SELECT MONTH(d.order.orderDate)," +
				" SUM(d.unitPrice*d.quantity*(1-d.discount))," +
				" SUM(d.quantity)," +
				" MIN(d.unitPrice)," +
				" MAX(d.unitPrice)," +
				" AVG(d.unitPrice)" +
				" FROM OrderDetail d " +
				" GROUP BY MONTH(d.order.orderDate)" +
				" ORDER BY MONTH(d.order.orderDate)";
		Session session = factory.getCurrentSession();
		Query query = session.createQuery(hql);
		List<Object[]> list = query.list();
		return list;
	}
}
