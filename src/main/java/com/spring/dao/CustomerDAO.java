package com.spring.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import com.spring.entity.Customer;
import com.spring.entity.Mobile;
import com.spring.util.HibernateUtil;

@Repository
public class CustomerDAO implements DAOInterface {

	public List<Customer> findAll(Class<Customer> type, Session session) {
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Customer> criteria = builder.createQuery(type);
		criteria.from(type);
		List<Customer> data = session.createQuery(criteria).getResultList();
		return data;
	}

	public Customer save(Customer customer, List<Mobile> mobiles) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.save(customer);
		t.commit();
		session.close();
		return customer;
	}

	public Customer findById(int id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Customer customer = session.find(Customer.class, id);
		session.close();
		return customer;
	}

	public String deleteById(int id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		Customer customer = session.find(Customer.class, id);

		if (customer == null) {
			return "Customer not found";
		} else {
			session.delete(customer);
			t.commit();
			session.close();
			return "Customer deleted";
		}
	}
	
	public Customer updateCustomer(Customer customer, int id) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		Customer existing = session.find(Customer.class, id);
		if(existing == null) {
			return new Customer();
		} else {			
			existing.setEmail(customer.getEmail());
			existing.setFirstName(customer.getFirstName());
			existing.setLastName(customer.getLastName());
			existing.setMobiles(customer.getMobiles());
			session.save(existing);
			t.commit();
			session.close();
			return existing;
		}
	}
}
