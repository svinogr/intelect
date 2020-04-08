package info.upump.demo.controllers;

import info.upump.demo.model.AutoNumber;
import info.upump.demo.service.AutoNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Map;

@Controller
@RequestMapping("/numbers")
public class AutoNumberCtrl {
    @ModelAttribute("numberActive")
    public String cssActivePage() {
        return "active";
    }

    @Autowired
    private AutoNumberService autoNumberService;

    @GetMapping()
    public String allNumbers(Model model,
                             @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable) {
        Page<AutoNumber> allNumbers = autoNumberService.findAllNumbers(pageable);
        model.addAttribute("page", allNumbers);

        return "numbers";
    }

    @PostMapping()
    public String saveNumber(
            @Valid AutoNumber autoNumber,
            BindingResult bindingResult,
            Model model,
            @RequestParam(required = false) String filter,
            @RequestParam("numberId") Long id,
            @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable) {


        if (autoNumber.getId() == null) {
            autoNumber.setId((id));
        }

        if (bindingResult.hasErrors()) {
            Map<String, String> errors = ControlerUtils.getErrors(bindingResult);
            model.addAttribute("error", errors);
            model.addAttribute("number", autoNumber);

            return "numberedit";

        } else {
            autoNumberService.addNumber(autoNumber);
        }

        Page<AutoNumber> allNumbers = autoNumberService.findAllNumbers(pageable);

        model.addAttribute("page", allNumbers);
        model.addAttribute("page");

        if (filter != null) {

            if (!filter.isEmpty()) {

                return String.format("redirect:/search?page=%d&size=%d&filter=%s", pageable.getPageNumber(), pageable.getPageSize(), filter);
            }
        }

        return String.format("redirect:/numbers?page=%d&size=%d", pageable.getPageNumber(), pageable.getPageSize());
    }

    @PostMapping("/edit/{autoNumber}")
    public String getNumber(
            @RequestParam(required = false) String filter,
            @PathVariable AutoNumber autoNumber,
            Model model,
            @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable) {
        model.addAttribute("number", autoNumber);
        Page<AutoNumber> allNumbers = autoNumberService.findAllNumbers(pageable);
        model.addAttribute("page", allNumbers);
        model.addAttribute("filter", filter);

        System.out.println("filter " + filter);
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

        return "numberedit";
    }

    @PostMapping("/delete/{autoNumber}")
    public String deleteNumber(
            @RequestParam(required = false) String filter,
            @PathVariable AutoNumber autoNumber,
            Model model,
            RedirectAttributes attributes,
            @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable) {

        if (autoNumber != null) {
            autoNumberService.deleteNumber(autoNumber);
        }

        Page<AutoNumber> allNumbers = autoNumberService.findAllNumbers(pageable);
        model.addAttribute("page", allNumbers);

        if (filter != null) {

            if (!filter.isEmpty()) {

                return String.format("redirect:/search?page=%d&size=%d&filter=%s", pageable.getPageNumber(), pageable.getPageSize(), filter);
            }
        }

        return String.format("redirect:/numbers?page=%d&size=%d", pageable.getPageNumber(), pageable.getPageSize());
    }
}
