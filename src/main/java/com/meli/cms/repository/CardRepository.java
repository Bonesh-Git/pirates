package com.meli.cms.repository;

import com.meli.cms.emf.JpaUtil;
import com.meli.cms.entity.Card;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

public class CardRepository {

    public void save(Card card) {
        EntityManager entityManager = JpaUtil.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.merge(card);
        transaction.commit();
        entityManager.close();
    }
}