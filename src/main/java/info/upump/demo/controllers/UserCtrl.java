package info.upump.demo.controllers;

import info.upump.demo.model.Role;
import info.upump.demo.model.User;
import info.upump.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/users")
@PreAuthorize("hasAnyAuthority('ADMIN')") // чтобы это заработало нудна анатация в webseccon  @EnableGlobalMethodSecurity(prePostEnabled = true)
public class UserCtrl {
    @ModelAttribute("userActive")
    public String cssActivePage() {
        return "active";
    }

    @Autowired
    private UserService userService;

    @GetMapping
    public String allUsers(Model model) {
        Iterable<User> allUsers = userService.findAllUsers();
        model.addAttribute("users", allUsers);

        return "userlist";
    }

    @GetMapping("{user}")
    public String userEditFrom(@PathVariable User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("roles", Role.values());

        return "useredit";
    }

    @GetMapping("/delete/{user}")
    public String deleteNumber(@PathVariable User user, Model model) {
        if (user != null){
            userService.deleteNumber(user);
        }

        return "redirect:/users";
    }


    @PostMapping
    public String saveUser(
            @RequestParam String name,
            @RequestParam String patronymic,
            @RequestParam String surname,
            @RequestParam String username,
            @RequestParam Map<String, String> form,
            @RequestParam("userId") User user
    ) {
        System.out.println("dddd");
        Set<String> setRoles = Arrays.stream(Role.values()).map(Role::name).collect(Collectors.toSet());

        user.getRoles().clear();
        user.setName(name);
        user.setPatronymic(patronymic);
        user.setSurname(surname);
        user.setUsername(username);

        for (String key : form.keySet()) {
            if (setRoles.contains(key)) {
                user.getRoles().add(Role.valueOf(key));
            }
        }

        userService.save(user);

        return "redirect:/users";
    }
}
