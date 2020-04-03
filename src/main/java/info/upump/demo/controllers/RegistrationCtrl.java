package info.upump.demo.controllers;

import info.upump.demo.model.Role;
import info.upump.demo.model.User;
import info.upump.demo.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;
import java.util.stream.Collectors;

@Controller
public class RegistrationCtrl {
@Autowired
private UserRepository userRepository;


    @GetMapping("/registration")
    public String registration(Model model) {
        Set<String> setRoles = Arrays.stream(Role.values()).map(Role::name).collect(Collectors.toSet());

        User user = new User();
        user.setUsername("");
        user.setName("");
        user.setSurname("");
        user.setPatronymic("");
        user.setPassword("");
        user.setId((new Long(0)));
        user.setRoles(new HashSet<>());

        model.addAttribute("roles", setRoles);
        model.addAttribute("user", user);

        return "registration";
    }

    @PostMapping("/registration")
    public String addNewUser(User user, @RequestParam Map<String, String> form,
                             Map<String, Object> map) {
        User byName = userRepository.findByUsername(user.getUsername());

        if (byName != null) {
            map.put("message", "user exist yet");
            return "registration";
        }

        Set<String> setRoles = Arrays.stream(Role.values()).map(Role::name).collect(Collectors.toSet());

        user.setActive(true);
        user.setRoles(new HashSet<>());

        for (String key : form.keySet()) {
            if (setRoles.contains(key)) {
                user.getRoles().add(Role.valueOf(key));
            }
        }
        userRepository.save(user);

        return "redirect:/users";
    }
}

