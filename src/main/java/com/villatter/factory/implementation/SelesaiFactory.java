package com.villatter.factory.implementation;

import com.villatter.dto.pengajuan.PengajuanFilterDTO;
import com.villatter.dto.pengajuan.PengajuanRowDTO;
import com.villatter.dto.selesai.SelesaiFilterDTO;
import com.villatter.dto.selesai.SelesaiRowDTO;
import com.villatter.factory.abstraction.IndexFactory;
import com.villatter.factory.abstraction.PdfFactory;
import com.villatter.service.abstraction.ReadService;
import com.villatter.service.abstraction.SelesaiServiceInterface;
import com.villatter.viewModel.implementation.selesai.SelesaiIndexViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import java.util.HashMap;
import java.util.Map;

@Scope("singleton")
@Component
public class SelesaiFactory implements IndexFactory, PdfFactory {

    @Qualifier("selesaiServiceImplementation")
    @Autowired
    private ReadService readService;

    @Autowired
    private SelesaiServiceInterface selesaiServiceInterface;

    private Map<String, Object> urlParameters = new HashMap<>();
    private Integer page;

    @Override
    public void setPage(Integer page) {
        this.page = page;
    }

    @Override
    public Integer getPage() {
        return this.page;
    }

    @Override
    public void setUrlParameters(String key, Object value) {
        urlParameters.put(key, value);
    }

    @Override
    public Map<String, Object> getUrlParameters() {
        return urlParameters;
    }

    private String getNama(){
        var nama = getUrlParameters().get("nama");
        return (nama != null) ? nama.toString() : null;
    }

    private String getJenisSurat(){
        var jenisSurat = getUrlParameters().get("jenisSurat");
        return (jenisSurat != null) ? jenisSurat.toString() : null;
    }

    @Override
    public void processIndex(Model model) {
        var pageCollection = readService.getGrid(page, new SelesaiFilterDTO(getNama(), getJenisSurat()));
        var grid = IndexFactory.getGridDTO(pageCollection, row -> new SelesaiRowDTO(row));
        var viewModel = new SelesaiIndexViewModel(getNama(), getJenisSurat())
                .setGrid(grid)
                .setPage(page)
                .setTotalPages(pageCollection.getTotalPages());
        viewModel.build(model);
    }

    private String noSurat;
    @Override
    public void setId(Object id) {
        this.noSurat = (id != null) ? id.toString() : null;
    }
    @Override
    public Object getId() {
        return noSurat;
    }

    @Override
    public byte[] getPdfByte() throws Exception {
        return selesaiServiceInterface.getPdfByte(noSurat);
    }
}
