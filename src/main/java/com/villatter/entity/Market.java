package com.villatter.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "Market")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Market {
    @Id
    @Column(name = "NoSuratFK")
    private String noSuratFK;

    @Column(name = "JenisUsaha")
    private String jenisUsaha;

    @Column(name = "TanggalMulai")
    private LocalDate tanggalMulai;
}
