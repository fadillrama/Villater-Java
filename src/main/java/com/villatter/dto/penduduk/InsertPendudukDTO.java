package com.villatter.dto.penduduk;

import com.villatter.validation.UniqueNikNumber;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Schema(description = "Data order dalam form yang akan digunakan untuk insert atau update.")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InsertPendudukDTO {

    @Schema(description = "NIK penduduk PK.")
    @UniqueNikNumber(message = "NIK sudah ada !")
    @NotBlank(message = "Nik harus diisi!")
    @Size(max = 20, message = "Tidak boleh lebih dari 20 karakter !!!")
    private  String nik;

    @Schema(description = "Nama depan penduduk.")
    @NotBlank(message = "Nama Depan harus diisi!")
    @Size(max = 50, message = "Tidak boleh lebih dari 50 karakter !!!")
    private String namaDepan;

    @Schema(description = "Nama tengah penduduk.")
    @NotBlank(message = "Nama Tengah harus diisi!")
    @Size(max = 50, message = "Tidak boleh lebih dari 50 karakter !!!")
    private String namaTengah;

    @Schema(description = "Nama belakang penduduk.")
    @NotBlank(message = "Nama Belakang harus diisi!")
    @Size(max = 50, message = "Tidak boleh lebih dari 50 karakter !!!")
    private String namaBelakang;

    @Schema(description = "Tempat lahir penduduk.")
    @NotBlank(message = "Tempat Lahir harus diisi!")
    @Size(max = 50, message = "Tidak boleh lebih dari 50 karakter !!!")
    private String tempatLahir;

    @Schema(description = "Tanggal lahir penduduk.")
    @NotNull(message = "Tanggal Lahir harus diisi!")
    private LocalDate tanggalLahir;

    @Schema(description = "Jenis kelamin penduduk.")
    @NotBlank(message = "Jenis Kelamin harus diisi!")
    private String jenisKelamin;

    @Schema(description = "Agama penduduk.")
    @NotBlank(message = "Agama harus diisi!")
    @Size(max = 10, message = "Tidak boleh lebih dari 10 karakter !!!")
    private String agama;

    @Schema(description = "Alamat jalan penduduk.")
    @NotBlank(message = "Jalan harus diisi!")
    @Size(max = 20, message = "Tidak boleh lebih dari 20 karakter !!!")
    private String jalan;

    @Schema(description = "Alamat dusun penduduk.")
    @NotBlank(message = "Dusun harus diisi!")
    @Size(max = 20, message = "Tidak boleh lebih dari 20 karakter !!!")
    private String dusun;

    @Schema(description = "Alamat RT penduduk.")
    @NotNull(message = "RT harus diisi!")
    @Min(value = 1, message = "Tidak boleh memasukan angka nol!")
    private Integer rt;

    @Schema(description = "Alamat RW penduduk.")
    @NotNull(message = "RW harus diisi!")
    @Min(value = 1, message = "Tidak boleh memasukan angka nol!")
    private Integer rw;

    @Schema(description = "Alamat desa penduduk.")
    @NotBlank(message = "Desa harus diisi!")
    @Size(max = 20, message = "Tidak boleh lebih dari 20 karakter !!!")
    private String desa;

    @Schema(description = "Alamat kecamatan penduduk.")
    @NotBlank(message = "Kecamatan harus diisi!")
    @Size(max = 20, message = "Tidak boleh lebih dari 20 karakter !!!")
    private String kecamatan;

    @Schema(description = "Alamat kabupaten penduduk.")
    @NotBlank(message = "Kabupaten harus diisi!")
    @Size(max = 20, message = "Tidak boleh lebih dari 20 karakter !!!")
    private String kabupaten;

    @Schema(description = "Kewarganegaraan penduduk.")
    @NotBlank(message = "Kewarganegaraan harus diisi!")
    @Size(max = 20, message = "Tidak boleh lebih dari 20 karakter !!!")
    private String kewarganegaraan;

    @Schema(description = "No KK penduduk.")
    @NotBlank(message = "No KK harus diisi!")
    @Size(max = 20, message = "Tidak boleh lebih dari 20 karakter !!!")
    private String noKK;

    @Schema(description = "Status kawin penduduk.")
    @NotBlank(message = "Status Kawin harus diisi!")
    @Size(max = 15, message = "Tidak boleh lebih dari 15 karakter !!!")
    private String statusKawin;
}
