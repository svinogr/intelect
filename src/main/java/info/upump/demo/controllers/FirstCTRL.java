package info.upump.demo.controllers;

import info.upump.demo.model.AutoNumber;

import info.upump.demo.model.Role;
import info.upump.demo.model.User;
import info.upump.demo.repo.AutoNumberRepo;
import info.upump.demo.repo.UserRepository;
import info.upump.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class FirstCTRL {
    @Autowired
    private AutoNumberRepo autoNumberRepo;

    @GetMapping("/test")
    private String testCtrl(Map<String, Object> model) {
        System.out.println("testCTRL");
        Iterable<AutoNumber> all = autoNumberRepo.findAll();
        model.put("all", all);
        return "test";
    }

    @GetMapping("/crud")
    private String crud(Model model) {
        List<AutoNumber> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add(new AutoNumber("o78" + i + "ca178", "жигули"));
        }
        autoNumberRepo.saveAll(list);

        return "crud";
    }

   // @Autowired
    //UserService userService;
    @GetMapping("/ad")
    public String addAdmin( Model model) {
        User user = new User();
        user.setId((long) 0);
        user.setUsername("test");
        user.setPassword("test");
        user.setName("test");
        user.setPatronymic("test");
        user.setSurname("test");
        user.setRoles(Arrays.stream(Role.values()).collect(Collectors.toSet()));

        System.out.println(user);

      ///  userService.save(user);

        return "numbers";
    }

}
