package com.yeahbutstill.kepotify.dao;

import com.yeahbutstill.kepotify.entity.Episode;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EpisodeDao extends JpaRepository<Episode, String> {
}
