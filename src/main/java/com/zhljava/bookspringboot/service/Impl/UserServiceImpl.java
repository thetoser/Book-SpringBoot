package com.zhljava.bookspringboot.service.Impl;

import com.zhljava.bookspringboot.mapper.UserMapper;
import com.zhljava.bookspringboot.pojo.User;
import com.zhljava.bookspringboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    public User getUserByName(String username) {
        return userMapper.selectUserByName(username);
    }

    public void saveUser(User user) {
        userMapper.insertUser(user);
    }

    public User getUserByNameAndPassword(String username, String password) {
        return userMapper.selectUserByNameAndPassword(username, password);
    }

}
