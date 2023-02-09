package com.zhljava.bookspringboot.controller;

import com.zhljava.bookspringboot.pojo.User;
import com.zhljava.bookspringboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/user/login")
    public String login(String username, String password, HttpSession session, Model model) {
        User user = userService.getUserByNameAndPassword(username, password);
        if (user != null) {
            session.setAttribute("user", user);
            return "redirect:/login_success";
        } else {
            model.addAttribute("msg", "用户名或密码错误!");
            model.addAttribute("username", username);
            return "user/login";
        }
    }

    @PostMapping("/user/register")
    public String register(User user, Model model, HttpSession session) {
        User userByName = userService.getUserByName(user.getUsername());
        if (userByName == null) {
            userService.saveUser(user);
            session.setAttribute("user", user);
            return "redirect:/register_success";
        } else {
            model.addAttribute("msg", "用户名已存在!");
            model.addAttribute("user", user);
            return "user/register";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

}
