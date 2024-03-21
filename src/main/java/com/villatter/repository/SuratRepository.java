package com.villatter.repository;

import com.villatter.entity.Surat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SuratRepository extends JpaRepository<Surat, Long> {
}
