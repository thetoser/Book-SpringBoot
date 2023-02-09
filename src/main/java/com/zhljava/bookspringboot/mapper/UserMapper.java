package com.zhljava.bookspringboot.mapper;

import com.zhljava.bookspringboot.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {

    User selectUserByName(@Param("username") String username);

    void insertUser(@Param("user") User user);

    User selectUserByNameAndPassword(@Param("username") String username, @Param("password") String password);
}
