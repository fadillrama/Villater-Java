package com.villatter.repository;

import com.villatter.dto.riwayat.RiwayatRowDTO;
import com.villatter.entity.Riwayat;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RiwayatRepository extends JpaRepository<Riwayat, String> {

    @Query("""
            SELECT COUNT(riw.noSurat)
            FROM Riwayat AS riw
            WHERE riw.noSurat LIKE %:segment%
            """)
    public Long countRiwayatByPeriod(@Param("segment") String segment);

    @Query("""
            SELECT COUNT(riw.noSurat)
            FROM Riwayat AS riw
            WHERE riw.nikPenduduk = :nik
            """)
    public Long countRiwayatByPenduduk(@Param("nik") String nik);

    @Query("""
            SELECT new com.villatter.dto.riwayat.RiwayatRowDTO(
                riw.noSurat, 
                sur.jenisSurat, 
                CONCAT(pen.namaDepan, ' ', pen.namaTengah, ' ', pen.namaBelakang),
                riw.tanggalDiajukan,
                riw.keperluan,
                'PENDING'
                )
            FROM Riwayat AS riw
            JOIN riw.surat AS sur
            JOIN riw.penduduk AS pen
            WHERE riw.tanggalDisetujui IS NULL
                AND CONCAT(pen.namaDepan, ' ', pen.namaTengah, ' ', pen.namaBelakang) LIKE %:nama%
                AND sur.jenisSurat LIKE %:jenisSurat%
            """)
    public Page<RiwayatRowDTO> getPengajuanSurat(@Param("nama") String nama,
                                                 @Param("jenisSurat") String jenisSurat, Pageable pageable);

    @Query("""
            SELECT new com.villatter.dto.riwayat.RiwayatRowDTO(
                riw.noSurat, 
                sur.jenisSurat, 
                CONCAT(pen.namaDepan, ' ', pen.namaTengah, ' ', pen.namaBelakang),
                riw.tanggalDiajukan,
                riw.keperluan,
                'SELESAI'
                )
            FROM Riwayat AS riw
            JOIN riw.surat AS sur
            JOIN riw.penduduk AS pen
            WHERE riw.tanggalDisetujui IS NOT NULL
                AND CONCAT(pen.namaDepan, ' ', pen.namaTengah, ' ', pen.namaBelakang) LIKE %:nama%
                AND sur.jenisSurat LIKE %:jenisSurat%
            """)
    public Page<RiwayatRowDTO> getSuratSelesai(@Param("nama") String nama,
                                               @Param("jenisSurat") String jenisSurat, Pageable pageable);

    @Query("""
            SELECT
                riw.noSurat, 
                sur.jenisSurat, 
                CONCAT(pen.namaDepan, ' ', pen.namaTengah, ' ', pen.namaBelakang),
                riw.tanggalDiajukan,
                riw.keperluan,
                'PENDING'
            FROM Riwayat AS riw
            JOIN riw.surat AS sur
            JOIN riw.penduduk AS pen
            WHERE riw.tanggalDisetujui IS NULL
                AND CONCAT(pen.namaDepan, ' ', pen.namaTengah, ' ', pen.namaBelakang) LIKE %:nama%
                AND sur.jenisSurat LIKE %:jenisSurat%
            """)
    public Page<Object> findAllPengajuan(@Param("nama") String nama,
                                         @Param("jenisSurat") String jenisSurat, Pageable pageable);

    @Query("""
            SELECT 
                riw.noSurat, 
                sur.jenisSurat, 
                CONCAT(pen.namaDepan, ' ', pen.namaTengah, ' ', pen.namaBelakang),
                riw.tanggalDiajukan,
                riw.tanggalDisetujui,
                riw.keperluan,
                'SELESAI'
            FROM Riwayat AS riw
            JOIN riw.surat AS sur
            JOIN riw.penduduk AS pen
            WHERE riw.tanggalDisetujui IS NOT NULL
                AND CONCAT(pen.namaDepan, ' ', pen.namaTengah, ' ', pen.namaBelakang) LIKE %:nama%
                AND sur.jenisSurat LIKE %:jenisSurat%
            """)
    public Page<Object> findAllSelesai(@Param("nama") String nama,
                                       @Param("jenisSurat") String jenisSurat, Pageable pageable);

    @Query("""
            SELECT
                riw.noSurat,
                sur.jenisSurat,
                riw.tanggalDiajukan,
                riw.tanggalDisetujui,
                riw.keperluan,
                CASE
                    WHEN riw.tanggalDisetujui IS NOT NULL THEN 'SELESAI'
                    ELSE 'PENDING'
                END
            FROM Riwayat AS riw
            JOIN riw.surat AS sur
            WHERE riw.nikPenduduk = :nik
                AND sur.jenisSurat LIKE %:jenisSurat%
            """)
    public Page<Object> findAllRiwayatByPenduduk(@Param("nik") String nik,
                                                 @Param("jenisSurat") String jenisSurat, Pageable pageable);

    @Query("""
            SELECT COUNT(riw.noSurat)
            FROM Riwayat AS riw
            WHERE riw.tanggalDisetujui IS NOT NULL
            """)
    public Long countTotalSelesai();

    @Query("""
            SELECT COUNT(riw.noSurat)
            FROM Riwayat AS riw
            WHERE riw.tanggalDisetujui IS NULL
            """)
    public Long countTotalPengajuan();
}
