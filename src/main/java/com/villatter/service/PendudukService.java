package com.villatter.service;

import com.villatter.dto.penduduk.PendudukDTO;
import com.villatter.dto.penduduk.PendudukRowDTO;
import com.villatter.dto.penduduk.UpdatePendudukDTO;
import com.villatter.entity.Penduduk;
import com.villatter.entity.Riwayat;
import com.villatter.repository.PendudukRepository;
import com.villatter.repository.RiwayatRepository;
import com.villatter.utility.MapperHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class PendudukService {
    @Autowired
    private PendudukRepository pendudukRepository;

    @Autowired
    private RiwayatRepository riwayatRepository;

    public Long countPendudukById(String nik){
        return pendudukRepository.countById(nik);
    }

    public UpdatePendudukDTO findOne(String nik){
        var entity = pendudukRepository.findById(nik).get();
        return new UpdatePendudukDTO(
                entity.getNik(),
                entity.getNamaDepan(),
                entity.getNamaTengah(),
                entity.getNamaBelakang(),
                entity.getTempatLahir(),
                entity.getTanggalLahir(),
                entity.getJenisKelamin(),
                entity.getAgama(),
                entity.getJalan(),
                entity.getDusun(),
                entity.getRt(),
                entity.getRw(),
                entity.getDesa(),
                entity.getKecamatan(),
                entity.getKabupaten(),
                entity.getKewarganegaraan(),
                entity.getNoKK(),
                entity.getStatusKawin()
        );
    }

    public PendudukDTO getOnePenduduk(String nik){
        var entity = pendudukRepository.findById(nik).get();
        return new PendudukDTO(
                entity.getNik(),
                entity.getNamaDepan(),
                entity.getNamaTengah(),
                entity.getNamaBelakang(),
                entity.getTempatLahir(),
                entity.getTanggalLahir(),
                entity.getJenisKelamin(),
                entity.getAgama(),
                entity.getJalan(),
                entity.getDusun(),
                entity.getRt(),
                entity.getRw(),
                entity.getDesa(),
                entity.getKecamatan(),
                entity.getKabupaten(),
                entity.getKewarganegaraan(),
                entity.getNoKK(),
                entity.getStatusKawin()
        );
    }

    public Boolean isNikNumberValid(String nik){
        nik = (nik == null) ? "defaultString" : nik;
        var total = pendudukRepository.countById(nik);
        if (total > 0){
            return false;
        }
        return true;
    }

    public String save(Object dto){
        var entity = new Penduduk();
        entity.setNik(MapperHelper.getStringField(dto, "nik"));
        entity.setNamaDepan(MapperHelper.getStringField(dto, "namaDepan"));
        entity.setNamaTengah(MapperHelper.getStringField(dto, "namaTengah"));
        entity.setNamaBelakang(MapperHelper.getStringField(dto, "namaBelakang"));
        entity.setTempatLahir(MapperHelper.getStringField(dto, "tempatLahir"));
        entity.setTanggalLahir(MapperHelper.getLocalDateField(dto, "tanggalLahir"));
        entity.setJenisKelamin(MapperHelper.getStringField(dto, "jenisKelamin"));
        entity.setAgama(MapperHelper.getStringField(dto, "agama"));
        entity.setJalan(MapperHelper.getStringField(dto, "jalan"));
        entity.setDusun(MapperHelper.getStringField(dto, "dusun"));
        entity.setRt(MapperHelper.getIntegerField(dto, "rt"));
        entity.setRw(MapperHelper.getIntegerField(dto, "rw"));
        entity.setDesa(MapperHelper.getStringField(dto, "desa"));
        entity.setKecamatan(MapperHelper.getStringField(dto, "kecamatan"));
        entity.setKabupaten(MapperHelper.getStringField(dto, "kabupaten"));
        entity.setKewarganegaraan(MapperHelper.getStringField(dto, "kewarganegaraan"));
        entity.setNoKK(MapperHelper.getStringField(dto, "noKK"));
        entity.setStatusKawin(MapperHelper.getStringField(dto, "statusKawin"));

        pendudukRepository.save(entity);
        return "Perubahan telah disimpan !";
    }

    public Page<PendudukRowDTO> getRow(Integer page, String nama){
        var pageable = PageRequest.of(page - 1, 10);
        var rows = pendudukRepository.getRow(nama, pageable);
        return rows;
    }

    public Long countRiwayat(String nik) {
        return riwayatRepository.countRiwayatByPenduduk(nik);
    }
}
