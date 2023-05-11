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

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Objects;
import java.util.UUID;

class PodcastCategoriesTest {

    @Test
    void testInsertCategories() throws IOException {

        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        PodcastCategories podcastCategories = new PodcastCategories();
        podcastCategories.setName("Tech");
        String imageBytes = Arrays.toString(
                Files.readAllBytes(
                        Path.of(
                                Objects.requireNonNull(getClass().getResource("/images/a4186047900_10.jpg"))
                                        .getPath()
                        )
                )
        );
        podcastCategories.setIcon(imageBytes);
        podcastCategories.setImage(imageBytes);
        Assertions.assertNotNull(podcastCategories.getId());
        Assertions.assertNotNull(podcastCategories.getName());

        entityManager.persist(podcastCategories);

        transaction.commit();
        entityManager.close();

    }

    @Test
    void testInsertPodcast() throws IOException {

        EntityManagerFactory entityManagerFactory = JpaUtil.getEntityManagerFactory();
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();

        Users user = entityManager.find(Users.class, UUID.fromString("1da6de12-fcf9-4240-97ca-3e85684b670c"));
        PodcastCategories podcastCategories = entityManager.find(PodcastCategories.class, UUID.fromString("2ec88e88-728c-4e3c-897c-fed67ebfad34"));



        Podcast podcast1 = new Podcast();
        podcast1.setName("Ngalor Ngidul");
        podcast1.setPodcastCategories(podcastCategories);
        podcast1.setUserPodcasts(user);
        String imageBytes = Arrays.toString(
                Files.readAllBytes(
                        Path.of(
                                Objects.requireNonNull(getClass().getResource("/images/a4186047900_10.jpg"))
                                        .getPath()
                        )
                )
        );
        podcast1.setImage(imageBytes);
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
        episode1.setPodcast(entityManager.find(Podcast.class, UUID.fromString("751c741a-aca4-4993-a9c6-331c20319041")));
        episode1.setDuration(1000);

        Assertions.assertNotNull(episode1.getTitle());
        Assertions.assertNotNull(episode1.getDescription());
        Assertions.assertNotNull(episode1.getPodcast());
        Assertions.assertNotNull(episode1.getDuration());

        entityManager.persist(episode1);

        transaction.commit();
        entityManager.close();

    }

}
