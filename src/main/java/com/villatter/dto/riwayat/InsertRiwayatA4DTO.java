package com.villatter.dto.riwayat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InsertRiwayatA4DTO {
    private Long idSurat;
    private String nikPenduduk;

    @NotBlank(message = "Nama Depan tidak boleh kosong!")
    @Size(max = 50, message = "Tidak boleh lebih dari 50 karakter!")
    private String namaDepanBaby;

    @NotBlank(message = "Nama Tengah tidak boleh kosong!")
    @Size(max = 50, message = "Tidak boleh lebih dari 50 karakter!")
    private String namaTengahBaby;

    @NotBlank(message = "Nama Belakang tidak boleh kosong!")
    @Size(max = 50, message = "Tidak boleh lebih dari 50 karakter!")
    private String namaBelakangBaby;

    @NotBlank(message = "Tempat Lahir tidak boleh kosong!")
    @Size(max = 50, message = "Tidak boleh lebih dari 50 karakter!")
    private String tempatLahirBaby;

    @NotNull(message = "Tanggal Mulai harus diisi!")
    private LocalDate tanggalLahirBaby;

    @NotBlank(message = "Jenis Kelamin harus dipilih!")
    private String jenisKelaminBaby;

    @NotBlank(message = "Agama tidak boleh kosong!")
    @Size(max = 10, message = "Tidak boleh lebih dari 10 karakter!")
    private String agamaBaby;

    @NotBlank(message = "Keperluan tidak boleh kosong!")
    @Size(max = 200, message = "Tidak boleh lebih dari 200 karakter!")
    private String keperluan;
}
