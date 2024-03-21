package com.villatter.controller;

import com.villatter.factory.abstraction.IndexFactory;
import com.villatter.factory.abstraction.PdfFactory;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/selesai")
public class SelesaiController {
    @Qualifier("selesaiFactory")
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
        return "surat/surat-selesai-index";
    }

    @Qualifier("selesaiFactory")
    @Autowired
    private PdfFactory pdfFactory;

    @GetMapping("/generatePdfLetter")
    public void generatePdfLetter(@RequestParam(required = true) String noSurat, HttpServletResponse response) throws Exception {
        pdfFactory.setId(noSurat);
        var pdfBytes = pdfFactory.getPdfByte();
        var fileNameDisposition = String.format("attachment; filename=\"%s-letter.pdf\"", noSurat);
        response.setContentType("application/pdf");
        response.setHeader("Content-Disposition", fileNameDisposition);
        response.getOutputStream().write(pdfBytes);
        response.getOutputStream().flush();
    }
}