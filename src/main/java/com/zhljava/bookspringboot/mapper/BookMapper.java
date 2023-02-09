package com.zhljava.bookspringboot.mapper;

import com.zhljava.bookspringboot.pojo.Book;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookMapper {

    List<Book> selectBookForList();

    void insertBook(@Param("book") Book book);

    void updateBook(@Param("book") Book book);

    void deleteBook(@Param("id") Integer id);

    Book selectBookById(@Param("id") Integer id);

    List<Book> selectBookForScope(@Param("min") Integer min,@Param("max") Integer max);
}
