package com.villatter.service.implementation;

import com.villatter.dto.pdf.HardPdfDTO;
import com.villatter.dto.riwayat.RiwayatRowDTO;
import com.villatter.repository.PendudukRepository;
import com.villatter.repository.RiwayatRepository;
import com.villatter.service.abstraction.ReadService;
import com.villatter.service.abstraction.SelesaiServiceInterface;
import com.villatter.utility.MapperHelper;
import com.villatter.utility.PDFLetterGenerator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Scope("singleton")
@Service
public class SelesaiServiceImplementation implements SelesaiServiceInterface, ReadService {

    @Autowired
    private RiwayatRepository riwayatRepository;

    @Autowired
    private PendudukRepository pendudukRepository;

    private final int rowsInPage = 10;

    @Override
    public Page<RiwayatRowDTO> getRowSelesai(String nama, String jenisSurat, Integer page){
        var pageable = PageRequest.of(page - 1, rowsInPage);
        var rows = riwayatRepository.getSuratSelesai(nama, jenisSurat, pageable);
        return rows;
    }

    @Override
    public byte[] getPdfByte(String noSurat) throws Exception {
        var riwayat = riwayatRepository.findById(noSurat).get();
        var penduduk = pendudukRepository.findById(riwayat.getNikPenduduk()).get();
        var dto = new HardPdfDTO(penduduk, riwayat);
        var pdfBytes = PDFLetterGenerator.generatorLetterPdf(dto);
        return pdfBytes;
    }

    @Override
    public <T> Page<Object> getGrid(Integer page, T filter) {
        var pagination = PageRequest.of(page - 1, rowsInPage);
        var nama = MapperHelper.getStringField(filter, "nama");
        var jenisSurat = MapperHelper.getStringField(filter, "jenisSurat");
        return riwayatRepository.findAllSelesai(nama, jenisSurat, pagination);
    }
}
