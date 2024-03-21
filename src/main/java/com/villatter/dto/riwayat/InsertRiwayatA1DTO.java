package com.villatter.dto.riwayat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InsertRiwayatA1DTO {

    private Long idSurat;
    private String nikPenduduk;

    @NotBlank(message = "Keperluan tidak boleh kosong!")
    @Size(max = 200, message = "Tidak boleh lebih dari 200 karakter!")
    private String keperluan;
}
