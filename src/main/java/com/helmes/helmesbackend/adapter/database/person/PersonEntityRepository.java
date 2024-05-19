package com.helmes.helmesbackend.adapter.database.person;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface PersonEntityRepository extends JpaRepository<PersonEntity, Long> {
    Optional<PersonEntity> findById(Long id);

    Set<PersonEntity> findByName(String name);
}
