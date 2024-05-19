package com.helmes.helmesbackend.adapter.database.personworksector;

import com.helmes.helmesbackend.appdomain.personworksector.SavePersonWorkSectors;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
class CreatePersonWorkSectorsRepositoryAdapter implements SavePersonWorkSectors {
    private final PersonWorkSectorEntityRepository personWorkSectorEntityRepository;

    @Override
    @Transactional
    public void execute(Request request) {
        personWorkSectorEntityRepository.saveAll(request.getPersonWorkSectors()
                                                        .stream()
                                                        .map(personWorkSector -> PersonWorkSectorEntity.builder()
                                                                                                       .personId(personWorkSector.getPersonId()
                                                                                                                                 .getValue())
                                                                                                       .workSectorId(personWorkSector.getWorkSectorId()
                                                                                                                                     .getValue())
                                                                                                       .build())
                                                        .collect(Collectors.toSet()));
    }
}
