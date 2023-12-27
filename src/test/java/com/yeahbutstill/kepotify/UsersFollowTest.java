package com.yeahbutstill.kepotify;

import com.yeahbutstill.kepotify.entity.*;
import com.yeahbutstill.kepotify.utils.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Path;
import java.time.LocalDate;
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

        Users users = entityManager.find(Users.class, UUID.fromString("e968af07-dd64-4c09-989e-6e70829aef08"));
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

        Users users = entityManager.find(Users.class, UUID.fromString("e968af07-dd64-4c09-989e-6e70829aef08"));
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

        Users users = entityManager.find(Users.class, UUID.fromString("e968af07-dd64-4c09-989e-6e70829aef08"));
        users.setFollowArtist(new HashSet<>());

        Artist artist = entityManager.find(Artist.class, UUID.fromString("9798d12c-386d-47d4-b3e1-1d78be2e9be0"));

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

        Users users = entityManager.find(Users.class, UUID.fromString("e968af07-dd64-4c09-989e-6e70829aef08"));
        users.setFollowArtist(new HashSet<>());

        Playlist playlist = entityManager.find(Playlist.class, UUID.fromString("dbc474ba-5ffe-47cd-ae29-dda1387d81fd"));

        users.getFollowPlaylist().add(playlist);

        entityManager.persist(users);
        Assertions.assertNotNull(users);
        Assertions.assertNotNull(playlist);

        transaction.commit();
        entityManager.close();

    }

    @Test
    void testCreatePlaylist() {

        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        Users turu = entityManager.find(Users.class, UUID.fromString("e968af07-dd64-4c09-989e-6e70829aef08"));

        PlaylistCategorie hardcore = new PlaylistCategorie();
        hardcore.setName("Jakarta Hardcore");
        Path path = new File(Objects.requireNonNull(getClass()
                        .getResource("/images/a4186047900_10.jpg"))
                .getFile()).toPath();
        hardcore.setImage(String.valueOf(path));
        hardcore.setIcon(String.valueOf(path));
        entityManager.persist(hardcore);

        Playlist playlist1 = new Playlist();
        playlist1.setName("Hardcore Aja Nich 3");
        playlist1.setDescription("Kumpulan lagu jakarta hardcore");
        Path path1 = new File(Objects.requireNonNull(getClass()
                        .getResource("/images/a4186047900_10.jpg"))
                .getFile()).toPath();
        playlist1.setImage(String.valueOf(path1));
        playlist1.setUserPlaylists(turu);
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

        Playlist playlist = entityManager.find(Playlist.class, UUID.fromString("129efe1d-d02d-4e0b-9651-85738a4b3d51"));
        Song song1 = entityManager.find(Song.class, UUID.fromString("e9674617-d36e-450a-b84c-f2af73b6bc85"));
        Song song2 = entityManager.find(Song.class, UUID.fromString("8b435a42-0caa-46e3-9007-c79793b75701"));
        Song song3 = entityManager.find(Song.class, UUID.fromString("57cedceb-ae63-47d0-b24d-c3a2058ac259"));
        Song song4 = entityManager.find(Song.class, UUID.fromString("b020003d-79aa-4346-bf04-028d33d1324b"));

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

        Playlist playlist = entityManager.find(Playlist.class, UUID.fromString("dbc474ba-5ffe-47cd-ae29-dda1387d81fd"));

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
