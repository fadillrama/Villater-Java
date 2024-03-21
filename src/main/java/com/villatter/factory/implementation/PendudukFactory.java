package com.villatter.factory.implementation;

import com.villatter.dto.penduduk.InsertPendudukDTO;
import com.villatter.dto.penduduk.PendudukFilterDTO;
import com.villatter.dto.penduduk.PendudukRowDTO;
import com.villatter.dto.penduduk.UpdatePendudukDTO;
import com.villatter.factory.abstraction.DeleteFactory;
import com.villatter.factory.abstraction.IndexFactory;
import com.villatter.factory.abstraction.UpsertFactory;
import com.villatter.service.abstraction.CrudService;
import com.villatter.service.abstraction.PendudukServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import com.villatter.viewModel.implementation.penduduk.PendudukDeleteViewModel;
import com.villatter.viewModel.implementation.penduduk.PendudukIndexViewModel;
import com.villatter.viewModel.implementation.penduduk.PendudukUpsertViewModel;

import java.util.HashMap;
import java.util.Map;

@Scope("singleton")
@Component
public class PendudukFactory implements IndexFactory, UpsertFactory, DeleteFactory {

    @Qualifier("pendudukServiceImplementation")
    @Autowired
    private CrudService service;

    @Autowired
    private PendudukServiceInterface pendudukServiceInterface;

    private Map<String, Object> urlParameters = new HashMap<>();
    private Integer page;
    private String getNama(){
        var nama = getUrlParameters().get("nama");
        return (nama != null) ? nama.toString() : null;
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
    @Override
    public void processIndex(Model model) {
        var pageCollection = service.getGrid(page, new PendudukFilterDTO(getNama()));
        var grid = IndexFactory.getGridDTO(pageCollection, row -> new PendudukRowDTO(row));
        var viewModel = new PendudukIndexViewModel(getNama())
                .setGrid(grid)
                .setPage(page)
                .setTotalPages(pageCollection.getTotalPages());
        viewModel.build(model);
    }

    private String nik;
    @Override
    public void setId(Object id) {
        nik = (id != null) ? id.toString() : null;
    }
    @Override
    public Object getId() {
        return nik;
    }

    @Override
    public void processUpsertForm(Model model) {
        var viewModel = new PendudukUpsertViewModel();
        if (nik != null) {
            var entity = service.getUpdate(nik);
            var dto = new UpdatePendudukDTO(entity);
            viewModel.setBreadCrumbs("Penduduk Index / Update Penduduk")
                    .setType("update")
                    .setDto(dto);
        } else {
            var dto = new InsertPendudukDTO();
            viewModel.setBreadCrumbs("Penduduk Index / Insert Penduduk")
                    .setType("insert")
                    .setDto(dto);
        }
        viewModel.build(model);
    }

    @Override
    public void processUpsertForm(Model model, Object dto) {
        var viewModel = new PendudukUpsertViewModel();
        if (nik != null) {
            viewModel.setBreadCrumbs("Penduduk Index / Update Penduduk")
                    .setType("update")
                    .setDto(dto);
        } else {
            viewModel.setBreadCrumbs("Penduduk Index / Insert Penduduk")
                    .setType("insert")
                    .setDto(dto);
        }
        viewModel.build(model);
    }

    @Override
    public void save(Object dto) {
        service.save(dto);
    }

    @Override
    public Boolean processDelete(Model model) {
        var dependentRiwayat = pendudukServiceInterface.dependentRiwayat(nik);
        if(dependentRiwayat > 0) {
            var viewModel = new PendudukDeleteViewModel()
                    .setBreadCrumbs("Penduduk Index / Fail to Delete Penduduk")
                    .setDependencies("dependentRiwayat", dependentRiwayat);
            viewModel.build(model);
            return false;
        }
        service.delete(nik);
        return true;
    }
}
