package com.villatter.service;

import com.villatter.repository.PendudukRepository;
import com.villatter.repository.RiwayatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DashboardService {

    @Autowired
    private RiwayatRepository riwayatRepository;

    @Autowired
    private PendudukRepository pendudukRepository;

    public Long getTotalSelesai(){
        return riwayatRepository.countTotalSelesai();
    }

    public Long getTotalPengajuan(){
        return riwayatRepository.countTotalPengajuan();
    }

    public Long getTotalPenduduk(){
        return pendudukRepository.countAllPenduduk();
    }
}
