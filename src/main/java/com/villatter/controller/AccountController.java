package com.villatter.controller;

import com.villatter.dto.account.ChangePasswordDTO;
import com.villatter.dto.account.RegisterDTO;
import com.villatter.service.abstraction.AccountService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService service;

    @GetMapping("/registerForm")
    public String registerForm(Model model){
        var dto = new RegisterDTO();
        model.addAttribute("dto", dto);
        return "account/register-form";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("dto") RegisterDTO dto, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
            return "account/register-form";
        }
        service.register(dto);
        return "redirect:/account/loginForm";
    }

    @GetMapping("/loginForm")
    public String loginForm(){
        return "account/login-form";
    }

    @RequestMapping(value = "/accessDenied", method = {RequestMethod.GET, RequestMethod.POST})
    public String accessDenied(Model model){
        return "account/access-denied";
    }

    @GetMapping("/failedLogin")
    public String failedLogin(Model model){
        return "account/failed-login";
    }

    @GetMapping("/changePasswordForm")
    public String changePasswordForm(@RequestParam(required = true) String username, Model model){
        var dto = new ChangePasswordDTO();
        dto.setUsername(username);
        model.addAttribute("dto", dto);
        return "account/change-password-form";
    }

    @PostMapping("/changePassword")
    public String changePassword(@Valid @ModelAttribute("dto") ChangePasswordDTO dto, BindingResult bindingResult, Model model){
        if (bindingResult.hasErrors()){
            return "account/change-password-form";
        }
        service.changePassword(dto);
        return "redirect:/";
    }
}
