package com.zhljava.bookspringboot.mapper;

import com.zhljava.bookspringboot.pojo.Order;
import com.zhljava.bookspringboot.pojo.OrderItem;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderMapper {

    void insertOrderItem(@Param("orderItem") OrderItem orderItem);

    void insertOrder(@Param("order") Order order);

    void updateStatus(@Param("orderId") String orderId, @Param("status") Integer status);

    List<OrderItem> selectOrderItemByOrderId(@Param("orderId") String orderId);

    List<Order> selectOrderByUserId(@Param("userId") Integer userId);

    List<Order> selectOrder();

}
