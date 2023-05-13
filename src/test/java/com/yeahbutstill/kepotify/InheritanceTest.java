package com.yeahbutstill.kepotify;

import com.yeahbutstill.kepotify.entity.Premium;
import com.yeahbutstill.kepotify.entity.Users;
import com.yeahbutstill.kepotify.entity.Vip;
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

}
