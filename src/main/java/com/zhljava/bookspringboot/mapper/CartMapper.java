package com.zhljava.bookspringboot.mapper;

import com.zhljava.bookspringboot.pojo.CartItem;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface CartMapper {

    void cleanCart(@Param("cartId") Integer cartId);

    void deleteCartItem(@Param("id") Integer id);

    void updateCountAndPrice(@Param("count") Integer count, @Param("totalPrice") BigDecimal totalPrice,
                             @Param("bookId") Integer bookId, @Param("cartId") Integer cartId);

    void insertCart(@Param("userId") Integer userId);

    void insertCartItem(@Param("cartItem") CartItem cartItem);

    Integer selectCartId(@Param("userId") Integer userId);

    List<CartItem> selectCartItemsById(@Param("cartId") Integer cartId);

    CartItem selectCartItemByCartIdAndBookId(@Param("bookId") Integer bookId, @Param("cartId") Integer cartId);

}
