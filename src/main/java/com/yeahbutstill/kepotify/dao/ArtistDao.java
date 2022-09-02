package com.yeahbutstill.kepotify.dao;

import com.yeahbutstill.kepotify.entity.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistDao extends JpaRepository<Artist, String> {
}
