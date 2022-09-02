package com.yeahbutstill.kepotify.service.impl;

import com.yeahbutstill.kepotify.dao.UserDao;
import com.yeahbutstill.kepotify.entity.Users;
import com.yeahbutstill.kepotify.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
@Slf4j
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public Users create(Users users) {
        log.info("Saving new user: {}", users.getName());
        return userDao.save(users);
    }

    @Override
    public List<Users> list(Integer limit) {
        log.info("Fetching all users: ");
        return userDao.findAll(PageRequest.of(0, limit)).toList();
    }

    @Override
    public Optional<Users> get(String id) {
        log.info("Fetching user by id: {}", id);
        return userDao.findById(id);
    }

    @Override
    public Users update(Users users) {
        log.info("Updating user: {}", users.getName());
        return userDao.save(users);
    }

    @Override
    public Boolean delete(String id) {
        log.info("Deleting user by id: {}", id);
        userDao.deleteById(id);
        return Boolean.TRUE;
    }

}
