package com.villatter.controller;

import com.villatter.factory.abstraction.AcceptFactory;
import com.villatter.factory.abstraction.IndexFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/pengajuan")
public class PengajuanController {

    @Qualifier("pengajuanFactory")
    @Autowired
    private IndexFactory indexFactory;

    @GetMapping("/index")
    public String index(@RequestParam(defaultValue = "") String nama,
                                    @RequestParam(defaultValue = "") String jenisSurat,
                                    @RequestParam(defaultValue = "1") Integer page, Model model){
        indexFactory.setPage(page);
        indexFactory.setUrlParameters("nama", nama);
        indexFactory.setUrlParameters("jenisSurat", jenisSurat);
        indexFactory.processIndex(model);
        return "surat/surat-pengajuan-index";
    }

    @Qualifier("pengajuanFactory")
    @Autowired
    private AcceptFactory acceptFactory;

    @GetMapping("/accept")
    public String accept(@RequestParam(required = true) String noSurat){
        acceptFactory.setId(noSurat);
        acceptFactory.acceptLetter(noSurat);
        return "redirect:/selesai/index";
    }
}
