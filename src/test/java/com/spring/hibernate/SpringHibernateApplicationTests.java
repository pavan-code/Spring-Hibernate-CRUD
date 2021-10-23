package com.spring.hibernate;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.spring.controller.CustomerController;
import com.spring.dao.CustomerDAO;
import com.spring.entity.Customer;

@SpringBootTest
class SpringHibernateApplicationTests {

	@Autowired
	CustomerDAO custdao;
	@Test
	void contextLoads() {
	}

	@Test
	void testCustomerController() {
		CustomerController c = new CustomerController();
//		String s = c.hello();
//		assertEquals("Hello", s);
//		Customer cust = c.getCustomer(42);
//		assertEquals("varshini", cust.getFirstName());
	}
}
