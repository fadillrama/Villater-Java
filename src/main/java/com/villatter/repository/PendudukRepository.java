package com.villatter.repository;

import com.villatter.dto.penduduk.PendudukRowDTO;
import com.villatter.entity.Penduduk;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PendudukRepository extends JpaRepository<Penduduk, String> {
    @Query("""
            SELECT COUNT(pen.nik)
            FROM Penduduk AS pen
            WHERE pen.nik = :nik
            """)
    public Long countById(@Param("nik") String nik);

    @Query("""
            SELECT new com.villatter.dto.penduduk.PendudukRowDTO(
                pen.nik, 
                CONCAT(pen.namaDepan, ' ', pen.namaTengah, ' ', pen.namaBelakang), 
                pen.tempatLahir,
                pen.tanggalLahir, 
                CASE
                    WHEN pen.jenisKelamin = 'L' THEN 'Laki-Laki'
                    ELSE 'Perempuan'
                END, 
                CONCAT(pen.jalan, ', ', pen.dusun, ', ', pen.rt, '/', pen.rw, ' ', pen.desa, ', ', pen.kecamatan)
            )
            FROM Penduduk AS pen
            WHERE CONCAT(pen.namaDepan, ' ', pen.namaTengah, ' ', pen.namaBelakang) LIKE %:nama%
            """)
    public Page<PendudukRowDTO> getRow(@Param("nama") String nama, Pageable pageable);

    @Query("""
			SELECT 
                pen.nik, 
                CONCAT(pen.namaDepan, ' ', pen.namaTengah, ' ', pen.namaBelakang), 
                pen.tempatLahir,
                pen.tanggalLahir, 
                CASE
                    WHEN pen.jenisKelamin = 'L' THEN 'Laki-Laki'
                    ELSE 'Perempuan'
                END, 
                CONCAT(pen.jalan, ', ', pen.dusun, ', ', pen.rt, '/', pen.rw, ' ', pen.desa, ', ', pen.kecamatan)
            FROM Penduduk AS pen
            WHERE CONCAT(pen.namaDepan, ' ', pen.namaTengah, ' ', pen.namaBelakang) LIKE %:nama%
			""")
    public Page<Object> findAll(@Param("nama") String nama, Pageable pageable);

    @Query("""
            SELECT COUNT(pen.nik)
            FROM Penduduk AS pen
            """)
    public Long countAllPenduduk();
}
