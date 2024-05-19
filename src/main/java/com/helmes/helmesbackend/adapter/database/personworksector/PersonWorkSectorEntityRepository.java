package com.helmes.helmesbackend.adapter.database.personworksector;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PersonWorkSectorEntityRepository extends JpaRepository<PersonWorkSectorEntity, Long> {
    Optional<PersonWorkSectorEntity> findById(Long id);

    List<PersonWorkSectorEntity> findAllByPersonId(Long personId);
}
