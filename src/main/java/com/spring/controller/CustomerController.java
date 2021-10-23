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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.dao.CustomerDAO;
import com.spring.entity.Customer;
import com.spring.entity.Mobile;
import com.spring.util.HibernateUtil;

@RestController
@RequestMapping("/customer")
public class CustomerController {
	
	@Autowired
	CustomerDAO custdao;
	
	@GetMapping
	public List<Customer> getCustomers() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		return custdao.findAll(Customer.class, session);
	}
	
	@PostMapping
	public String addCustomer(@RequestBody Customer customer) {
		custdao.save(customer, customer.getMobiles());
		return "Customer added";
	}
	
	@GetMapping("/{id}")
	public Customer getCustomer(@PathVariable int id) {
		return custdao.findById(id);
	}
	
	@DeleteMapping("/{id}")
	public String deleteCustomer(@PathVariable int id) {
		return custdao.deleteById(id);		
	}
	
	@PutMapping("/{id}")
	public Customer updateCustomer(@RequestBody Customer customer, @PathVariable int id) {
		return custdao.updateCustomer(customer, id);
	}
}
