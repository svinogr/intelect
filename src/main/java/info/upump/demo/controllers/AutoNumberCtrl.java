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
    public String allNumbers(@RequestParam(required = false) String filter,  Model model) {
       // Iterable<AutoNumber> allNumbers = autoNumberService.findAllNumbers();
        setFilterToModel(filter, model);
       // model.addAttribute("numbers", allNumbers);

        return "numbers";
    }

    @PostMapping("/{filter}")
    public  String filter(@RequestParam String filter, Model model) {
        setFilterToModel(filter, model);

        return "numbers";
    }

    @PostMapping()
    public String saveNumber(
            @RequestParam(required = false) String filter,
            @RequestParam String number,
            @RequestParam String description,
            @RequestParam("numberId") AutoNumber autoNumber,
            Model model) {
        if (autoNumber == null) {
            autoNumber = new AutoNumber();
            autoNumber.setId((long) 0);
        }

        System.out.println(filter);

        autoNumber.setNumber(number);
        autoNumber.setDescription(description);

        autoNumberService.addNumber(autoNumber);

        setFilterToModel(filter, model);

        return "numbers";
    }

    @PostMapping("/edit/{autoNumber}")
    public String getNumber(
            @RequestParam(required = false) String filter,
            @PathVariable AutoNumber autoNumber,
            Model model) {
        System.out.println("filter in edit " +filter);
        System.out.println(autoNumber.toString());
        model.addAttribute("number", autoNumber);
        setFilterToModel(filter, model);

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

    @PostMapping("/delete/{autoNumber}")
    public String deleteNumber(
            @RequestParam(required = false) String filter,
            @PathVariable AutoNumber autoNumber,
            Model model) {

        if (autoNumber != null){
            autoNumberService.deleteNumber(autoNumber);
        }

        setFilterToModel(filter, model);

        return "numbers";
    }

    private void setFilterToModel(@RequestParam(required = false) String filter, Model model) {
        System.out.println("filter " + filter );
        if (filter != null) {
            model.addAttribute("numbers", autoNumberService.filter(filter));
            model.addAttribute("filter", filter);
        } else model.addAttribute("numbers", autoNumberService.findAllNumbers());
    }
}
