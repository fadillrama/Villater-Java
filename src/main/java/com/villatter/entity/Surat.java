package com.villatter.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "Surat")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Surat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    private Long id;

    @Column(name = "JenisSurat")
    private String jenisSurat;
}
