package com.meli.cms.emf;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JpaUtil {
    private static final EntityManagerFactory factory;

    static {
        factory = Persistence.createEntityManagerFactory("jpa");
    }

    public static EntityManager getEntityManager() {
        return factory.createEntityManager();
    }

    public static void closeFactory() {
        if (factory.isOpen()) {
            factory.close();
        }
    }
}