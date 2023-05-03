package com.example.sanction_service.repository;

import com.example.sanction_service.model.SanctionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SanctionRepository extends JpaRepository<SanctionEntity, Long> {
    Optional<SanctionEntity> findByNameContainingIgnoreCase(String name);

    SanctionEntity save(SanctionEntity sanctionEntity);
//    List<SanctionEntity> saveAll(List<SanctionEntity> sanctions);
}
