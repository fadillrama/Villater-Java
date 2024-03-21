package com.villatter.dto.pdf;

import com.villatter.entity.Penduduk;
import com.villatter.entity.Riwayat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HardPdfDTO {
    private Penduduk penduduk;
    private Riwayat riwayat;
}
