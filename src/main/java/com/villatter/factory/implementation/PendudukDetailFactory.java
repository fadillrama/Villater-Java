package com.villatter.factory.implementation;

import com.villatter.dto.penduduk.PendudukDetailGridDTO;
import com.villatter.factory.abstraction.IndexDetailFactory;
import com.villatter.factory.abstraction.IndexFactory;
import com.villatter.service.abstraction.PendudukServiceInterface;
import com.villatter.viewModel.implementation.penduduk.PendudukDetailViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;

import java.util.HashMap;
import java.util.Map;

@Component
public class PendudukDetailFactory implements IndexDetailFactory {

    @Autowired
    PendudukServiceInterface pendudukServiceInterface;
    private Object parent;
    private String parentId;

    private Map<String, Object> urlParameters = new HashMap<>();
    private Integer page;


    @Override
    public Object getParent() {
        return parent;
    }

    @Override
    public void setParentId(Object parentId) {
        this.parentId = parentId.toString();
    }

    @Override
    public Object getParentId() {
        return parentId;
    }

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

    private String getJeniSurat(){
        return getUrlParameters().get("jenisSurat").toString();
    }

    @Override
    public void processIndex(Model model) {
        var parent = pendudukServiceInterface.getPendudukHeader(parentId);
        var pageCollection = pendudukServiceInterface.getRiwayatGridByPenduduk(parentId, page, getJeniSurat());
        var grid = IndexFactory.getGridDTO(pageCollection, row -> new PendudukDetailGridDTO(row));
        var viewModel = new PendudukDetailViewModel(getJeniSurat())
                .setParent(parent)
                .setParentId(parentId)
                .setGrid(grid)
                .setPage(page)
                .setTotalPages(pageCollection.getTotalPages());
        viewModel.build(model);
    }
}
