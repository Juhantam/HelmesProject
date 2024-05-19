package com.helmes.helmesbackend.adapter.database.worksector;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WorkSectorEntityRepository extends JpaRepository<WorkSectorEntity, Long> {
    List<WorkSectorEntity> findAll();

    Optional<WorkSectorEntity> findById(Long id);
}
