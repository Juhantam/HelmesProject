package com.helmes.helmesbackend.adapter.database.personworksector;

import com.helmes.helmesbackend.appdomain.personworksector.DeletePersonWorkSectors;
import com.helmes.helmesbackend.appdomain.personworksector.PersonWorkSector;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
class DeletePersonWorkSectorsRepositoryAdapter implements DeletePersonWorkSectors {
    private final PersonWorkSectorEntityRepository personWorkSectorEntityRepository;

    @Override
    @Transactional
    public void execute(Request request) {
        personWorkSectorEntityRepository.deleteAllById(request.getIds()
                                                              .stream()
                                                              .map(PersonWorkSector.Id::getValue)
                                                              .collect(Collectors.toSet()));
    }
}
