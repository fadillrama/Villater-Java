package com.villatter.dto.pengajuan;

import com.villatter.utility.MapperHelper;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.nio.MappedByteBuffer;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PengajuanRowDTO {
    private String noSurat;
    private String jenisSurat;
    private String namaPengaju;
    private LocalDate tanggalDiajukan;
    private String keperluan;
    private String status;

    public PengajuanRowDTO(Object row){
        noSurat = MapperHelper.getStringField(row, 0);
        jenisSurat = MapperHelper.getStringField(row, 1);
        namaPengaju = MapperHelper.getStringField(row, 2);
        tanggalDiajukan = MapperHelper.getLocalDateField(row, 3);
        keperluan = MapperHelper.getStringField(row, 4);
        status = MapperHelper.getStringField(row, 5);
    }
}
