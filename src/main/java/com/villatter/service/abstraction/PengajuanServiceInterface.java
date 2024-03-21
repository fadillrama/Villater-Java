package com.villatter.service.abstraction;

import com.villatter.dto.riwayat.RiwayatRowDTO;
import org.springframework.data.domain.Page;

public interface PengajuanServiceInterface {
    public Page<RiwayatRowDTO> getRowPengajuan(String nama, String jenisSurat, Integer page);
    public void acceptSurat(String noSurat);
}
