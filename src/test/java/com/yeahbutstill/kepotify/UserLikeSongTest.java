package com.yeahbutstill.kepotify;

import com.yeahbutstill.kepotify.entity.Song;
import com.yeahbutstill.kepotify.entity.Users;
import com.yeahbutstill.kepotify.utils.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.UUID;

class UserLikeSongTest {

    @Test
    void testUserLikeSong() {

        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        Users user = entityManager.find(Users.class, UUID.fromString("db3023a9-1dfd-4466-ad88-e4fd747b7353"));
        user.setLikes(new HashSet<>());
        user.getLikes().add(entityManager.find(Song.class, UUID.fromString("9b90378b-77f1-4da1-94f2-034dde04c31d")));
        user.getLikes().add(entityManager.find(Song.class, UUID.fromString("9e1deea6-2194-427f-8cfa-12ffac980ae5")));

        entityManager.merge(user);
        Assertions.assertNotNull(user.getLikes());

        user.getLikes().forEach(song -> System.out.println(song.getTitle()));

        transaction.commit();
        entityManager.close();

    }

}
