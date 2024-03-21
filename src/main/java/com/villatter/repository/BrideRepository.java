package com.villatter.repository;

import com.villatter.entity.Bride;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrideRepository extends JpaRepository<Bride, String> {
}
