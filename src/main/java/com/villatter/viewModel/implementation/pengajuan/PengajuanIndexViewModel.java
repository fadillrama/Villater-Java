package com.villatter.viewModel.implementation.pengajuan;

import com.villatter.viewModel.abstraction.IndexViewModel;
import org.springframework.ui.Model;

public class PengajuanIndexViewModel extends IndexViewModel {
    private String nama;
    private String jenisSurat;

    public PengajuanIndexViewModel(String nama, String jenisSurat) {
        super();
        this.nama = nama;
        this.jenisSurat = jenisSurat;
        setBreadCrumbs("Pengajuan Index")
                .setTableHeaders(new String[] {"No Surat", "Jenis Surat", "Nama Pengaju", "Tanggal Pengajuan", "Keperluan", "Status"})
                .setActionUrl("/pengajuan/index");
    }

    @Override
    public void build(Model model) {
        var urlParameters = String.format("&nama=%s&jenisSurat=%s", nama, jenisSurat);
        model.addAttribute("nama", nama);
        model.addAttribute("jenisSurat", jenisSurat);
        super.build(model, urlParameters);
    }
}
