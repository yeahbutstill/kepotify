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

        Users user = entityManager.find(Users.class, UUID.fromString("1da6de12-fcf9-4240-97ca-3e85684b670c"));
        user.setLikes(new HashSet<>());
        user.getLikes().add(entityManager.find(Song.class, UUID.fromString("cb4ca109-3eee-4d9b-884b-875a52d0cecd")));
        user.getLikes().add(entityManager.find(Song.class, UUID.fromString("f24eb0eb-dd92-47ac-a578-2487ea3b062f")));

        entityManager.merge(user);
        Assertions.assertNotNull(user.getLikes());

        user.getLikes().forEach(song -> System.out.println(song.getTitle()));

        transaction.commit();
        entityManager.close();

    }

}
