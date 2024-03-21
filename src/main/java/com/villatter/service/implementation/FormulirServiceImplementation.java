package com.villatter.service.implementation;

import com.villatter.dto.riwayat.*;
import com.villatter.entity.*;
import com.villatter.repository.*;
import com.villatter.service.abstraction.FormulirServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Scope("singleton")
@Service("formulirSingletonService")
public class FormulirServiceImplementation implements FormulirServiceInterface {
    @Autowired
    private RiwayatRepository riwayatRepository;

    @Autowired
    private SuratRepository suratRepository;

    @Autowired
    private KendaraanRepository kendaraanRepository;

    @Autowired
    private MarketRepository marketRepository;

    @Autowired
    private BabyRepository babyRepository;

    @Autowired
    private BrideRepository brideRepository;

    @Override
    public void saveFormulirASatu(InsertRiwayatA1DTO dto){
        var entity = new Riwayat();
        var noSurat = getNoSurat(LocalDate.now(), dto.getIdSurat());

        entity.setNoSurat(noSurat);
        entity.setIdSurat(dto.getIdSurat());
        entity.setNikPenduduk(dto.getNikPenduduk());
        entity.setTanggalDiajukan(LocalDate.now());
        entity.setKeperluan(dto.getKeperluan());
        riwayatRepository.save(entity);
    }

    @Override
    public void saveFormulirADua(InsertRiwayatA2DTO dto){
        var entity = new Riwayat();
        var kendaraan = new Kendaraan();
        var noSurat = getNoSurat(LocalDate.now(), dto.getIdSurat());

        entity.setNoSurat(noSurat);
        entity.setIdSurat(dto.getIdSurat());
        entity.setNikPenduduk(dto.getNikPenduduk());
        entity.setTanggalDiajukan(LocalDate.now());
        entity.setKeperluan(dto.getKeperluan());
        var entitySaved = riwayatRepository.save(entity);

        kendaraan.setNoSuratFK(entitySaved.getNoSurat());
        kendaraan.setModel(dto.getModel());
        kendaraan.setMerek(dto.getMerek());
        kendaraan.setRoda(dto.getRoda());
        kendaraan.setWarna(dto.getWarna());
        kendaraan.setNoPolisi(dto.getNoPolisi());
        kendaraanRepository.save(kendaraan);
    }

    @Override
    public void saveFormulirATiga(InsertRiwayatA3DTO dto){
        var entity = new Riwayat();
        var market = new Market();
        var noSurat = getNoSurat(LocalDate.now(), dto.getIdSurat());

        entity.setNoSurat(noSurat);
        entity.setIdSurat(dto.getIdSurat());
        entity.setNikPenduduk(dto.getNikPenduduk());
        entity.setTanggalDiajukan(LocalDate.now());
        entity.setKeperluan(dto.getKeperluan());
        var entitySaved = riwayatRepository.save(entity);

        market.setNoSuratFK(entitySaved.getNoSurat());
        market.setJenisUsaha(dto.getJenisUsaha());
        market.setTanggalMulai(dto.getTanggalMulai());
        marketRepository.save(market);
    }

    @Override
    public void saveFormulirAEmpat(InsertRiwayatA4DTO dto){
        var entity = new Riwayat();
        var baby = new Baby();
        var noSurat = getNoSurat(LocalDate.now(), dto.getIdSurat());

        entity.setNoSurat(noSurat);
        entity.setIdSurat(dto.getIdSurat());
        entity.setNikPenduduk(dto.getNikPenduduk());
        entity.setTanggalDiajukan(LocalDate.now());
        entity.setKeperluan(dto.getKeperluan());
        var entitySaved = riwayatRepository.save(entity);

        baby.setNoSuratFK(entitySaved.getNoSurat());
        baby.setNamaDepan(dto.getNamaDepanBaby());
        baby.setNamaTengah(dto.getNamaTengahBaby());
        baby.setNamaBelakang(dto.getNamaBelakangBaby());
        baby.setTempatLahir(dto.getTempatLahirBaby());
        baby.setTanggalLahir(dto.getTanggalLahirBaby());
        baby.setJenisKelamin(dto.getJenisKelaminBaby());
        baby.setAgama(dto.getAgamaBaby());
        babyRepository.save(baby);
    }

    @Override
    public void saveFormulirALima(InsertRiwayatA5DTO dto){
        var entity = new Riwayat();
        var bride = new Bride();
        var noSurat = getNoSurat(LocalDate.now(), dto.getIdSurat());

        entity.setNoSurat(noSurat);
        entity.setIdSurat(dto.getIdSurat());
        entity.setNikPenduduk(dto.getNikPenduduk());
        entity.setTanggalDiajukan(LocalDate.now());
        entity.setKeperluan(dto.getKeperluan());
        var entitySaved = riwayatRepository.save(entity);

        bride.setNoSuratFK(entitySaved.getNoSurat());
        bride.setNik(dto.getNikBride());
        bride.setNamaDepan(dto.getNamaDepanBride());
        bride.setNamaTengah(dto.getNamaTengahBride());
        bride.setNamaBelakang(dto.getNamaBelakangBride());
        bride.setTempatLahir(dto.getTempatLahirBride());
        bride.setTanggalLahir(dto.getTanggalLahirBride());
        bride.setJenisKelamin(dto.getJenisKelaminBride());
        bride.setAgama(dto.getAgamaBride());
        bride.setJalan(dto.getJalan());
        bride.setDusun(dto.getDusun());
        bride.setRt(dto.getRt());
        bride.setRw(dto.getRw());
        bride.setDesa(dto.getDesa());
        bride.setKecamatan(dto.getKecamatan());
        bride.setKabupaten(dto.getKabupaten());
        bride.setKewarganegaraan(dto.getKewarganegaraan());
        bride.setNoKK(dto.getNoKK());
        bride.setStatusKawin(dto.getStatusKawin());
        bride.setAnakKe(dto.getAnakKe());
        brideRepository.save(bride);
    }

    @Override
    public String getNoSurat(LocalDate tanggalPengajuan, Long idSurat){
        var formatter = DateTimeFormatter.ofPattern("MM/yy");
        var segmentPertama = formatter.format(tanggalPengajuan);
        var surat = suratRepository.findById(idSurat).get();
        var jenisSurat = surat.getJenisSurat();
        String[] namaSuratSplit = jenisSurat.split(" ");
        StringBuilder segmentKedua = new StringBuilder();
        for (var kata : namaSuratSplit) {
            segmentKedua.append(kata.substring(0, 1));
        }
        var nextRollingNumber = riwayatRepository.countRiwayatByPeriod(segmentPertama+"/"+segmentKedua) + 1;
        return String.format("%s/%s/%03d", segmentPertama, segmentKedua, nextRollingNumber);
    }
}
