package com.yeahbutstill.kepotify;

import com.yeahbutstill.kepotify.entity.Album;
import com.yeahbutstill.kepotify.entity.Artist;
import com.yeahbutstill.kepotify.entity.Concert;
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
import java.time.LocalDateTime;
import java.time.Year;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.UUID;

class ArtisAlbumSongTest {

    @Test
    void testInsertArtist() {

        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        Artist artist = new Artist();
        artist.setName("Final Attack");
        artist.setBio("Final Attack is a Hardcore Band From Indonesia");
        artist.setWikipedia("https://en.wikipedia.org/wiki/Final_Attack");
        artist.setFacebook("https://www.facebook.com/finalattack.band");
        artist.setInstagram("https://www.instagram.com/finalattack_band");
        artist.setTwitter("https://twitter.com/finalattack_band");
        entityManager.persist(artist);

        Assertions.assertNotNull(artist);

        transaction.commit();
        entityManager.close();

    }

    @Test
    void testInsertAlbumAndSong() throws IOException {

        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        Album album = new Album();
        album.setTitle("From Dust and Ashes");
        String imageBytes = Arrays.toString(
                Files.readAllBytes(
                        Path.of(
                                Objects.requireNonNull(getClass().getResource("/images/a4186047900_10.jpg"))
                                        .getPath()
                        )
                )
        );
        album.setImage(imageBytes);
        album.setRelease(Year.of(2015));
        entityManager.persist(album);
        Assertions.assertNotNull(album);

        Song song1 = new Song();
        song1.setTitle("We're Invincible");
        song1.setDuration(100);
        song1.setAlbum(album);
        entityManager.persist(song1);

        Song song2 = new Song();
        song2.setTitle("Fish In a Pond");
        song2.setDuration(240);
        song2.setAlbum(album);
        entityManager.persist(song2);

        Song song3 = new Song();
        song3.setTitle("Buried Deep");
        song3.setDuration(134);
        song3.setAlbum(album);
        entityManager.persist(song3);

        Song song4 = new Song();
        song4.setTitle("Devils Bizz");
        song4.setDuration(300);
        song4.setAlbum(album);
        entityManager.persist(song4);

        Song song5 = new Song();
        song5.setTitle("Drifted Away");
        song5.setDuration(226);
        song5.setAlbum(album);
        entityManager.persist(song5);

        Song song6 = new Song();
        song6.setTitle("Shallow Mind");
        song6.setDuration(307);
        song6.setAlbum(album);
        entityManager.persist(song6);

        Song song7 = new Song();
        song7.setTitle("Kata Mereka");
        song7.setDuration(158);
        song7.setAlbum(album);
        entityManager.persist(song7);

        Song song8 = new Song();
        song8.setTitle("Overdosed");
        song8.setDuration(250);
        song8.setAlbum(album);
        entityManager.persist(song8);


        transaction.commit();
        entityManager.close();

    }


