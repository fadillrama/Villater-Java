package com.villatter.dto.penduduk;

import com.villatter.utility.MapperHelper;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Schema(description = "Data penduduk yang akan ditampilkan di halaman index grid.")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PendudukRowDTO {

    @Schema(description = "NIK penduduk PK.")
    private  String nik;

    @Schema(description = "Nama lengkap penduduk.")
    private String fullName;

    @Schema(description = "Tempat lahir penduduk.")
    private String tempatLahir;

    @Schema(description = "Tanggal lahir penduduk .")
    private LocalDate tanggalLahir;

    @Schema(description = "Jenis kelamin penduduk.")
    private String jenisKelamin;

    @Schema(description = "Alamat lengkap penduduk.")
    private String alamat;

    public PendudukRowDTO(Object row){
        nik = MapperHelper.getStringField(row, 0);
        fullName = MapperHelper.getStringField(row, 1);
        tempatLahir = MapperHelper.getStringField(row, 2);
        tanggalLahir = MapperHelper.getLocalDateField(row, 3);
        jenisKelamin = MapperHelper.getStringField(row, 4);
        alamat = MapperHelper.getStringField(row, 5);
    }
}
