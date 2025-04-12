package com.meli.cms.service;

import com.meli.cms.carrier.CustomerRegisterCarrier;
import com.meli.cms.entity.Customer;
import com.meli.cms.repository.CustomerRepository;

import java.util.UUID;


public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer registerCustomer(CustomerRegisterCarrier customerRegisterCarrier) {
        return customerRepository.save(new Customer(customerRegisterCarrier.getFirstName(), customerRegisterCarrier.getLastName(), customerRegisterCarrier.getNationalId()));

    }

    public Customer getCustomer(UUID customerId) {
        return customerRepository.findByCustomerId(customerId);
    }
}