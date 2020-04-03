package info.upump.demo.controllers;

import info.upump.demo.model.AutoNumber;
import info.upump.demo.service.AutoNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
//@ControllerAdvice()
@RequestMapping("/numbers")
public class AutoNumberCtrl {
    @ModelAttribute("numberActive")
    public String cssActivePage() {
        return "active";
    }

    @Autowired
    private AutoNumberService autoNumberService;

    @GetMapping()
    public String allNumbers(Model model) {
        Iterable<AutoNumber> allNumbers = autoNumberService.findAllNumbers();
        model.addAttribute("numbers", allNumbers);

        return "numbers";
    }

    @PostMapping()
    public String saveNumber(
            @RequestParam String number,
            @RequestParam String description,
            @RequestParam("numberId") AutoNumber autoNumber,
            Model model) {
        if (autoNumber == null) {
            autoNumber = new AutoNumber();
            autoNumber.setId((long) 0);
        }

        autoNumber.setNumber(number);
        autoNumber.setDescription(description);

        autoNumberService.addNumber(autoNumber);

        return "redirect:/numbers";
    }

    @GetMapping("{autoNumber}")
    public String getNumber(@PathVariable AutoNumber autoNumber, Model model) {
        System.out.println(autoNumber.toString());
        model.addAttribute("number", autoNumber);

        return "numberedit";
    }

    @GetMapping("/add")
    public String addNumber(Model model) {
        AutoNumber autoNumber = new AutoNumber();
        autoNumber.setId((long) 0);
        autoNumber.setNumber("");
        autoNumber.setDescription("");

        model.addAttribute("number", autoNumber);

        return "numberedit";
    }

    @GetMapping("/delete/{autoNumber}")
    public String deleteNumber(@PathVariable AutoNumber autoNumber, Model model) {
        if (autoNumber != null){
            autoNumberService.deleteNumber(autoNumber);
        }

        return "redirect:/numbers";
    }
}
