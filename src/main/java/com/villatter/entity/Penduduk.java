package com.villatter.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "Penduduk")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Penduduk {
    @Id
    @Column(name = "Nik")
    private  String nik;

    @Column(name = "NamaDepan")
    private String namaDepan;

    @Column(name = "NamaTengah")
    private String namaTengah;

    @Column(name = "NamaBelakang")
    private String namaBelakang;

    @Column(name = "TempatLahir")
    private String tempatLahir;

    @Column(name = "TanggalLahir")
    private LocalDate tanggalLahir;

    @Column(name = "JenisKelamin")
    private String jenisKelamin;

    @Column(name = "Agama")
    private String agama;

    @Column(name = "Jalan")
    private String jalan;

    @Column(name = "Dusun")
    private String dusun;

    @Column(name = "RT")
    private Integer rt;

    @Column(name = "RW")
    private Integer rw;

    @Column(name = "Desa")
    private String desa;

    @Column(name = "Kecamatan")
    private String kecamatan;

    @Column(name = "Kabupaten")
    private String kabupaten;

    @Column(name = "Kewarganegaraan")
    private String kewarganegaraan;

    @Column(name = "NoKk")
    private String noKK;

    @Column(name = "StatusKawin")
    private String statusKawin;
}
