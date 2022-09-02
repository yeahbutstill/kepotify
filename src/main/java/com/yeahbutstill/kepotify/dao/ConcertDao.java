package com.yeahbutstill.kepotify.dao;

import com.yeahbutstill.kepotify.entity.Concert;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConcertDao extends JpaRepository<Concert, String> {
}
