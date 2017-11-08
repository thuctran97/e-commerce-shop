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

import eshop.entity.Category;
import eshop.entity.Supplier;

@Transactional
@Component
public class CategoryService {
	@Autowired
	SessionFactory factory;
	/**
	 * Them moi Category
	 * @param category la doi tuong moi 
	 * @throws khong them duoc vao co so du lieu
	 */
	public void insert(Category category) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.save(category);
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
	
	/**
	 * Cap nhat Category
	 * @param category doi tuong cap nhat 
	 * @throws khong cap nhat duoc
	 */
	public void update(Category category) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.update(category);
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
	
	/**
	 * Xoa Category
	 * @param category chua Id can xoa
	 * @throws Khong xoa duoc 
	 */
	public void delete(Category category) {
		Session session = factory.openSession();
		Transaction t = session.beginTransaction();
		try {
			session.delete(category);
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
	
	/**
	 * Tai lai Category tu CSDL
	 * @param category chua Id can tai 
	 */
	public void refresh(Category category) {
		Session session = factory.getCurrentSession();
		session.refresh(category);
		// Nap kem Products voi category
		Hibernate.initialize(category.getProducts());
	}
	
	/**
	 * Tai Category tu CSDL
	 * @param id la Id cua Category can tai
	 * @return Thuc the tai duoc 
	 */
	public Category get(Integer id) {
		Session session = factory.getCurrentSession();
		Category category = (Category) session.get(Category.class, id);
		// Nap kem Products voi category
		Hibernate.initialize(category.getProducts());
		return category;
	}
	
	/**
	 * Truy van tat ca Category
	 * @return Danh sach tat ca Category 
	 */
	public List<Category> list() {
		String hql = "FROM Category";
		Session session = factory.getCurrentSession();
		Query query = session.createQuery(hql);
		List<Category> list = query.list();
		return list;
	}

	public List<Category> get3Items() {
		String hql = "FROM Category WHERE SIZE(products) >= 5";
		Session session = factory.getCurrentSession();
		Query query = session.createQuery(hql);
		List<Category> list = query.list();
		
		Collections.shuffle(list);  
		List<Category> cat3 = list.subList(0, 3);
		for(Category c : cat3){
			Hibernate.initialize(c.getProducts());
		}
		return cat3;
	}
}
