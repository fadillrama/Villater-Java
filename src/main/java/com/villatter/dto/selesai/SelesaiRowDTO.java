package com.villatter.dto.selesai;

import com.villatter.utility.MapperHelper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SelesaiRowDTO {
    private String noSurat;
    private String jenisSurat;
    private String namaPengaju;
    private LocalDate tanggalDiajukan;
    private LocalDate tanggalDisetujui;
    private String keperluan;
    private String status;

    public SelesaiRowDTO(Object row){
        noSurat = MapperHelper.getStringField(row, 0);
        jenisSurat = MapperHelper.getStringField(row, 1);
        namaPengaju = MapperHelper.getStringField(row, 2);
        tanggalDiajukan = MapperHelper.getLocalDateField(row, 3);
        tanggalDisetujui = MapperHelper.getLocalDateField(row, 4);
        keperluan = MapperHelper.getStringField(row, 5);
        status = MapperHelper.getStringField(row, 6);
    }
}
