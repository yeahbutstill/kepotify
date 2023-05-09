package com.yeahbutstill.kepotify;

import com.yeahbutstill.kepotify.entity.Artist;
import com.yeahbutstill.kepotify.entity.Users;
import com.yeahbutstill.kepotify.enums.StatusRecord;
import com.yeahbutstill.kepotify.utils.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.UUID;

class UsersFollowTest {

    @Test
    void testInsertUser() {

        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        Users users = new Users();
        users.setName("Maya");
        users.setEmail("maya@ya.ru");
        users.setPassword("maya");
        users.setBirthday(LocalDate.of(1993, 9, 22));
        users.setEnvironment("SIT");
        users.setStatusRecord(StatusRecord.ACTIVE);

        Assertions.assertNotNull(users);
        entityManager.persist(users);

        transaction.commit();
        entityManager.close();

    }

    @Test
    void testUpdateUser() {

        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        Users users = entityManager.find(Users.class, UUID.fromString("920c96a1-77b8-4ce0-b7ca-bad8555e3bae"));
        users.setName("Naruto");
        users.setEmail("naruto@ya.ru");
        users.setPassword("naruto");
        users.setBirthday(LocalDate.of(1980, 9, 1));
        users.setEnvironment("SIT");

        Assertions.assertNotNull(users.getName());
        entityManager.merge(users);

        transaction.commit();
        entityManager.close();

    }

    @Test
    void testUsersFollow() {

        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        Users users = entityManager.find(Users.class, UUID.fromString("920c96a1-77b8-4ce0-b7ca-bad8555e3bae"));
        users.setFollowArtist(new HashSet<>());

        Artist artist = entityManager.find(Artist.class, UUID.fromString("3867b2b0-90fb-4e35-b61f-c409226c518c"));

        users.getFollowArtist().add(artist);
        Assertions.assertNotNull(users);
        Assertions.assertNotNull(artist);

        transaction.commit();
        entityManager.close();

    }

}
