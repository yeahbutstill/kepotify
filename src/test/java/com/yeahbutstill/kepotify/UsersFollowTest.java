package com.yeahbutstill.kepotify;

import com.yeahbutstill.kepotify.entity.*;
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
        users.setName("Dariu");
        users.setEmail("dariu@gmail.com");
        users.setPassword("dariu riu");
        users.setBirthday(LocalDate.of(1883, 12, 13));

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

        Users users = entityManager.find(Users.class, UUID.fromString("1da6de12-fcf9-4240-97ca-3e85684b670c"));
        users.setName("Turu");
        users.setEmail("turu@gmail.com");
        users.setPassword("turu terus");
        users.setBirthday(LocalDate.of(1980, 9, 1));

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

        Users users = entityManager.find(Users.class, UUID.fromString("1da6de12-fcf9-4240-97ca-3e85684b670c"));
        Assertions.assertNotNull(users);
        Assertions.assertEquals("Turu, turu@gmail.com, 1980-09-01", users.getUserDetails());

        transaction.commit();
        entityManager.close();

    }

    @Test
    void testUsersFollowArtist() {

        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        Users users = entityManager.find(Users.class, UUID.fromString("1da6de12-fcf9-4240-97ca-3e85684b670c"));
        users.setFollowArtist(new HashSet<>());

        Artist artist = entityManager.find(Artist.class, UUID.fromString("d92f445d-15f4-465a-bbd0-fbee14b3aa86"));

        users.getFollowArtist().add(artist);
        Assertions.assertNotNull(users);
        Assertions.assertNotNull(artist);
        entityManager.persist(users);

        transaction.commit();
        entityManager.close();

    }

    @Test
    void testUsersFollowPlaylist() {

        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        Users users = entityManager.find(Users.class, UUID.fromString("1da6de12-fcf9-4240-97ca-3e85684b670c"));
        users.setFollowArtist(new HashSet<>());

        Playlist playlist = entityManager.find(Playlist.class, UUID.fromString("587016cc-3c8c-4da2-9a26-76d7cb3d374e"));

        users.getFollowPlaylist().add(playlist);
        Assertions.assertNotNull(users);
        Assertions.assertNotNull(playlist);
        entityManager.persist(users);

        transaction.commit();
        entityManager.close();

    }

    @Test
    void testCreatePlaylist() throws IOException {

        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        Users turu = entityManager.find(Users.class, UUID.fromString("1da6de12-fcf9-4240-97ca-3e85684b670c"));

        PlaylistCategorie hardcore = new PlaylistCategorie();
        hardcore.setName("Jakarta Hardcore");
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
        playlist1.setName("Hardcore Aja Nich 3");
        playlist1.setDescription("Kumpulan lagu jakarta hardcore");
        String imageBytes1 = Arrays.toString(
                Files.readAllBytes(
                        Path.of(
                                Objects.requireNonNull(getClass().getResource("/images/a0193793750_16.jpg"))
                                        .getPath()
                        )
                )
        );
        playlist1.setImage(imageBytes1);
        playlist1.setUser(turu);
        playlist1.setPlaylistCategories(hardcore);
        entityManager.persist(playlist1);

        Assertions.assertNotNull(turu);
        Assertions.assertNotNull(playlist1);

        transaction.commit();
        entityManager.close();

    }

    @Test
    void testCreateContainsPlaylist() {

        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        Playlist playlist = entityManager.find(Playlist.class, UUID.fromString("fd71ff34-d845-4e74-ba69-c8cfc93600cf"));
        Song song1 = entityManager.find(Song.class, UUID.fromString("606987ba-1016-4d32-8aa1-6a3f840f7b47"));
        Song song2 = entityManager.find(Song.class, UUID.fromString("f24eb0eb-dd92-47ac-a578-2487ea3b062f"));
        Song song3 = entityManager.find(Song.class, UUID.fromString("2de8c7b5-8ad7-42a7-b45f-c8d77f994fd6"));
        Song song4 = entityManager.find(Song.class, UUID.fromString("a750cb1d-2453-4a61-9bf0-d1c1c9c3b2c0"));

        playlist.setContainSong(new HashSet<>());
        playlist.getContainSong().add(song1);
        playlist.getContainSong().add(song2);
        playlist.getContainSong().add(song3);
        playlist.getContainSong().add(song4);

        entityManager.merge(playlist);

        Assertions.assertNotNull(playlist);
        Assertions.assertNotNull(song1);
        Assertions.assertNotNull(song2);
        Assertions.assertNotNull(song3);
        Assertions.assertNotNull(song4);

        transaction.commit();
        entityManager.close();

    }

    @Test
    void testFindContainsPlaylist() {

        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        Playlist playlist = entityManager.find(Playlist.class, UUID.fromString("fd71ff34-d845-4e74-ba69-c8cfc93600cf"));

        playlist.getContainSong().forEach(song -> {
            System.out.println(song.getAlbum().getTitle());
            System.out.println(song.getArtists().stream().iterator().next().getName());
            System.out.println(song.getTitle());
        });

        Assertions.assertNotNull(playlist);

        transaction.commit();
        entityManager.close();

    }

}
