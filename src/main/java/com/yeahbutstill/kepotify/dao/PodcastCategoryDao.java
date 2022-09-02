package com.yeahbutstill.kepotify.dao;

import com.yeahbutstill.kepotify.entity.PodcastCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PodcastCategoryDao extends JpaRepository<PodcastCategory, String> {
}
