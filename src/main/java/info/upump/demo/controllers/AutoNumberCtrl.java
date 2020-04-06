package info.upump.demo.controllers;

import info.upump.demo.model.AutoNumber;
import info.upump.demo.service.AutoNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Map;

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
    public String allNumbers(@RequestParam(required = false) String filter, Model model) {
        // Iterable<AutoNumber> allNumbers = autoNumberService.findAllNumbers();
        System.out.println(filter);
        setFilterToModel(filter, model);
        // model.addAttribute("numbers", allNumbers);

        return "numbers";
    }

    @PostMapping("/{filter}")
    public String filter(@RequestParam String filter, RedirectAttributes redirectAttributes, Model model) {
        if (filter == null || filter.isEmpty()) {
            return "redirect:/numbers";
        }

        setFilterToModel(filter, model);

        return "numbers";
    }

    @PostMapping()
    public String saveNumber(
            @RequestParam(required = false) String filter,
            @Valid AutoNumber autoNumber,
            BindingResult bindingResult,
            Model model) {

        System.out.println(autoNumber.toString());
        System.out.println(filter);
        if (autoNumber.getId() == null) {
            autoNumber.setId((long) 0);
        }



        if (bindingResult.hasErrors()) {
            Map<String, String> errors = ControlerUtils.getErrors(bindingResult);
            model.addAttribute("error", errors);
            model.addAttribute("number", autoNumber);

            return "numberedit";

        } else {
            autoNumberService.addNumber(autoNumber);
        }

        setFilterToModel(filter, model);

        return "numbers";
    }

    @PostMapping("/edit/{autoNumber}")
    public String getNumber(
            @RequestParam(required = false) String filter,
            @PathVariable AutoNumber autoNumber,
            Model model) {
        System.out.println("filter in edit " + filter);
        System.out.println(autoNumber.toString());
        model.addAttribute("number", autoNumber);
        setFilterToModel(filter, model);

        return "numberedit";
    }

    @GetMapping("/add")
    public String addNumber(Model model, @RequestParam(required = false) String filter) {
        AutoNumber autoNumber = new AutoNumber();
        autoNumber.setId((long) 0);
        autoNumber.setNumber("");
        autoNumber.setDescription("");

        model.addAttribute("number", autoNumber);
        model.addAttribute("filter", filter);
        //setFilterToModel(filter, model);

        return "numberedit";
    }

    @PostMapping("/delete/{autoNumber}")
    public String deleteNumber(
            @RequestParam(required = false) String filter,
            @PathVariable AutoNumber autoNumber,
            Model model) {

        if (autoNumber != null) {
            autoNumberService.deleteNumber(autoNumber);
        }

        setFilterToModel(filter, model);

        return "/numbers";
    }

    private void setFilterToModel(String filter, Model model) {
        System.out.println("filter1 " + filter);
        if (filter != null) {
            if (filter.isEmpty()) {
                filter = null;
            }
        }

        model.addAttribute("filter", filter);

        System.out.println("filter2 " + filter);
        if (filter != null) {
            System.out.println("folf");
            model.addAttribute("numbers", autoNumberService.filter(filter));

        } else model.addAttribute("numbers", autoNumberService.findAllNumbers());
    }
}
