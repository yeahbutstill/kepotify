package com.yeahbutstill.kepotify;

import com.yeahbutstill.kepotify.entity.Album;
import com.yeahbutstill.kepotify.entity.Artist;
import com.yeahbutstill.kepotify.entity.Song;
import com.yeahbutstill.kepotify.utils.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Year;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.UUID;

class ArtisAlbumSongTest {

    @Test
    void testInsertAlbumAndSong() throws IOException {

        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        Album album = new Album();
        album.setTitle("Good To Feel");
        String imageBytes = Arrays.toString(
                Files.readAllBytes(
                        Path.of(
                                Objects.requireNonNull(getClass().getResource("/images/a0193793750_16.jpg"))
                                        .getPath()
                        )
                )
        );
        album.setImage(imageBytes);
        album.setRelease(Year.of(2018));
        entityManager.persist(album);
        Assertions.assertNotNull(album);

        Song song1 = new Song();
        song1.setTitle("Song 1");
        song1.setDuration(100);
        song1.setAlbum(album);
        entityManager.persist(song1);

        Song song2 = new Song();
        song2.setTitle("Song 2");
        song2.setDuration(100);
        song2.setAlbum(album);
        entityManager.persist(song2);

        transaction.commit();
        entityManager.close();

    }


    @Test
    void testFindAlbumAndSong() {

        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        Album album = entityManager.find(Album.class, UUID.fromString("bfe2f301-9ec6-49d4-a7af-0c0bdcfd5663"));
        Assertions.assertEquals("Candy Says", album.getTitle());
        Assertions.assertEquals(2, album.getSongs().size());

        album.getSongs().forEach(song -> {
            System.out.println(song.getTitle());
        });

        transaction.commit();
        entityManager.close();

    }

    @Test
    void testArtisHasAlbums() {

        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        Artist artist = entityManager.find(Artist.class, UUID.fromString("3867b2b0-90fb-4e35-b61f-c409226c518c"));
        artist.setHasAlbums(new HashSet<>());

        Album album1 = entityManager.find(Album.class, UUID.fromString("bfe2f301-9ec6-49d4-a7af-0c0bdcfd5663"));
        Album album2 = entityManager.find(Album.class, UUID.fromString("e2c3ee10-b096-429b-81f5-9642f0afa6b6"));

        artist.getHasAlbums().add(album1);
        artist.getHasAlbums().add(album2);

        entityManager.merge(artist);

        transaction.commit();
        entityManager.close();

    }

    @Test
    void testUpdateArtisHasAlbums() {

        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        Artist artist = entityManager.find(Artist.class, UUID.fromString("3867b2b0-90fb-4e35-b61f-c409226c518c"));
        artist.getHasAlbums().forEach(album -> {
            System.out.println(album.getTitle());
        });

        transaction.commit();
        entityManager.close();

    }

}
