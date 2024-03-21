package com.villatter.factory.implementation;

import com.villatter.dto.pengajuan.PengajuanFilterDTO;
import com.villatter.dto.pengajuan.PengajuanRowDTO;
import com.villatter.factory.abstraction.AcceptFactory;
import com.villatter.factory.abstraction.IndexFactory;
import com.villatter.service.abstraction.PengajuanServiceInterface;
import com.villatter.service.abstraction.ReadService;
import com.villatter.viewModel.implementation.pengajuan.PengajuanIndexViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import java.util.HashMap;
import java.util.Map;

@Scope("singleton")
@Component
public class PengajuanFactory implements IndexFactory, AcceptFactory {

    @Qualifier("pengajuanServiceImplementation")
    @Autowired
    private ReadService readService;

    @Autowired
    private PengajuanServiceInterface pengajuanServiceInterface;

    private Map<String, Object> urlParameters = new HashMap<>();
    private Integer page;

    @Override
    public void setPage(Integer page) {
        this.page = page;
    }

    @Override
    public Integer getPage() {
        return page;
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
        var pageCollection = readService.getGrid(page, new PengajuanFilterDTO(getNama(), getJenisSurat()));
        var grid = IndexFactory.getGridDTO(pageCollection, row -> new PengajuanRowDTO(row));
        var viewModel = new PengajuanIndexViewModel(getNama(), getJenisSurat())
                .setGrid(grid)
                .setPage(page)
                .setTotalPages(pageCollection.getTotalPages());
        viewModel.build(model);
    }

    private String noSurat;

    @Override
    public void acceptLetter(String noSurat) {
        pengajuanServiceInterface.acceptSurat(noSurat);
    }

    @Override
    public void setId(Object id) {
        this.noSurat = id.toString();
    }

    @Override
    public Object getId() {
        return this.noSurat;
    }
}
