package com.zhljava.bookspringboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {

    @GetMapping("/")
    public String index() {
        return "forward:/index/book/1";
    }

    @GetMapping("/user/login")
    public String userLogin() {
        return "user/login";
    }

    @GetMapping("/user/register")
    public String userRegister() {
        return "user/register";
    }

    @GetMapping("/register_success")
    public String register_success() {
        return "user/register_success";
    }

    @GetMapping("/login_success")
    public String login_success() {
        return "user/login_success";
    }

    @GetMapping("/manager")
    public String manager() {
        return "manager/manager";
    }

    @GetMapping("/book/add")
    public String bookAdd() {
        return "manager/book_edit";
    }

    @GetMapping("/checkout")
    public String checkout() {
        return "cart/checkout";
    }

}
