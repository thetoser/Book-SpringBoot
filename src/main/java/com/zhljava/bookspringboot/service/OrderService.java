package com.zhljava.bookspringboot.service;

import com.zhljava.bookspringboot.pojo.CartItem;
import com.zhljava.bookspringboot.pojo.Order;
import com.zhljava.bookspringboot.pojo.OrderItem;

import java.util.List;

public interface OrderService {

    void updateStatus(String orderId, Integer status);

    List<OrderItem> getOrderItem(String orderId);

    List<Order> getOrder();

    List<Order> getOrderByUserId(Integer userId);

    void createOrder(List<CartItem> list, Order order);
}
