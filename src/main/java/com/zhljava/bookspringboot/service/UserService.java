package com.zhljava.bookspringboot.service;

import com.zhljava.bookspringboot.pojo.User;

public interface UserService {

    User getUserByNameAndPassword(String username, String password);

    User getUserByName(String username);

    void saveUser(User user);
}
