package com.villatter.controller;

import com.villatter.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dashboard")
public class DashboardController {

    @Autowired
    private DashboardService service;

    @GetMapping("/index")
    public String dashboard(Model model){
        var totalSelesai = service.getTotalSelesai();
        var totalPengajuan = service.getTotalPengajuan();
        var totalPenduduk = service.getTotalPenduduk();

        model.addAttribute("totalSelesai", totalSelesai);
        model.addAttribute("totalPengajuan", totalPengajuan);
        model.addAttribute("totalPenduduk", totalPenduduk);
        model.addAttribute("breadCrumbs", "Dashboard");
        return "dashboard/dashboard";
    }
}