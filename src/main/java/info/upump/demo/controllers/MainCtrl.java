package info.upump.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class MainCtrl {

    @GetMapping(value = "/")
    private String main(Map<String, Object> map){

        return "main";
    }
}
