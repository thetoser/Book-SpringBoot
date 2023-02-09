package com.zhljava.bookspringboot.controller;

import com.zhljava.bookspringboot.pojo.Book;
import com.zhljava.bookspringboot.pojo.CartItem;
import com.zhljava.bookspringboot.pojo.User;
import com.zhljava.bookspringboot.service.BookService;
import com.zhljava.bookspringboot.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.util.List;

@Controller
public class CartController {

    @Autowired
    private CartService cartService;
    @Autowired
    private BookService bookService;

    @GetMapping("/cart/update/{count}/{bookId}")
    public String cartUpdate(@PathVariable("count") Integer count,
                             @PathVariable("bookId") Integer bookId, HttpSession session) {

        User user = (User) session.getAttribute("user");
        cartService.updateCount(bookId, user.getId(), count);

        return "redirect:/cart";
    }

    @GetMapping("/cart/clean")
    public String cartClean(HttpSession session) {
        User user = (User) session.getAttribute("user");
        cartService.cleanCart(user.getId());

        return "redirect:/cart";
    }

    @GetMapping("/cart/delete/{id}")
    public String cartDelete(@PathVariable("id") Integer id) {
        cartService.deleteCartItem(id);
        return "redirect:/cart";
    }

    @GetMapping("/cart/add/{bookId}/{pageNum}")
    @Transactional
    public String cartAdd(@PathVariable("pageNum") Integer pageNum,
                          @PathVariable("bookId") Integer bookId, HttpSession session) {
        Book book = bookService.getBookById(bookId);
        User user = (User) session.getAttribute("user");
        Integer userId = user.getId();

        cartService.saveCart(userId);
        cartService.saveCartItem(book, userId);

        session.setAttribute("bookName", book.getName());
        this.setCountAndTotalPrice(session, userId);
        return "redirect:/index/book/" + pageNum;
    }

    @GetMapping("/cart")
    public String cart(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        Integer userId = user.getId();
        List<CartItem> list = cartService.getCartItemsByUserId(userId);

        if (list.size() != 0) {
            this.setCountAndTotalPrice(session, userId);
            model.addAttribute("cartItems", list);
        }
        return "cart/cart";
    }

    private void setCountAndTotalPrice(HttpSession session, Integer userId) {
        List<CartItem> list = cartService.getCartItemsByUserId(userId);

        int count = 0;
        BigDecimal total = new BigDecimal(0);
        for (CartItem cartItem : list) {
            count += cartItem.getCount();
            total = total.add(cartItem.getTotalPrice());
        }

        session.setAttribute("count", count);
        session.setAttribute("totalPrice", total);
    }

}
