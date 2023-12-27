package com.yeahbutstill.kepotify;

import com.yeahbutstill.kepotify.entity.Episode;
import com.yeahbutstill.kepotify.entity.Podcast;
import com.yeahbutstill.kepotify.entity.PodcastCategories;
import com.yeahbutstill.kepotify.entity.Users;
import com.yeahbutstill.kepotify.utils.JpaUtil;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.EntityTransaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.nio.file.Path;
import java.util.Objects;
import java.util.UUID;

class PodcastCategoriesTest {

    @Test
    void testInsertCategories() {

        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        PodcastCategories podcastCategories = new PodcastCategories();
        podcastCategories.setName("Tech");
        Path path = new File(Objects.requireNonNull(getClass()
                        .getResource("/images/a4186047900_10.jpg"))
                .getFile()).toPath();

        podcastCategories.setIcon(String.valueOf(path));
        podcastCategories.setImage(String.valueOf(path));

        entityManager.persist(podcastCategories);
        Assertions.assertNotNull(podcastCategories.getName());
        Assertions.assertNotNull(podcastCategories.getId());

        transaction.commit();
        entityManager.close();

    }

    @Test
    void testInsertPodcast() {

        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        Users user = entityManager.find(Users.class, UUID.fromString("db3023a9-1dfd-4466-ad88-e4fd747b7353"));
        PodcastCategories podcastCategories = entityManager.find(PodcastCategories.class, UUID.fromString("10e5c348-837f-4d30-b1e8-bc1c13798079"));


        Podcast podcast1 = new Podcast();
        podcast1.setName("Ngalor Ngidul");
        podcast1.setPodcastCategories(podcastCategories);
        podcast1.setUserPodcasts(user);
        Path path = new File(Objects.requireNonNull(getClass()
                        .getResource("/images/a4186047900_10.jpg"))
                .getFile()).toPath();
        podcast1.setImage(String.valueOf(path));
        podcast1.setAbout("yeahbutstill");
        Assertions.assertNotNull(podcast1.getName());
        Assertions.assertNotNull(podcast1.getPodcastCategories());
        Assertions.assertNotNull(podcast1.getUserPodcasts());

        entityManager.persist(podcast1);


        transaction.commit();
        entityManager.close();

    }

    @Test
    void testInsertEpisodes() {

        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        Episode episode1 = new Episode();
        episode1.setTitle("Ngalor Ngidul");
        episode1.setDescription("yeahbutstill");
        episode1.setPodcast(entityManager.find(Podcast.class, UUID.fromString("1e24b26d-5ae6-424a-9c83-f5996cb78653")));
        episode1.setDuration(1000);

        Assertions.assertNotNull(episode1.getTitle());
        Assertions.assertNotNull(episode1.getDescription());
        Assertions.assertNotNull(episode1.getPodcast());
        Assertions.assertNotNull(episode1.getDuration());

        entityManager.persist(episode1);

        transaction.commit();
        entityManager.close();

    }

    @Test
    void testLazyFindEpisodes() {

        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        Podcast podcast = entityManager.find(Podcast.class, UUID.fromString("1e24b26d-5ae6-424a-9c83-f5996cb78653"));
        Episode episode = entityManager.find(Episode.class, UUID.fromString("8f5306d9-1cf0-48ea-a40e-683ff0903974"));
        Assertions.assertNotNull(podcast);
        Assertions.assertNotNull(podcast.getEpisodes());
        Assertions.assertNotNull(episode);

        transaction.commit();
        entityManager.close();

    }

}
