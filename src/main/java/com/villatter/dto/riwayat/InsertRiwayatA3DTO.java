package com.villatter.dto.riwayat;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InsertRiwayatA3DTO {
    private Long idSurat;
    private String nikPenduduk;

    @NotBlank(message = "Jenis Usaha tidak boleh kosong!")
    @Size(max = 20, message = "Tidak boleh lebih dari 20 karakter!")
    private String jenisUsaha;

    @NotNull(message = "Tanggal Mulai harus diisi!")
    private LocalDate tanggalMulai;

    @NotBlank(message = "Keperluan tidak boleh kosong!")
    @Size(max = 200, message = "Tidak boleh lebih dari 200 karakter!")
    private String keperluan;
}
