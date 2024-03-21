package com.villatter.dto.riwayat;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InsertRiwayatA2DTO {
    private Long idSurat;
    private String nikPenduduk;

    @NotBlank(message = "Model tidak boleh kosong!")
    @Size(max = 20, message = "Tidak boleh lebih dari 20 karakter!")
    private String model;

    @NotBlank(message = "Merek tidak boleh kosong!")
    @Size(max = 20, message = "Tidak boleh lebih dari 20 karakter!")
    private String merek;

    @NotNull(message = "Roda tidak boleh kosong!")
    @Min(value = 2, message = "Harus minimal dua Roda!")
    @Max(value = 12, message = "Maksimal dua belas Roda!")
    private Integer roda;

    @NotBlank(message = "Warna tidak boleh kosong!")
    @Size(max = 20, message = "Tidak boleh lebih dari 20 karakter!")
    private String warna;

    @NotBlank(message = "No Polisi tidak boleh kosong!")
    @Size(max = 10, message = "Tidak boleh lebih dari 10 karakter!")
    private String noPolisi;

    @NotBlank(message = "Keperluan tidak boleh kosong!")
    @Size(max = 200, message = "Tidak boleh lebih dari 200 karakter!")
    private String keperluan;
}
