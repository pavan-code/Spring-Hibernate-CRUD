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
public class CustomerDAO {

	public List<Customer> findAll(Class<Customer> type, Session session) {
		CriteriaBuilder builder = session.getCriteriaBuilder();
		CriteriaQuery<Customer> criteria = builder.createQuery(type);
		criteria.from(type);
		List<Customer> data = session.createQuery(criteria).getResultList();
		return data;
	}

	public Customer save(Customer customer, Mobile mobile) {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction t = session.beginTransaction();
		session.save(customer);
		session.save(mobile);
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
			session.delete(session.find(Mobile.class, customer.getMobile().getId()));
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
			Mobile m = session.get(Mobile.class, existing.getMobile().getId());
			existing.setEmail(customer.getEmail());
			existing.setFirstName(customer.getFirstName());
			existing.setLastName(customer.getLastName());
			existing.setMobile(customer.getMobile());
			m.setMobile(customer.getMobile().getMobile());
			m.setBand(customer.getMobile().getBand());
			m.setOperator(customer.getMobile().getOperator());
			t.commit();
			session.close();
			return existing;
		}
	}
}
