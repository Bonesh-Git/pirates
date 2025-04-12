package com.meli.cms.repository;

import com.meli.cms.emf.JpaUtil;
import com.meli.cms.entity.Branch;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.UUID;

public class BranchRepository {

    public Branch save(Branch branch) {
        EntityManager entityManager = JpaUtil.getEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(branch);
        transaction.commit();
        entityManager.close();
        return branch;
    }

    public Branch findByBranchId(UUID branchId) {
        EntityManager entityManager = JpaUtil.getEntityManager();
        Branch branch = entityManager.find(Branch.class, branchId);
        entityManager.close();
        return branch;
    }
}