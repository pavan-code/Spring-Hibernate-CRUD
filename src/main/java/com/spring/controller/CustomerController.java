package com.spring.controller;

import java.util.List;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.spring.dao.CustomerDAO;
import com.spring.entity.Customer;
import com.spring.entity.Mobile;
import com.spring.util.HibernateUtil;

@RestController
public class CustomerController {
	
	@Autowired
	CustomerDAO custdao;
	
	@GetMapping("/customers")
	public List<Customer> getCustomers() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		return custdao.findAll(Customer.class, session);
	}
	
	@PostMapping("/addCustomer")
	public String addCustomer(@RequestBody Customer customer) {
		System.out.println(customer);
		Customer c = new Customer();
		c.setFirstName(customer.getFirstName());
		c.setEmail(customer.getEmail());
		c.setLastName(customer.getLastName());
		Mobile m = new Mobile();
		m.setBand(customer.getMobile().getBand());
		m.setMobile(customer.getMobile().getMobile());
		m.setOperator(customer.getMobile().getOperator());
		c.setMobile(m);
		custdao.save(c, m);
		return "Customer added";
	}
	
	@GetMapping("/customer/{id}")
	public Customer getCustomer(@PathVariable int id) {
		return custdao.findById(id);
	}
	
	@DeleteMapping("/customer/{id}")
	public String deleteCustomer(@PathVariable int id) {
		return custdao.deleteById(id);		
	}
	
	@PutMapping("/customer/{id}")
	public Customer updateCustomer(@RequestBody Customer customer, @PathVariable int id) {
		return custdao.updateCustomer(customer, id);
	}
}
