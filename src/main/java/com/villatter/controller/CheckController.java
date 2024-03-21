package com.villatter.controller;

import com.villatter.service.PendudukService;
import com.villatter.service.abstraction.PendudukServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/check")
public class CheckController {

    @Autowired
    private PendudukServiceInterface service;

    @GetMapping("/cekPenduduk")
    public String landingPage(@RequestParam(required = false) String nik,
                              @RequestParam(required = false) String message, Model model){
        model.addAttribute("message", message);
        model.addAttribute("nik", nik);
        return "check/cek-penduduk";
    }

    @PostMapping("/pilihSurat")
    public String pilihSurat(@RequestParam(required = true) String nik, Model model, RedirectAttributes redirectAttributes){
        var total = service.countPendudukById(nik);
        if(total > 0){
            model.addAttribute("nik", nik);
            return "formulir/pilih-surat";
        }
        redirectAttributes.addAttribute("message", "NIK tidak ditemukan!");
        redirectAttributes.addAttribute("nik", nik);
        return "redirect:/check/cekPenduduk";
    }
}
