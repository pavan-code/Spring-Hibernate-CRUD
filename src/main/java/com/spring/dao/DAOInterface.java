package com.spring.dao;

import java.util.List;

import org.hibernate.Session;

import com.spring.entity.Customer;
import com.spring.entity.Mobile;

public interface DAOInterface {
	public List<Customer> findAll(Class<Customer> type, Session session);
	public Customer save(Customer customer, List<Mobile> mobiles);
	public Customer findById(int id);
	public String deleteById(int id);
	public Customer updateCustomer(Customer customer, int id) ;
}
