package com.villatter.dto.riwayat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RiwayatRowDTO {
    private String noSurat;
    private String jenisSurat;
    private String namaPengaju;
    private LocalDate tanggalPengajuan;
    private String keperluan;
    private String status;
}