    @Test
    void testFindAlbumAndSong() {

        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        Album album = entityManager.find(Album.class, UUID.fromString("9fb8bdd8-b0e3-4d9d-bb2d-bf6a74de02f3"));
        Assertions.assertEquals("From Dust and Ashes", album.getTitle());
        Assertions.assertEquals(8, album.getSongs().size());

        album.getSongs().forEach(song -> {
            System.out.println(song.getTitle() + " - " + song.getArtists() + " - " + song.getAlbum().getTitle());
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

        Artist artist = entityManager.find(Artist.class, UUID.fromString("d92f445d-15f4-465a-bbd0-fbee14b3aa86"));
        artist.setHasAlbums(new HashSet<>());

        Album album1 = entityManager.find(Album.class, UUID.fromString("9fb8bdd8-b0e3-4d9d-bb2d-bf6a74de02f3"));
        Album album2 = entityManager.find(Album.class, UUID.fromString("dc1ce796-be52-4125-924b-098e402ec821"));

        artist.getHasAlbums().add(album1);
        artist.getHasAlbums().add(album2);

        Assertions.assertNotNull(artist.getHasAlbums());
        Assertions.assertEquals(2, artist.getHasAlbums().size());
        Assertions.assertEquals(8, album1.getSongs().size());
        Assertions.assertEquals(2, album2.getSongs().size());

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

        Artist artist = entityManager.find(Artist.class, UUID.fromString("de3c9ca1-5f31-44f1-bb61-cd22f94eba39"));
        Album album = null;

        for (Album item : artist.getHasAlbums()) {
            album = item;
            break;
        }

        artist.getHasAlbums().remove(album);
        entityManager.merge(artist);
        Assertions.assertNotNull(artist.getHasAlbums());

        transaction.commit();
        entityManager.close();

    }

    @Test
    void testSingSong() {

        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        Artist artist = entityManager.find(Artist.class, UUID.fromString("d92f445d-15f4-465a-bbd0-fbee14b3aa86"));
        artist.setSingSong(new HashSet<>());

        Song song1 = entityManager.find(Song.class, UUID.fromString("a9c58ef4-3dbe-4d88-aa00-886220c7ad84"));
        Song song2 = entityManager.find(Song.class, UUID.fromString("efb1b7c3-31fe-4e1f-a346-afb839ede459"));
        Song song3 = entityManager.find(Song.class, UUID.fromString("be368679-7d17-4590-85fb-49cc71c2c34e"));
        Song song4 = entityManager.find(Song.class, UUID.fromString("c0c26e68-5220-4d5b-ae42-e7909597e1a8"));
        Song song5 = entityManager.find(Song.class, UUID.fromString("a750cb1d-2453-4a61-9bf0-d1c1c9c3b2c0"));
        Song song6 = entityManager.find(Song.class, UUID.fromString("2de8c7b5-8ad7-42a7-b45f-c8d77f994fd6"));
        Song song7 = entityManager.find(Song.class, UUID.fromString("f24eb0eb-dd92-47ac-a578-2487ea3b062f"));
        Song song8 = entityManager.find(Song.class, UUID.fromString("606987ba-1016-4d32-8aa1-6a3f840f7b47"));

        artist.getSingSong().add(song1);
        artist.getSingSong().add(song2);
        artist.getSingSong().add(song3);
        artist.getSingSong().add(song4);
        artist.getSingSong().add(song5);
        artist.getSingSong().add(song6);
        artist.getSingSong().add(song7);
        artist.getSingSong().add(song8);
        Assertions.assertNotNull(artist.getSingSong());

        entityManager.merge(artist);

        transaction.commit();
        entityManager.close();

    }

    @Test
    void testFetchArtisHasAlbums() {

        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        Song song = entityManager.find(Song.class, UUID.fromString("a9c58ef4-3dbe-4d88-aa00-886220c7ad84"));
        Assertions.assertNotNull(song.getAlbum());
        Assertions.assertEquals(8, song.getAlbum().getSongs().size());
        Assertions.assertEquals("We're Invincible", song.getAlbum().getSongs().get(0).getTitle());

        transaction.commit();
        entityManager.close();

    }

    @Test
    void testInsertConcerts() {

        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        Concert concert1 = new Concert();
        concert1.setUrl("https://www.youtube.com/watch?v=dQw4w9WgXcQ");
        concert1.setEventAt(LocalDateTime.of(2021, 1, 13, 19, 12));
        concert1.setLocation("Jakarta - Rossi Music Fatmawati");
        concert1.setLon(-6.17);
        concert1.setLat(106.8);

        Concert concert2 = new Concert();
        concert2.setUrl("https://www.youtube.com/watch?v=dQw4w9WgXcQ");
        concert2.setEventAt(LocalDateTime.now());
        concert2.setLocation("Jakarta - Rossi Music Fatmawati");
        concert2.setLon(-6.17);
        concert2.setLat(106.8);

        Assertions.assertNotNull(concert1);
        Assertions.assertNotNull(concert2);

        entityManager.persist(concert1);
        entityManager.persist(concert2);

        transaction.commit();
        entityManager.close();

    }


    @Test
    void testInsertArtistPerforms() {

        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        Artist artist = entityManager.find(Artist.class, UUID.fromString("d92f445d-15f4-465a-bbd0-fbee14b3aa86"));
        artist.setPerforms(new HashSet<>());

        Concert concert1 = entityManager.find(Concert.class, UUID.fromString("6a1afe0d-ced7-44eb-9a74-d28ebfd41f11"));
        Concert concert2 = entityManager.find(Concert.class, UUID.fromString("5e0dcf71-b303-4f01-8086-2cbb976bdb55"));

        artist.getPerforms().add(concert1);
        artist.getPerforms().add(concert2);
        Assertions.assertNotNull(artist.getPerforms());

        entityManager.merge(artist);

        transaction.commit();
        entityManager.close();

    }

}
