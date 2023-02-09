package com.zhljava.bookspringboot.service.Impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zhljava.bookspringboot.mapper.BookMapper;
import com.zhljava.bookspringboot.pojo.Book;
import com.zhljava.bookspringboot.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class BookServiceImpl implements BookService {

    @Autowired
    private BookMapper bookMapper;

    @Override
    public PageInfo<Book> getBookForPage(Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Book> list = bookMapper.selectBookForList();
        return new PageInfo<>(list, 5);
    }

    @Override
    public Book getBookById(Integer id) {
        return bookMapper.selectBookById(id);
    }

    @Override
    public void saveBook(Book book) {
        bookMapper.insertBook(book);
    }

    @Override
    public void updateBook(Book book) {
        bookMapper.updateBook(book);
    }

    @Override
    public void deleteBook(Integer id) {
        bookMapper.deleteBook(id);
    }

    @Override
    public PageInfo<Book> getBookForScope(Integer pageNum, Integer pageSize, Integer min, Integer max) {
        PageHelper.startPage(pageNum, pageSize);
        List<Book> list = bookMapper.selectBookForScope(min, max);
        return new PageInfo<>(list, 5);
    }


}
