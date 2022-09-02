package com.yeahbutstill.kepotify.dao;

import com.yeahbutstill.kepotify.entity.Podcast;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PodcastDao extends JpaRepository<Podcast, String> {
}
