package com.zhljava.bookspringboot.controller;

import com.zhljava.bookspringboot.pojo.CartItem;
import com.zhljava.bookspringboot.pojo.Order;
import com.zhljava.bookspringboot.pojo.OrderItem;
import com.zhljava.bookspringboot.pojo.User;
import com.zhljava.bookspringboot.service.CartService;
import com.zhljava.bookspringboot.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private CartService cartService;

    @GetMapping("/create/order")
    @Transactional
    public String createOrder(HttpSession session) {
        BigDecimal totalPrice = (BigDecimal) session.getAttribute("totalPrice");
        User user = (User) session.getAttribute("user");
        Integer userId = user.getId();

        List<CartItem> list = cartService.getCartItemsByUserId(userId);
        String orderId = UUID.randomUUID().toString();
        Order order = new Order(orderId, LocalDateTime.now(), totalPrice, 0, userId);

        orderService.createOrder(list, order);
        cartService.cleanCart(userId);

        session.removeAttribute("bookName");
        session.setAttribute("orderId", orderId);
        return "redirect:/checkout";
    }

    @GetMapping("/update/status/{status}/{orderId}")
    public String updateStatus(@PathVariable("status") Integer status,
                               @PathVariable("orderId") String orderId) {

        orderService.updateStatus(orderId, status);
        if (status == 1) {
            return "redirect:/manager/order";
        } else {
            return "redirect:/order";
        }
    }

    @GetMapping("/order/detail/{user}/{orderId}")
    public String orderDetail(@PathVariable("orderId") String orderId,
                              @PathVariable("user") String userOrManager,
                              Model model) {
        List<OrderItem> list = orderService.getOrderItem(orderId);

        model.addAttribute("orderItems", list);
        model.addAttribute("orderId", orderId);
        model.addAttribute("user", userOrManager);

        return "order/order_item";
    }

    @GetMapping("/manager/order")
    public String managerOrder(Model model) {
        List<Order> list = orderService.getOrder();

        if (list.size() != 0) {
            model.addAttribute("orders", list);
        }
        return "manager/order_manager";
    }

    @GetMapping("/order")
    public String order(HttpSession session, Model model) {
        User user = (User) session.getAttribute("user");
        List<Order> list = orderService.getOrderByUserId(user.getId());

        if (list.size() != 0) {
            model.addAttribute("orders", list);
        }
        return "order/order";
    }

}
