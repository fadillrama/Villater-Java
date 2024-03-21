package com.villatter.service.abstraction;

import com.villatter.dto.riwayat.RiwayatRowDTO;
import org.springframework.data.domain.Page;

public interface SelesaiServiceInterface {
    public Page<RiwayatRowDTO> getRowSelesai(String nama, String jenisSurat, Integer page);
    public byte[] getPdfByte(String noSurat) throws Exception;
}
