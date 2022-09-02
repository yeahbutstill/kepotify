package com.yeahbutstill.kepotify.dao;

import com.yeahbutstill.kepotify.entity.Playlist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaylistDao extends JpaRepository<Playlist, String> {
}
