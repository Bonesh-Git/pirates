package com.meli.cms.repository;

import com.meli.cms.emf.JpaUtil;
import com.meli.cms.entity.Customer;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.UUID;

public class CustomerRepository {

    public Customer save(Customer customer) {
        EntityManager entityManager = JpaUtil.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(customer);
        transaction.commit();
        entityManager.close();
        return customer;
    }

    public Customer findByCustomerId(UUID customerId) {
        EntityManager entityManager = JpaUtil.getEntityManager();
        Customer customer = entityManager.find(Customer.class, customerId);
        entityManager.close();
        return customer;
    }
}