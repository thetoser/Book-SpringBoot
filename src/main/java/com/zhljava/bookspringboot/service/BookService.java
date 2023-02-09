package com.zhljava.bookspringboot.service;

import com.github.pagehelper.PageInfo;
import com.zhljava.bookspringboot.pojo.Book;

public interface BookService {

    PageInfo<Book> getBookForPage(Integer pageNum, Integer pageSize);

    Book getBookById(Integer id);

    void saveBook(Book book);

    void updateBook(Book book);

    void deleteBook(Integer id);

    PageInfo<Book> getBookForScope(Integer pageNum, Integer pageSize, Integer min, Integer max);

}
