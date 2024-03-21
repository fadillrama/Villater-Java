package com.villatter.viewModel.implementation.penduduk;

import com.villatter.utility.MapperHelper;
import com.villatter.viewModel.abstraction.IndexDetailViewModel;
import org.springframework.ui.Model;

public class PendudukDetailViewModel extends IndexDetailViewModel {
    private String jenisSurat;

    public PendudukDetailViewModel(String jenisSurat) {
        this.jenisSurat = jenisSurat;
        setTableHeaders(new String[] {"No Surat", "Jenis Surat", "Tanggal Diajukan", "Tanggal Disetujui","Keperluan", "Status"})
                .setActionUrl("/penduduk/detail");
    }

    private String getPendudukFullName(Object parent){
        String firstName = MapperHelper.getStringField(parent, "namaDepan");
        String middleName = MapperHelper.getStringField(parent, "namaTengah");
        String lastName = MapperHelper.getStringField(parent, "namaBelakang");
        String fullName = String.format("%s %s %s", firstName, middleName, lastName);
        return fullName;
    }

    @Override
    public void build(Model model) {
        var urlParameters = String.format("&nik=%s&jenisSurat=%s", getParentId(), jenisSurat);
        model.addAttribute("jenisSurat", jenisSurat);
        var fullName = getPendudukFullName(getParent());
        setBreadCrumbs(String.format("Penduduk Index / Letter of %s", fullName));
        model.addAttribute("headerFullName", fullName);
        super.build(model, urlParameters);
    }
}
