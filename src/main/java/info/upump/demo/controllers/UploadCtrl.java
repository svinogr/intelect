package info.upump.demo.controllers;

import info.upump.demo.service.AutoNumberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UploadCtrl {
    @ModelAttribute("numberActive")
    public String cssActivePage() {
        return "active";
    }

    @Autowired
    AutoNumberService autoNumberService;

    @PostMapping("/upload")
    public String handleFileUpload(@RequestParam("file") MultipartFile file, RedirectAttributes
            redirectAttributes,
                                   Model model) {
       if (file == null || file.isEmpty()){
           model.addAttribute("uploadMessage", "Файл невозможно обработать");
           model.addAttribute("result", true);

       }else {
           model.addAttribute("uploadMessage", "Файл обработан");
           model.addAttribute("result", true);
       }

       model.addAttribute("numbers", autoNumberService.findAllNumbers());

        return "numbers";
    }
}
