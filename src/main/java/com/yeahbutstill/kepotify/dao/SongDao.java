package com.yeahbutstill.kepotify.dao;

import com.yeahbutstill.kepotify.entity.Song;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SongDao extends JpaRepository<Song, String> {
}
