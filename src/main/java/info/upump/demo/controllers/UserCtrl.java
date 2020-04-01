package info.upump.demo.controllers;

import info.upump.demo.model.User;
import info.upump.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class UserCtrl {
    @Autowired
    private UserService userService;

    @GetMapping
    public String allUsers(Model model) {
        Iterable<User> allUsers = userService.findAllUsers();
        model.addAttribute("users", allUsers);
        return "userlist";
    }
}
