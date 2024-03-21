package com.villatter.dto.penduduk;

import com.villatter.utility.MapperHelper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PendudukDetailGridDTO {
    private String noSurat;
    private String jenisSurat;
    private LocalDate tanggalDiajukan;
    private LocalDate tanggalDisetujui;
    private String keperluan;
    private String status;

    public PendudukDetailGridDTO(Object row) {
        noSurat = MapperHelper.getStringField(row, 0);
        jenisSurat = MapperHelper.getStringField(row, 1);
        tanggalDiajukan = MapperHelper.getLocalDateField(row, 2);
        tanggalDisetujui = MapperHelper.getLocalDateField(row, 3);
        keperluan = MapperHelper.getStringField(row, 4);
        status = MapperHelper.getStringField(row, 5);
    }
}
