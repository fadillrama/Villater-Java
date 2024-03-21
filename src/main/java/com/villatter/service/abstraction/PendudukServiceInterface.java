package com.villatter.service.abstraction;

import org.springframework.data.domain.Page;

public interface PendudukServiceInterface {
    public Long dependentRiwayat(String nik);
    public Long countPendudukById(String nik);
    public Object getPendudukHeader(String nik);
    public Page<Object> getRiwayatGridByPenduduk(String nik, Integer page, String jenisSurat);
}
