package com.yeahbutstill.kepotify;

import com.yeahbutstill.kepotify.entity.*;
import com.yeahbutstill.kepotify.utils.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

class InheritanceTest {

    @Test
    void testUserMemberTypeSingleTable() {

        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        Users users = new Users();
        users.setName("John");
        users.setEmail("j@j.com");
        users.setBirthday(LocalDate.now());
        users.setPassword("password");
        entityManager.persist(users);
        Assertions.assertNotNull(users);

        Premium premium = new Premium();
        premium.setName("Lah");
        premium.setEmail("lah@l.com");
        premium.setBirthday(LocalDate.now());
        premium.setPassword("password");
        premium.setHargaPremeium(new BigDecimal(100));
        premium.setMemberExpiredDate(LocalDate.now().plusYears(1).atStartOfDay());
        entityManager.persist(premium);
        Assertions.assertNotNull(premium);

        Vip vip = new Vip();
        vip.setName("Loh");
        vip.setEmail("loh@l.com");
        vip.setBirthday(LocalDate.now());
        vip.setPassword("password");
        vip.setHargaVIP(new BigDecimal(200));
        vip.setMemberExpiredDate(LocalDate.now().plusYears(2).atStartOfDay());
        entityManager.persist(vip);
        Assertions.assertNotNull(vip);

        transaction.commit();
        entityManager.close();

    }

    @Test
    void testFindUserMemberTypeSingleTable() {

        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        Premium premium = entityManager.find(Premium.class, UUID.fromString("183dcfbd-82cb-4c6f-92b4-3f0ecd68e8cc"));
        Assertions.assertEquals("Lah", premium.getName());

        Users users = entityManager.find(Users.class, UUID.fromString("7e907093-3a1d-433f-82bf-53982e592c75"));
        Vip vip = (Vip) users;
        Assertions.assertEquals("Loh", vip.getName());


        transaction.commit();
        entityManager.close();

    }

    @Test
    void testInsertPaymentTypeJoinTable() {

        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        PaymentGopay gopay = new PaymentGopay();
        gopay.setGopayId("0877777777777");
        gopay.setAmount(new BigDecimal(100_000));
        entityManager.persist(gopay);
        Assertions.assertNotNull(gopay);

        PaymentCreditCard creditCard = new PaymentCreditCard();
        creditCard.setMaskedCard("4555-5555-5555-5555");
        creditCard.setAmount(new BigDecimal(500_000));
        creditCard.setBank("BCA");
        entityManager.persist(creditCard);
        Assertions.assertNotNull(creditCard);

        Payment payment = entityManager.find(Payment.class, UUID.fromString(String.valueOf(gopay.getId())));
        PaymentGopay paymentGopay = (PaymentGopay) payment;
        Assertions.assertEquals(gopay.getGopayId(), paymentGopay.getGopayId());

        transaction.commit();
        entityManager.close();

    }

    @Test
    void testFindPaymentTypeJoinTable() {

        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        Payment payment = entityManager.find(Payment.class, UUID.fromString("3d039b9f-5a78-4331-8661-19e830cc95a5"));
        PaymentGopay paymentGopay = (PaymentGopay) payment;
        PaymentGopay findPaymentGopay = entityManager.find(PaymentGopay.class, UUID.fromString("3d039b9f-5a78-4331-8661-19e830cc95a5"));
        PaymentCreditCard findPaymentCreditCard = entityManager.find(PaymentCreditCard.class, UUID.fromString("121636c0-7682-4e09-833d-856368700055"));

        Assertions.assertEquals(paymentGopay.getId(), payment.getId());
        Assertions.assertEquals(paymentGopay.getGopayId(), findPaymentGopay.getGopayId());
        Assertions.assertEquals("BCA", findPaymentCreditCard.getBank());

        transaction.commit();
        entityManager.close();

    }


    @Test
    void testInsertTransactionsTypeSingleTable() {

        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        Transaction transaction1 = new Transaction();
        transaction1.setBalance(new BigDecimal(100_000_000));
        entityManager.persist(transaction1);
        Assertions.assertNotNull(transaction1);

        TransactionDebit transactionDebit = new TransactionDebit();
        transactionDebit.setBalance(new BigDecimal(200_000_000));
        transactionDebit.setDebitAmount(new BigDecimal(100_000_000));
        entityManager.persist(transactionDebit);
        Assertions.assertNotNull(transactionDebit);

        TransactionCredit transactionCredit = new TransactionCredit();
        transactionCredit.setBalance(new BigDecimal(100_000_000));
        transactionCredit.setCreditAmount(new BigDecimal(100_000_000));
        entityManager.persist(transactionCredit);
        Assertions.assertNotNull(transactionCredit);

        transaction.commit();
        entityManager.close();

    }

    @Test
    void testFindChildTransactionsTypeSingleTable() {

        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        TransactionDebit transactionDebit = entityManager.find(TransactionDebit.class, UUID.fromString("72b85fa4-eb83-4564-9e39-d0a8626019e0"));
        TransactionCredit transactionCredit = entityManager.find(TransactionCredit.class, UUID.fromString("75b0997a-a2c3-49e0-bc11-7e3ca007bb07"));
        Assertions.assertNotNull(transactionDebit);
        Assertions.assertNotNull(transactionCredit);

        transaction.commit();
        entityManager.close();

    }

}
