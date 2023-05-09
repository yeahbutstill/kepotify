package com.yeahbutstill.kepotify;

import com.yeahbutstill.kepotify.entity.Artist;
import com.yeahbutstill.kepotify.entity.Playlist;
import com.yeahbutstill.kepotify.entity.PlaylistCategory;
import com.yeahbutstill.kepotify.entity.Users;
import com.yeahbutstill.kepotify.enums.EnvironmentType;
import com.yeahbutstill.kepotify.utils.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.UUID;

class UsersFollowTest {

    @Test
    void testInsertUser() {

        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        Users users = new Users();
        users.setName("Gara");
        users.setEmail("gara@ya.ru");
        users.setPassword("gara");
        users.setBirthday(LocalDate.of(1983, 5, 13));

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
        users.setEnvironment(EnvironmentType.UAT);

        Assertions.assertNotNull(users.getName());
        entityManager.merge(users);

        transaction.commit();
        entityManager.close();

    }

    @Test
    void testLoadUserDetails() {

        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        Users users = entityManager.find(Users.class, UUID.fromString("9b1036a0-06ca-46da-99a1-e02c025d6629"));
        Assertions.assertNotNull(users);
        Assertions.assertEquals("Gara, gara@ya.ru, 1983-05-13", users.getUserDetails());

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


    @Test
    void testCreatePlaylist() throws IOException {

        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        Users kakashi = new Users();
        kakashi.setName("Kakashi");
        kakashi.setEmail("kakashi@ya.ru");
        kakashi.setPassword("kaka");
        kakashi.setBirthday(LocalDate.of(1983, 5, 13));
        entityManager.persist(kakashi);

        PlaylistCategory hardcore = new PlaylistCategory();
        hardcore.setName("Hardcore");
        String imageBytes = Arrays.toString(
                Files.readAllBytes(
                        Path.of(
                                Objects.requireNonNull(getClass().getResource("/images/a0193793750_16.jpg"))
                                        .getPath()
                        )
                )
        );
        hardcore.setImage(imageBytes);
        hardcore.setIcon(imageBytes);
        entityManager.persist(hardcore);

        Playlist playlist1 = new Playlist();
        playlist1.setName("Hardcore Aja Nich 1");
        playlist1.setDescription("Kumpulan lagu hardcore");
        String imageBytes1 = Arrays.toString(
                Files.readAllBytes(
                        Path.of(
                                Objects.requireNonNull(getClass().getResource("/images/a0193793750_16.jpg"))
                                        .getPath()
                        )
                )
        );
        playlist1.setImage(imageBytes1);
        playlist1.setUser(kakashi);
        playlist1.setPlaylistCategory(hardcore);
        entityManager.persist(playlist1);

        Playlist playlist2 = new Playlist();
        playlist2.setName("Hardcore Aja Nich 2");
        playlist2.setDescription("Kumpulan lagu hardcore");
        String imageBytes2 = Arrays.toString(
                Files.readAllBytes(
                        Path.of(
                                Objects.requireNonNull(getClass().getResource("/images/a0193793750_16.jpg"))
                                        .getPath()
                        )
                )
        );
        playlist2.setImage(imageBytes2);
        playlist2.setUser(kakashi);
        playlist2.setPlaylistCategory(hardcore);
        entityManager.persist(playlist2);



        Assertions.assertNotNull(kakashi);
        Assertions.assertNotNull(playlist1);
        Assertions.assertNotNull(playlist2);

        transaction.commit();
        entityManager.close();

    }

}
