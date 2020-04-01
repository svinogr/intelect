package info.upump.demo.controllers;

import info.upump.demo.model.Role;
import info.upump.demo.model.User;
import info.upump.demo.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashSet;
import java.util.Map;

@Controller
public class RegistrationCtrl {
@Autowired
private UserRepository userRepository;


    @GetMapping("/registration")
    public String registration() {
        return "registration";
    }

    @PostMapping("/registration")
    public String addNewUser(User user, Map<String, Object> map) {
        User byName = userRepository.findByUsername(user.getUsername());

        if (byName != null) {
            map.put("message", "user exist yet");
            return "registration";
        }

        user.setActive(true);
        user.setRoles(new HashSet<Role>(){
            {
                //add(Role.ADMIN);
        add(Role.USER);
        }});

        userRepository.save(user);

        return "redirect:/login";
    }
}

