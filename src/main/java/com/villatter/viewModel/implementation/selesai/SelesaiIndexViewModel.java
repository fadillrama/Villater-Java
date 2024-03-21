package com.villatter.viewModel.implementation.selesai;

import com.villatter.viewModel.abstraction.IndexViewModel;
import org.springframework.ui.Model;

public class SelesaiIndexViewModel extends IndexViewModel {
    private String nama;
    private String jenisSurat;

    public SelesaiIndexViewModel(String nama, String jenisSurat) {
        super();
        this.nama = nama;
        this.jenisSurat = jenisSurat;
        setBreadCrumbs("Selesai Index")
                .setTableHeaders(new String[] {"No Surat", "Jenis Surat", "Nama Pengaju", "Tanggal Pengajuan", "Tanggal Disetujui", "Keperluan", "Status"})
                .setActionUrl("/selesai/index");
    }

    @Override
    public void build(Model model) {
        var urlParameters = String.format("&nama=%s&jenisSurat=%s", nama, jenisSurat);
        model.addAttribute("nama", nama);
        model.addAttribute("jenisSurat", jenisSurat);
        super.build(model, urlParameters);
    }
}
