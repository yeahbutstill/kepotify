package com.yeahbutstill.kepotify;

import com.yeahbutstill.kepotify.entity.Transaction;
import com.yeahbutstill.kepotify.entity.TransactionCredit;
import com.yeahbutstill.kepotify.entity.TransactionDebit;
import com.yeahbutstill.kepotify.utils.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.LockModeType;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.UUID;

class LockingTest {

    @Test
    void testInsertOptimisticLocking() {

        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        // unmanaged entity
        Transaction transaction = new Transaction();
        transaction.setBalance(new BigDecimal(50_000_000));
        entityManager.persist(transaction); // managed entity

        TransactionCredit transactionCredit = new TransactionCredit();
        transactionCredit.setBalance(transaction.getBalance());
        transactionCredit.setCreditAmount(new BigDecimal(20_000_000));
        entityManager.persist(transactionCredit);

        TransactionDebit transactionDebit = new TransactionDebit();
        transactionDebit.setBalance(transaction.getBalance());
        transactionDebit.setDebitAmount(new BigDecimal(20_000_000));
        entityManager.persist(transactionDebit);

        Assertions.assertNotNull(transaction.getVersion());
        Assertions.assertNotNull(transactionCredit.getVersion());
        Assertions.assertNotNull(transactionDebit.getVersion());

        entityTransaction.commit();
        entityManager.close();

    }

    @Test
    void testUpdateOptimisticLocking1() throws InterruptedException {

        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        TransactionCredit transactionCredit = entityManager
                .find(TransactionCredit.class, UUID.fromString("14c5e083-54da-43ac-8b61-81f36c126ffc"));
        transactionCredit.setBalance(new BigDecimal(6000));

        // nunggu 10 detik sebelum commit
        Thread.sleep(10 * 1000L);
        entityManager.persist(transactionCredit);
        Assertions.assertNotNull(transactionCredit.getVersion());

        entityTransaction.commit();
        entityManager.close();

    }

    @Test
    void testUpdateOptimisticLocking2() {

        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        TransactionCredit transactionCredit = entityManager
                .find(TransactionCredit.class, UUID.fromString("14c5e083-54da-43ac-8b61-81f36c126ffc"));
        transactionCredit.setBalance(new BigDecimal(5000));

        entityManager.persist(transactionCredit);
        Assertions.assertNotNull(transactionCredit.getVersion());

        entityTransaction.commit();
        entityManager.close();

    }

    @Test
    void testUpdatePessimisticLocking1() throws InterruptedException {

        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        TransactionCredit transactionCredit = entityManager
                .find(TransactionCredit.class,
                        UUID.fromString("14c5e083-54da-43ac-8b61-81f36c126ffc"),
                        LockModeType.PESSIMISTIC_WRITE);
        transactionCredit.setBalance(new BigDecimal(50_000));
        Thread.sleep(10 * 1000L);
        entityManager.persist(transactionCredit);

        entityTransaction.commit();
        entityManager.close();

    }

    @Test
    void testUpdatePessimisticLocking2() {

        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction entityTransaction = entityManager.getTransaction();
        entityTransaction.begin();

        TransactionCredit transactionCredit = entityManager
                .find(TransactionCredit.class,
                        UUID.fromString("14c5e083-54da-43ac-8b61-81f36c126ffc"),
                        LockModeType.PESSIMISTIC_WRITE);
        transactionCredit.setBalance(new BigDecimal(5_000_000));
        entityManager.persist(transactionCredit);

        entityTransaction.commit();
        entityManager.close();

    }

}
