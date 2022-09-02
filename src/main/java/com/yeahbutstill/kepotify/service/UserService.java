package com.yeahbutstill.kepotify.service;

import com.yeahbutstill.kepotify.entity.Users;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Users create(Users users);
    List<Users> list(Integer limit);
    Optional<Users> get(String id);
    Users update(Users users);
    Boolean delete(String id);

}
