package com.spring.hibernate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.spring.controller.CustomerController;
import com.spring.dao.CustomerDAO;
import com.spring.entity.Customer;

public class MockitoControllerTest {
	@InjectMocks
    private CustomerController customerController;

    @Mock
    private CustomerDAO custdao;
    @BeforeEach
    public void init() {
        MockitoAnnotations.initMocks(this);
    }
    @Test
    public void testController() {
    	Customer customer = customerController.getCustomer(42);
    	verify(custdao).findById(42);
    	assertEquals("varshini", customer.getFirstName());
    }
}
