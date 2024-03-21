package com.villatter.service.implementation;

import com.villatter.entity.Penduduk;
import com.villatter.repository.PendudukRepository;
import com.villatter.repository.RiwayatRepository;
import com.villatter.service.abstraction.CrudService;
import com.villatter.service.abstraction.PendudukServiceInterface;
import com.villatter.utility.MapperHelper;
import org.apache.catalina.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Scope("singleton")
@Service
public class PendudukServiceImplementation implements CrudService, PendudukServiceInterface {

    @Autowired
    private PendudukRepository pendudukRepository;

    @Autowired
    private RiwayatRepository riwayatRepository;

    private final int rowsInPage = 10;

    @Override
    public <T> Page<Object> getGrid(Integer page, T filter) {
        var pagination = PageRequest.of(page - 1, rowsInPage);
        var name = MapperHelper.getStringField(filter, "nama");
        return pendudukRepository.findAll(name, pagination);
    }

    @Override
    public Object getUpdate(Object id) {
        var entity = pendudukRepository.findById(id.toString());
        return entity.isPresent() ? entity.get() : null;
    }

    @Override
    public Object save(Object dto){
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

        return pendudukRepository.save(entity);
    }

    @Override
    public void delete(Object id) {
        pendudukRepository.deleteById(id.toString());
    }

    @Override
    public Long dependentRiwayat(String nik) {
        return riwayatRepository.countRiwayatByPenduduk(nik);
    }

    @Override
    public Long countPendudukById(String nik){
        return pendudukRepository.countById(nik);
    }

    @Override
    public Object getPendudukHeader(String nik) {
        var entity = pendudukRepository.findById(nik);
        return entity.isPresent() ? entity.get() : null;
    }

    @Override
    public Page<Object> getRiwayatGridByPenduduk(String nik, Integer page, String jenisSurat) {
        var pagination = PageRequest.of(page - 1, rowsInPage);
        var rows = riwayatRepository.findAllRiwayatByPenduduk(nik, jenisSurat, pagination);
        return rows; // TODO fix bug in looping
    }
}
