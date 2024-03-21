package com.villatter.dto.penduduk;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PendudukDTO {
    private  String nik;
    private String namaDepan;
    private String namaTengah;
    private String namaBelakang;
    private String tempatLahir;
    private LocalDate tanggalLahir;
    private String jenisKelamin;
    private String agama;
    private String jalan;
    private String dusun;
    private Integer rt;
    private Integer rw;
    private String desa;
    private String kecamatan;
    private String kabupaten;
    private String kewarganegaraan;
    private String noKK;
    private String statusKawin;
}
