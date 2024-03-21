package com.villatter.viewModel.implementation.penduduk;

import org.springframework.ui.Model;
import com.villatter.viewModel.abstraction.IndexViewModel;

public class PendudukIndexViewModel extends IndexViewModel {

    private String nama;

    public PendudukIndexViewModel(String nama) {
        super();
        this.nama = nama;
        setBreadCrumbs("Penduduk Index")
                .setTableHeaders(new String[] {"NIK", "Nama Lengkap", "Tempat, Tanggal Lahir", "Jenis Kelamin", "Alamat"})
                .setActionUrl("/penduduk/index");
    }

    @Override
    public void build(Model model) {
        var urlParameters = String.format("&nama=%s", nama);
        model.addAttribute("nama", nama);
        super.build(model, urlParameters);
    }
}
