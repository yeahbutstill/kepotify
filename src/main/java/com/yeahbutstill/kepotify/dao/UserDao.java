package com.yeahbutstill.kepotify.dao;

import com.yeahbutstill.kepotify.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<Users, String> {
}
