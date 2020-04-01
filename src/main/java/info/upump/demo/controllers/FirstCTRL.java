package info.upump.demo.controllers;

import info.upump.demo.model.AutoNumber;

import info.upump.demo.repo.AutoNumberRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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

}
