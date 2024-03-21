package com.villatter.service.implementation;

import com.villatter.dto.riwayat.RiwayatRowDTO;
import com.villatter.repository.RiwayatRepository;
import com.villatter.service.abstraction.PengajuanServiceInterface;
import com.villatter.service.abstraction.ReadService;
import com.villatter.utility.MapperHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Scope("singleton")
@Service
public class PengajuanServiceImplementation implements PengajuanServiceInterface, ReadService {

    @Autowired
    private RiwayatRepository riwayatRepository;

    private final int rowsInPage = 10;

    @Override
    public Page<RiwayatRowDTO> getRowPengajuan(String nama, String jenisSurat, Integer page){
        var pageable = PageRequest.of(page - 1, 10);
        var rows = riwayatRepository.getPengajuanSurat(nama, jenisSurat, pageable);
        return rows;
    }

    @Override
    public void acceptSurat(String noSurat){
        var surat = riwayatRepository.findById(noSurat).get();
        surat.setTanggalDisetujui(LocalDate.now());
        riwayatRepository.save(surat);
    }

    @Override
    public <T> Page<Object> getGrid(Integer page, T filter) {
        var pagination = PageRequest.of(page - 1, rowsInPage);
        var nama = MapperHelper.getStringField(filter, "nama");
        var jenisSurat = MapperHelper.getStringField(filter, "jenisSurat");
        return riwayatRepository.findAllPengajuan(nama, jenisSurat, pagination);
    }
}