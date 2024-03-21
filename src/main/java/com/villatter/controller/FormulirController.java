package com.villatter.controller;

import com.villatter.dto.riwayat.*;
import com.villatter.service.abstraction.FormulirServiceInterface;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/formulir")
public class FormulirController {

    @Autowired
    private FormulirServiceInterface service;

    @GetMapping("/buatSurat")
    public String buatSurat(@RequestParam(required = true) String nik,
                            @RequestParam(required = true) Long idSurat, Model model){
        var idSuratConvert = idSurat.toString();
        switch (idSuratConvert){
            case "1":
                var skbb = new InsertRiwayatA1DTO();
                skbb.setIdSurat(idSurat);
                skbb.setNikPenduduk(nik);
                model.addAttribute("dto", skbb);
                return "formulir/formulir-a1";
            case "2":
                var skd = new InsertRiwayatA1DTO();
                skd.setIdSurat(idSurat);
                skd.setNikPenduduk(nik);
                model.addAttribute("dto", skd);
                return "formulir/formulir-a1";
            case "3":
                var skk = new InsertRiwayatA1DTO();
                skk.setIdSurat(idSurat);
                skk.setNikPenduduk(nik);
                model.addAttribute("dto", skk);
                return "formulir/formulir-a1";
            case "4":
                var sktm = new InsertRiwayatA1DTO();
                sktm.setIdSurat(idSurat);
                sktm.setNikPenduduk(nik);
                model.addAttribute("dto", sktm);
                return "formulir/formulir-a1";
            case "5":
                var skkkb = new InsertRiwayatA2DTO();
                skkkb.setIdSurat(idSurat);
                skkkb.setNikPenduduk(nik);
                model.addAttribute("dto", skkkb);
                return "formulir/formulir-a2";
            case "6":
                var sku = new InsertRiwayatA3DTO();
                sku.setIdSurat(idSurat);
                sku.setNikPenduduk(nik);
                model.addAttribute("dto", sku);
                return "formulir/formulir-a3";
            case "7":
                var skl = new InsertRiwayatA4DTO();
                skl.setIdSurat(idSurat);
                skl.setNikPenduduk(nik);
                model.addAttribute("dto", skl);
                return "formulir/formulir-a4";
            case "8":
                var srp = new InsertRiwayatA5DTO();
                srp.setIdSurat(idSurat);
                srp.setNikPenduduk(nik);
                model.addAttribute("dto", srp);
                return "formulir/formulir-a5";
            default:
                model.addAttribute("nik", nik);
                return "formulir/pilih-surat";
        }
    }

    @PostMapping("/insertASatu")
    public String insertSuratASatu(@Valid @ModelAttribute("dto") InsertRiwayatA1DTO dto, BindingResult bindingResult, Model model){
        if(!bindingResult.hasErrors()){
            service.saveFormulirASatu(dto);
            return "check/cek-penduduk";
        }
        return "formulir/formulir-a1";
    }

    @PostMapping("/insertADua")
    public String insertSuratADua(@Valid @ModelAttribute("dto") InsertRiwayatA2DTO dto, BindingResult bindingResult, Model model){
        if(!bindingResult.hasErrors()){
            service.saveFormulirADua(dto);
            return "check/cek-penduduk";
        }
        return "formulir/formulir-a2";
    }

    @PostMapping("/insertATiga")
    public String insertSuratATiga(@Valid @ModelAttribute("dto") InsertRiwayatA3DTO dto, BindingResult bindingResult, Model model){
        if(!bindingResult.hasErrors()){
            service.saveFormulirATiga(dto);
            return "check/cek-penduduk";
        }
        return "formulir/formulir-a3";
    }

    @PostMapping("/insertAEmpat")
    public String insertSuratAEmpat(@Valid @ModelAttribute("dto") InsertRiwayatA4DTO dto, BindingResult bindingResult, Model model){
        if(!bindingResult.hasErrors()){
            service.saveFormulirAEmpat(dto);
            return "check/cek-penduduk";
        }
        return "formulir/formulir-a4";
    }

    @PostMapping("/insertALima")
    public String insertSuratALima(@Valid @ModelAttribute("dto") InsertRiwayatA5DTO dto, BindingResult bindingResult, Model model){
        if(!bindingResult.hasErrors()){
            service.saveFormulirALima(dto);
            return "check/cek-penduduk";
        }
        return "formulir/formulir-a5";
    }
}
