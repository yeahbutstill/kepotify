package com.yeahbutstill.kepotify.dao;

import com.yeahbutstill.kepotify.entity.PlaylistCategorie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaylistCategoryDao extends JpaRepository<PlaylistCategorie, String> {
}
