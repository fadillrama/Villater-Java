package com.villatter.viewModel.implementation.penduduk;

import com.villatter.viewModel.abstraction.DeleteViewModel;
import org.springframework.ui.Model;

public class PendudukDeleteViewModel extends DeleteViewModel {
    @Override
    public void build(Model model) {
        model.addAttribute("breadCrumbs", breadCrumbs);
        var dependentRiwayat = dependencies.get("dependentRiwayat");
        model.addAttribute("dependentRiwayat", dependentRiwayat);
    }
}
