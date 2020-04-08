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
import org.springframework.web.bind.annotation.*;

@Controller()
@RequestMapping("search")
public class SearchCtrl {
    @ModelAttribute("searchActive")
    public String cssActivePage() {
        return "active";
    }

    @Autowired
    AutoNumberService autoNumberService;

    @GetMapping
    public String getSearch(@RequestParam(required = false) String filter, @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable, Model model) {
        if (filter == null || filter.isEmpty()) {
            System.out.println(1);

            model.addAttribute("page", Page.empty());
            model.addAttribute("filter", filter);
        }else {
            System.out.println(2);
            model.addAttribute("filter", filter);
            model.addAttribute("page", autoNumberService.filter(filter, pageable));
        }

        return "search";
    }


  /*  @PostMapping("/{filter}")
    public String filter(@RequestParam String filter, Model model,
                         @PageableDefault(sort = {"id"}, direction = Sort.Direction.DESC) Pageable pageable) {
        if (filter == null || filter.isEmpty()) {
            return "redirect:/search";
        }

        System.out.println("in filter ");

        model.addAttribute("page", autoNumberService.filter(filter, pageable));
        model.addAttribute("filter", filter);

        return "/search";
    }*/

}
