package com.meli.cms.repository;

import com.meli.cms.emf.JpaUtil;
import com.meli.cms.entity.Deposit;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.UUID;

public class DepositRepository {

    public Deposit save(Deposit deposit) {
        EntityManager entityManager = JpaUtil.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(deposit);
        transaction.commit();
        entityManager.close();
        return deposit;
    }

    public Deposit findByDepositId(UUID depositId) {
        EntityManager entityManager = JpaUtil.getEntityManager();
        Deposit deposit = entityManager.find(Deposit.class, depositId);
        entityManager.close();
        return deposit;
    }
}