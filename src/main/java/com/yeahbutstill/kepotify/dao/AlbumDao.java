package com.yeahbutstill.kepotify.dao;

import com.yeahbutstill.kepotify.entity.Album;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlbumDao extends JpaRepository<Album, String > {
}
