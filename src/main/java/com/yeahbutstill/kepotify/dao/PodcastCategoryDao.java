package com.yeahbutstill.kepotify.dao;

import com.yeahbutstill.kepotify.entity.PodcastCategories;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PodcastCategoryDao extends JpaRepository<PodcastCategories, String> {
}
