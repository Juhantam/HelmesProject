package com.helmes.helmesbackend.adapter.database.personworksector;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PersonWorkSectorsInfoEntityRepository extends JpaRepository<PersonWorkSectorsInfoEntity, Long> {
    Optional<PersonWorkSectorsInfoEntity> findByPersonId(Long personId);
}
