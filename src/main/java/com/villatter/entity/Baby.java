package com.villatter.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "Baby")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Baby {
    @Id
    @Column(name = "NoSuratFK")
    private String noSuratFK;

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
}
