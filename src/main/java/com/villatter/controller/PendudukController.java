package com.villatter.controller;

import com.villatter.dto.penduduk.InsertPendudukDTO;
import com.villatter.dto.penduduk.UpdatePendudukDTO;
import com.villatter.factory.abstraction.DeleteFactory;
import com.villatter.factory.abstraction.IndexDetailFactory;
import com.villatter.factory.abstraction.IndexFactory;
import com.villatter.factory.abstraction.UpsertFactory;
import com.villatter.service.PendudukService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/penduduk")
public class PendudukController {

    @Autowired
    private PendudukService service;

//    @GetMapping("/index")
//    public String index(@RequestParam(defaultValue = "1") Integer page,
//                        @RequestParam(defaultValue = "") String nama, Model model){
//        var rows = service.getRow(page, nama);
//
//        model.addAttribute("grid", rows);
//        model.addAttribute("nama", nama);
//        model.addAttribute("totalPages", rows.getTotalPages());
//        model.addAttribute("currentPage", page);
//        return "penduduk/penduduk-index";
//    }

//    @GetMapping("/upsertForm")
//    public String upsertForm(@RequestParam(required = false) String nik, Model model){
//        if (nik != null){
//            var dto = service.findOne(nik);
//            model.addAttribute("dto", dto);
//            return "penduduk/penduduk-update-form";
//        }
//        var dto = new InsertPendudukDTO();
//        model.addAttribute("dto", dto);
//        return "penduduk/penduduk-insert-form";
//    }

//    @PostMapping("/update")
//    public String update(@Valid @ModelAttribute("dto") UpdatePendudukDTO dto, BindingResult bindingResult, Model model){
//        if (!bindingResult.hasErrors()){
//            service.save(dto);
//            return "redirect:/penduduk/index";
//        }
//        return "penduduk/penduduk-update-form";
//    }

//    @PostMapping("/insert")
//    public String insert(@Valid @ModelAttribute("dto") InsertPendudukDTO dto, BindingResult bindingResult, Model model){
//        if (!bindingResult.hasErrors()){
//            service.save(dto);
//            return "redirect:/penduduk/index";
//        }
//        return "penduduk/penduduk-insert-form";
//    }

//    @RequestMapping(value = "/delete", method = {RequestMethod.GET, RequestMethod.POST})
//    public String delete(@RequestParam(required = true) String nik, Model model) {
//        var dependencies = service.countRiwayat(nik);
//        if(dependencies > 0) {
//            model.addAttribute("dependentRiwayat", dependencies);
//            return "penduduk/penduduk-delete";
//        }
//        return "redirect:/penduduk/index";
//    }


// ================================================================================


    @Qualifier("pendudukFactory")
    @Autowired
    private IndexFactory indexFactory;

    @GetMapping("/index")
    public String index(@RequestParam(defaultValue = "1") Integer page,
                        @RequestParam(defaultValue = "") String nama, Model model){
        indexFactory.setPage(page);
        indexFactory.setUrlParameters("nama", nama);
        indexFactory.processIndex(model);
        return "penduduk/penduduk-index";
    }

    @Qualifier("pendudukFactory")
    @Autowired
    private UpsertFactory upsertFactory;

    @GetMapping("/upsertForm")
    public String upsertForm(@RequestParam(required = false) String nik, Model model){
        upsertFactory.setId(nik);
        upsertFactory.processUpsertForm(model);
        return "penduduk/penduduk-form";
    }

    @PostMapping("/update")
    public String update(@Valid @ModelAttribute("dto") UpdatePendudukDTO dto, BindingResult bindingResult, Model model){
        if (!bindingResult.hasErrors()){
            upsertFactory.save(dto);
            return "redirect:/penduduk/index";
        }
        upsertFactory.processUpsertForm(model, dto);
        return "penduduk/penduduk-form";
    }

    @PostMapping("/insert")
    public String insert(@Valid @ModelAttribute("dto") InsertPendudukDTO dto, BindingResult bindingResult, Model model){
        if (!bindingResult.hasErrors()){
            upsertFactory.save(dto);
            return "redirect:/penduduk/index";
        }
        upsertFactory.processUpsertForm(model, dto);
        return "penduduk/penduduk-form";
    }

    @Qualifier("pendudukFactory")
    @Autowired
    private DeleteFactory deleteFactory;

    @RequestMapping(value = "/delete", method = {RequestMethod.GET, RequestMethod.POST})
    public String delete(@RequestParam(required = true) String nik, Model model) {
        deleteFactory.setId(nik);
        var success = deleteFactory.processDelete(model);
        if(!success) {
            return "penduduk/penduduk-delete";
        }
        return "redirect:/penduduk/index";
    }

    @Qualifier("pendudukDetailFactory")
    @Autowired
    private IndexDetailFactory indexDetailFactory;

    @GetMapping("/detail")
    public String detail(@RequestParam(required = true) String nik,
                         @RequestParam(defaultValue = "1") Integer page,
                         @RequestParam(defaultValue= "") String jenisSurat, Model model) {
        indexDetailFactory.setParentId(nik);
        indexDetailFactory.setPage(page);
        indexDetailFactory.setUrlParameters("jenisSurat", jenisSurat);
        indexDetailFactory.processIndex(model);
        return "penduduk/penduduk-detail";
    }
}
// TODO fix bug in update penduduk