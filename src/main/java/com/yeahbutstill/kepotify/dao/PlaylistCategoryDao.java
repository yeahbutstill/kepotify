package com.yeahbutstill.kepotify.dao;

import com.yeahbutstill.kepotify.entity.PlaylistCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaylistCategoryDao extends JpaRepository<PlaylistCategory, String> {
}
