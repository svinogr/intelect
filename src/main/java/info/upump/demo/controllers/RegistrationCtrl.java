package info.upump.demo.controllers;

import info.upump.demo.model.Role;
import info.upump.demo.model.User;
import info.upump.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.bind.BindResult;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class RegistrationCtrl {
@Autowired
private UserService userService;


    @GetMapping("/registration")
    public String registration(Model model) {
      //  Set<String> setRoles = Arrays.stream(Role.values()).map(Role::name).collect(Collectors.toSet());
        Map<String, String> roles = Arrays.stream(Role.values()).map(Role::name).collect(Collectors.toMap(
                role -> role, role -> ""
        ));

        User user = new User();
        user.setUsername("");
        user.setName("");
        user.setSurname("");
        user.setPatronymic("");
        user.setPassword("");
        user.setPassword2("");
        user.setId((new Long(0)));
        user.setRoles(new HashSet<>());
   //     user.getRoles().add(Role.USER);

        model.addAttribute("roles", roles);
        model.addAttribute("user", user);

        return "registration";
    }

    @PostMapping("/registration")
    public String addNewUser(@Valid User user,
                             BindingResult bindingResult,
                             Model model,
        @RequestParam Map<String, String> form) {
        Map<String, String> errors = ControlerUtils.getErrors(bindingResult);

        if(user.getPassword() != null && !user.getPassword().equals(user.getPassword2())) {
            errors.put("password2Error", "пароли не совпадают");
            bindingResult.addError(new ObjectError("password2", ""));
        }

        if (bindingResult.hasErrors()) {

            model.addAttribute("error", errors);

            Map<String, String> roles = Arrays.stream(Role.values()).map(Role::name).collect(Collectors.toMap(
                    role -> role, role -> ""
            ));

            for (String key : form.keySet()) {
                if (roles.containsKey(key)) {
                  roles.put(key, "checked");
                }
            }

            System.out.println(user.toString());
            model.addAttribute("roles", roles);

            user.setId((long) 0);
            model.addAttribute("user", user);

           return "/registration";

        }else {
            User byName = userService.findByUsername(user.getUsername());

            if (byName != null) {
                model.addAttribute("message", "user exist yet");
                model.addAttribute("user", user);
                Map<String, String> roles = Arrays.stream(Role.values()).map(Role::name).collect(Collectors.toMap(
                        role -> role, role -> ""
                ));

                for (String key : form.keySet()) {
                    if (roles.containsKey(key)) {
                        roles.put(key, "checked");
                    }
                }

                model.addAttribute("roles", roles);

                user.setId((long) 0);

                return "registration";
            }

            Map<String, String> roles = Arrays.stream(Role.values()).map(Role::name).collect(Collectors.toMap(
                    role -> role, role -> ""
            ));

            user.setActive(true);
            user.setRoles(new HashSet<>());

            for (String key : form.keySet()) {
                if (roles.containsKey(key)) {
                    user.getRoles().add(Role.valueOf(key));
                }
            }
            userService.save(user);
        }

        return "redirect:/users";
    }

}

