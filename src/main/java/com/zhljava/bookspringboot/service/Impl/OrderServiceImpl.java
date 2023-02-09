package com.zhljava.bookspringboot.service.Impl;

import com.zhljava.bookspringboot.mapper.BookMapper;
import com.zhljava.bookspringboot.mapper.OrderMapper;
import com.zhljava.bookspringboot.pojo.Book;
import com.zhljava.bookspringboot.pojo.CartItem;
import com.zhljava.bookspringboot.pojo.Order;
import com.zhljava.bookspringboot.pojo.OrderItem;
import com.zhljava.bookspringboot.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private BookMapper bookMapper;

    @Override
    public void updateStatus(String orderId, Integer status) {
        orderMapper.updateStatus(orderId, status);
    }

    @Override
    public List<OrderItem> getOrderItem(String orderId) {
        return orderMapper.selectOrderItemByOrderId(orderId);
    }

    @Override
    public List<Order> getOrder() {
        return orderMapper.selectOrder();
    }

    @Override
    public List<Order> getOrderByUserId(Integer userId) {
        return orderMapper.selectOrderByUserId(userId);
    }

    @Override
    @Transactional
    public void createOrder(List<CartItem> list, Order order) {
        orderMapper.insertOrder(order);
        for (CartItem cartItem : list) {
            OrderItem orderItem = new OrderItem(null, cartItem.getName(), cartItem.getCount(),
                    cartItem.getPrice(), cartItem.getTotalPrice(), order.getOrderId());

            orderMapper.insertOrderItem(orderItem);

            Book book = bookMapper.selectBookById(cartItem.getBookId());
            book.setSales(book.getSales() + cartItem.getCount());
            book.setStock(book.getStock() - cartItem.getCount());

            bookMapper.updateBook(book);
        }
    }

}
