package com.helmes.helmesbackend.adapter.database.worksector;

import com.helmes.helmesbackend.appdomain.worksector.FindWorkSectors;
import com.helmes.helmesbackend.appdomain.worksector.WorkSector;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Slf4j
@Component
@RequiredArgsConstructor
class FindWorkSectorsRepositoryAdapter implements FindWorkSectors {
    private final WorkSectorEntityRepository workSectorEntityRepository;

    @Override
    @Transactional
    public Set<WorkSector> execute() {
        return WorkSector.toDomain(workSectorEntityRepository.findAll());
    }
}
