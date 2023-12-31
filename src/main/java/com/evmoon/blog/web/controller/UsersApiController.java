package com.evmoon.blog.web.controller;

import com.evmoon.blog.domain.Users;
import com.evmoon.blog.web.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/users")
public class UsersApiController {
    private final UserService userService;

    Logger logger = LoggerFactory.getLogger(UsersApiController.class);

    private UsersApiController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getUsers(Model model) {
        List<Map<String, String>> userList = userService.getUsers();
        model.addAttribute("users", userList);
        logger.info("User API: List users");
        return "users/list";
    }

    @GetMapping("/form")
    String getForm() {
        return "users/form";
    }

    @PostMapping("/login")
    String login(String userId, String password, HttpSession session) {
        try {
            Users user = userService.getByUserName(userId);
            if (!user.getPassword().equals(password)) {
                logger.info("User API: Password inaccurate");
                return "users/login";
            }
            session.setAttribute("sessionedUser", user);
            logger.info("User API: login");
            return "redirect:/";
        } catch (Exception e) {
            logger.info("User API: User id not found");
            return "users/login";
        }
    }

    @GetMapping("/login")
    String getLoginForm() {
        return "users/login";
    }

    @GetMapping("/logout")
    String logout(HttpSession session) {
        session.invalidate();
        logger.info("User API: logout");
        return "redirect:/";
    }

    @GetMapping("/{id}")
    String findById(@PathVariable int id, Model model) {
        Users user = userService.getByUserId(id);
        model.addAttribute("user", user);
        return "users/profile";
    }

    @GetMapping("/profile")
    String getProfile(Users user, Model model) {
        model.addAttribute("user", user);
        return "users/profile";
    }

    @PostMapping
    String createUser(Users user, HttpSession session) {
        Users createdUser = userService.addUser(user);
        session.setAttribute("sessionedUser", createdUser);
        logger.info("User API: signup");
        return "redirect:/users";
    }

    @GetMapping("/update")
    String getUpdateForm(Model model, HttpSession session) {
        Users user = (Users) session.getAttribute("sessionedUser");
        model.addAttribute("user", user);
        return "users/updateForm";
    }

    @PutMapping("/{id}")
    String updateUserProfile(String newPassword, Users updateUser, HttpSession session) {
        Users currentUser = (Users) session.getAttribute("sessionedUser");
        userService.updateUser(currentUser.getId(), updateUser, newPassword);
        logger.info("User API: profile update");
        return "redirect:/users";
    }
}
