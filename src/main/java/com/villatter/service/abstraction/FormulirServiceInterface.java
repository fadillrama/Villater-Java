package com.villatter.service.abstraction;

import com.villatter.dto.riwayat.*;

import java.time.LocalDate;

public interface FormulirServiceInterface {
    public void saveFormulirASatu(InsertRiwayatA1DTO dto);
    public void saveFormulirADua(InsertRiwayatA2DTO dto);
    public void saveFormulirATiga(InsertRiwayatA3DTO dto);
    public void saveFormulirAEmpat(InsertRiwayatA4DTO dto);
    public void saveFormulirALima(InsertRiwayatA5DTO dto);
    public String getNoSurat(LocalDate tanggalPengajuan, Long idSurat);
}
