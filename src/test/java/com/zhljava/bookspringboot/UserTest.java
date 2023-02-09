package com.zhljava.bookspringboot;

import com.zhljava.bookspringboot.pojo.User;
import com.zhljava.bookspringboot.service.Impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UserTest {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @Test
    public void testSelectUser() {
        User user = userServiceImpl.getUserByName("admin");
        System.out.println(user);
    }

    @Test
    public void testInsertUser() {
        User user = new User(null, "tom", "123", "tom@qq.com");
        userServiceImpl.saveUser(user);
    }

}
