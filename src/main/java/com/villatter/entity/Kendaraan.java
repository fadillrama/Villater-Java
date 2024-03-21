package com.villatter.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Kendaraan")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Kendaraan {
    @Id
    @Column(name = "NoSuratFK")
    private String noSuratFK;

    @Column(name = "Model")
    private String model;

    @Column(name = "Merek")
    private String merek;

    @Column(name = "Roda")
    private Integer roda;

    @Column(name = "Warna")
    private String warna;

    @Column(name = "NoPolisi")
    private String noPolisi;
}
