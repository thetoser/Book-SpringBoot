package com.zhljava.bookspringboot.service.Impl;

import com.zhljava.bookspringboot.mapper.CartMapper;
import com.zhljava.bookspringboot.pojo.Book;
import com.zhljava.bookspringboot.pojo.CartItem;
import com.zhljava.bookspringboot.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private CartMapper cartMapper;

    @Override
    public void updateCount(Integer bookId, Integer userId, Integer count) {
        Integer cartId = cartMapper.selectCartId(userId);

        CartItem cartItem = cartMapper.selectCartItemByCartIdAndBookId(bookId, cartId);
        BigDecimal price = cartItem.getPrice();
        BigDecimal totalPrice = price.multiply(new BigDecimal(count));

        cartMapper.updateCountAndPrice(count, totalPrice, bookId, cartId);
    }

    @Override
    public void cleanCart(Integer userId) {
        Integer cartId = cartMapper.selectCartId(userId);
        cartMapper.cleanCart(cartId);
    }

    @Override
    public void deleteCartItem(Integer id) {
        cartMapper.deleteCartItem(id);
    }

    @Override
    public void saveCartItem(Book book, Integer userId) {
        Integer cartId = cartMapper.selectCartId(userId);

        CartItem item = cartMapper.selectCartItemByCartIdAndBookId(book.getId(), cartId);

        if (item == null) {
            CartItem cartItem = new CartItem(null, book.getId(), book.getName(), 1,
                                            book.getPrice(), book.getPrice(), cartId);

            cartMapper.insertCartItem(cartItem);
        } else {
            Integer count = item.getCount();
            count++;

            BigDecimal price = item.getPrice();
            BigDecimal totalPrice = price.multiply(new BigDecimal(count));
            cartMapper.updateCountAndPrice(count, totalPrice, book.getId(), cartId);
        }
    }

    @Override
    public void saveCart(Integer userId) {
        Integer cartId = cartMapper.selectCartId(userId);
        if (cartId == null) {
            cartMapper.insertCart(userId);
        }
    }

    @Override
    public List<CartItem> getCartItemsByUserId(Integer userId) {
        Integer cartId = cartMapper.selectCartId(userId);
        return cartMapper.selectCartItemsById(cartId);
    }
}
