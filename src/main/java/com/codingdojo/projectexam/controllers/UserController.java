package com.codingdojo.projectexam.controllers;

import com.codingdojo.projectexam.models.LoginUser;
import com.codingdojo.projectexam.models.User;
import com.codingdojo.projectexam.services.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class UserController {


    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String index(Model model, HttpSession session) {
        if(session.getAttribute("user")==null){
            model.addAttribute("newUser", new User());
            model.addAttribute("newLogin", new LoginUser());
            return "login.jsp";
        }
        return "redirect:/tasks";
    }

    @GetMapping("/register")
    public String register(){

        return "redirect:/";
    }
    @GetMapping("/login")
    public String login(){

        return "redirect:/";
    }


    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("newUser") User newUser,
                           BindingResult result, Model model, HttpSession session, RedirectAttributes attribute) {
        userService.save(newUser,result);

        if(result.hasErrors()) {

            model.addAttribute("newLogin", new LoginUser());
            return "login.jsp";
        }
        attribute.addFlashAttribute("success", "Registro Exitoso");
        return "redirect:/";
    }

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("newLogin") LoginUser newLogin,
                        BindingResult result, Model model, HttpSession session) {
        User user=userService.login(newLogin, result);
        if(result.hasErrors()) {
            model.addAttribute("newUser", new User());
            return "login.jsp";
        }
        user.setPassword("");
        session.setAttribute("user",user);

        return "redirect:/tasks";


    }
    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.setAttribute("user",null);
        return "redirect:/";


    }





}
