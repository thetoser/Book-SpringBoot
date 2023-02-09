package com.zhljava.bookspringboot;

import com.github.pagehelper.PageInfo;
import com.zhljava.bookspringboot.pojo.Book;
import com.zhljava.bookspringboot.service.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class BookTest {

    @Autowired
    private BookService bookService;

    @Test
    public void testPage() {
        PageInfo<Book> page = bookService.getBookForPage(1, 4);
        List<Book> list = page.getList();
        list.forEach(System.out::println);
    }

}
