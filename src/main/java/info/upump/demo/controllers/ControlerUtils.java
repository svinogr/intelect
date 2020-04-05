package info.upump.demo.controllers;

import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import java.util.Map;
import java.util.stream.Collectors;

public class ControlerUtils {
    public static Map<String, String> getErrors(BindingResult bindingResult, Model model) {
        Map<String, String> collect = bindingResult.getFieldErrors().stream().collect(Collectors.toMap(
                fieldError -> fieldError.getField() +"Error",
                fieldError -> fieldError.getDefaultMessage()
        ));
        System.out.println(collect.keySet());
        return collect;
    }
}
