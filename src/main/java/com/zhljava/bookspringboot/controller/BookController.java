package com.zhljava.bookspringboot.controller;

import com.github.pagehelper.PageInfo;
import com.zhljava.bookspringboot.pojo.Book;
import com.zhljava.bookspringboot.pojo.CartItem;
import com.zhljava.bookspringboot.pojo.User;
import com.zhljava.bookspringboot.service.BookService;
import com.zhljava.bookspringboot.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class BookController {

    @Autowired
    private BookService bookService;
    @Autowired
    private CartService cartService;

    @GetMapping("/book/edit")
    public String bookEdit(Book book, Integer pages, Integer pageNum) {
        if (book.getId() == null) {
            bookService.saveBook(book);
            return "redirect:/manager/book/" + pages;
        } else {
            bookService.updateBook(book);
            return "redirect:/manager/book/" + pageNum;
        }
    }

    @GetMapping("/book/delete/{id}")
    public String bookDelete(@PathVariable("id") Integer id, HttpSession session) {
        bookService.deleteBook(id);
        PageInfo<Book> page = (PageInfo<Book>) session.getAttribute("page");
        return "redirect:/manager/book/" + page.getPageNum();
    }

    @GetMapping("/book/update/{id}")
    public String bookUpdate(@PathVariable("id") Integer id, Model model) {
        Book book = bookService.getBookById(id);
        model.addAttribute("book", book);

        return "manager/book_edit";
    }

    @GetMapping("/manager/book/{pageNum}")
    public String managerBook(@PathVariable("pageNum") Integer pageNum, HttpSession session) {
        PageInfo<Book> page = bookService.getBookForPage(pageNum, 4);
        session.setAttribute("page", page);

        return "manager/book_manager";
    }

    @GetMapping("/index/book/{pageNum}")
    public String indexBook(@PathVariable("pageNum") Integer pageNum,
                            Model model, HttpSession session,
                            Integer minPage, Integer maxPage) {

        Integer minSession = (Integer) session.getAttribute("min");
        Integer maxSession = (Integer) session.getAttribute("max");

        if (minPage != null) {
            session.setAttribute("min", minPage);
        } else if (minSession != null) {
            minPage = minSession;
        } else {
            minPage = 0;
            session.setAttribute("min", minPage);
        }

        if (maxPage != null) {
            session.setAttribute("max", maxPage);
        } else if (maxSession != null) {
            maxPage = maxSession;
        } else {
            maxPage = Integer.MAX_VALUE;
            session.setAttribute("max", maxPage);
        }

        PageInfo<Book> page = bookService.getBookForScope(pageNum, 4, minPage, maxPage);
        model.addAttribute("page", page);

        this.setCount(session);
        return "index";
    }

    private void setCount(HttpSession session) {
        User user = (User) session.getAttribute("user");
        List<CartItem> list = cartService.getCartItemsByUserId(user.getId());

        int count = 0;
        for (CartItem cartItem : list) {
            count += cartItem.getCount();
        }

        session.setAttribute("count", count);
    }

}
