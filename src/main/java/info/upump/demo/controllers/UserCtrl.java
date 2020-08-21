package info.upump.demo.controllers;

import info.upump.demo.model.Role;
import info.upump.demo.model.User;
import info.upump.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/users")
@PreAuthorize("hasAnyAuthority('ADMIN')")
// чтобы это заработало нудна анатация в webseccon  @EnableGlobalMethodSecurity(prePostEnabled = true)
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

    //forma new user
    @GetMapping("/add")
    public String addUser(Model model) {
        User user = new User();
        user.setUsername("");
        user.setName("");
        user.setSurname("");
        user.setPatronymic("");
        user.setPassword("");
        user.setPassword2("");
        user.setId((new Long(0)));
        user.setRoles(new HashSet<>());
        user.getRoles().add(Role.USER);

        model.addAttribute("roles", user.getRoles());
        model.addAttribute("user", user);

        System.out.println("user in new User" + user.toString());

        return "useredit";
    }

    @GetMapping("{user}")
    public String userEditFrom(@PathVariable User user, Model model) {
        user.setPassword("");
        user.setPassword2("");
        model.addAttribute("user", user);

        model.addAttribute("roles", user.getRoles());

        System.out.println("user in user with ID    " + user.toString());
        return "useredit";
    }

    @GetMapping("/delete/{user}")
    public String deleteNumber(@PathVariable User user, Model model) {
        if (user != null) {
            userService.deleteNumber(user);
        }

        return "redirect:/users";
    }

    @PostMapping("save")
    public String saveOrUpdateUser(@Valid User user, BindingResult bindingResult, Model model, @RequestParam Map<String, String> formWithRoles) {
        Map<String, String> errors = ControlerUtils.getErrors(bindingResult);

        if (user.getPassword() != null && !user.getPassword().equals(user.getPassword2())) {
            errors.put("password2Error", "пароли не совпадают");

            if (!errors.containsKey("password2Error")) {
                errors.put("password2Error", "пароли не совпадают");
                bindingResult.addError(new ObjectError("password2", "")); // чтобы сработало условие  наличие ошибки если ее нет
            }
        }

        User byUsername = userService.findByUsername(user.getUsername());

        if (byUsername != null && user.getId() == 0) {
            errors.put("usernameError", "Пользователь с таким логином уже есть");
            bindingResult.addError(new ObjectError("username", "")); // чтобы сработало условие  наличие ошибки если ее нет
        }


        if (formWithRoles.containsKey("ADMIN")) {
            user.setRoles(Arrays.stream(Role.values()).collect(Collectors.toSet()));
        } else {
            user.setRoles(Arrays.stream(new Role[]{Role.USER}).collect(Collectors.toSet()));
        }


        if (bindingResult.hasErrors()) {

            model.addAttribute("error", errors);

            Map<String, String> roles = Arrays.stream(Role.values()).map(Role::name).collect(Collectors.toMap(
                    role -> role, role -> ""
            ));

            model.addAttribute("roles", user.getRoles());

            model.addAttribute("user", user);

            return "/useredit";

        } else {
            System.out.println("user --------- " + user);
            user.setActive(true);
            userService.save(user);

            return "redirect:/users";
        }
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
