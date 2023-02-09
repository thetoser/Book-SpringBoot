package com.zhljava.bookspringboot.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CartItem {

    private Integer id;
    private Integer bookId;
    private String name;
    private Integer count;
    private BigDecimal price;
    private BigDecimal totalPrice;
    private Integer cartId;

}
