package com.villatter.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "Riwayat")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Riwayat {

    @Id
    @Column(name = "NoSurat")
    private String noSurat;

    @Column(name = "IdSurat")
    private Long idSurat;

    @ManyToOne
    @JoinColumn(name = "IdSurat", insertable = false, updatable = false)
    private Surat surat;

    @Column(name = "NikPenduduk")
    private String nikPenduduk;

    @ManyToOne
    @JoinColumn(name = "NikPenduduk", insertable = false, updatable = false)
    private Penduduk penduduk;

    @Column(name = "TanggalDiajukan")
    private LocalDate tanggalDiajukan;

    @Column(name = "TanggalDisetujui")
    private LocalDate tanggalDisetujui;

    @Column(name = "Keperluan")
    private String keperluan;
}
