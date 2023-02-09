package com.zhljava.bookspringboot.service;

import com.zhljava.bookspringboot.pojo.Book;
import com.zhljava.bookspringboot.pojo.CartItem;

import java.util.List;

public interface CartService {

    void updateCount(Integer bookId, Integer userId, Integer count);

    void cleanCart(Integer userId);

    void deleteCartItem(Integer id);

    void saveCartItem(Book book, Integer cartId);

    void saveCart(Integer userId);

    List<CartItem> getCartItemsByUserId(Integer userId);

}
