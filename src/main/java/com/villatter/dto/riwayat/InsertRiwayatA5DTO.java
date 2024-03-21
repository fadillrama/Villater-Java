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
public class InsertRiwayatA5DTO {
    private Long idSurat;
    private String nikPenduduk;

    @NotBlank(message = "Nik harus diisi!")
    @Size(max = 20, message = "Tidak boleh lebih dari 20 karakter !!!")
    private  String nikBride;

    @NotBlank(message = "Nama Depan harus diisi!")
    @Size(max = 50, message = "Tidak boleh lebih dari 50 karakter !!!")
    private String namaDepanBride;

    @NotBlank(message = "Nama Tengah harus diisi!")
    @Size(max = 50, message = "Tidak boleh lebih dari 50 karakter !!!")
    private String namaTengahBride;

    @NotBlank(message = "Nama Belakang harus diisi!")
    @Size(max = 50, message = "Tidak boleh lebih dari 50 karakter !!!")
    private String namaBelakangBride;

    @NotBlank(message = "Tempat Lahir harus diisi!")
    @Size(max = 50, message = "Tidak boleh lebih dari 50 karakter !!!")
    private String tempatLahirBride;

    @NotNull(message = "Tanggal Lahir harus diisi!")
    private LocalDate tanggalLahirBride;

    @NotBlank(message = "Jenis Kelamin harus diisi!")
    private String jenisKelaminBride;

    @NotBlank(message = "Agama harus diisi!")
    @Size(max = 10, message = "Tidak boleh lebih dari 10 karakter !!!")
    private String agamaBride;

    @NotBlank(message = "Jalan harus diisi!")
    @Size(max = 20, message = "Tidak boleh lebih dari 20 karakter !!!")
    private String jalan;

    @NotBlank(message = "Dusun harus diisi!")
    @Size(max = 20, message = "Tidak boleh lebih dari 20 karakter !!!")
    private String dusun;

    @NotNull(message = "RT harus diisi!")
    @Min(value = 1, message = "Tidak boleh memasukan angka nol!")
    private Integer rt;

    @NotNull(message = "RW harus diisi!")
    @Min(value = 1, message = "Tidak boleh memasukan angka nol!")
    private Integer rw;

    @NotBlank(message = "Desa harus diisi!")
    @Size(max = 20, message = "Tidak boleh lebih dari 20 karakter !!!")
    private String desa;

    @NotBlank(message = "Kecamatan harus diisi!")
    @Size(max = 20, message = "Tidak boleh lebih dari 20 karakter !!!")
    private String kecamatan;

    @NotBlank(message = "Kabupaten harus diisi!")
    @Size(max = 20, message = "Tidak boleh lebih dari 20 karakter !!!")
    private String kabupaten;

    @NotBlank(message = "Kewarganegaraan harus diisi!")
    @Size(max = 20, message = "Tidak boleh lebih dari 20 karakter !!!")
    private String kewarganegaraan;

    @NotBlank(message = "No KK harus diisi!")
    @Size(max = 20, message = "Tidak boleh lebih dari 20 karakter !!!")
    private String noKK;

    @NotBlank(message = "Status Kawin harus diisi!")
    @Size(max = 15, message = "Tidak boleh lebih dari 15 karakter !!!")
    private String statusKawin;

    @NotNull(message = "Anak Ke harus diisi!")
    @Min(value = 1, message = "Tidak boleh memasukan angka nol!")
    private Integer anakKe;

    @NotBlank(message = "Keperluan tidak boleh kosong!")
    @Size(max = 200, message = "Tidak boleh lebih dari 200 karakter!")
    private String keperluan;
}
