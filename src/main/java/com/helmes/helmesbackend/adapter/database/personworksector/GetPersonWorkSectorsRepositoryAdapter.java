package com.helmes.helmesbackend.adapter.database.personworksector;

import com.helmes.helmesbackend.appdomain.personworksector.GetPersonWorkSectors;
import com.helmes.helmesbackend.appdomain.personworksector.PersonWorkSector;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
class GetPersonWorkSectorsRepositoryAdapter implements GetPersonWorkSectors {
    private final PersonWorkSectorEntityRepository personWorkSectorEntityRepository;

    @Override
    @Transactional
    public Set<PersonWorkSector> execute(Request request) {
        return personWorkSectorEntityRepository.findAllByPersonId(request.getPersonId())
                                               .stream()
                                               .map(PersonWorkSectorEntity::toDomain)
                                               .collect(Collectors.toSet());
    }
}
