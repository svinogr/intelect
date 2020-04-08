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
    public String allNumbers(@RequestParam(required = false) String filter, Model model,
                             @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable) {
        // Iterable<AutoNumber> allNumbers = autoNumberService.findAllNumbers();
        System.out.println(filter);
        setFilterToModel(filter, model, pageable);
        // model.addAttribute("numbers", allNumbers);
        System.out.println("pageM " + pageable.getPageNumber());
        return "numbers";
    }

    @PostMapping("/{filter}")
    public String filter(@RequestParam String filter, Model model,
                         @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable) {
        if (filter == null || filter.isEmpty()) {
            return "redirect:/numbers";
        }

        System.out.println("in filter ");

        setFilterToModel(filter, model, pageable);
/*
        return  String.format("redirect:/number/filter?page=%d&size=%d", pageable.getPageNumber(), pageable.getPageSize());
*/
        return "/numbers";
    }


    @PostMapping()
    public String saveNumber(
            @RequestParam(required = false) String filter,
            @Valid AutoNumber autoNumber,
            BindingResult bindingResult,
            Model model,
            @RequestParam("numberId") Long id,
            @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable) {

        System.out.println(autoNumber.toString());
        System.out.println("id " + id);
        System.out.println(filter);

        System.out.println("after edit "+ autoNumber.toString());

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
        System.out.println("after edit "+ autoNumber.toString());

        setFilterToModel(filter, model, pageable);
        model.addAttribute("page");

        System.out.println(String.format("redirect:/numbers?page=%d&size=%d", pageable.getPageNumber(), pageable.getPageSize()));

        return String.format("redirect:/numbers?page=%d&size=%d", pageable.getPageNumber(), pageable.getPageSize());
    }

    @PostMapping("/edit/{autoNumber}")
    public String getNumber(
            @RequestParam(required = false) String filter,
            @PathVariable AutoNumber autoNumber,
            Model model,
            @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable) {
        System.out.println("filter in edit " + filter);
        System.out.println("befor edit "+ autoNumber.toString());
        model.addAttribute("number", autoNumber);
        setFilterToModel(filter, model, pageable);

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
            Model model,
            RedirectAttributes attributes,
            @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable) {

        System.out.println("pageM " + pageable.getPageNumber());

        if (autoNumber != null) {
            autoNumberService.deleteNumber(autoNumber);
        }

        setFilterToModel(filter, model, pageable);;

        return String.format("redirect:/numbers?page=%d&size=%d", pageable.getPageNumber(), pageable.getPageSize());
    }

    private void setFilterToModel(String filter, Model model, Pageable pageable) {
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
            model.addAttribute("page", autoNumberService.filter(filter, pageable));

        }
         else {
            Page<AutoNumber> allNumbers = autoNumberService.findAllNumbers(pageable);
            System.out.println("page " + allNumbers.getNumber());

            model.addAttribute("page", autoNumberService.findAllNumbers(pageable));

        }
    }

    private void setFilterToModel2(String filter, RedirectAttributes attributes, Pageable pageable) {
        System.out.println("filter1 " + filter);
        if (filter != null) {
            if (filter.isEmpty()) {
                filter = null;
            }
        }

        attributes.addAttribute("filter", filter);

        System.out.println("filter2 " + filter);
        System.out.println(pageable.getPageNumber());
        if (filter != null) {
            System.out.println("folf");
            attributes.addFlashAttribute("page", autoNumberService.filter(filter, pageable));

        } else {

            Page<AutoNumber> allNumbers = autoNumberService.findAllNumbers(pageable);
            System.out.println("page " + allNumbers.getNumber());
            attributes.addFlashAttribute("page", autoNumberService.findAllNumbers(pageable));
        }
    }
}
